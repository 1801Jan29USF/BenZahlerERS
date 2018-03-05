import { TestBed, async, inject } from '@angular/core/testing';

import { ManLoggedGuard } from './man-logged.guard';

describe('ManLoggedGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ManLoggedGuard]
    });
  });

  it('should ...', inject([ManLoggedGuard], (guard: ManLoggedGuard) => {
    expect(guard).toBeTruthy();
  }));
});
