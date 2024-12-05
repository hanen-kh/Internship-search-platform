import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigninrecruiterComponent } from './signinrecruiter.component';

describe('SigninrecruiterComponent', () => {
  let component: SigninrecruiterComponent;
  let fixture: ComponentFixture<SigninrecruiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SigninrecruiterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SigninrecruiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
