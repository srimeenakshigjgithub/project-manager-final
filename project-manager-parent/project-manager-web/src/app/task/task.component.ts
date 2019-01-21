import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { DatePipe } from '@angular/common';

import { ProjectManagerService } from '../shared/pm.service';
declare var jQuery:any;

@Component({
  selector: ' project-manager-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
    taskList: any = [];
    projectName: string = '';
    projectId: any = 0;
    inputParam: any = {};
    order: number = 1;  
    fieldName: string = '';
    projectList: any = [];
    selectedProject: string;
    techError: boolean = false;
    screenLoader: boolean;

    constructor(public projectManagerService: ProjectManagerService, public router: Router, private datepipe: DatePipe) {
    }

    ngOnInit() {
        this.screenLoader = true;
        this.projectId = 0;
        this.projectName = '';
        this.taskList = [];
        this.inputParam = {};
        this.getProjectDetails();
    }

    getTask() {
      this.screenLoader = true;
      this.inputParam = {
          "task" : {
              "projectId" : this.projectId
          }
      };
      this.projectManagerService.getTask(this.inputParam).subscribe(
        (data: any) => {
            if(null != data && undefined != data) {
                this.taskList = data.task;
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

    getProjectDetails() {
        this.projectManagerService.getProject().subscribe(
          (data: any) => {
              if(null != data && undefined != data) {
                  this.projectList = data.project;
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

    

    setProject(proj: any) {
        this.projectName = proj.project;
        this.projectId = proj.projectId;
        jQuery('#searchProjectModalWindow').modal("hide");
        this.getTask();
    }

    editTask(task: any) {
        this.projectManagerService.task = task;
        this.router.navigate(['/edittask']);
    }

    endTask(task: any) {
        this.screenLoader = true;
        this.inputParam = {
            "task" : {
                "taskId" : task.taskId,
                "task" : task.task,
                "parentId" : task.parentId,
                "projectId" : task.projectId,
                "priority" : task.priority,
                "startDate" : this.datepipe.transform(task.startDate, 'yyyy-MM-dd'),
                "endDate" : this.datepipe.transform(new Date(), 'yyyy-MM-dd'),
                "status" : "COMPLETED"
            }
        };
        this.projectManagerService.modifyTask(this.inputParam).subscribe(
            (data: any) => {
                if(null != data && undefined != data && null !== data.status && undefined !== data.status && 'Success' === data.status) {
                    this.router.navigate(['/viewtask']);
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
        this.getTask();
    }

    getProject() {
        jQuery("#searchProjectPopupOpener").click();
    }

    sortTaskBy(prop : any) {
        this.order = this.order * (-1);
        let order_val = this.order == 1 ? 'asc' : 'desc';
        this.fieldName = prop + "-" + order_val;
        return false;
    }
}