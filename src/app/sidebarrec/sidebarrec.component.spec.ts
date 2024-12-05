import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarrecComponent } from './sidebarrec.component';

describe('SidebarrecComponent', () => {
  let component: SidebarrecComponent;
  let fixture: ComponentFixture<SidebarrecComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SidebarrecComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SidebarrecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
