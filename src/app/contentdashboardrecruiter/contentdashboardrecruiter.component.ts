import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-contentdashboardrecruiter',
  templateUrl: './contentdashboardrecruiter.component.html',
  styleUrl: './contentdashboardrecruiter.component.css'
})
export class ContentdashboardrecruiterComponent implements OnInit {
  openPositions = 8; // Exemple : à récupérer depuis l'API
  totalApplications = 124;
  interviewsScheduled = 15;
  hiredCandidates = 6;

 

 
  tasks: string[] = []; // Liste des tâches
  task: string = ''; // Nouvelle tâche

  // Ajouter une tâche
  addTask() {
    if (this.task.trim()) {
      this.tasks.push(this.task.trim());
      this.task = '';
    }
  }

  // Supprimer une tâche
  removeTask(index: number) {
    this.tasks.splice(index, 1);
  }
 
 
  currentYear = new Date().getFullYear();
  currentMonth = new Date().getMonth();
  daysOfWeek = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
  monthNames = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];
  dates: { date: number; events: string[] }[] = [];

  

  generateCalendar() {
    const firstDay = new Date(this.currentYear, this.currentMonth, 1).getDay();
    const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();

    this.dates = [];
    for (let i = 0; i < firstDay; i++) {
      this.dates.push({ date: 0, events: [] }); // Empty slots for alignment
    }
    for (let i = 1; i <= daysInMonth; i++) {
      this.dates.push({ date: i, events: [] });
    }
  }

  previousMonth() {
    if (this.currentMonth === 0) {
      this.currentMonth = 11;
      this.currentYear--;
    } else {
      this.currentMonth--;
    }
    this.generateCalendar();
  }

  nextMonth() {
    if (this.currentMonth === 11) {
      this.currentMonth = 0;
      this.currentYear++;
    } else {
      this.currentMonth++;
    }
    this.generateCalendar();
  }

  addEvent(date: { date: number; events: string[] }) {
    if (date.date === 0) return; // Ignore empty slots
    const eventName = prompt('Enter event name:');
    if (eventName) {
      date.events.push(eventName);
    }
  }
  saveChanges() {
    
    alert('Dear Recruiter your changes saved successfully!');
    
  }
  addjob() {
    
    alert('The job announcement has been added successfully!');
    
  }

  // Sample candidates list
  candidates = [
    { name: 'Alice', email: 'alice@example.com', phone: '123-456-7890', speciality: 'Engineering',cvUrl: 'assets/resume/r1.pdf' },
    { name: 'Bob', email: 'bob@example.com', phone: '234-567-8901', speciality: 'Marketing' ,cvUrl: 'assets/resume/r2.pdf'},
    { name: 'Charlie', email: 'charlie@example.com', phone: '345-678-9012', speciality: 'HR' ,cvUrl: 'assets/resume/r1.pdf'},
    { name: 'Diana', email: 'diana@example.com', phone: '456-789-0123', speciality: 'Engineering',cvUrl: 'assets/resume/r1.pdf' },
   { name: 'Jack', email: 'jack@example.com', phone: '012-345-6789', speciality: 'Marketing' ,cvUrl: 'assets/resume/r1.pdf'},
  ];

  specialities = ['Engineering', 'Marketing', 'HR', 'Design'];
  selectedSpeciality = '';
  filteredCandidates = [...this.candidates];
  selectedCandidate: any = null;
  pdfSrc: any ;
  constructor(private sanitizer: DomSanitizer, private fb: FormBuilder) {
    this.generateCalendar();
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  filterCandidates() {
    if (this.selectedSpeciality) {
      this.filteredCandidates = this.candidates.filter(
        (candidate) => candidate.speciality === this.selectedSpeciality
      );
    } else {
      this.filteredCandidates = [...this.candidates];
    }
  }

  viewDetails(candidate: any) {
    if (!candidate.cvUrl) {
      alert('No CV available for this candidate.');
      return;
    }
    this.selectedCandidate = candidate;
    this.pdfSrc = this.sanitizer.bypassSecurityTrustResourceUrl(candidate.cvUrl);
  }
  

  closeDetails() {
    this.selectedCandidate = null;
  }

 
   // Méthode pour télécharger le CV
   downloadCV(candidate: any) {
    const link = document.createElement('a');
    link.href = candidate.cvUrl;
    link.download = `${candidate.nom}_CV.pdf`;
    link.click();
  }
  jobTitle: string = '';
  companyName: string = '';
  jobDescription: string = '';
  requiredSkills: string = '';
  salaryRange: string = '';
  applicationDeadline: string = '';
  location: string = '';
  paymentSource: string = '';
  
  isSalaryValid: boolean = true; // Variable pour vérifier la validité du salaire
  successMessage: string = '';

  // Méthode pour valider le champ salaryRange
  validateSalary() {
    this.isSalaryValid = !isNaN(+this.salaryRange) && this.salaryRange.trim() !== ''; // Vérifie si le salaire est un nombre
  }

  // Méthode appelée à chaque changement dans le champ salaryRange
  onSalaryChange() {
    this.validateSalary(); // Valide dès que l'utilisateur modifie la valeur
  }

  // Méthode pour soumettre le formulaire
  

  // Méthode pour réinitialiser le formulaire
  resetForm() {
    this.jobTitle = '';
    this.companyName = '';
    this.jobDescription = '';
    this.requiredSkills = '';
    this.salaryRange = '';
    this.applicationDeadline = '';
    this.location = '';
    this.paymentSource = '';
    this.successMessage = '';
  }
  submitForm() {
    alert("Form submitted successfully!");
  }
}