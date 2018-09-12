package techtool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Computer {
	
	private String computerName;
	private String computerOS;
	private String computerIP;
	private String distinguishedName;
	private String lastLogonTimeStamp;
	private String lastLogonUser;
	private String dateOfLastChange;
	private String createdOn;
	private String manufacturer;
	private String model;
	private String cpu;
	private String cpuLoad;
	private String serialNumber;
	private String currentLoggedinUser;
	private String currentClockSpeed;
	private String maxClockSpeed;
	private float driveCapacity;
	private	float driveSpace;
	private float ram;
	private float freeRam;
	private String lastReboot;
	private Button computerButton;
	
	
	public Computer(String computerName, Button computerButton) {
		super();
		this.computerName = computerName;
		this.computerButton = computerButton;
	}


	public String getComputerName() {
		return computerName;
	}


	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}


	public String getComputerOS() {
		return computerOS;
	}


	public void setComputerOS(String computerOS) {
		this.computerOS = computerOS;
	}

	
	public String getComputerIP() {
		return computerIP;
	}


	public void setComputerIP(String computerIP) {
		this.computerIP = computerIP;
	}


	public String getDistinguishedName() {
		return distinguishedName;
	}


	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}


	public String getLastLogonTimeStamp() {
		return lastLogonTimeStamp;
	}


	public void setLastLogonTimeStamp(String lastLogonTimeStamp) {
		this.lastLogonTimeStamp = lastLogonTimeStamp;
	}


	public String getLastLogonUser() {
		return lastLogonUser;
	}


	public void setLastLogonUser(String lastLogonUser) {
		this.lastLogonUser = lastLogonUser;
	}


	public String getDateOfLastChange() {
		return dateOfLastChange;
	}


	public void setDateOfLastChange(String dateOfLastChange) {
		this.dateOfLastChange = dateOfLastChange;
	}


	public String getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	

	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	public String getCurrentLoggedinUser() {
		return currentLoggedinUser;
	}


	public void setCurrentLoggedinUser(String currentLoggedinUser) {
		this.currentLoggedinUser = currentLoggedinUser;
	}

	
	public String getCurrentClockSpeed() {
		return currentClockSpeed;
	}


	public void setCurrentClockSpeed(String currentClockSpeed) {
		this.currentClockSpeed = currentClockSpeed;
	}


	public String getMaxClockSpeed() {
		return maxClockSpeed;
	}


	public void setMaxClockSpeed(String maxClockSpeed) {
		this.maxClockSpeed = maxClockSpeed;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	

	public String getCpuLoad() {
		return cpuLoad;
	}


	public void setCpuLoad(String cpuLoad) {
		this.cpuLoad = cpuLoad;
	}


	public float getDriveCapacity() {
		return driveCapacity;
	}


	public void setDriveCapacity(float driveCapacity) {
		this.driveCapacity = driveCapacity;
	}


	public float getDriveSpace() {
		return driveSpace;
	}


	public void setDriveSpace(float driveSpace) {
		this.driveSpace = driveSpace;
	}


	public float getRam() {
		return ram;
	}


	public void setRam(float ram) {
		this.ram = ram;
	}

	
	public float getFreeRam() {
		return freeRam;
	}


	public void setFreeRam(float freeRam) {
		this.freeRam = freeRam;
	}


	public String getLastReboot() {
		return lastReboot;
	}


	public void setLastReboot(String lastReboot) {
		this.lastReboot = lastReboot;
	}


	public Button getComputerButton() {
		return computerButton;
	}
	
	public ArrayList<String> getADInfo() throws IOException {
		
		ArrayList<String> cmd = new ArrayList<>();
		
	cmd.add("Get-ADComputer " + this.getComputerName() + " -properties OperatingSystem, IPV4Address, DistinguishedName, LastLogonDate, whenChanged, whenCreated");
	
	ArrayList<String> cmdoutput = new ArrayList<>();

	cmdoutput = Main.powershellRunCommands(cmd);
	
	cmdoutput.remove(0);
	cmdoutput.remove(0);
	for(String line : cmdoutput) {
		//System.out.println(line);
	}
	System.out.println(cmdoutput.get(cmdoutput.size() - 1));
	return cmdoutput;
	
	
	
}
	
	public boolean isReachable() {
		ArrayList<String> cmdoutput = new ArrayList<>();
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"powershell.exe", "ping -n 1 -w 500 " + this.getComputerIP() 
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
	
	public void ping() {
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", "ping " + this.getComputerName() 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
	}
	
	public void continuousPing() {
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", "ping -t " + this.getComputerName() 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
	}
	
	public void sfcScan() {
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", "psexec \\\\" + this.getComputerName() + " -s sfc /scannow" 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void restartSpooler() {
		
		
				
				
		try {
			
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "Get-Service -name Spooler -computername " + this.getComputerName() + " | Restart-Service" 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void remoteRegistry() throws IOException  {
		ArrayList<String> cmds = new ArrayList<>();
		
		cmds.add("Get-Service -name RemoteRegistry -computername " + this.getComputerName() + " | Set-Service -StartupType Manual");
		
		cmds.add("Get-Service -name RemoteRegistry -computername " + this.getComputerName() + " | Start-Service");
		cmds.add("Get-Service -name RemoteRegistry -computername " + this.getComputerName());
		
		Main.powershellExecute(false, cmds.get(0));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Thread Sleep was interrupted");
			e.printStackTrace();
		}
		Main.powershellExecute(false, cmds.get(1));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Thread Sleep was interrupted");
			e.printStackTrace();
		}
		Main.powershellRunCommand(cmds.get(2)).forEach(String -> System.out.println(String));
		
		
	}
	
	
	public void openC$() {
		try {
			
			Main.powershellExecute(false,"ii \\\\" + this.getComputerName() + "\\c$");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void logOff() {
		try {
			
			Main.powershellExecute(false,"(gwmi win32_operatingsystem -ComputerName " + this.getComputerName() + ").Win32Shutdown(4)");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void restart() {
		try {
			
			Main.powershellExecute(false,"(gwmi win32_operatingsystem -ComputerName " + this.getComputerName() + ").Win32Shutdown(6)");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void shutdown() {
		try {
			
			Main.powershellExecute(false,"(gwmi win32_operatingsystem -ComputerName " + this.getComputerName() + ").Win32Shutdown(12)");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};	
	}
	
	public void openRemoteDesktop() {
		
		try {
			
			Main.powershellExecute(false,"mstsc /v:"+ this.getComputerName() + " /f");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		
	}
	
public void openComputerManagement() {
		
		try {
			
			Main.powershellExecute(false,"compmgmt.msc -a /computer="+ this.getComputerName());
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		
	}
	
	public ArrayList<String> getSystemInfo() {
		ArrayList<String> cmds = new ArrayList<>();
		
		String computerSystem = "Get-WmiObject CIM_ComputerSystem -computer " + this.getComputerName()+" | Format-List Manufacturer, Model, TotalPhysicalMemory, UserName";
		String computerBIOS = "Get-WmiObject CIM_BIOSElement -computer " + this.getComputerName()+" | Format-List SoftwareElementID, SerialNumber";
		String computerOS = "Get-WmiObject CIM_OperatingSystem -computer " + this.getComputerName() +" | Format-List FreePhysicalMemory, LastBootUpTime";
		String computerCPU = "Get-WmiObject CIM_Processor -computer " + this.getComputerName() + " | Format-List Name, LoadPercentage, CurrentClockSpeed, MaxClockSpeed";
		String computerHDD = "Get-wmiobject CIM_LogicalDisk -computer " + this.getComputerName() + " -Filter \"\"DeviceID = 'C:'\"\"\"";
		
		cmds.add(computerSystem);
		cmds.add(computerBIOS);
		cmds.add(computerOS);
		cmds.add(computerCPU);
		cmds.add(computerHDD);

		try {
			cmds = Main.removeDuplicates(Main.powershellRunCommands(cmds));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cmds;
	}
	
	public void getMobileInfo() {
		
		
		String imei = "psexec \\\\" + this.getComputerName()+" netsh mbn show interface";
		String iccid = "psexec \\\\" + this.getComputerName()+" netsh mbn sh read i=*";
		
		
		try {
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", imei
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", iccid
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		public ArrayList<String> getMobileInfoTest() {
			
			ArrayList<String> cmds = new ArrayList<>();
			
			String imei = "psexec \\\\" + this.getComputerName()+" netsh mbn show interface";
			String iccid = "psexec \\\\" + this.getComputerName()+" netsh mbn sh read i=*";
			
			cmds.add(imei);
			//cmds.add(iccid);

				
				try {
					cmds = Main.cmdRunCommands(cmds);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				return cmds;
		}
		
		public void rdpON() {
			String reg = "REG ADD \"\\\\"+ this.computerName +"\\HKLM\\SYSTEM\\CurrentControlSet\\Control\\Terminal Server\" /v fDenyTSConnections /t REG_DWORD /d 0 /f";
			String service = "Get-Service -name TermService -computername " + this.getComputerName() + " | Start-Service";
			String serviceCheck = "Get-Service -name RemoteRegistry -computername " + this.getComputerName();
			
			try {
				if(Main.powershellRunCommand(serviceCheck).contains("Running  RemoteRegistry     Remote Registry")) {
					Main.cmdExecute(reg);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep was interrupted.");
						e.printStackTrace();
					}
					Main.powershellExecute(false,service);
				}else {remoteRegistry();
				Main.cmdExecute(reg);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Thread sleep was interrupted.");
					e.printStackTrace();
				}
				Main.powershellExecute(false,service);
				
				}
				
				if(checkRemoteRegistryService() == true) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Remote Desktop Enabled");
					alert.setHeaderText("Remote Desktop was successfully Enabled!" );
					alert.setContentText("Would you like to proceed connecting to " + this.getComputerName() + " via Remote Desktop?");

					ButtonType buttonTypeOne = new ButtonType("Yes");
					ButtonType buttonTypeTwo = new ButtonType("No");
					alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
					alert.showAndWait();
					if(alert.getResult() == buttonTypeOne) {
						Alert fyi = new Alert(AlertType.INFORMATION);
						fyi.setTitle("FYI");
						fyi.setHeaderText("Sometimes remote desktop won't connect the first time");
						fyi.setContentText("If you can't connect, try again after a minute or so.");
						fyi.showAndWait();
						openRemoteDesktop();
					}else {alert.close();};
					
				}else {Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Something went wrong");
				alert.setHeaderText("Remote Desktop was NOT Enabled!" );
				alert.setContentText("Would you like to try again?");

				ButtonType buttonTypeOne = new ButtonType("Yes");
				ButtonType buttonTypeTwo = new ButtonType("No");
				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
				alert.showAndWait();
				
				if(alert.getResult() == buttonTypeOne) {
					rdpON();
				}else {alert.close();};
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};	
		}
		
		public void rdpOFF() {

			String reg = "REG ADD \"\\\\"+ this.computerName +"\\HKLM\\SYSTEM\\CurrentControlSet\\Control\\Terminal Server\" /v fDenyTSConnections /t REG_DWORD /d 1 /f" ;
			String service = "Get-Service -name TermService -computername " + this.getComputerName() + " | Stop-Service";
			try {
				
				Main.cmdExecute(reg);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Thread Sleep was Interrupted");
					e.printStackTrace();
				}
				Main.powershellExecute(false,service);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};	
			
		}
		
		public boolean checkRemoteRegistryService() throws IOException {
			boolean isEnabled = false;
			ArrayList<String> output = new ArrayList<>();
			String registryCheck = "reg query \"\\\\" + this.getComputerName() + "\\HKLM\\SYSTEM\\CurrentControlSet\\Control\\Terminal Server\" /v fDenyTSConnections /t REG_DWORD";
			output = Main.powershellRunCommand("Get-Service -name RemoteRegistry -ComputerName " + this.getComputerName() + " | format-list status");
			
			if(output.contains("Status : Running") && Main.cmdRunCommand(registryCheck).contains("    fDenyTSConnections    REG_DWORD    0x0")) {
				isEnabled = true;
			}else {isEnabled = false;}
			
			return isEnabled;
		}
		
		public ArrayList<String> getNetworkAdapterInfo() {
			ArrayList<String> output = new ArrayList<>();
			
			try {
				output = Main.powershellRunCommand("Get-WmiObject -Class Win32_NetworkAdapterConfiguration -ComputerName " + this.getComputerName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
		}

}
