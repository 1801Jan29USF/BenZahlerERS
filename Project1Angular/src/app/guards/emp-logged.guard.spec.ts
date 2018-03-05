import { TestBed, async, inject } from '@angular/core/testing';

import { EmpLoggedGuard } from './emp-logged.guard';

describe('EmpLoggedGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmpLoggedGuard]
    });
  });

  it('should ...', inject([EmpLoggedGuard], (guard: EmpLoggedGuard) => {
    expect(guard).toBeTruthy();
  }));
});
