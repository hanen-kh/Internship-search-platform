import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupcandidateComponent } from './signupcandidate.component';

describe('SignupcandidateComponent', () => {
  let component: SignupcandidateComponent;
  let fixture: ComponentFixture<SignupcandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignupcandidateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignupcandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
