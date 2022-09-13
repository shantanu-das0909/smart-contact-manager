import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { Contact } from './contact.model';
import { Signup } from '../signup.model';


interface UserResponse {
  userID: number,
  username: string,
  name: string,
  userEmail: string,
  userPhone: string
}

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  userResponse: UserResponse | null = null;

  constructor(private http: HttpClient) { }

  getUserByUserName(username: string) {
    return this.http.get<UserResponse>(
      environment.USER_MICROSERVICE_URL + '/getUserByUsername/' + username
    ).pipe(
      tap((response: UserResponse) => {
        this.userResponse = response;
        console.log(this.userResponse);
      })
    );
  }

  getContactList(userId: number) {
    return this.http.get<Contact[]>(
      environment.CONTACT_MICROSERVICE_URL + '/getContacts/' + userId
    );
  }

  updateContact(contactId: number, contact: Contact) {
    return this.http.put<Contact>(
      environment.CONTACT_MICROSERVICE_URL + '/updateContact', contact
    );
  }

  getContactById(contactId: number) {
    return this.http.get<Contact>(
      environment.CONTACT_MICROSERVICE_URL + '/getContact/' + contactId
    );
  }

  addContact(contact: {
    contactName:string | undefined,
    contactEmail: string | undefined,
    contactPhone: string | undefined,
    userId: number | undefined
  })
  {
    return this.http.post<Contact>(
      environment.CONTACT_MICROSERVICE_URL + '/addContact', contact
    );
  }

  deleteContact(contactId: number) {
    return this.http.delete(
      environment.CONTACT_MICROSERVICE_URL + '/deleteContact/' + contactId
    );
  }

  signup(signup: Signup) {
    return this.http.post(
      environment.AUTH_SERVICE_URL + '/signup', signup
    );
  }

  handleError(errorResponse: HttpErrorResponse) {
    return throwError(errorResponse.error.message);
  }
}
