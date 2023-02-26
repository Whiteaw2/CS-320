package services;


import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;  

public class Appointment {
	
	//private variables for holding info
	private String UID = null;
	private Date appointmentDate = new Date();
	private String description;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  

	public Appointment (String UID, Date appointmentDate, String description) {
		
		//set initialized values
		setField("UID", UID);
		setField("appointmentDate", appointmentDate);
		setField("description", description);
		
	}
	//per field handling of setter with only STRINGS
	public String getField(String field) {
		if (field == "UID") {
			return this.UID; 
		}
		if (field == "appointmentDate") {
			String stringDate = dateFormat.format(this.appointmentDate);
			return stringDate;
		}
		if (field == "description") {
			return this.description; 
		}
		//catch others
		throw new IllegalArgumentException("Field does not exist");
		
	}
	
	//per field handling of setter with only STRINGS
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
		/* FIXME delete if not converting from string to dates
		 * if (field == "appointmentDate") {
		 
			Date convertedDate = StringToDate(value); 
			if(value == null || convertedDate.before(new Date())) {
				throw new IllegalArgumentException("Name not valid");
			}
			this.appointmentDate = convertedDate;
		}
		*/
		if (field == "description") {
			if(value == null || value.length() > 50) {
				throw new IllegalArgumentException("Description not valid");
			}
			this.description = value;
		}
	}
	
	//OVERLOADED //handling of setter
	public void setField(String field, Date providedDate) {
		if (field == "appointmentDate") {
			Date now = new Date();
			//CANNOT be exact time as now and CANNOT BE IN THE PAST
			if(providedDate == null || providedDate.before(now)){
				//FIXME Delete debug prints below
				//System.out.println("Provided time was prior to new time");
				//System.out.println("Provided time = " + providedDate);
				//System.out.println("this.time     = "+ this.appointmentDate + '\n');
				throw new IllegalArgumentException("Appointment date not valid");
			}
			this.appointmentDate = providedDate;
		}

	}
	/*FIXME remove if not covering data entry of string for date
	//format string to date
	public Date StringToDate(String input)throws Exception { 
		
		Date formattedDate = new SimpleDateFormat("dd/MM/yyyy").parse(input); 
		return formattedDate;
	}
	*/
}
