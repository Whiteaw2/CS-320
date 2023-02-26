package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.TaskService;



class TaskServiceTest {


	//TEST VALUE Variables
	String tenDigitString = "1234567890";
	String diffTenDigitString = "0987654321";
	String elevenDigitString = "12345678901";
	String twentyDigitString = "12345678911234567892";
	String diffTwentyDigitString = "29876543211987654321";
	String twentyOneDigitString = "123456789112345678921";
	String fiftyDigitString = "12345678911234567892123456789312345678941234567895";
	String diffFiftyDigitString = "59876543214987654321398765432129876543211987654321";
	String fiftyOneDigitString = "123456789112345678921234567893123456789412345678951";	
	
	
	@Test //All valid fields //FORMAT IS (UID, name, description)
	void testValidTaskEntry() {
		TaskService testTaskService = new TaskService();
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
		assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "name").equals(twentyDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "description").equals(fiftyDigitString));
	}
	
	//Task DELETE TESTING BELOW --------------------------------------------------------------------
	
	@Test //Valid task deletion //FORMAT IS (UID, name, description)
	void testTaskDelete() {
			//create task service
			TaskService testTaskService = new TaskService();
			
			//add task through service
			testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
			
			//check all values are right
			assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));
			assertTrue(testTaskService.getField(tenDigitString, "name").equals(twentyDigitString));
			assertTrue(testTaskService.getField(tenDigitString, "description").equals(fiftyDigitString));
		
			//Select/delete entry
			testTaskService.deleteTask(tenDigitString);
		
		//ensure UID does not exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTaskService.validateUID(tenDigitString);
	}); }
	
	//MULTIPLE TASK INPUT TESTING BELOW --------------------------------------------------------------------	
	
	@Test //add multiple Tasks //FORMAT IS (UID, name, description)
	void testValidTaskEntry_Multi() {
		//Add service
		TaskService testTaskService = new TaskService();
		//Add new task
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
		
		//check all values are right
		assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "name").equals(twentyDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "description").equals(fiftyDigitString));

		//add different task
		testTaskService.addTask(diffTenDigitString, diffTwentyDigitString, diffFiftyDigitString);
	
		//check values 
		assertTrue(testTaskService.getField(diffTenDigitString, "UID").equals(diffTenDigitString));
		assertTrue(testTaskService.getField(diffTenDigitString, "name").equals(diffTwentyDigitString));
		assertTrue(testTaskService.getField(diffTenDigitString, "description").equals(diffFiftyDigitString));
	}
	
	@Test //Try to create same task twice //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_Multi() {
		//new task service
		TaskService testTaskService = new TaskService();
		
		//Add new task
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
	
		//catch error when trying to create new with same UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTaskService.addTask(tenDigitString, diffTwentyDigitString, diffFiftyDigitString);
	}); }
	
	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Should fail to update UID //FORMAT IS (UID, name, description)
	void testValidTaskEntry_UpdateUID() {
		//new task service
		TaskService testTaskService = new TaskService();
		
		//initialize new task in service
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
		//Check that it exists
		assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));

		//catch error when trying to update UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTaskService.setField(tenDigitString, "UID", diffTenDigitString);
		}); }
	
	
	//TASK UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Update all but UID w/valid input //FORMAT IS (UID, name, description)
	void testValidTaskEntry_UpdateTask() {
		TaskService testTaskService = new TaskService();
		//Add new task and populate fields
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
		
		//check all values are right
		assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "name").equals(twentyDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "description").equals(fiftyDigitString));

		//set as new values
		testTaskService.setField(tenDigitString, "name", diffTwentyDigitString);
		testTaskService.setField(tenDigitString, "description", diffFiftyDigitString);

		//check all values are right
		assertTrue(testTaskService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "name").equals(diffTwentyDigitString));
		assertTrue(testTaskService.getField(tenDigitString, "description").equals(diffFiftyDigitString));
	}
	
	@Test  //Update field with invalid UID  //FORMAT IS (UID, name, description)
	void testTaskEntry_UpdateInvalidTaskUID() {
		TaskService testTaskService = new TaskService();
		//Add new task and populate fields
		testTaskService.addTask(tenDigitString, twentyDigitString, fiftyDigitString);
		
		//catch error when trying to update a task that doesn't exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTaskService.setField(diffTenDigitString, "name", diffTenDigitString);
	}); }
/*
	@Test
	void test() {
		fail("Not yet implemented");
*/
}
