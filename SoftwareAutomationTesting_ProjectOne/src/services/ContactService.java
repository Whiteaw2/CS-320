package services;

import java.io.*;
import java.util.*;

public class ContactService {
	

	
	
	//create hashmap to hold contacts in memory
	private Map<String, Contact> contactMap = new HashMap<String, Contact>();
	
	public static void main(String[] args)
	{
		//create instance of class
		ContactService contactService = new ContactService();
		
	
		
	}

	//create new contact
	public void addContact(String UID, String fName, String lName, String phone, String address) 
	{
		//check existence and duplicates
		if (!contactMap.containsKey(UID)){
			
			//if it doesn't exist, create it and put it in the list
			Contact contact = new Contact(UID, fName, lName, phone, address);
			contactMap.put(contact.getField("UID"), contact);
			
			return;
		} else {
			throw new IllegalArgumentException("UID already exists. Please choose another.");
		}
	}
	
	
	//remove contact from list of contacts
	public void deleteContact(String UID) {
		
		//validate that UID exists
		validateUID(UID);
		
		//remove it from list
		contactMap.remove(UID);
		return;
	}
	
	//getter
	public String getField(String UID, String field) {
		
		//does UID exist
		validateUID(UID);
		
		//get contact, then field value
		Contact contact = contactMap.get(UID);
		return contact.getField(field);
	}
	
	//setter
	public String setField(String UID, String field, String newVal) {
		
		//does UID exist
		validateUID(UID);
		
		//get contact, then set field
		Contact contact = contactMap.get(UID);
		contact.setField(field, newVal);
		
		return contact.getField(field);
	}
	
	
	//check list for UID
	public void validateUID(String UID) {
		if (contactMap.containsKey(UID)){
			return;
		} else {
			throw new IllegalArgumentException("UID does not exist");
		}
	}
}