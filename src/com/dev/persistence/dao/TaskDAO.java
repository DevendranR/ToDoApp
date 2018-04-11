package com.dev.persistence.dao;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dev.bean.Task;
import com.dev.persistence.entity.TaskEntity;
import com.dev.resources.HibernateUtility;

public class TaskDAO {

	public List<Task> fetchTasks() throws Exception{
		SessionFactory sessionFactory=null;
		Session session=null;
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			Query query2=session.createQuery("Select t from TaskEntity t where t.status != ?");
			query2.setParameter(0, "Disabled");
			List<TaskEntity> list = query2.list();
			if(list.size()>0){
				List<Task> taskList = new ArrayList<>();
				for (TaskEntity taskEntity : list) {
					Task task = new Task();
					task.setTaskId(taskEntity.getTaskId());
					task.setTaskName(taskEntity.getTaskName());
					task.setStatus(taskEntity.getStatus());
					taskList.add(task);
				}
				return taskList;
			}
		} catch (Exception exception) {
			throw exception;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}
	public String deleteTask(int taskId) throws Exception{
		SessionFactory sessionFactory=null;
		Session session=null;
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			Query query2=session.createQuery("Select t from TaskEntity t where t.taskId=?");
			query2.setParameter(0, taskId);
			
			List<TaskEntity> list = query2.list();
			if(list.size()>0){
				TaskEntity taskEntity = list.get(0);
				taskEntity.setStatus("Disabled");
				session.beginTransaction();
				Integer id = (Integer) session.save(taskEntity);
				session.getTransaction().commit();
			}
			return "Task Deleted";
		} catch (Exception exception) {
			throw exception;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	public String completeTask(int taskId) throws Exception{
		SessionFactory sessionFactory=null;
		Session session=null;
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();

			Query query2=session.createQuery("Select t from TaskEntity t where t.taskId=?");
			query2.setParameter(0, taskId);
			
			List<TaskEntity> list = query2.list();
			if(list.size()>0){
				TaskEntity taskEntity = list.get(0);
				taskEntity.setStatus("Completed");
				session.beginTransaction();
				Integer id = (Integer) session.save(taskEntity);
				session.getTransaction().commit();
			}
			return "Task Completed";
			
		} catch (Exception exception) {
			throw exception;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
	}
	public String createTask(Task task) throws Exception{
		SessionFactory sessionFactory=null;
		Session session=null;
		try{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();

			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setTaskName(task.getTaskName());
			taskEntity.setStatus("Pending");
			session.beginTransaction();
			Integer id = (Integer) session.save(taskEntity);
			session.getTransaction().commit();
			
			return "Task Created with Id"+id;
		} catch (Exception exception) {
			throw exception;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			
		}
	}
	


}
