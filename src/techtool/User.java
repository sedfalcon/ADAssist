package techtool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class User {

	private String employeeNumber;
	private String name;
	private String title;
	private String created;
	private String department;
	private String userName;
	private String manager;
	private String phone;
	private String email;
	private String location;
	private String loginScript;
	private String description;
	private String dName;
	private boolean islockedOut = false;
	private boolean isEnabled = false;
	private boolean pwdExpired = false;
	private String pwdChangeDate;
	private Button userButton;
	
	
	public User(String userName, Button userButton) {
		super();
		this.userName = userName;
		this.userButton = userButton;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public static String powershellGetUserProperty(String un, String object) throws IOException {
		
		String cmd = "Get-ADUser " + un + " -Properties " +  object +  " | Select-Object " + object; 
		ArrayList<String> cmdoutput = new ArrayList<>();

		ProcessBuilder builder = new ProcessBuilder(
				"powershell.exe", cmd 
				);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		String nextline;
		
	
		while((nextline = r.readLine()) != null) {
			//nextline = nextline.replaceAll("\\s+", "");
			
			
			cmdoutput.add(nextline);
			
		}
		return cmdoutput.get(3);
		
		
	}


	public String getEmployeeNumber() {
		return employeeNumber;
	}


	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getManager() {
		return manager;
	}


	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getLoginScript() {
		return loginScript;
	}


	public void setLoginScript(String loginScript) {
		this.loginScript = loginScript;
	}

	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getdName() {
		return dName;
	}


	public void setdName(String dName) {
		this.dName = dName;
	}


	public boolean islockedOut() {
		return islockedOut;
	}


	public void setIslockedOut(String isLockedOut) {
		
		if(isLockedOut.contains("True")) {
		this.islockedOut = true;
		}else {this.islockedOut = false;}
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(String isEnabled) {
		if(isEnabled.contains("True")) {
			this.isEnabled = true;
			}else {this.isEnabled = false;}
	}


	public boolean isPwdExpired() {
		return pwdExpired;
	}


	public void setPwdExpired(String pwdExpired) {
		if(pwdExpired.contains("True")) {
			this.pwdExpired = true;
		}else{this.pwdExpired = false;}
	}


	public String getPwdChangeDate() {
		return pwdChangeDate;
	}


	public void setPwdChangeDate(String pwdChangeDate) {
		this.pwdChangeDate = pwdChangeDate;
	}


	public Button getUserButton() {
		return userButton;
	}


	public void setUserButton(Button userButton) {
		this.userButton = userButton;
	}
	
	public ArrayList<String> getADInfo() throws IOException {
		
		String cmd = "Get-ADUser " + this.getUserName() + " -properties employeenumber, Title, DisplayName, Department, LockedOut, adminDisplayName, PasswordLastSet, telephoneNumber, EmailAddress, ScriptPath, Enabled, StreetAddress, PasswordExpired, Created, Description, DistinguishedName"; 
		ArrayList<String> cmdoutput = new ArrayList<>();

		ProcessBuilder builder = new ProcessBuilder(
				"powershell.exe", cmd 
				);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		String nextline;
		
	
		while((nextline = r.readLine()) != null) {
			//nextline = nextline.replaceAll("\\s+", "");
			
			
			cmdoutput.add(nextline);
			
		}
		cmdoutput.remove(0);
		cmdoutput.remove(0);
		cmdoutput.forEach(String -> System.out.println(String));
		return cmdoutput;
		
		
	}
	/*****************************************
	* Appends file with username and timestamp
	* @param un
	*****************************************/
	public void activityLogStamp(){
		File activityLog = new File("activityLog.txt");
		Timestamp timestamp = new Timestamp();
		
		//Creates file if it doesn't exist
		if(!activityLog.exists()){
			//activityLog.getParentFile().mkdirs();
		}
		try {
			
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(activityLog, true))) {
                        writer.write(this.getUserName() + " " + timestamp.getTimestamp());
                        writer.newLine();
                        writer.flush();
                    }
		
		} catch (IOException e) {
                    // TODO Auto-generated catch block

		}
		finally{}
	}
	/*************************
	* End userLoginStamp
	**************************/
	
	public ArrayList<String> getGroups() {
		ArrayList<String> output = new ArrayList<>();
		try {
			output = Main.powershellRunCommand("Get-ADPrincipalGroupMembership " + this.getUserName() + " | select name");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.remove(0);
		output.remove(0);
		output.remove(0);
		java.util.Collections.sort(output);
		output.remove(0);
		output.remove(0);
		
		return output;
	}

	/*public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber() throws IOException {
		
		this.employeeNumber = powershellGetUserProperty(getUserName(), "EmployeeNumber");	
	}

	public String getName() {
		return name;
	}

	public void setName() throws IOException {
		
		this.name = powershellGetUserProperty(getUserName(), "DisplayName");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle() throws IOException {
		this.title = powershellGetUserProperty(getUserName(), "Title");
	}


	public boolean islockedOut() {
		return islockedOut;
	}

	public void setIslockedOut() throws IOException {
		
		if(powershellGetUserProperty(getUserName(), "LockedOut").contains("True")) {
			this.islockedOut = true;	
		}else {this.islockedOut = false;}
		
	}

	public boolean isEnabled() {

		return isEnabled;
	}

	public void setEnabled() throws IOException {
		
		this.isEnabled = Boolean.getBoolean(powershellGetUserProperty(getUserName(), "Enabled"));
	}

	public boolean isPwdExpired() {
		
		return pwdExpired;
	}

	public void setPwdExpired() throws IOException {
		
		this.pwdExpired = Boolean.getBoolean(powershellGetUserProperty(getUserName(), "PasswordExpired"));
	}

	public String getPwdChangeDate() {
		
		return pwdChangeDate;
	}

	public void setPwdChangeDate() throws IOException {
		
		this.pwdChangeDate = powershellGetUserProperty(getUserName(), "PasswordLastSet");
	}
	
	
	public String getManager() {
		
		return manager;
	}

	public void setManager() throws IOException {
		
		this.manager  = powershellGetUserProperty(getUserName(), "adminDisplayName");
	}

	
	public String getPhone() {
		
		return phone;
	}

	public void setPhone() throws IOException {
		
		this.phone = powershellGetUserProperty(getUserName(), "telephoneNumber");
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail() throws IOException {
		
		this.email = powershellGetUserProperty(getUserName(), "EmailAddress");
	}
	
	public String getLoginScript() {
		
		return loginScript;
	}

	public void setLoginScript() throws IOException {
		
		this.loginScript = powershellGetUserProperty(getUserName(), "ScriptPath");
	}

	public Button getUserButton() {
		
		return userButton;
	}*/
	

}
