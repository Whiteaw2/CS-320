package services;

import java.io.*;
import java.util.*;

public class TaskService {
	

	
	
	//create hashmap to hold tasks in memory
	private Map<String, Task> taskMap = new HashMap<String, Task>();
	
	public static void main(String[] args)
	{
		//create instance of class
		TaskService taskService = new TaskService();
		
	
		
	}

	//create new task
	public void addTask(String UID, String name, String description) 
	{
		//check existence and duplicates
		if (!taskMap.containsKey(UID)){
			
			//if it doesn't exist, create it and put it in the list
			Task Task = new Task(UID, name, description);
			taskMap.put(Task.getField("UID"), Task);
			
			return;
		} else {
			throw new IllegalArgumentException("UID already exists. Please choose another.");
		}
	}
	
	
	//remove task from list of tasks
	public void deleteTask(String UID) {
		
		//validate that UID exists
		validateUID(UID);
		
		//remove it from list
		taskMap.remove(UID);
		return;
	}
	
	//getter
	public String getField(String UID, String field) {
		
		//does UID exist
		validateUID(UID);
		
		//get task, then field value
		Task task = taskMap.get(UID);
		return task.getField(field);
	}
	
	//setter
	public String setField(String UID, String field, String newVal) {
		
		//does UID exist
		validateUID(UID);
		
		//get task, then set field
		Task task = taskMap.get(UID);
		task.setField(field, newVal);
		
		return task.getField(field);
	}
	
	
	//check list for UID
	public void validateUID(String UID) {
		if (taskMap.containsKey(UID)){
			return;
		} else {
			throw new IllegalArgumentException("UID does not exist");
		}
	}
}