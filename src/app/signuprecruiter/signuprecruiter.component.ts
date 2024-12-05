import { Component } from '@angular/core';
import{Location} from '@angular/common';

@Component({
  selector: 'app-signuprecruiter',
  templateUrl: './signuprecruiter.component.html',
  styleUrl: './signuprecruiter.component.css'
})

export class SignuprecruiterComponent {
  constructor(private location: Location) {}
  goBack(): void {
    this.location.back(); // Retourne à la page précédente
  }
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  companyName: string = '';
  password: string = '';
  confirmPassword: string = '';
  address: string = '';
  companyUrl: string = '';

  simulateSignup(): void {
    // Validation des mots de passe
    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match!');
      return;
    }
    // Création d'un objet avec les données du formulaire
    const formData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      companyName: this.companyName,
      password: this.password,
      address: this.address,
      companyUrl: this.companyUrl,
    };

 // Enregistrement des données dans LocalStorage pour la simulation
    localStorage.setItem('signupData', JSON.stringify(formData));

    // Affichage de la réussite et des données dans la console
    alert('User data saved successfully. ');
    console.log('Form Data:', formData);
  }
}
