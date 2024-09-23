import { Routes } from '@angular/router';
import { TodoFormComponent } from './todo-form/todo-form.component';
import { TodoListComponent } from './todo-list/todo-list.component';

export const routes: Routes = [
    {
        path: '',
        component: TodoListComponent,
        title: 'TodoListApp | Home',
    },
    {
        path: 'tasks/:id',
        component: TodoFormComponent,
        title: 'TodoListApp | Update task'
    },
];
