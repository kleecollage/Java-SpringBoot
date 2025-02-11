import { Routes } from '@angular/router';
import { PeopleComponent } from './people/people.component';
import { FormComponent } from './form/form.component';

export const routes: Routes = [
  {path: '', component: PeopleComponent},
  {path: 'people', component: PeopleComponent, children: [
    {path: 'add', component: FormComponent},
    {path: ':idPerson', component: FormComponent }
  ]},
];
