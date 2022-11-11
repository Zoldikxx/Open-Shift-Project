import { TestBed } from '@angular/core/testing';
import { StationaerviceService } from './stationaervice.service';

describe('StationaerviceService', () => {
  let service: StationaerviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StationaerviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
