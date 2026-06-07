import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';

export interface AppNotification {
  id: string;
  message: string;
  type: string;
  read: boolean;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/notifications';
  private notificationsSubject = new BehaviorSubject<AppNotification[]>([]);
  private eventSource: EventSource | null = null;

  notifications$ = this.notificationsSubject.asObservable();
  unreadCount$ = this.notifications$.pipe(
    map(notifications => notifications.filter(notification => !notification.read).length)
  );

  loadAll(): void {
    this.http.get<AppNotification[]>(this.apiUrl).subscribe({
      next: notifications => this.notificationsSubject.next(notifications),
      error: error => console.error(error)
    });
  }

  connect(token: string): void {
    if (this.eventSource || !token) {
      return;
    }

    this.eventSource = new EventSource(`${this.apiUrl}/stream?token=${encodeURIComponent(token)}`);

    this.eventSource.addEventListener('notification', (event) => {
      const notification = JSON.parse((event as MessageEvent).data) as AppNotification;
      this.notificationsSubject.next([notification, ...this.notificationsSubject.value]);
    });

    this.eventSource.onerror = () => {
      this.disconnect();
      setTimeout(() => this.connect(token), 5000);
    };
  }

  disconnect(): void {
    this.eventSource?.close();
    this.eventSource = null;
  }

  markRead(id: string): void {
    this.http.patch(`${this.apiUrl}/${id}/read`, {}).subscribe({
      next: () => {
        this.notificationsSubject.next(
          this.notificationsSubject.value.map(notification =>
            notification.id === id ? { ...notification, read: true } : notification
          )
        );
      },
      error: error => console.error(error)
    });
  }
}
