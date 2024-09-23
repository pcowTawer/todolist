import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-todo-item',
  standalone: true,
  imports: [],
  templateUrl: './todo-item.component.html',
  styleUrl: './todo-item.component.css'
})
export class TodoItemComponent {

  @Input() task!: Task
  @Output() deleteItemEvent = new EventEmitter<string>();
  handleDelete() {
    this.deleteItemEvent.emit(this.task.id);
  }
}
