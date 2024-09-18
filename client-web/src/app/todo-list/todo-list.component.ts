import { Component } from '@angular/core';
import { TodoItemComponent } from "../todo-item/todo-item.component";
import { Task } from '../task';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [TodoItemComponent, ReactiveFormsModule],
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.css'
})
export class TodoListComponent {
  taskForm = new FormGroup({
    title: new FormControl(''),
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
  
  tasks: Task[] = [
    {
      'id': '1',
      'title': 'My first task',
      'description': 'My first task description',
      'completed': true
    },
    {
      'id': '2',
      'title': 'My second task',
      'description': 'My second task description',
      'completed': true
    },
    {
      'id': '3',
      'title': 'My third task',
      'description': 'My third task description',
      'completed': true
    },
  ]
}
