import { Injectable } from '@angular/core';
import { Task } from './task';
import Parse from 'parse';

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

  addTask(): void {
    try {
      const task = new Parse.Object("Tasks");
      task.set("title", "TaskTitle");
      task.set("description", "TaskDesctiption");
      task.set("completed", true);
      task.save().then(() => {
        console.log("Task added succesfully")
      })
    } catch (error) {
      console.log(error)
    }
    
  }
  constructor() { }
}
