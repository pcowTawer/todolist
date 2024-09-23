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

  async addTask(title: string, description: string): Promise<Task> {
    const taskParseObject = new Parse.Object("Tasks");
    taskParseObject.set("title", title);
    taskParseObject.set("description", description);
    taskParseObject.set("completed", false);
    return taskParseObject.save().then((taskParseObject) => {
      return {
        id: taskParseObject.get('id'),
        title: taskParseObject.get('title'),
        description: taskParseObject.get('description'),
        completed: taskParseObject.get('completed'),
      } as Task
    })
    
  }
  constructor() { }
}
