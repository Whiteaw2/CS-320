package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.Contact;




class ContactTest {

	//TEST VALUE Variables
	String nineDigitString = "123456789";
	String tenDigitString = "1234567890";
	String diffTenDigitString = "0987654321";
	String elevenDigitString = "12345678901";
	String twentyNineDigitString = "12345678911234567892123456789";
	String thirtyDigitString = "123456789112345678921234567893";
	String diffThirtyDigitString = "398765432129876543211987654321";
	String thirtyOneDigitString = "1234567891123456789212345678931";

	
	
	@Test //All valid fields
	void testValidContactEntry() {
		Contact testContact = new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
		assertTrue(testContact.getField("UID").equals(tenDigitString));
		assertTrue(testContact.getField("fName").equals(tenDigitString));
		assertTrue(testContact.getField("lName").equals(tenDigitString));
		assertTrue(testContact.getField("phone").equals(tenDigitString));
		assertTrue(testContact.getField("address").equals(thirtyDigitString));
	}
	
	//GET TESTING BELOW --------------------------------------------------------------------
	@Test //Get invalid field name  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_invalidCall() {
		Contact testContact = new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContact.getField("invalidInput");
		}); }

	//CONTACT UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test //All valid fields
	void testValidContactEntry_multi() {
		Contact testContact = new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
		assertTrue(testContact.getField("UID").equals(tenDigitString));
		assertTrue(testContact.getField("fName").equals(tenDigitString));
		assertTrue(testContact.getField("lName").equals(tenDigitString));
		assertTrue(testContact.getField("phone").equals(tenDigitString));
		assertTrue(testContact.getField("address").equals(thirtyDigitString));
	
		testContact.setField("fName", diffTenDigitString);
		testContact.setField("lName", diffTenDigitString);
		testContact.setField("phone", diffTenDigitString);
		testContact.setField("address", diffThirtyDigitString);

		assertTrue(testContact.getField("fName").equals(diffTenDigitString));
		assertTrue(testContact.getField("lName").equals(diffTenDigitString));
		assertTrue(testContact.getField("phone").equals(diffTenDigitString));
		assertTrue(testContact.getField("address").equals(diffThirtyDigitString));
	}
	
	
	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID immutable  //FORMAT IS (UID, fName, lName, phone, address)
	void testContactEntry_UIDUpdate() {
		Contact testContact = new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
		assertTrue(testContact.getField("UID").equals(tenDigitString));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testContact.setField("UID", diffTenDigitString);
	}); }
	
	//ENTRY TOO LONG TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID too long  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_UIDLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(elevenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
	}); }
	
	@Test //fName too long  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_fNameLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, elevenDigitString, tenDigitString, tenDigitString, thirtyDigitString);
	}); }
	
	@Test //lName too long  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_lNameLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, elevenDigitString, tenDigitString, thirtyDigitString);
	}); }
	
	@Test //phone too long  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_phoneLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, tenDigitString, elevenDigitString,  thirtyDigitString);
	}); }
	
	@Test //address too long  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_addressLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  thirtyOneDigitString);
	}); }
	
	
	//ENTRY TOO SHORT TESTING BELOW --------------------------------------------------------------------
	
	@Test //phone too short   //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_phoneShort() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, tenDigitString, nineDigitString,  thirtyDigitString);
	}); }
	
	
	//NULL TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID Null  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_UIDNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, tenDigitString, tenDigitString, tenDigitString,  thirtyDigitString);
	}); }
	
	@Test //fName Null  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_fNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, null, tenDigitString, tenDigitString,  thirtyDigitString);
	}); }
	
	@Test //lName Null  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_lNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, null, tenDigitString,  thirtyDigitString);
	}); }
	
	@Test //phone Null  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_phoneNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, tenDigitString, null,  thirtyDigitString);
	}); }
	
	@Test //address Null  //FORMAT IS (UID, fName, lName, phone, address)
	void testInvalidContactEntry_addressNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(tenDigitString, tenDigitString, tenDigitString, tenDigitString,  null);
	}); }
	
	
	
	
	//void test2 () {
	//	fail("Not yet implemented");
	//}
	
}
