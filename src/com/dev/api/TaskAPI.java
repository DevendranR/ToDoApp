package com.dev.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.dev.bean.Message;
import com.dev.bean.Task;
import com.dev.resources.Factory;
import com.dev.service.TaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("TaskAPI")
public class TaskAPI {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTask(String dataRecieved)
	{
		Response returnValue = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Task task = gson.fromJson(dataRecieved,Task.class);
		System.out.println(dataRecieved);
		try {
			TaskService teamService = Factory.createTaskService();
			Message message = new Message();
			message.setMessage(teamService.createTask(task));
			String value = gson.toJson(message);
			returnValue = Response.ok(value).build();
		} catch (Exception e) {
			String value = gson.toJson(e.getMessage());
			if(e.getMessage().contains("DAO")){
			returnValue=Response.status(Status.SERVICE_UNAVAILABLE).entity(value).build();
			}
			else{
				returnValue=Response.status(Status.BAD_REQUEST).entity(value).build();
			}
			
		}
		
		return returnValue;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchTasks()
	{
		Response returnValue = null;
		Gson gson = new GsonBuilder().create();
		try {
			TaskService taskService=Factory.createTaskService();
			
			List<Task> taskList = taskService.fetchTasks();

			String value = gson.toJson(taskList);
			returnValue = Response.ok(value).build();
		} catch (Exception e) {
			
			String value = gson.toJson(e.getMessage());
			if(e.getMessage().contains("DAO")){
				returnValue=Response.status(Status.SERVICE_UNAVAILABLE).entity(value).build();
			}else{
				returnValue=Response.status(Status.BAD_REQUEST).entity(value).build();
			
			}
		}
		
		return returnValue;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response completeTask(String dataRecieved)
	{
		
		Response returnValue = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		Task task = gson.fromJson(dataRecieved,Task.class);
		
		try {
			TaskService taskService = Factory.createTaskService();
			Message message = new Message();
			message.setMessage(taskService.completeTask(task.getTaskId()));
			String value = gson.toJson(message);
			returnValue = Response.ok(value).build();
		} catch (Exception e) {
			String value = gson.toJson(e.getMessage());
			if(e.getMessage().contains("DAO")){
			returnValue=Response.status(Status.SERVICE_UNAVAILABLE).entity(value).build();
			}
			else{
				returnValue=Response.status(Status.BAD_REQUEST).entity(value).build();
			}
			
		}
		
		return returnValue;
	}
	
	
	
	@DELETE
	@Path("/{taskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTask(@PathParam("taskId") int taskId)
	{
		Response returnValue = null;
		Gson gson = new GsonBuilder().create();
		
		try {
			TaskService taskService = Factory.createTaskService();
			Message message = new Message();
			message.setMessage(taskService.deleteTask(taskId));
			String value = gson.toJson(message);
			returnValue = Response.ok(value).build();
		} catch (Exception e) {
			String value = gson.toJson(e.getMessage());
			if(e.getMessage().contains("DAO")){
				returnValue=Response.status(Status.SERVICE_UNAVAILABLE).entity(value).build();
			}else{
				returnValue=Response.status(Status.BAD_REQUEST).entity(value).build();
			
			}
		}
		
		return returnValue;
	}
}
