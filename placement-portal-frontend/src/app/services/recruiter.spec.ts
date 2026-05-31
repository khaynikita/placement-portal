import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

import { RecruiterService } from './recruiter';

describe('RecruiterService', () => {
  let service: RecruiterService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(RecruiterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
