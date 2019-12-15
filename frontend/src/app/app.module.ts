import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router'
import { AuthService } from './services/auth.service';
import { FourOhFourComponent } from './principal/four-oh-four/four-oh-four.component';
import { AuthGuard } from './services/auth-guard.service';
import { ListeIndividusComponent } from './liste-individus/liste-individus.component';
import { IndividuVueComponent } from './liste-individus/individu-vue/individu-vue.component';
import { IndividuFormComponent } from './liste-individus/individu-form/individu-form.component';
import { HeaderComponent } from './header/header.component';
import { SigninComponent } from './auth/signin/signin.component';
import { AccueilComponent } from './principal/accueil/accueil.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTableModule} from '@angular/material/table';
import { NgSelectModule } from '@ng-select/ng-select';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatInputModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSlideToggleModule,
  MatPaginatorModule
 } from '@angular/material';
import { ListeDepartementComponent } from './liste-departement/liste-departement.component';
import { DepartementVueComponent } from './liste-departement/departement-vue/departement-vue/departement-vue.component';
import { DepartementFormComponent } from './liste-departement/departement-form/departement-form/departement-form.component';
import { FeuilleDeTempsDepComponent } from './feuille-de-temps/departement/feuille-de-temps-dep/feuille-de-temps-dep.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import {MatSortModule} from '@angular/material/sort';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from '@busacca/ng-pick-datetime';  
import { Title } from "@angular/platform-browser";

// the second parameter 'fr' is optional
registerLocaleData(localeFr, 'fr');


const appRoutes: Routes = [
  { path: 'individus', canActivate: [AuthGuard], component: ListeIndividusComponent, },
  { path: 'departements', canActivate: [AuthGuard], component: ListeDepartementComponent},
  { path: 'new/departement', canActivate: [AuthGuard], component: DepartementFormComponent },
  { path: 'edit/departement/:id', canActivate: [AuthGuard], component: DepartementFormComponent },
  { path: 'new/individu', canActivate: [AuthGuard], component: IndividuFormComponent },
  { path: 'edit/individu/:id', canActivate: [AuthGuard], component: IndividuFormComponent },
  { path: 'auth/signin',  component: SigninComponent },
  { path: 'feuilleDeTemps/departement/:id', canActivate: [AuthGuard],  component: FeuilleDeTempsDepComponent },
  { path: '', canActivate: [AuthGuard], component: ListeIndividusComponent },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  declarations: [
    AppComponent,
    FourOhFourComponent,
    ListeIndividusComponent,
    IndividuVueComponent,
    IndividuFormComponent,
    HeaderComponent,
    SigninComponent,
    AccueilComponent,
    ListeDepartementComponent,
    DepartementVueComponent,
    DepartementFormComponent,
    FeuilleDeTempsDepComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatFormFieldModule,
    MatInputModule,
    NgSelectModule,
    MatSlideToggleModule,
    MatPaginatorModule,
    MatSelectModule,
    MatTableModule,
    MatSortModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    MatCheckboxModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    Title,
    {provide: localeFr, useValue: 'fr' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
