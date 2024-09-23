import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-todo-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './todo-form.component.html',
  styleUrl: './todo-form.component.css'
})
export class TodoFormComponent {

  route: ActivatedRoute = inject(ActivatedRoute);
  taskId = '';
  constructor() {
      this.taskId = this.route.snapshot.params['id'];
  }

  taskForm = new FormGroup({
    title: new FormControl('', Validators.required),
    description: new FormControl(''),
  });

  async handleSubmit(title : string, description : string) {
    
  }
}
