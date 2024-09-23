import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { TodoFormComponent } from './todo-form/todo-form.component';

export const routes: Routes = [
    {
        path: '',
        component: AppComponent,
        title: 'TodoListApp | Home',
    },
    {
        path: 'tasks/:id',
        component: TodoFormComponent,
        title: 'TodoListApp | Update task'
    },
];
