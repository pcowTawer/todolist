import { Component, inject } from '@angular/core';
import { TodoItemComponent } from "../todo-item/todo-item.component";
import { Task } from '../task';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [TodoItemComponent, ReactiveFormsModule],
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.css'
})
export class TodoListComponent {
  
  taskService = inject(TaskService);

  tasks: Task[] = [];

  constructor () {
    this.tasks = this.taskService.getTasks();
  }
  
  taskForm = new FormGroup({
    title: new FormControl('', Validators.required),
    description: new FormControl(''),
  });

  handleSubmit() {
    this.tasks.push({
      'id': (Math.random() + 1).toString(36).substring(7),
      'title': this.taskForm.value.title ?? '',
      'description': this.taskForm.value.description ?? '',
      'completed': false
    } as Task)
  }
}
