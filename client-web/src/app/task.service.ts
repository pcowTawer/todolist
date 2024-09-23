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
      const taskObjects = await query.find();
      this.tasks = taskObjects.map((taskObject) => {
        return {
          id: taskObject.id,
          title: taskObject.get('title'),
          description: taskObject.get('description') ?? '',
          completed: taskObject.get('completed') ?? ''
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
        id: taskParseObject.id,
        title: taskParseObject.get('title'),
        description: taskParseObject.get('description'),
        completed: taskParseObject.get('completed'),
      } as Task
    })
    
  }
  constructor() { }
}
