import { Component } from '@angular/core';
import jsPDF from 'jspdf';
@Component({
  selector: 'app-cv',
  templateUrl: './cv.component.html',
  styleUrl: './cv.component.css'
})
export class CvComponent {
  
  cvData = {
    name: '',
    email: '',
    phone: '',
    summary: '',
    skills: '',
    photo: '',
  };

  // Handle Photo Upload
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.cvData.photo = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
// Méthode pour générer le PDF du CV
generatePDF() {
  const doc = new jsPDF();

  // Ajouter une photo si elle est présente
  if (this.cvData.photo) {
    doc.addImage(this.cvData.photo, 'JPEG', 10, 10, 40, 40);
  }

  // Ajouter le nom
  doc.setFontSize(18);
  doc.text('Name: ' + this.cvData.name, 60, 20);

  // Ajouter l'email
  doc.setFontSize(12);
  doc.text('Email: ' + this.cvData.email, 60, 30);

  // Ajouter le numéro de téléphone
  doc.text('Phone: ' + this.cvData.phone, 60, 40);

  // Ajouter le résumé
  doc.text('Summary: ' + this.cvData.summary, 60, 50);

  // Ajouter les compétences
  doc.text('Skills: ' + this.cvData.skills, 60, 60);

  // Générer et télécharger le PDF
  doc.save('cv.pdf');
}
}
