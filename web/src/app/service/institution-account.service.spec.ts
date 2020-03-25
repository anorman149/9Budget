import { TestBed } from '@angular/core/testing';

import { InstitutionAccountService } from './institution-account.service';

describe('InstitutionAccountService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InstitutionAccountService = TestBed.get(InstitutionAccountService);
    expect(service).toBeTruthy();
  });
});
