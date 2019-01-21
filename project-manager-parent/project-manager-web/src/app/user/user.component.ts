import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { NgForm, FormControl } from '@angular/forms';

import { ProjectManagerService } from '../shared/pm.service';

@Component({
  selector: 'project-manager-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    userList: any = [];
    user: any = {};
    searchText: string;
    isAdd: boolean;
    inputParam: any;
    techError: boolean = false;
    screenLoader: boolean;
    order: number = 1;  
    fieldName: string = '';

    @ViewChild('addUserForm') addUserForm: NgForm;

    constructor(public projectManagerService: ProjectManagerService, public router: Router) {
    }

    ngOnInit() {
        this.screenLoader = true;
        this.isAdd = true;
        this.getUserDetails();        
    }

    getUserDetails() {
        this.projectManagerService.getUser().subscribe(
          (data: any) => {
              if(null != data && undefined != data) {
                  this.userList = data.user;
                  this.screenLoader = false;
              } else {
                  this.screenLoader = false;
                  this.techError = true;
              }
          },
          (err: any) => {
              this.screenLoader = false;
              this.techError = true;
          }    
        );
    }

    addUser(usr: any) {
        this.screenLoader = true;
        this.inputParam = {
            "action" : this.isAdd ? 'ADD' : 'EDIT',
            "user" : {
                "userId" : this.isAdd ? 0 : usr.userId,
                "firstName" : usr.firstName,
                "lastName" : usr.lastName,
                "employeeId" : usr.employeeId
            }
        };

        this.projectManagerService.modifyUser(this.inputParam).subscribe(
            (data: any) => {
                if(null != data && undefined != data && null !== data.status && undefined !== data.status && 'Success' === data.status) {
                    this.getUserDetails();
                    this.screenLoader = false;
                } else {
                    this.screenLoader = false;
                    this.techError = true;
                }
            },
            (err: any) => {
                this.screenLoader = false;
                this.techError = true;
            }    
        );
        this.user = {};
        this.reset();
        this.router.navigate(['/']);
    }

    editUser(usr : any) {
        this.user = usr;
        this.isAdd = false;
    }

    deleteUser(usr : any) {
        this.screenLoader = true;
        this.inputParam = {
            "action" : "DELETE",
            "user" : usr
        };

        this.projectManagerService.modifyUser(this.inputParam).subscribe(
            (data: any) => {
                if(null != data && undefined != data && null !== data.status && undefined !== data.status && 'Success' === data.status) {
                    this.getUserDetails();
                    this.screenLoader = false;
                } else {
                    this.screenLoader = false;
                    this.techError = true;
                }
            },
            (err: any) => {
                this.screenLoader = false;
                this.techError = true;
            }    
        );
        this.user = {};
        this.reset();
        this.router.navigate(['/']);
    }

    reset() {
        this.user = {};
        this.isAdd = true;
        this.searchText = '';
        this.inputParam = {};
        this.techError = false;
        this.addUserForm.controls['firstName'].markAsPristine();
        this.addUserForm.controls['firstName'].markAsUntouched();
        this.addUserForm.controls['lastName'].markAsPristine();
        this.addUserForm.controls['lastName'].markAsUntouched();
        this.addUserForm.controls['employeeId'].markAsPristine();
        this.addUserForm.controls['employeeId'].markAsUntouched();
    }

    sortUser(prop: string) {
        this.order = this.order * (-1);
        let order_val = this.order == 1 ? 'asc' : 'desc';
        this.fieldName = prop + "-" + order_val;
        return false;
    }
}