import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signincandidate',
  templateUrl: './signincandidate.component.html',
  styleUrl: './signincandidate.component.css'
})
export class SignincandidateComponent implements OnInit {
  constructor(private router:Router){


  }
    ngOnInit(): void {
      throw new Error('Method not implemented.');
    }
}
