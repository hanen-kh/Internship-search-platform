import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink} from '@angular/router';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signupcandidate',
  templateUrl: './signupcandidate.component.html',
  styleUrl: './signupcandidate.component.css'
})
export class SignupcandidateComponent implements OnInit {
  constructor(private router:Router){


  }
    ngOnInit(): void {
      throw new Error('Method not implemented.');
    }

}
