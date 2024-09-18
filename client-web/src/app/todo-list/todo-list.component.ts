import { Component } from '@angular/core';
import { TodoItemComponent } from "../todo-item/todo-item.component";
import { Task } from '../task';
@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [TodoItemComponent],
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.css'
})
export class TodoListComponent {
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
