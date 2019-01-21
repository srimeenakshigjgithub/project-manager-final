import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TaskComponent } from './task.component';
import { AddTaskComponent } from './addtask/addtask.component';
import { EditTaskComponent } from './edittask/edittask.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'viewtask', component: TaskComponent  },
      { path: 'addtask', component: AddTaskComponent  },
      { path: 'edittask', component: EditTaskComponent }
    ])
  ],
  exports: [RouterModule]
})
export class TaskRoutingModule { }