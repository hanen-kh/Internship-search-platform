import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardrecruiterComponent } from './dashboardrecruiter.component';

describe('DashboardrecruiterComponent', () => {
  let component: DashboardrecruiterComponent;
  let fixture: ComponentFixture<DashboardrecruiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardrecruiterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardrecruiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
