import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardcandidateComponent } from './dashboardcandidate.component';

describe('DashboardcandidateComponent', () => {
  let component: DashboardcandidateComponent;
  let fixture: ComponentFixture<DashboardcandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardcandidateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardcandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
