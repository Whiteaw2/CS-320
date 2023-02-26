package services;


import java.util.*;

public class Task {
	
	//private variables for holding info
	private String UID = null;
	private String name;
	private String description;
	

	public Task (String UID, String name, String description) {
		
		//per-instance list for condensed method code to iterate through values
		Map<String, String> inputCheckList = new HashMap<String, String>()
		{
			{
				//add input into list
				put("UID", UID);
				put("name", name);
				put("description", description);
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
		if (field == "name") {
			return this.name; 
		}
		if (field == "description") {
			return this.description; 
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
		if (field == "name") {
			if(value == null || value.length() > 20) {
				throw new IllegalArgumentException("Name not valid");
			}
			this.name = value;
		}
		if (field == "description") {
			if(value == null || value.length() > 50) {
				throw new IllegalArgumentException("Description not valid");
			}
			this.description = value;
		}
	}
}
