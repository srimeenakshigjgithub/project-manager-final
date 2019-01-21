import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RouterModule , Router } from '@angular/router';
import { Config } from './env.config';

@Injectable()
export class ProjectManagerService {
  
  task: any = {};
  parentTask: any = {};
  private apiUrl = 'http://localhost:8686';
  
  constructor(private http: Http, private router : Router, private httpClient: HttpClient) {}

    getUser() {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept':'*/*'
      });
      return this.httpClient.get(this.apiUrl+'/getUser', {headers: headers});
    }

    getProject() {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.get(this.apiUrl+'/getProject', {headers: headers});
    }

    getParentTask() {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.get(this.apiUrl+'/getParentTask', {headers: headers});
    }

    getTask(inputParam : {}) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.post(this.apiUrl+'/viewTask', inputParam, {headers: headers});
    }

    modifyUser(inputParam : {}) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.post(this.apiUrl+'/updateUser', inputParam, {headers: headers});
    }

    modifyProject(inputParam : {}) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.post(this.apiUrl+'/updateProject', inputParam, {headers: headers});
    }

    modifyParentTask(inputParam : {}) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.post(this.apiUrl+'/updateParentTask', inputParam, {headers: headers});
    }

    modifyTask(inputParam : {}) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      return this.httpClient.post(this.apiUrl+'/updateTask', inputParam, {headers: headers});
    }
}