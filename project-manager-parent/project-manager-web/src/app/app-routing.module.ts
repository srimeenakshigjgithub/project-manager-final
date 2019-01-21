import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProjectComponent } from './project/project.component';
import { TaskComponent } from './task/task.component';
import { AddTaskComponent } from './task/addtask/addtask.component';
import { EditTaskComponent } from './task/edittask/edittask.component';
import { UserComponent } from './user/user.component';

@NgModule({
  imports: [
    RouterModule.forRoot([
      { path: '', redirectTo: '/adduser', pathMatch: 'full' },
      { path: 'addproject', component: ProjectComponent },
      { path: 'viewtask', component: TaskComponent  },
      { path: 'addtask', component: AddTaskComponent  },
      { path: 'edittask', component: EditTaskComponent },
      { path: 'adduser', component: UserComponent }
    ])
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }