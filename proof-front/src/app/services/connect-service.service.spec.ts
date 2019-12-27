import { TestBed } from '@angular/core/testing';

import { ConnectServiceService } from './connect-service.service';

describe('ConnectServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConnectServiceService = TestBed.get(ConnectServiceService);
    expect(service).toBeTruthy();
  });
});
