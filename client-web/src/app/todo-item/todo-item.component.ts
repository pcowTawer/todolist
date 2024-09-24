import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { Task } from '../task';
import { RouterLink } from '@angular/router';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-todo-item',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './todo-item.component.html',
  styleUrl: './todo-item.component.css',
})
export class TodoItemComponent {
  @Input() task!: Task;
  @Output() deleteItemEvent = new EventEmitter<string>();
  taskService = inject(TaskService);

  async handleCheckboxChange() {
    await this.taskService.updateTask(this.task.id, this.task = {...this.task, completed : !this.task.completed});
  }

  handleDelete() {
    this.deleteItemEvent.emit(this.task.id);
  }
}
