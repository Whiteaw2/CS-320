package services;

import java.io.*;
import java.util.*;

public class AppointmentService {
	

	
	
	//create hashmap to hold appointments in memory
	private Map<String, Appointment> appointmentMap = new HashMap<String, Appointment>();
	
	public static void main(String[] args)
	{
		//create instance of class
		AppointmentService appointmentService = new AppointmentService();
		
	
		
	}

	//create new appointment
	public void addAppointment(String UID, Date appointmentDate, String description) 
	{
		//check existence and duplicates
		if (!appointmentMap.containsKey(UID)){
			
			//if it doesn't exist, create it and put it in the list
			Appointment Appointment = new Appointment(UID, appointmentDate, description);
			appointmentMap.put(Appointment.getField("UID"), Appointment);
			
			return;
		} else {
			throw new IllegalArgumentException("UID already exists. Please choose another.");
		}
	}
	
	
	//remove appointment from list of appointments
	public void deleteAppointment(String UID) {
		
		//validate that UID exists
		validateUID(UID);
		
		//remove it from list
		appointmentMap.remove(UID);
		return;
	}
	
	//getter
	public String getField(String UID, String field) {
		
		//does UID exist
		validateUID(UID);
		
		//get appointment, then field value
		Appointment appointment = appointmentMap.get(UID);
		return appointment.getField(field);
	}
	
	//setter
	public String setField(String UID, String field, String newVal) {
		
		//does UID exist
		validateUID(UID);
		
		//get appointment, then set field
		Appointment appointment = appointmentMap.get(UID);
		appointment.setField(field, newVal);
		
		return appointment.getField(field);
	}
	
	//Overloaded setter for appointmentDate
	public String setField(String UID, String field, Date newVal) {
		
		//does UID exist
		validateUID(UID);
		
		//get appointment, then set field
		Appointment appointment = appointmentMap.get(UID);
		appointment.setField(field, newVal);
		
		return appointment.getField(field);
	}
	
	
	//check list for UID
	public void validateUID(String UID) {
		if (appointmentMap.containsKey(UID)){
			return;
		} else {
			throw new IllegalArgumentException("UID does not exist");
		}
	}
}