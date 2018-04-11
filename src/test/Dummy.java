package test;

import java.util.List;

import com.dev.bean.Task;
import com.dev.service.TaskService;

public class Dummy {
	public static void main(String[] args) {
		TaskService t = new TaskService();
		Task task = new Task();
		task.setTaskName("FinishTest");
		try {
			t.createTask(task);
			List<Task> x = t.fetchTasks();
			for (Task task2 : x) {
				System.out.println(task2.getTaskId());
				System.out.println(task2.getTaskName());
				System.out.println(task2.getStatus());

			}
			t.completeTask(1001);
			List<Task> x2 = t.fetchTasks();
			for (Task task2 : x2) {
				System.out.println(task2.getTaskId());
				System.out.println(task2.getTaskName());
				System.out.println(task2.getStatus());

			}
			t.deleteTask(1001);
			List<Task> x3 = t.fetchTasks();
			for (Task task2 : x3) {
				System.out.println(task2.getTaskId());
				System.out.println(task2.getTaskName());
				System.out.println(task2.getStatus());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
