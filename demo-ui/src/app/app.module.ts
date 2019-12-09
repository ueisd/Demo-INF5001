import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppareilComponent } from './appareil/appareil.component';
import { AppareilService } from './services/appareil.service';
import { AppareilViewComponent } from './appareil/appareil-view/appareil-view.component';
import { Routes, RouterModule } from '@angular/router'
import { AuthService } from './services/auth.service';
import { SingleAppareilComponent } from './appareil/single-appareil/single-appareil.component';
import { FourOhFourComponent } from './principal/four-oh-four/four-oh-four.component';
import { AuthGuard } from './services/auth-guard.service';
import { UserListComponent } from './user-list/user-list.component';
import { UserService } from './services/User.service';
import { NewUserComponent } from './user-list/new-user/new-user.component';
import { ListeIndividusComponent } from './liste-individus/liste-individus.component';
import { IndividuVueComponent } from './liste-individus/individu-vue/individu-vue.component';
import { IndividuFormComponent } from './liste-individus/individu-form/individu-form.component';
import { HeaderComponent } from './header/header.component';
import { SigninComponent } from './auth/signin/signin.component';
import { AccueilComponent } from './principal/accueil/accueil.component';
import { FormtestComponent } from './liste-individus/formtest/formtest/formtest.component';
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

// the second parameter 'fr' is optional
registerLocaleData(localeFr, 'fr');


const appRoutes: Routes = [
  { path: 'appareils', canActivate: [AuthGuard], component: AppareilViewComponent },
  { path: 'appareils/:id', component: SingleAppareilComponent },
  { path: 'users', canActivate: [AuthGuard], component: UserListComponent },
  { path: 'individus', canActivate: [AuthGuard], component: ListeIndividusComponent },
  { path: 'departements', component: ListeDepartementComponent},
  { path: 'formtest', canActivate: [AuthGuard], component: FormtestComponent },
  { path: 'new/departement', canActivate: [AuthGuard], component: DepartementFormComponent },
  { path: 'edit/departement/:id', canActivate: [AuthGuard], component: DepartementFormComponent },
  { path: 'new/individu', canActivate: [AuthGuard], component: IndividuFormComponent },
  { path: 'edit/individu/:id', canActivate: [AuthGuard], component: IndividuFormComponent },
  { path: 'new-user', canActivate: [AuthGuard], component: NewUserComponent },
  { path: 'auth/signin',  component: SigninComponent },
  { path: 'feuilleDeTemps/departement/:id', canActivate: [AuthGuard],  component: FeuilleDeTempsDepComponent },
  { path: '', component: AppareilViewComponent },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  declarations: [
    AppComponent,
    AppareilComponent,
    AppareilViewComponent,
    SingleAppareilComponent,
    FourOhFourComponent,
    UserListComponent,
    NewUserComponent,
    ListeIndividusComponent,
    IndividuVueComponent,
    IndividuFormComponent,
    HeaderComponent,
    SigninComponent,
    AccueilComponent,
    FormtestComponent,
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
    MatSortModule
  ],
  providers: [
    AppareilService,
    AuthService,
    AuthGuard,
    UserService,
    {provide: localeFr, useValue: 'fr' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
