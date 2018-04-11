package com.dev.resources;

import com.dev.bean.Task;
import com.dev.persistence.dao.TaskDAO;
import com.dev.service.TaskService;


public class Factory
{
	public static TaskDAO createTaskDAO(){
		return new TaskDAO();
	}
	public static TaskService createTaskService(){
		return new TaskService();
	}
	public static Task createSchedule(){
		return new Task();
	}
}