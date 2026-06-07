import { AsyncPipe, CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';

import { AuthService } from '../../services/auth';
import { AppNotification, NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification-bell',
  imports: [CommonModule, AsyncPipe],
  templateUrl: './notification-bell.html',
  styleUrl: './notification-bell.css',
})
export class NotificationBell implements OnInit {

  private notificationService = inject(NotificationService);
  private authService = inject(AuthService);

  notifications$ = this.notificationService.notifications$;
  unreadCount$ = this.notificationService.unreadCount$;
  isOpen = false;

  ngOnInit(): void {
    const token = this.authService.getToken();

    if (token) {
      this.notificationService.loadAll();
      this.notificationService.connect(token);
    }
  }

  togglePanel(): void {
    this.isOpen = !this.isOpen;
  }

  handleClick(notification: AppNotification): void {
    if (!notification.read) {
      this.notificationService.markRead(notification.id);
    }
  }
}
