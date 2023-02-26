package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.AppointmentService;


class AppointmentServiceTest {


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
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	Date today = new Date(); 
	Calendar cal = Calendar. getInstance();

	//VALID ENTRY TESTING BELOW --------------------------------------------------------------------
	
	
	@Test //All valid fields //FORMAT IS (UID, appointmentDate, description)
	void testValidAppointmentEntry() {
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		AppointmentService testAppointmentService = new AppointmentService();
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
		assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testAppointmentService.getField(tenDigitString, "appointmentDate").equals(dateFormat.format(todayPlus)));
		assertTrue(testAppointmentService.getField(tenDigitString, "description").equals(fiftyDigitString));
	}

	//Appointment DELETE TESTING BELOW --------------------------------------------------------------------
	
	@Test //Valid appointment deletion //FORMAT IS (UID, appointmentDate, description)
	void testAppointmentDelete() {
			//create appointment service
			AppointmentService testAppointmentService = new AppointmentService();
			
			
			//FIXME find better way to dynamically add time
			cal.setTime(today);
			cal.add(Calendar.MINUTE, 1);
			@SuppressWarnings("deprecation")
			Date todayPlus = new Date(cal.getTime().toString());
			
			//add appointment through service
			testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
			
			//check all values are right
			assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));
			assertTrue(testAppointmentService.getField(tenDigitString, "appointmentDate").equals(dateFormat.format(todayPlus)));
			assertTrue(testAppointmentService.getField(tenDigitString, "description").equals(fiftyDigitString));
		
			//Select/delete entry
			testAppointmentService.deleteAppointment(tenDigitString);
		
		//ensure UID does not exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testAppointmentService.validateUID(tenDigitString);
	}); }

	//MULTIPLE APPOINTMENT INPUT TESTING BELOW --------------------------------------------------------------------	
	
	@Test //add multiple Appointments //FORMAT IS (UID, appointmentDate, description)
	void testValidAppointmentEntry_Multi() {
		//create appointment service
		AppointmentService testAppointmentService = new AppointmentService();	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//check all values are right
		assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testAppointmentService.getField(tenDigitString, "appointmentDate").equals(dateFormat.format(todayPlus)));
		assertTrue(testAppointmentService.getField(tenDigitString, "description").equals(fiftyDigitString));

		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MONTH, 1);
		@SuppressWarnings("deprecation")
		Date todayPlusMonth = new Date(cal.getTime().toString());
		
		//add Appointment through service
		testAppointmentService.addAppointment(diffTenDigitString, todayPlusMonth, diffFiftyDigitString);
		
		//check all values are right
		assertTrue(testAppointmentService.getField(diffTenDigitString, "UID").equals(diffTenDigitString));
		assertTrue(testAppointmentService.getField(diffTenDigitString, "appointmentDate").equals(dateFormat.format(todayPlusMonth)));
		assertTrue(testAppointmentService.getField(diffTenDigitString, "description").equals(diffFiftyDigitString));
	}
	
	@Test //Try to create same Appointment twice //FORMAT IS (UID, appointmentDate, description)
	void testInvalidAppointmentEntry_Multi() {
		//create appointment service
		AppointmentService testAppointmentService = new AppointmentService();	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MONTH, 1);
		@SuppressWarnings("deprecation")
		Date todayPlusMonth = new Date(cal.getTime().toString());
		
		//catch error when trying to create new with same UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlusMonth, diffFiftyDigitString);
	}); }

	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Should fail to update UID //FORMAT IS (UID, appointmentDate, description)
	void testValidAppointmentEntry_UpdateUID() {
		//create appointment service
		AppointmentService testAppointmentService = new AppointmentService();	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//check UID exists
		assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));

		//catch error when trying to update UID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testAppointmentService.setField(tenDigitString, "UID", diffTenDigitString);
		}); }
	

	//APPOINTMENT UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test  //Update all but UID w/valid input //FORMAT IS (UID, appointmentDate, description)
	void testValidAppointmentEntry_UpdateAppointment() {
		//create appointment service
		AppointmentService testAppointmentService = new AppointmentService();	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//check all values are right
		assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testAppointmentService.getField(tenDigitString, "appointmentDate").equals(dateFormat.format(todayPlus)));
		assertTrue(testAppointmentService.getField(tenDigitString, "description").equals(fiftyDigitString));
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlusMonth = new Date(cal.getTime().toString());
		
		//Set new values
		testAppointmentService.setField(tenDigitString, "appointmentDate", todayPlusMonth);
		testAppointmentService.setField(tenDigitString, "description", diffFiftyDigitString);
		
		//check all values are right
		assertTrue(testAppointmentService.getField(tenDigitString, "UID").equals(tenDigitString));
		assertTrue(testAppointmentService.getField(tenDigitString, "appointmentDate").equals(dateFormat.format(todayPlusMonth)));
		assertTrue(testAppointmentService.getField(tenDigitString, "description").equals(diffFiftyDigitString));
	}
	
	@Test  //Update field with invalid UID  //FORMAT IS (UID, appointmentDate, description)
	void testAppointmentEntry_UpdateInvalidAppointmentUID() {
		//create appointment service
		AppointmentService testAppointmentService = new AppointmentService();	
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//add appointment through service
		testAppointmentService.addAppointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//catch error when trying to update an appointment that doesn't exist
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testAppointmentService.setField(diffTenDigitString, "description", diffFiftyDigitString);
		}); 
	}
	
}
