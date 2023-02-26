package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.Task;


class TaskTest {

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
		Task testTask = new Task(tenDigitString, twentyDigitString, fiftyDigitString);
		assertTrue(testTask.getField("UID").equals(tenDigitString));
		assertTrue(testTask.getField("name").equals(twentyDigitString));
		assertTrue(testTask.getField("description").equals(fiftyDigitString));
	}
	
	//GET TESTING BELOW --------------------------------------------------------------------
	@Test //Get invalid field name  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_invalidCall() {
		Task testTask = new Task(tenDigitString, twentyDigitString, fiftyDigitString);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTask.getField("invalidInput");
		}); }

	//Task UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test //All valid fields
	void testValidTaskEntry_multi() {
		Task testTask = new Task(tenDigitString, twentyDigitString, fiftyDigitString);
		//test that each was set correctly
		assertTrue(testTask.getField("UID").equals(tenDigitString));
		assertTrue(testTask.getField("name").equals(twentyDigitString));
		assertTrue(testTask.getField("description").equals(fiftyDigitString));
		
		//replace with new values
		testTask.setField("name", diffTwentyDigitString);
		testTask.setField("description", diffFiftyDigitString);

		//test that each was set correctly
		assertTrue(testTask.getField("name").equals(diffTwentyDigitString));
		assertTrue(testTask.getField("description").equals(diffFiftyDigitString));
	}
	
//
	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID immutable  //FORMAT IS (UID, name, description)
	void testTaskEntry_UIDUpdate() {
		Task testTask = new Task(tenDigitString, twentyDigitString, fiftyDigitString);
		assertTrue(testTask.getField("UID").equals(tenDigitString));
		
		//try to set new value to UID (should not be possible)
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testTask.setField("UID", diffTenDigitString);
	}); }
	
	//ENTRY TOO LONG TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID too long  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_UIDLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(elevenDigitString, twentyDigitString, fiftyDigitString);
	}); }
	
	@Test //name too long  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_nameLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenDigitString, twentyOneDigitString, fiftyDigitString);
	}); }
	
	@Test //description too long  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_descriptionLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenDigitString, twentyDigitString, fiftyOneDigitString);
	}); }
	
	
	//NULL TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID null  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_UIDNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, twentyDigitString, fiftyDigitString);
	}); }
	
	@Test //name too long  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_nameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenDigitString, null, fiftyDigitString);
	}); }
	
	@Test //description too long  //FORMAT IS (UID, name, description)
	void testInvalidTaskEntry_descriptionNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenDigitString, twentyDigitString, null);
	}); }
	
}
