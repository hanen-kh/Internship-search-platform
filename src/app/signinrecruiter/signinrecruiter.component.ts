import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink} from '@angular/router';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-signinrecruiter',
  templateUrl: './signinrecruiter.component.html',
  styleUrl: './signinrecruiter.component.css'
})
export class SigninrecruiterComponent implements OnInit{
  constructor(private router:Router){
    
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  goDashboard(): void {
    // Navigation vers le tableau de bord
    this.router.navigate(['/dashboardRecruiter']);
  }
}
