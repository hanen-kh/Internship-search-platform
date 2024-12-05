import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignuprecruiterComponent } from './signuprecruiter.component';

describe('SignuprecruiterComponent', () => {
  let component: SignuprecruiterComponent;
  let fixture: ComponentFixture<SignuprecruiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignuprecruiterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignuprecruiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
