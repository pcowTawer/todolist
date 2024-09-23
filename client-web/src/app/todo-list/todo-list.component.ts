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

  async ngOnInit() {
    this.tasks = await this.taskService.getTasks();
  }
  
  taskForm = new FormGroup({
    title: new FormControl('', Validators.required),
    description: new FormControl(''),
  });

  async handleSubmit() {
    const newTask = await this.taskService.addTask(
      this.taskForm.value.title!, 
      this.taskForm.value.description ?? ''
    );
    this.tasks.push(newTask)
  }

  async handleDelete(id: string) {
    await this.taskService.deleteTask(id)
    const newTasks = this.tasks.filter((task) => task.id !== id);
    this.tasks = newTasks
  }
}
