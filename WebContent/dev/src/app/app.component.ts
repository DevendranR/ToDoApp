import { Component , OnInit} from '@angular/core';
import { Task } from './task'
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  tasks:Task[];
  message:string;
  constructor( private http: HttpClient ) { }
  model = new Task('','','');
  
  URI = "http://localhost:6453/ToDoApp/api/";
  
  ngOnInit(){
		this.fetchTasks();
  }
  createTask(){
	  this.model.taskId='1001';
	  this.http.post(this.URI+"TaskAPI/",this.model)
             .subscribe(data=>{
				console.log(this.model);
				this.message = "Task Created"
				this.fetchTasks();
			});
  }
    completeTask(task:Task){
	  this.http.put(this.URI+"TaskAPI/",task)
             .subscribe(data=>{
				this.message = "Task Completed"
				this.fetchTasks();
			});
  }
	deleteTask(task:Task){
		this.http.delete(this.URI+"TaskAPI/"+JSON.parse(task.taskId))
             .subscribe(data=>{
				this.message = "Task deleted"
				this.fetchTasks();
			});
	}
	fetchTasks(){
		this.http.get<any>(this.URI+"TaskAPI/")
             .subscribe(data=>{
				console.log(data);
				this.tasks = data;
		});
	}
}
