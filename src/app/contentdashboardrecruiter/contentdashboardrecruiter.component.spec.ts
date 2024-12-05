import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentdashboardrecruiterComponent } from './contentdashboardrecruiter.component';

describe('ContentdashboardrecruiterComponent', () => {
  let component: ContentdashboardrecruiterComponent;
  let fixture: ComponentFixture<ContentdashboardrecruiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContentdashboardrecruiterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContentdashboardrecruiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
