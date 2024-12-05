import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignincandidateComponent } from './signincandidate.component';

describe('SignincandidateComponent', () => {
  let component: SignincandidateComponent;
  let fixture: ComponentFixture<SignincandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignincandidateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignincandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
