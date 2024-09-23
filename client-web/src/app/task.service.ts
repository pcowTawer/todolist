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

  addTask(title: string, description: string): void {
    try {
      const taskParseObject = new Parse.Object("Tasks");
      taskParseObject.set("title", title);
      taskParseObject.set("description", description);
      taskParseObject.set("completed", false);
      taskParseObject.save().then(() => {
        console.log("Task added succesfully")
      })
    } catch (error) {
      console.log(error)
    }
    
  }
  constructor() { }
}
