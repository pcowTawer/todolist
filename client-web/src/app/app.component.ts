import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoListComponent } from './todo-list/todo-list.component';
import Parse from 'parse';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TodoListComponent],
  template: `
    <app-todo-list></app-todo-list>
  `,
  styleUrls: [`./app.component.css`],
})
export class AppComponent {
  title = 'TodoListApp';
}

Parse.initialize('APPLICATION_ID')
Parse.serverURL = "http://localhost:1337/parse"
