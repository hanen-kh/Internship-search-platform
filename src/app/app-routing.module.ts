import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninrecruiterComponent } from './signinrecruiter/signinrecruiter.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardcandidateComponent } from './dashboardcandidate/dashboardcandidate.component';
import { DashboardrecruiterComponent } from './dashboardrecruiter/dashboardrecruiter.component';
import { SignincandidateComponent } from './signincandidate/signincandidate.component';
import { SignuprecruiterComponent } from './signuprecruiter/signuprecruiter.component';
import { SignupcandidateComponent } from './signupcandidate/signupcandidate.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { OfferdetailsComponent } from './offerdetails/offerdetails.component';
import { OffersComponent } from './offers/offers.component';
import { CvComponent } from './cv/cv.component';
import { CompaniesComponent } from './companies/companies.component';

const routes: Routes = [{ path: 'loginRecruiter', component: SigninrecruiterComponent },
  { path: 'home', component: HomeComponent },
  {path: 'offers', component: OffersComponent},
  {path: 'companies', component: CompaniesComponent},
  {path: 'cv', component: CvComponent},
  {path: 'detail', component: OfferdetailsComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'navbar', component: NavbarComponent },
{path: 'dashboardRecruiter', component : DashboardrecruiterComponent},
{path: 'dashboardCandidate', component : DashboardcandidateComponent},
{path: 'loginCandidate', component : SignincandidateComponent},
{path: 'signupRecruiter', component : SignuprecruiterComponent},
{path: 'signupCandidate', component : SignupcandidateComponent},
{path: 'forgotPassword', component : ForgotpasswordComponent},];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
