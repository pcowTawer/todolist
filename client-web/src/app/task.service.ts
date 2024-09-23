import { Injectable } from '@angular/core';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private tasks: Task[] = [
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

  getTasks(): Task[] {
    return this.tasks;
  }

  getTask(id: string) {
    return this.tasks.find((task) => task.id === id)
  }
  constructor() { }
}
