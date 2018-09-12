package techtool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.Button;

public class Printer {

	private String name;
	private String ipAddress;
	private String driverName;
	private String location;
	private String created;
	private String serverName;
	private String distinguishedName;
	private Button printerButton;
	
	
	public Printer(String name, Button printerButton) {
		this.name = name;
		this.printerButton = printerButton;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getDriverName() {
		return driverName;
	}


	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getDistinguishedName() {
		return distinguishedName;
	}


	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}


	public Button getPrinterButton() {
		return printerButton;
	}


	public void setPrinterButton(Button printerButton) {
		this.printerButton = printerButton;
	}
	
	public ArrayList<String> getADInfo() throws IOException {
		
		String cmd = "Get-ADObject -Filter 'printername -eq " + "''" + this.getName() + "''' -Properties *| select * | Format-List -Property *"; 
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
		return cmdoutput;
		
		
	}
	
	public boolean isReachable() {
		ArrayList<String> cmdoutput = new ArrayList<>();
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"powershell.exe", "ping -n 1 -w 500 " + this.getIpAddress() 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			String nextline;
			
		
			while((nextline = r.readLine()) != null) {
				
				if(nextline.equals("Request timed out.")) {
					return false;
				}else{cmdoutput.add(nextline);}
			}		

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(cmdoutput.contains("Approximate round trip times in milli-seconds:")) {
			return true;
		}
		else{return false;}
	}

public ArrayList<String> getQueueStatus() throws IOException {
		
		String cmd = "get-printer -ComputerName " + this.getServerName() + " -Name " + this.getName() + " | format-list printerstatus, jobcount"; 
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
		return cmdoutput;
		
		
	}
	
}
