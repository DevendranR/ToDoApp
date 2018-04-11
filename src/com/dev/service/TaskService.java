package com.dev.service;

import java.util.List;

import com.dev.bean.Task;
import com.dev.resources.Factory;

public class TaskService {
	public String createTask(Task task) throws Exception{
		
			return Factory.createTaskDAO().createTask(task);
				
	}
	public String completeTask(int taskId) throws Exception{
		
			return Factory.createTaskDAO().completeTask(taskId);	
	}
	public String deleteTask(int taskId) throws Exception{
		
		return Factory.createTaskDAO().deleteTask(taskId);	
	}
	public List<Task> fetchTasks() throws Exception {
		
		return Factory.createTaskDAO().fetchTasks();	
	
	}
}
