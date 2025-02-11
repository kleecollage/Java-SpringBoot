import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse} from "@angular/common/http";
import { Person } from "./person.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private httpClient: HttpClient) { }

  baseUrl = "http://localhost:8080/gm-1.0-SNAPSHOT/webservice/people";

  loadPeople(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.baseUrl);
  }

  addPerson(person: Person): Observable<Person> {
    return this.httpClient.post<Person>(this.baseUrl, person);
  }

  updatePerson(idPerson: number, person: Person) {
    let url: string;
    url = this.baseUrl + '/' + idPerson;
    this.httpClient.put(url, person)
    .subscribe({
      next: (response) => {
        console.log("Result to update person: ", response)
      },
      error: (error) => console.log("Error on update person: ", error)
    })
  }

  deletePerson(idPerson: number) {
    let url: string;
    url = this.baseUrl + '/' + idPerson;
    this.httpClient.delete(url)
    .subscribe({
      next: (response) => {
        console.log("Result to delete person: ", response)
      },
      error: (error) => console.log("Error on deleting person: ", error)
    })
  }
}
