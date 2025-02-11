import { Component, OnInit } from '@angular/core';
import { Person } from '../person.model';
import { PersonService } from '../person-service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-people',
  imports: [ CommonModule, RouterModule ],
  templateUrl: './people.component.html',
  styles: ``
})
export class PeopleComponent implements OnInit{

  people: Person[] = []

  constructor(private personService: PersonService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.personService.getPeople()
    .subscribe({
      next: (peopleObtained: Person[]) => {
        // Load people data
        this.people = peopleObtained;
        this.personService.setPeople(this.people);
        console.log('People obtained from subscriber: ' + this.people);
      }
    });
  }

  goAdd() {
    console.log('Lets go to add!');
    this.router.navigate(['./people/add'])
  }
}
