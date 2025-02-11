import { Component, OnInit } from '@angular/core';
import { PersonService } from '../person-service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Person } from '../person.model';

@Component({
  selector: 'app-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './form.component.html',
  styles: ``
})
export class FormComponent implements OnInit {

  idPerson: number = 0;
  nameInput: string = '';

  constructor(private personService: PersonService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idPerson = this.route.snapshot.params['idPerson'];
    console.log('Recover person ID: ' + this.idPerson);

    if (this.idPerson != null) {
      const person = this.personService.findPerson(this.idPerson);
      if(person != null ) this.nameInput = person.name;
    }
  }

  onSavePerson() {
    const personToSave = new Person(this.idPerson, this.nameInput);

    if (this.idPerson != null)
      this.personService.updatePerson(this.idPerson, personToSave)
    else
      this.personService.addPerson(personToSave);

    this.router.navigate(['people']);
  }

  onDeletePerson() {
    if (this.idPerson != null) {
      console.log('Person to remove: ' + this.idPerson);
      this.personService.deletePerson(this.idPerson)
    }
    this.router.navigate(['people'])
  }


}
