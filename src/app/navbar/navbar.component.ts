import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
constructor(private router:Router){


}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}


