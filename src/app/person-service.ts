import { Injectable } from "@angular/core";
import { Person } from './person.model';
import { DataService } from "./data-service";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  people: Person[] = [];

  constructor(private dataService: DataService) { }

  setPeople(people: Person[]) {
    this.people = people
  }

  getPeople() {
    return this.dataService.loadPeople();
  }

  addPerson(person: Person) {
    console.log('Person to add: ', person.name);
    this.dataService.addPerson(person)
    .subscribe({
      next: (person: Person) => {
        // Recover Person object with his ID
        console.log('Person added to array: ' + person.idPerson);
        this.people.push(person)
      }
    })
  }

  findPerson(id: number) {
    const person: Person | undefined = this.people.find(person => person.idPerson == id);
    if (person)
      console.log('Person found: ' + person.idPerson + ' ' + person.name);
    else
      console.log('Person not found');

    return person;
  }

  updatePerson(id: number, person: Person) {
    console.log('Person to update: ' + person.idPerson);
    this.dataService.updatePerson(id, person);
  }

  deletePerson(id: number) {
    console.log('Delete person with ID: ' + id);
    const index = this.people.findIndex(person => person.idPerson == id);
    this.people.splice(index, 1);
    this.dataService.deletePerson(id);
  }
}
