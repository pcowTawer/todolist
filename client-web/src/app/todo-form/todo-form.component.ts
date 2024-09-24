import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-todo-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './todo-form.component.html',
  styleUrl: './todo-form.component.css'
})
export class TodoFormComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  taskService = inject(TaskService)

  taskId = ''

  taskForm = new FormGroup({
    title: new FormControl('', Validators.required),
    description: new FormControl(''),
  });

  constructor () {
    this.taskId = this.route.snapshot.params['id'];
    this.taskForm.setValue({
      title : this.taskService.getTask(this.taskId)?.title ?? '',
      description : this.taskService.getTask(this.taskId)?.description ?? ''
    })
  }
  async handleSubmit() {
    
  }
}
