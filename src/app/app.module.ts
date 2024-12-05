import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgOptimizedImage} from "@angular/common";
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SigninrecruiterComponent } from './signinrecruiter/signinrecruiter.component';
import { SignincandidateComponent } from './signincandidate/signincandidate.component';
import { SignuprecruiterComponent } from './signuprecruiter/signuprecruiter.component';
import { SignupcandidateComponent } from './signupcandidate/signupcandidate.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { DashboardrecruiterComponent } from './dashboardrecruiter/dashboardrecruiter.component';
import { DashboardcandidateComponent } from './dashboardcandidate/dashboardcandidate.component';
import { ContentdashboardrecruiterComponent } from './contentdashboardrecruiter/contentdashboardrecruiter.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { SidebarrecComponent } from './sidebarrec/sidebarrec.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ReactiveFormsModule } from '@angular/forms';
import { OffersComponent } from './offers/offers.component';
import { CvComponent } from './cv/cv.component';
import { OfferdetailsComponent } from './offerdetails/offerdetails.component';
import { CompaniesComponent } from './companies/companies.component';  // <-- Importez ReactiveFormsModule ici

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    
    SigninrecruiterComponent,
    SignincandidateComponent,
    SignuprecruiterComponent,
    SignupcandidateComponent,
    ForgotpasswordComponent,
    DashboardrecruiterComponent,
    DashboardcandidateComponent,
    ContentdashboardrecruiterComponent,
    SidebarrecComponent,
    OffersComponent,
    CvComponent,
    OfferdetailsComponent,
    CompaniesComponent,
   
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgOptimizedImage,
        FormsModule,
        FullCalendarModule,
        PdfViewerModule,
        NgxExtendedPdfViewerModule,
        BrowserAnimationsModule,
        
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
