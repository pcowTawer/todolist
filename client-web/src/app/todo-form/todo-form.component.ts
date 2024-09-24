import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../task.service';
import { Task } from '../task';

@Component({
  selector: 'app-todo-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './todo-form.component.html',
  styleUrl: './todo-form.component.css'
})
export class TodoFormComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  router: Router = inject(Router)

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
    await this.taskService.updateTask(this.taskId, {
      title : this.taskForm.value.title ?? '',
      description : this.taskForm.value.description ?? ''
    } as Task)
    .then(() => {
      this.router.navigate(['/']);
    })
  }
}
