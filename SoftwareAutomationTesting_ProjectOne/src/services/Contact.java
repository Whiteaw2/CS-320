package services;


import java.util.*;

public class Contact {
	
	//private variables for holding info
	private String UID = null;
	private String fName;
	private String lName;
	private String phone;
	private String address;
	

	public Contact (String UID, String fName, String lName, String phone, String address) {
		
		//per-instance list for condensed method code to iterate through values
		Map<String, String> inputCheckList = new HashMap<String, String>()
		{
			{
				//add input into list
				put("UID", UID);
				put("fName", fName);
				put("lName", lName);
				put("phone", phone);
				put("address", address);
			}
		};
		
		//using set method vs. initialization
		inputCheckList.forEach((key,value) 
				-> setField(key, value));
		

	}
	
	public String getField(String field) {
		if (field == "UID") {
			return this.UID; 
		}
		if (field == "fName") {
			return this.fName; 
		}
		if (field == "lName") {
			return this.lName; 
		}
		if (field == "phone") {
			return this.phone; 
		}
		if (field == "address") {
			return this.address; 
		}
		//catch others
		throw new IllegalArgumentException("Field does not exist");
		
	}

	
	//per field handling of setter
	public void setField(String field, String value) {
		if (field == "UID") {
			if (this.UID != null) {
				throw new IllegalArgumentException("Unique ID cannot be updated");
			}
			if(value == null || value.length() > 10) {
				throw new IllegalArgumentException("Unique ID not valid");
			} 
			this.UID = value;
		}
		if (field == "fName") {
			if(value == null || value.length() > 10) {
				throw new IllegalArgumentException("First name not valid");
			}
			this.fName = value;
		}
		if (field == "lName") {
			if(value == null || value.length() > 10) {
				throw new IllegalArgumentException("Last name not valid");
			}
			this.lName = value;
		}
		if (field == "phone") {
			if(value == null || value.length() != 10) {
				throw new IllegalArgumentException("Phone number not valid");
			}
			this.phone = value;
		}
		if (field == "address") {
			if(value == null || value.length() > 30) {
				throw new IllegalArgumentException("Address not valid");
			}
			this.address = value;
		}	
	}
}
