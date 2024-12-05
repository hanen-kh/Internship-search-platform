import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfferdetailsComponent } from './offerdetails.component';

describe('OfferdetailsComponent', () => {
  let component: OfferdetailsComponent;
  let fixture: ComponentFixture<OfferdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OfferdetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OfferdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
