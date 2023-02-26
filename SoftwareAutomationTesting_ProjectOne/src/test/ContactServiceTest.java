package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.ContactService;




class ContactServiceTest {


	//TEST VALUE Variables
	String nineDigitString = "123456789";
	String tenDigitString = "1234567890";
	String diffTenDigitString = "0987654321";
	String elevenDigitString = "12345678901";
	String twentyNineDigitString = "12345678911234567892123456789";
	String thirtyDigitString = "123456789112345678921234567893";
	String diffThirtyDigitString = "398765432129876543211987654321";
	String thirtyOneDigitString = "1234567891123456789212345678931";

	
	
	@Test //All valid fields //FORMAT IS (UID, fName, lName, phone, address)
	void testValidContactEntry() {
		ContactService testContactService = new ContactService();
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		assertTrue(testContactService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "fName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "lName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "phone").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "address").equals(thirtyDigitString));
	}
	
	//CONTACT DELETE TESTING BELOW --------------------------------------------------------------------
	
	@Test //Valid contact deletion //FORMAT IS (UID, fName, lName, phone, address)
	void testContactDelete() {
		ContactService testContactService = new ContactService();
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		assertTrue(testContactService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "fName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "lName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "phone").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "address").equals(thirtyDigitString));
	
		testContactService.deleteContact(tenDigitString);
		
		//ensure UID does not exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContactService.validateUID(tenDigitString);
	}); }
	
	//MULTIPLE CONTACT INPUT TESTING BELOW --------------------------------------------------------------------	
	
	@Test //add multiple contacts //FORMAT IS (UID, fName, lName, phone, address)
	void testValidContactEntry_Multi() {
		//Add service
		ContactService testContactService = new ContactService();
		//initialize new contact
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		
		//check values
		assertTrue(testContactService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "fName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "lName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "phone").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "address").equals(thirtyDigitString));

		//add different contact
		testContactService.addContact(diffTenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		
		//check values 
		assertTrue(testContactService.getField(diffTenDigitString, "UID").equals(diffTenDigitString));
		assertTrue(testContactService.getField(diffTenDigitString, "fName").equals(tenDigitString));
		assertTrue(testContactService.getField(diffTenDigitString, "lName").equals(tenDigitString));
		assertTrue(testContactService.getField(diffTenDigitString, "phone").equals(tenDigitString));
		assertTrue(testContactService.getField(diffTenDigitString, "address").equals(thirtyDigitString));
	}
	
	@Test //Try to create same contact twice //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_Multi() {
		//new contact service
		ContactService testContactService = new ContactService();
		
		//initialize new contact in service
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
	
		//catch error when trying to create new with same UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
	}); }
	
	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Fail to update UID //FORMAT IS (UID, fName, lName, phone, address)
	void testValidContactEntry_UpdateUID() {
		//new contact service
		ContactService testContactService = new ContactService();
		
		//initialize new contact in service
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		assertTrue(testContactService.getField(tenDigitString, "UID").equals(tenDigitString));

		//catch error when trying to update UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContactService.setField(tenDigitString, "UID", diffTenDigitString);
		}); }
	
	
	//CONTACT UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Update all but UID w/valid input //FORMAT IS (UID, fName, lName, phone, address)
	void testValidContactEntry_UpdateContact() {
		ContactService testContactService = new ContactService();
		//Add new contact and populate fields
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		
		//check that all fields match from creation
		assertTrue(testContactService.getField(tenDigitString, "fName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "lName").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "phone").equals(tenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "address").equals(thirtyDigitString));

		//set as new values
		testContactService.setField(tenDigitString, "fName", diffTenDigitString);
		testContactService.setField(tenDigitString, "lName", diffTenDigitString);
		testContactService.setField(tenDigitString, "phone", diffTenDigitString);
		testContactService.setField(tenDigitString, "address", diffThirtyDigitString);

		//check that all fields match new values
		assertTrue(testContactService.getField(tenDigitString, "fName").equals(diffTenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "lName").equals(diffTenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "phone").equals(diffTenDigitString));
		assertTrue(testContactService.getField(tenDigitString, "address").equals(diffThirtyDigitString));
	}
	
	@Test  //Update field with invalid UID//FORMAT IS (UID, fName, lName, phone, address)
	void testContactEntry_UpdateInvalidContactUID() {
		ContactService testContactService = new ContactService();
		//Add new contact and populate fields
		testContactService.addContact(tenDigitString, tenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
		
		//catch error when trying to update a contact that doesn't exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContactService.setField(diffTenDigitString, "fName", diffTenDigitString);
	}); }
/*
	@Test
	void test() {
		fail("Not yet implemented");
*/
}
