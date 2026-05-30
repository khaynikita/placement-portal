import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumeAnalzer } from './resume-analzer';

describe('ResumeAnalzer', () => {
  let component: ResumeAnalzer;
  let fixture: ComponentFixture<ResumeAnalzer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResumeAnalzer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResumeAnalzer);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
