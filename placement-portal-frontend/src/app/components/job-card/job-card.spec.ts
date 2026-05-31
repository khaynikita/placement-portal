import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { JobCard } from './job-card';

describe('JobCard', () => {
  let component: JobCard;
  let fixture: ComponentFixture<JobCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobCard],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobCard);
    component = fixture.componentInstance;
    component.job = {
      id: 'job-1',
      title: 'Software Engineer',
      companyName: 'Acme',
      location: 'Remote',
      salary: '100000',
      jobType: 'FULL_TIME',
      description: 'Build products',
      requirements: 'Angular and Spring',
      experienceRequired: '1 year',
      skills: ['Angular'],
      postedBy: 'recruiter-1',
      createdAt: '2026-05-31',
      status: 'OPEN'
    };
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
