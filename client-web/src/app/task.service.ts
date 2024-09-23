import { Injectable } from '@angular/core';
import Parse from 'parse';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private tasks: Task[] = [];

  async getTasks(): Promise<Task[]> {
    try {
      const query = new Parse.Query("Tasks");
      const response = await query.find();
      this.tasks = response.map((object) => {
        return {
          id: object.get('id'),
          title: object.get('title'),
          description: object.get('description') ?? '',
          completed: object.get('completed') ?? ''
        } as Task
      })
      return this.tasks ?? []
    } catch (error) {
      console.log(error);
      return []
    }
  }

  getTask(id: string) : Task | undefined {
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
