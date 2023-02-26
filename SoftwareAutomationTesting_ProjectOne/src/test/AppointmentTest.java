package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import services.Appointment;

class AppointmentTest {

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
	//TIME ENTERED IS NOW PLUS ONE MINUTE IN FUTURE
	//FIXME find better way to dynamically add time
	void testValidAppointmentEntry() {
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		Appointment testAppointment = new Appointment(tenDigitString, todayPlus, fiftyDigitString);
		assertTrue(testAppointment.getField("UID").equals(tenDigitString));
		assertTrue(testAppointment.getField("appointmentDate").equals(dateFormat.format(todayPlus)));
		assertTrue(testAppointment.getField("description").equals(fiftyDigitString));
	}	
	
/*FIXME remove as instruction logic does not allow exact time setting	
	@Test //All valid fields //FORMAT IS (UID, appointmentDate, description)
	void testValidAppointmentEntry_exactTime() {
		
		//Create and set
		Appointment testAppointment = new Appointment(tenDigitString, today, fiftyDigitString);
		
		//Test values
		assertTrue(testAppointment.getField("UID").equals(tenDigitString));
		assertTrue(testAppointment.getField("appointmentDate").equals(dateFormat.format(today)));
		assertTrue(testAppointment.getField("description").equals(fiftyDigitString));
	}
	*/
	
	//INVALID TIME ENTRY TESTING BELOW --------------------------------------------------------------------
	
	 @Test //TIME ENTERED EQUALS NOW EXACTLY (SHOULD FAIL) //FORMAT IS (UID, appointmentDate, description)
	 
	void testInValidAppointmentEntry_timeExact() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment testAppointment = new Appointment(tenDigitString, today, fiftyDigitString);
		}); 
	}
	
	
	@Test //TIME ENTERED EQUALS NOW MINUS ONE MINUTE (SHOULD FAIL) //FORMAT IS (UID, appointmentDate, description)
	void testInValidAppointmentEntry_timeMinus() {
		//FIXME find better way to dynamically add time
		//Take the current time, convert to calendar, add number of hours, set to date
		cal.setTime(today);
		//Subtract one minute
		cal.add(Calendar.MINUTE, -1);
		@SuppressWarnings("deprecation")
		Date TodayMinus = new Date(cal.getTime().toString());
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment testAppointment = new Appointment(tenDigitString, TodayMinus, fiftyDigitString);
		}); 
	}
	
	//ENTRY TOO LONG TESTING BELOW --------------------------------------------------------------------

	 
		@Test //UID too long  //FORMAT IS (UID, appointmentDate, description)
		void testInvalidAppointmentEntry_UIDLong() {
			//FIXME find better way to dynamically add time
			cal.setTime(today);
			cal.add(Calendar.MINUTE, 1);
			@SuppressWarnings("deprecation")
			Date todayPlus = new Date(cal.getTime().toString());
			
			//Create new with invalid UID, but valid others
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				Appointment testAppointment = new Appointment(elevenDigitString, todayPlus, fiftyDigitString);
		}); }
		
		
		@Test //description too long  //FORMAT IS (UID, appointmentDate, description)
		void testInvalidAppointmentEntry_descriptionLong() {
			//FIXME find better way to dynamically add time
			cal.setTime(today);
			cal.add(Calendar.MINUTE, 1);
			@SuppressWarnings("deprecation")
			Date todayPlus = new Date(cal.getTime().toString());
			
			//Create new with invalid UID, but valid others
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				Appointment testAppointment = new Appointment(tenDigitString, todayPlus, fiftyOneDigitString);
		}); }

	//GET TESTING BELOW --------------------------------------------------------------------
	@Test //Get invalid field name  //FORMAT IS (UID, appointmentDate, description)
	void testInvalidAppointmentEntry_invalidFieldCall() {
		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//Create new
		Appointment testAppointment = new Appointment(tenDigitString, todayPlus, fiftyDigitString);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testAppointment.getField("invalidInput");
		}); }

	/* FIXME delete as requirements do not specify ability to update
	//Appointment UPDATE TESTING BELOW --------------------------------------------------------------------
	
	@Test //All valid fields
	void testValidEntry_multi() {
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//Create new
		Appointment testAppointment = new Appointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//test that each was set correctly
		assertTrue(testAppointment.getField("UID").equals(tenDigitString));
		assertTrue(testAppointment.getField("appointmentDate").equals(dateFormat.format(todayPlus)));
		assertTrue(testAppointment.getField("description").equals(fiftyDigitString));
		
		//replace with new values
		//FIXME find better way to dynamically add time
		cal.add(Calendar.MONTH, 1);
		@SuppressWarnings("deprecation")
		Date todayPlusMonth = new Date(cal.getTime().toString());
		
		testAppointment.setField("appointmentDate", todayPlusMonth);
		testAppointment.setField("description", diffFiftyDigitString);

		//test that each was set correctly
		assertTrue(testAppointment.getField("appointmentDate").equals(dateFormat.format(todayPlusMonth)));
		assertTrue(testAppointment.getField("description").equals(diffFiftyDigitString));
	}
	*/
	
	//IMMUTABILITY TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID immutable  //FORMAT IS (UID, appointmentDate, description)
	void testAppointmentEntry_UIDUpdate() {
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		//Create new
		Appointment testAppointment = new Appointment(tenDigitString, todayPlus, fiftyDigitString);
		
		//Check UID correct
		assertTrue(testAppointment.getField("UID").equals(tenDigitString));
		
		//try to set new value to UID (should not be possible)
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testAppointment.setField("UID", diffTenDigitString);
	}); }
	
	

	
	//NULL TESTING BELOW --------------------------------------------------------------------
	
	@Test //UID null  //FORMAT IS (UID, appointmentDate, description)
	void testInvalidAppointmentEntry_UIDNull() {
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null, todayPlus, fiftyDigitString);
		}); 
	}
	
	@Test //appointmentDate null  //FORMAT IS (UID, appointmentDate, description)
	void testInvalidAppointmentEntry_appointmentDateNull() {		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenDigitString, null, fiftyDigitString);
		}); 
	}
	
	@Test //description null  //FORMAT IS (UID, appointmentDate, description)
	void testInvalidAppointmentEntry_descriptionNull() {		
		//FIXME find better way to dynamically add time
		cal.setTime(today);
		cal.add(Calendar.MINUTE, 1);
		@SuppressWarnings("deprecation")
		Date todayPlus = new Date(cal.getTime().toString());
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenDigitString, todayPlus, null);
		}); 
	}
	
}
