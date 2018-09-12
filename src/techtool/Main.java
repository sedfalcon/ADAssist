package techtool;


import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.Optional;
import java.util.Stack;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {
	final File f = new File("CSS/application.css");
	private TextField searchTF = new TextField();
	private GridPane mainGPane = new GridPane();
	private static Scene searchScene;
	private static GridPane userResultsGPane = new GridPane();
	private static GridPane computerResultsGPane = new GridPane();
	private static GridPane printerResultsGPane = new GridPane();
	//TilePane adResultList = new TilePane();
	private VBox adResultList = new VBox(2);
	private Button searchButton = new Button("SEARCH");
	private static Button unlockButton = new Button ("unlock");
	private static Button resetPasswordButton = new Button ("Reset Password");
	private static Button enableDisableButton = new Button ("this is enable button");
	private Label adUserlabel = new Label("AD Users (0)");
	private Label adComputerlabel = new Label("AD Computers (0)");
	private Label adPrinterlabel = new Label("AD Printers (0)");
	private HBox searchHB = new HBox(5);
	private HBox unlockHB = new HBox(2);
	private static HBox enableHB = new HBox(2);
	private HBox passwordResetHB = new HBox(2);
	private VBox userResultVB = new VBox(1);
	private VBox computerResultVB = new VBox(1);
	private VBox printerResultVB = new VBox(1);
	private static User activeUser;
	private static Computer activeComputer;
	private static Printer activePrinter;
	private MenuBar menuBar = new MenuBar();
	private Menu customSearch = new Menu("Search Options");
	private CheckMenuItem userSearch = new CheckMenuItem("Users");
	private CheckMenuItem computerSearch = new CheckMenuItem("Computers"); 
	private CheckMenuItem printerSearch = new CheckMenuItem("Printers"); 
	final static Clipboard clipboard = Clipboard.getSystemClipboard();
	final static ClipboardContent content = new ClipboardContent();
	//customSearch.getItems().addAll(userSearch, computerSearch);
	final static Timestamp timestamp = new Timestamp();
	final static Stack<Button> backArray = new Stack<>();
	final static Stack<Button> forwardArray = new Stack<>();
	private static Button backButton = new Button("<<");
	private static Button forwardButton = new Button(">>");
	private SplitPane mainSplitPane = new SplitPane();
	
	private static ComboBox<String> computerCMDComboBox;
	private Button computerCMDButton = new Button("RUN");
	private static TextArea computerCMDText = new TextArea();
	private HBox computerCMDHB = new HBox(5);
	private HBox computerResultHB = new HBox(10);
	private HBox lastLogonHB = new HBox (10);
	private static RadioButton rdpON, rdpOFF;
	private Label rdpLabel;
	private HBox rdpHB = new HBox (5);
	//private Scene commandRun = new Scene(computerCMDText, 500, 500);
	private Stage computerCMDStage = new Stage();
	private static Circle statusCircle = new Circle(30.0f, 13.5f, 10.0f);
	private static Circle printerStatusCircle = new Circle(30.0f, 13.5f, 10.0f);
	private static Label resultComputerStamp = new Label ("Test");
	private static Button getLastLogonButton =  new Button ("Last Changed Profile");
	private static Button lastLogonResult = new Button ();
	private static TitledPane userGroupList = new TitledPane();
	private static ListView<String> listView = new ListView<>();
	private Button getSysInfo = new Button ("System Information");
	private Button getMobileInfo = new Button ("Mobile Information");
	private Button getNetworkAdapterInfo = new Button ("Network Adapter Information");
	private static Label operatingSystem = new Label ("OS:");
	private static Label computerLastLogon = new Label ("Last Logon:");
	private static Label computerCreated = new Label ("Created:");
	private static Label computerLastChanged = new Label ("Changed:");
	
	private static Label resultOS = new Label ("OS:");
	private static Label resultComputerLastLogon = new Label ("Last Logon:");
	private static Label resultComputerCreated = new Label ("Created:");
	private static Label resultComputerChanged = new Label ("Changed:");
	
	
	private static Label managerLabel;
	private static Label loginScriptLabel;
	private static Label phoneLabel;
	private static Label emailLabel;
	private static Label nameTitleLabel;
	private static Label lockedOutLabel;
	private static Label pwdLastSetLabel;
	private static Label isEnabledLabel;
	private static Label locationLabel;
	private static Label passwordExpiredLabel;
	private static Label changePasswordLabel;
	private static Label createdLabel;
	private static Label descriptionLabel;
	private static Label dNameLabel;
	
	private static Label resultUserStamp = new Label("Test");
	private static Label resultManager = new Label("Test");
	private static Label resultLockedOut = new Label("Test");
	private static Label resultLoginScript = new Label("Test");
	private static Label resultPhone = new Label("Test");
	private static Button resultEmail = new Button("Test");
	private static Label resultNameTitle = new Label("Test");
	private static Label resultPWDLastSet = new Label("Test");
	private static Label resultisEnabled = new Label("Test");
	private static Label resultLocation = new Label ("Test");
	private static Label resultPasswordExpired = new Label ("Test");
	private static Label resultCreated = new Label("Test");
	private static Label resultDescription = new Label("Test");
	private static Label resultDN = new Label("Test");
	
	private static ArrayList<User> userArray = new ArrayList<>();
	private static ArrayList<Computer> computerArray = new ArrayList<>();
	private static ArrayList<Printer> printerArray = new ArrayList<>();
	
	private static HBox printerStampHBox = new HBox(5);
	private static Label resultPrinterStamp = new Label("Test");
	private static Button printerIPButton = new Button("Test");
	private static Label printerLocation = new Label("Test");
	private static Label resultPrinterLocation = new Label("Test");
	private static Label printerTimeStamp = new Label();
	private static Label printerDriver = new Label();
	private static Label resultPrinterDriver = new Label();
	private static Label printerCreated = new Label();
	private static Label resultPrinterCreated = new Label();
	private static Label printerDN = new Label();
	private static Label resultPrinterDN = new Label();
	private static Label resultPrinterStatus = new Label();
	private static Label resultPrinterJobs = new Label();
	
	/*****************************
	 * CSS STRINGS
	 ****************************/
	
	String userButton = "-fx-border-color: none;" + 
			"-fx-background-color: white;" + 
			"-fx-max-width: 200px;" + 
			"-fx-max-height: 10px;" + 
			"-fx-text-fill: blue;" + 
			"-fx-font-size: 15px;" + 
			"-fx-padding: 0 0 0 5;";
	
	/*****************
	 * Runs an ArrayList of powershell commands 
	 * Returns ArrayList of Strings of command output
	 * @param cmds
	 * @return
	 * @throws IOException
	 ********************/
	public static ArrayList<String> powershellRunCommands(ArrayList<String> cmds) throws IOException {
		ArrayList<String> cmdoutput = new ArrayList<>();
		
		for(String cmd : cmds) {
			ProcessBuilder builder = new ProcessBuilder(
					"powershell.exe", cmd 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			
			String nextline;
			
			while((nextline = r.readLine()) != null) {
				
				cmdoutput.add(nextline);	
			}
		}
		return cmdoutput;
	}
	
	public static ArrayList<String> cmdRunCommands(ArrayList<String> cmds) throws IOException {
		ArrayList<String> cmdoutput = new ArrayList<>();
		
		for(String cmd : cmds) {
			ProcessBuilder builder = new ProcessBuilder(
					"cmd.exe", "/C", cmd 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			
			String nextline;
			
			while((nextline = r.readLine()) != null) {
				
				cmdoutput.add(nextline);	
			}
		}
		return cmdoutput;
	}
	
	public static ArrayList<String> cmdRunCommand(String cmd) throws IOException {
		ArrayList<String> cmdoutput = new ArrayList<>();
		
		
			ProcessBuilder builder = new ProcessBuilder(
					"cmd.exe", "/C", cmd 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			
			String nextline;
			
			while((nextline = r.readLine()) != null) {
				
				cmdoutput.add(nextline);	
			}
		
		return cmdoutput;
	}
	
	
	/***********************************************************
	 * Executes a single PowerShell command with no output return
	 * Using Boolean Parameter "True" will open powershell window 
	 * and show command execution.
	 * 
	 * Using parameter "False" will prevent window from showing
	 * 
	 * @param window
	 * @param cmd
	 * @throws IOException
	 *************************************************************/

	public static void powershellExecute(Boolean window, String cmd) throws IOException {
		
		if(window == false) {
		ProcessBuilder builder = new ProcessBuilder(
				"powershell.exe", cmd 
				);
		builder.redirectErrorStream(true);
	    builder.start();
		}else if(window = true) {
			ProcessBuilder builder = new ProcessBuilder(
					"cmd", "/c", "start", "powershell.exe", "-NoExit", "-Command", cmd 
					);
			builder.redirectErrorStream(true);
		    builder.start();
			
		}else {System.out.println("No valid boolean argument"); }
	    
	}
	
	public static void cmdExecute(String cmd) throws IOException {
		Runtime RT = Runtime.getRuntime();
		RT.exec(cmd);
	}
	
	/************************
	 * Runs single PowerShell command
	 * returns ArrayList of strings of command output
	 * @param cmd
	 * @return
	 * @throws IOException
	 ***************************/
	public static ArrayList<String> powershellRunCommand(String cmd) throws IOException {
		ArrayList<String> cmdoutput = new ArrayList<>();
		
		
			ProcessBuilder builder = new ProcessBuilder(
					"powershell.exe", cmd 
					);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			
			String nextline;
			
			while((nextline = r.readLine()) != null) {
				
				cmdoutput.add(nextline);	
			}
		
		return cmdoutput;
	}
	
	
	/***********************************
	 * Removes duplicates from an ArrayList of Strings
	 * @param list
	 * @return
	 **********************************/
	 static ArrayList<String> removeDuplicates(ArrayList<String> list) {

	        // Store unique items in result.
	        ArrayList<String> result = new ArrayList<>();

	        // Record encountered Strings in HashSet.
	        HashSet<String> set = new HashSet<>();

	        // Loop over argument list.
	        for (String item : list) {

	            // If String is not in set, add it to the list and the set.
	            if (!set.contains(item)) {
	                result.add(item);
	                set.add(item);
	            }
	        }
	        return result;
	    }//end removeDuplicates Method
	 
	 /*************************************************
	  * Turns computer search result into active button
	  * Builds the computer object and displays it
	  * @param computer
	  * @throws IOException
	  *************************************************/
	 
	 public static void computerButton(Computer computer) throws IOException {
		
		 computer.getComputerButton().addEventHandler(ActionEvent.ACTION, (e) -> {
			 
			 activeComputer = computer;
			 if(activeComputer != null ) {
				 
				 if(!backArray.isEmpty() == true) {
					 if (backArray.peek() != activeComputer.getComputerButton()) {
						 
						 System.out.println("BackArray was not empty and added: " + activeComputer.getComputerButton().getText());
				 			backArray.push(activeComputer.getComputerButton());
				 		}
				 }else {
					 System.out.println("BackArray was empty and added: " + activeComputer.getComputerButton().getText());
					 backArray.push(activeComputer.getComputerButton());}
			 }
				 
			 if(forwardArray.isEmpty() == true) {
				 forwardButton.setDisable(true);
			 }else {forwardButton.setDisable(false);}
			 
			 computerResultsGPane.setVisible(false);
			 
			 computerCMDComboBox.setPromptText("Select a Command...");
			 ArrayList<String> computerAttributes = new ArrayList<>();
			 
			 try {
				computerAttributes = computer.getADInfo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 
			 for(String attribute : computerAttributes) {
				// System.out.println(attribute);
				 if(attribute.startsWith("DistinguidshedName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setDistinguishedName(attribute);
				 }
				 if(attribute.startsWith("IPv4Address")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setComputerIP(attribute);
					 
				 }
				 if(attribute.startsWith("LastLogonDate")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setLastLogonTimeStamp(attribute);
				 }
				 if(attribute.startsWith("OperatingSystem")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setComputerOS(attribute);
				 }
				 if(attribute.startsWith("whenChanged")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setDateOfLastChange(attribute);
				 }
				 if(attribute.startsWith("whenCreated")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 computer.setCreatedOn(attribute);
				 }
				 
			 }
			 
			 if (computer.isReachable() == true) {
				 statusCircle.setFill(javafx.scene.paint.Color.GREEN);
			 }else {statusCircle.setFill(javafx.scene.paint.Color.RED);}
			 
			 resultComputerStamp.setText("Computer: " + computer.getComputerName() + " - " + computer.getComputerIP() + " as of " + timestamp.getTimestamp());
			 resultOS.setText(computer.getComputerOS());
			 resultComputerLastLogon.setText(computer.getLastLogonTimeStamp());
			 resultComputerCreated.setText(computer.getCreatedOn());
			 resultComputerChanged.setText(computer.getDateOfLastChange());
			 
			 computerCMDText.clear();
			 computerCMDText.setWrapText(true);
			 
			 
			 userResultsGPane.setVisible(false);
			 computerResultsGPane.setVisible(true);
			 printerResultsGPane.setVisible(false);
			 
			 
			 if(statusCircle.getFill().equals(javafx.scene.paint.Color.GREEN)){
			try {
				boolean rrCheck = activeComputer.checkRemoteRegistryService();
				if(rrCheck == true) {
					rdpON.setSelected(true);
					rdpOFF.setSelected(false);
				}else {rdpOFF.setSelected(true);
					   rdpON.setSelected(false);}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 }else {rdpOFF.setSelected(true);
			        rdpON.setSelected(false);}
		 });
	 }//END computerButton Method
	 
	
	 /************************************************************
	  * Generates User details when User results button is clicked
	  * @param user
	  * @throws IOException
	  *************************************************************/
	 public static void userButton(User user) throws IOException {
		 
		 
		 user.getUserButton().addEventHandler(ActionEvent.ACTION, (e) -> {
			 activeUser = user;
			 if(activeUser != null ) {
				 
				 if(!backArray.isEmpty() == true) {
					 if (backArray.peek() != activeUser.getUserButton()) {
						 
						 System.out.println("BackArray was not empty and added: " + activeUser.getUserButton().getText());
				 			backArray.push(activeUser.getUserButton());
				 		}
				 }else {
					 System.out.println("BackArray was empty and added: " + activeUser.getUserButton().getText());
					 backArray.push(activeUser.getUserButton());}
			 }
			 
			 if(forwardArray.isEmpty() == true) {
				 forwardButton.setDisable(true);
			 }else {forwardButton.setDisable(false);}
			 
			 userResultsGPane.setVisible(false);
			 activeUser.activityLogStamp();
			 
			 ArrayList<String> userAttributes = new ArrayList<>();
			 try {
				userAttributes = user.getADInfo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 for(String attribute: userAttributes) {
				
				 if(attribute.startsWith("ScriptPath")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setLoginScript(attribute);
				 }
				 if(attribute.startsWith("adminDisplayName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setManager(attribute);
				 }
				 if(attribute.startsWith("Title")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setTitle(attribute);
				 }
				 if(attribute.startsWith("Created")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setCreated(attribute);
				 }
				 if(attribute.startsWith("Department")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 
					 switch (attribute) {
					 case "Information Technology":
						 attribute = "IT";
						 break;
					 case "Health Department":
						 attribute = "HLT";
						 break;
					 case "Public Works":
						 attribute = "PW";
						 break;
					 case "Sheriffs Office":
						 attribute = "SO";
						 break;
					 case "Assessors":
						 attribute = "ASR";
						 break;
					 case "Business Services":
						 attribute = "BS";
						 break;
					 case "Finance":
						 attribute = "FIN";
						 break;
					 case "Treasurer":
						 attribute = "TSR";
						 break;
					 case "District Attorney":
						 attribute = "DA";
						 break;
					 case "Community Services":
						 attribute = "CS";
						 break;
					 case "Board of Commissioners":
						 attribute = "BOC";
						 break;
					 case "Clerks Office":
						 attribute = "CLK";
						 break;
					 case "Juvenile Department":
						 attribute = "JUV";
						 break;
					 case "Legal Counsel":
						 attribute = "LGL";
						 break;
					 default: attribute = ""; 
					 }
					 user.setDepartment(attribute);
				 }
				 if(attribute.startsWith("telephoneNumber")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setPhone(attribute);
				 }
				 if(attribute.startsWith("EmailAddress")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setEmail(attribute);
				 }
				 if(attribute.startsWith("DisplayName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setName(attribute);
				 }
				 if(attribute.startsWith("LockedOut")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setIslockedOut(attribute);
				 }
				 if(attribute.startsWith("Description")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setDescription(attribute);
				 }
				 if(attribute.startsWith("EmployeeNumber")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setEmployeeNumber(attribute);
				 }
				 if(attribute.startsWith("PasswordLastSet")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setPwdChangeDate(attribute);
				 }
				 if(attribute.startsWith("Enabled")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setEnabled(attribute);
				 }
				 if(attribute.startsWith("StreetAddress")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setLocation(attribute);
				 }
				 if(attribute.startsWith("PasswordExpired")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setPwdExpired(attribute);
				 }
				 if(attribute.startsWith("DistinguishedName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 user.setdName(attribute);
				 }
			 }

			 resultUserStamp.setText(user.getDepartment() + " User: " + user.getUserName() + " - " + user.getEmployeeNumber() + " as of " + timestamp.getTimestamp());
			 resultManager.setText(user.getManager());
			 resultLocation.setText(user.getLocation());
			 resultCreated.setText(user.getCreated());
			 resultDescription.setText(user.getDescription());
			 System.out.println(user.islockedOut());
			 
			 if(user.islockedOut() == true) {
				 resultLockedOut.setText("LOCKED");
				 resultLockedOut.getStyleClass().add("result-red");
				 resultLockedOut.setStyle("-fx-text-fill: red;");
			 }else {
				 resultLockedOut.getStyleClass().add("result");
				 resultLockedOut.setStyle("-fx-text-fill: black; -fx-font-weight: normal; -fx-font-size: 14px; -fx-padding: 0 0 0 10px");
				 resultLockedOut.setText("Unlocked");
			 		}
			 
			 resultLoginScript.setText(user.getLoginScript());	
			 resultPhone.setText(user.getPhone());
			 resultEmail.setText(user.getEmail());
			 
			 resultNameTitle.setText(user.getName() + ", " + user.getTitle());
			 resultPWDLastSet.setText(user.getPwdChangeDate());
			 resultDN.setText(user.getdName());
			 resultDN.maxWidth(50);
			 resultDN.setTooltip(new Tooltip("Double Click to Copy"));
			 resultDN.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
				              content.putString(resultDN.getText());
				              clipboard.setContent(content);
				          
				            }
				        }
				    }
				});
			 
			 if(user.isEnabled() == true) {
				 
				 resultisEnabled.setText("Enabled");
				 resultisEnabled.setStyle("-fx-text-fill: green; -fx-font-size: 14px");
				 enableDisableButton.setText("disable");
				 	
			 }else {resultisEnabled.setText("Disabled");
			 
			 		resultisEnabled.setStyle("-fx-text-fill: red; -fx-font-size: 16px");
			 		enableDisableButton.setText("enable");		
			 }
			 		
			 	
			 if(user.isPwdExpired() == true) {
				 resultPasswordExpired.setText("Password must be changed");
			 }else {resultPasswordExpired.setText("False");}
			 
			 
			 userResultsGPane.setVisible(true);
			 computerResultsGPane.setVisible(false);
			 printerResultsGPane.setVisible(false);
			 listView.setVisible(false);
			 userGroupList.setExpanded(false);
			 
			 
			}); //END userButton Method
		 
		 
	 }
	 
	 public static void printerButton(Printer printer) {
		 printer.getPrinterButton().addEventHandler(ActionEvent.ACTION, (e) -> {
			 
			 activePrinter = printer;
			 if(activePrinter != null ) {
				 
				 if(!backArray.isEmpty() == true) {
					 if (backArray.peek() != activePrinter.getPrinterButton()) {
						 
						 System.out.println("BackArray was not empty and added: " + activePrinter.getPrinterButton().getText());
				 			backArray.push(activePrinter.getPrinterButton());
				 		}
				 }else {
					 System.out.println("BackArray was empty and added: " + activePrinter.getPrinterButton().getText());
					 backArray.push(activePrinter.getPrinterButton());}
			 }
			 
			 if(forwardArray.isEmpty() == true) {
				 forwardButton.setDisable(true);
			 }else {forwardButton.setDisable(false);}
			 ArrayList<String> printerAttributes = new ArrayList<>();
			 try {
				printerAttributes = printer.getADInfo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 printerAttributes.forEach(String -> System.out.println(String));
		 
			 for(String attribute : printerAttributes) {
				 
				 if(attribute.startsWith("printername")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setName(attribute);
				 }
				 if(attribute.startsWith("portName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 attribute = attribute.replace("{", "");
					 attribute = attribute.replace("}", "");
					 attribute = attribute.replace("IP_", "");
					 printer.setIpAddress(attribute);
				 }
				 if(attribute.startsWith("location")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setLocation(attribute);
				 }
				 if(attribute.startsWith("shortServerName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setServerName(attribute);
				 }
				 if(attribute.startsWith("driverName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setDriverName(attribute);
				 }
				 if(attribute.startsWith("Created")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setCreated(attribute);
				 }
				 if(attribute.startsWith("DistinguishedName")) {
					 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
					 attribute = attribute.replaceFirst(" ", "");
					 printer.setDistinguishedName(attribute);
				 }
				 
				
			 }
			 
			 if (printer.isReachable() == true) {
				 printerStatusCircle.setFill(javafx.scene.paint.Color.GREEN);
			 }else {printerStatusCircle.setFill(javafx.scene.paint.Color.RED);}
			 System.out.println(printer.getName() + " ON " + printer.getServerName() + " - " + printer.getIpAddress() + " " + printer.getLocation());
		 
			 resultPrinterStamp.setText(printer.getName() + " on " + printer.getServerName() + " -");
			 resultPrinterLocation.setText(printer.getLocation());
			 resultPrinterDriver.setText(printer.getDriverName());
			 resultPrinterCreated.setText(printer.getCreated());
			 resultPrinterDN.setText(printer.getDistinguishedName());
			 resultPrinterDN.setTooltip(new Tooltip("Double Click to Copy"));
			 resultPrinterDN.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
				              content.putString(resultPrinterDN.getText());
				              clipboard.setContent(content);
				          
				            }
				        }
				    }
				});
			 printerIPButton.setText(printer.getIpAddress());
			 printerTimeStamp.setText("as of " + timestamp.getTimestamp());
			 
			printerResultsGPane.setVisible(true);
			userResultsGPane.setVisible(false);
			computerResultsGPane.setVisible(false);
			resultPrinterStatus.setText("");
			resultPrinterJobs.setText("");
			// if(printerStatusCircle.getFill().equals(javafx.scene.paint.Color.GREEN)){
			
			/*try {
				activePrinter.getQueueStatus().forEach(String -> System.out.println(String));
				
				for(String attribute : activePrinter.getQueueStatus()) {
					if(attribute.startsWith("PrinterStatus")) {
						 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
						 attribute = attribute.replaceFirst(" ", "");
						 resultPrinterStatus.setText("Status:	 " + attribute);
						 
					 }
					if(attribute.startsWith("jobcount")) {
						 attribute = attribute.substring(attribute.indexOf(":")+1,attribute.length());
						 attribute = attribute.replaceFirst(" ", "");
						 resultPrinterJobs.setText("Jobs:		 " + attribute); 
					 }
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		//} 
		 });
	 }
	 
	 public void computerCMDRun(String cmd) throws InterruptedException, IOException {
		 switch (cmd) {
			case "Ping" :
			activeComputer.ping();
				break;
			case "Ping -t":
				activeComputer.continuousPing();
				break;
			case "SFC /Scannow":
				activeComputer.sfcScan();
				break;
			case "Restart Spooler":
				activeComputer.restartSpooler();
				break;
			case "Open C$":
				activeComputer.openC$();
				break;			
			case "Logoff": 
				activeComputer.logOff();
				break;
			case "Restart": 
				activeComputer.restart();
				break;
			case "Shutdown": 
				activeComputer.shutdown();
				break;
			case "Open Computer Managment": 
				activeComputer.openComputerManagement();
				break;
			case "Remote Registry ON":
				activeComputer.remoteRegistry();
				break;
			case "Open Remote Desktop":
				activeComputer.openRemoteDesktop();;
				break;
			}
	 }
	 
	 /*******************************************************
	  * Method used to check password string for required characters
	  * in order to meet complexity requirements
	  * @param str
	  * @return
	  ***********************************************************/
	 private static boolean checkString(String str) {
		    char ch;
		    boolean capitalFlag = false;
		    boolean lowerCaseFlag = false;
		    boolean numberFlag = false;
		    for(int i=0;i < str.length();i++) {
		        ch = str.charAt(i);
		        if( Character.isDigit(ch)) {
		            numberFlag = true;
		        }
		        else if (Character.isUpperCase(ch)) {
		            capitalFlag = true;
		        } else if (Character.isLowerCase(ch)) {
		            lowerCaseFlag = true;
		        }
		        if(numberFlag && capitalFlag && lowerCaseFlag)
		            return true;
		    }
		    return false;
		}
	 
	
	 	public static String convertTimestamp(String timestamp) {
		 timestamp = timestamp.substring(0, 15);
		 String month = timestamp.substring(4, 6);
		 String year = timestamp.substring(0, 4);
		 String day = timestamp.substring(6, 8);
		 String hour = timestamp.substring(8, 10);
		 String minute = timestamp.substring(10, 12);
		 String second = timestamp.substring(12, 14);
		 
		 timestamp = month + "/" + day + "/" + year + " " + hour + ":" + minute + ":" + second;
	    return timestamp;
	 	}
	 	
	 	public static int timeto12(LocalTime time){
			int hour;
			
			if((time.getHour()-12)>0){
				hour = time.getHour() - 12;
			}
			else{hour = time.getHour();}
			
			return hour;
		}
	 	
	 	public static String getAMPM(LocalTime time){
			String AM = "AM";
			String PM = "PM";
			if((time.getHour()-12)==0){
				return PM;
			}
			else if((time.getHour()-12)>0){
				return PM;
			}
			else{return AM;}
			
		}
	 	
	 	
	public static void main(String[] args) throws IOException {
		launch(args);
		 
	}
	
	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
		
		 System.out.println(timestamp.getTimestamp());
		
		
		unlockButton.getStyleClass().add("unlock-button");
		enableDisableButton.getStyleClass().add("unlock-button");
		resetPasswordButton.getStyleClass().add("reset-button");
			resetPasswordButton.setAlignment(Pos.CENTER_LEFT);
		resultEmail.getStyleClass().add("reset-button");
			resultEmail.setAlignment(Pos.CENTER_LEFT);
		
		adUserlabel.getStyleClass().add("ad-label");
		adComputerlabel.getStyleClass().add("ad-label");
		adPrinterlabel.getStyleClass().add("ad-label");
		
		resultUserStamp.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10px 0px;");
		resultComputerStamp.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10px 0px;");
		resultPrinterStamp.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10px 0px;");
		printerTimeStamp.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 0 0 10px 0px;");
		
		managerLabel = new Label("Manager:");
			managerLabel.getStyleClass().add("result-label");
		loginScriptLabel = new Label("Login Script:");
			loginScriptLabel.getStyleClass().add("result-label");
		lockedOutLabel = new Label("Locked Out:");
			lockedOutLabel.getStyleClass().add("result-label");
		phoneLabel = new Label("Phone:");
			phoneLabel.getStyleClass().add("result-label");
		emailLabel = new Label("Email:"); 
			emailLabel.getStyleClass().add("result-label");
		nameTitleLabel = new Label("Name, Title:");
			nameTitleLabel.getStyleClass().add("result-label");
		pwdLastSetLabel = new Label("PWD Changed:");
			pwdLastSetLabel.getStyleClass().add("result-label");
		isEnabledLabel = new Label("Enabled:");
			isEnabledLabel.getStyleClass().add("result-label");
		locationLabel = new Label("Location:");
			locationLabel.getStyleClass().add("result-label");
		passwordExpiredLabel = new Label("PWD Expired:");
			passwordExpiredLabel.getStyleClass().add("result-label");
		createdLabel = new Label("Created On:");
			createdLabel.getStyleClass().add("result-label");
		changePasswordLabel = new Label("Change PWD:");
			changePasswordLabel.getStyleClass().add("result-label");
		descriptionLabel = new Label("Description:");
			descriptionLabel.getStyleClass().add("result-label");
		dNameLabel = new Label("DN:");	
			dNameLabel.getStyleClass().add("result-label");
		
		resultManager.getStyleClass().add("result");
		resultLoginScript.getStyleClass().add("result");
		resultLockedOut.getStyleClass().add("result");
		resultPhone.getStyleClass().add("result");
		resultNameTitle.getStyleClass().add("result");
		resultPWDLastSet.getStyleClass().add("result");
		resultisEnabled.getStyleClass().add("result");
		resultLocation.getStyleClass().add("result");
		resultPasswordExpired.getStyleClass().add("result");
		resultCreated.getStyleClass().add("result");
		resultDescription.getStyleClass().add("result");
		resultDN.getStyleClass().add("dnresult");
		
		operatingSystem.getStyleClass().add("result-label");
		computerLastLogon.getStyleClass().add("result-label");
		computerCreated.getStyleClass().add("result-label");
		computerLastChanged.getStyleClass().add("result-label");
		
		resultOS.getStyleClass().add("result");
		resultComputerLastLogon.getStyleClass().add("result");
		resultComputerCreated.getStyleClass().add("result");
		resultComputerChanged.getStyleClass().add("result");
		
		printerLocation.setText("Location:");
		printerLocation.getStyleClass().add("result-label");
		resultPrinterLocation.getStyleClass().add("result");
		
		printerDriver.setText("Driver:");
		printerDriver.getStyleClass().add("result-label");
		resultPrinterDriver.getStyleClass().add("result");
		
		printerCreated.setText("Created:");
		printerCreated.getStyleClass().add("result-label");
		resultPrinterCreated.getStyleClass().add("result");
		
		printerDN.setText("DN:");
		printerDN.getStyleClass().add("result-label");
		resultPrinterDN.getStyleClass().add("result");
		
		resultPrinterStatus.getStyleClass().add("result-label");
		resultPrinterJobs.getStyleClass().add("result-label");
		
		/***********************************************
		 * SEARCH / MENU BAR SETTINGS
		 **********************************************/
		
		searchTF.setMinSize(450, 13);
		
		searchButton.getStyleClass().add("Search-button");
		backButton.getStyleClass().add("back-button");
		backButton.setAlignment(Pos.CENTER_LEFT);
		forwardButton.getStyleClass().add("back-button");
		if(forwardArray.isEmpty() == true) {
			 forwardButton.setDisable(true);
		 }else {forwardButton.setDisable(false);}
		
		customSearch.getItems().addAll(userSearch, computerSearch, printerSearch);
		
		menuBar.getMenus().add(customSearch);	
		menuBar.setStyle("-fx-background-color: transparent;");
		userSearch.setSelected(true);
		computerSearch.setSelected(true);
		printerSearch.setSelected(true);
		
		searchHB.getChildren().addAll(backButton, forwardButton, searchTF, searchButton, menuBar);
		unlockHB.getChildren().addAll(resultLockedOut, unlockButton);
		enableHB.getChildren().addAll(resultisEnabled, enableDisableButton);
		unlockHB.setPadding(new Insets(2, 0, 0, 0));
		unlockHB.setPadding(new Insets(2, 0, 0, 0));
		searchHB.setPrefWidth(850);
		//searchHB.setMinWidth(850);
		searchHB.getStyleClass().add("searchHB");
		searchHB.setAlignment(Pos.TOP_CENTER);
        
		/******************************
		 * SEARCH RESULTS PANEL SETTINGS
		 ********************************/
        ScrollPane scrolly = new ScrollPane();
        scrolly.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrolly.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrolly.setContent(adResultList);
        scrolly.setStyle("-fx-background-color: transparent;-fx-border-color: none;");
        scrolly.setFitToWidth(true);
        
		adResultList.heightProperty().addListener((obs, oldVal, newVal) -> {
			
		});
		//adResultList.setMaxWidth(300);
		adResultList.getChildren().addAll(userResultVB, computerResultVB, printerResultVB);
		adResultList.getStyleClass().add("resultList");
		adResultList.setMinHeight(100);
		adResultList.setPrefWidth(180);
		
		computerResultVB.getChildren().addAll(adComputerlabel);
		computerResultVB.setStyle("-fx-background-color: transparent; -fx-border-color: black;-fx-border-width: 0px 0px 0px 0px;");
		//computerResultVB.setMaxWidth(300);
		computerResultVB.setMinWidth(180);
		computerResultVB.setMinHeight(100);
		
		
		userResultVB.getChildren().addAll(adUserlabel);
		GridPane.setMargin(adUserlabel, new Insets(5,0,0,5));
		userResultVB.setStyle("-fx-background-color: transparent; -fx-border-color: black;-fx-border-width: 0px 0px 0px 0px;");
		//userResultVB.setMaxWidth(300);
		userResultVB.setMinWidth(180);
		userResultVB.setMinHeight(100);
		
		printerResultVB.getChildren().addAll(adPrinterlabel);
		//printerResultVB.setMaxWidth(300);
		printerResultVB.setStyle("-fx-background-color: transparent; -fx-border-color: black;-fx-border-width: 0px 0px 0px 0px;");
		printerResultVB.setMinWidth(180);
		printerResultVB.setMinHeight(395);
		
		/****************************************
		 * USER RESULTS LAYOUT SETTINGS
		 **************************************/
		userResultsGPane.setVisible(false);
		//userResultsGPane.setGridLinesVisible(true);
		userGroupList.setText("Groups");
		userGroupList.setContent(listView);
		userGroupList.setExpanded(false);
		userGroupList.setMaxWidth(400);
		listView.setMaxHeight(150);
		
		//listView.setStyle("listView");
		userGroupList.setStyle("-fx-background-color: transparent;-fx-border-color: none;");
		
		
		userResultsGPane.getChildren().addAll(resultUserStamp, resultNameTitle, resultManager, resultCreated, resultDescription, resultLoginScript, enableHB, resultPasswordExpired, 
				unlockHB, resultPhone, resultEmail, resultPWDLastSet, resultLocation, resultDN, managerLabel, loginScriptLabel, lockedOutLabel, phoneLabel, emailLabel, nameTitleLabel, 
				pwdLastSetLabel, isEnabledLabel, passwordExpiredLabel, createdLabel, descriptionLabel, locationLabel, changePasswordLabel, resetPasswordButton, dNameLabel, userGroupList);
		GridPane.setConstraints(resultUserStamp, 0, 0);
		GridPane.setColumnSpan(resultUserStamp, 2);
		GridPane.setConstraints(nameTitleLabel, 0, 1);
		GridPane.setConstraints(managerLabel, 0, 2);
		GridPane.setConstraints(locationLabel, 0, 3);
		GridPane.setConstraints(phoneLabel, 0, 4);
		GridPane.setConstraints(emailLabel, 0, 5);
		GridPane.setConstraints(descriptionLabel, 0, 6);
		GridPane.setConstraints(createdLabel, 0, 7);
		GridPane.setConstraints(loginScriptLabel, 0, 8);
		GridPane.setConstraints(lockedOutLabel, 0, 9);
		GridPane.setConstraints(isEnabledLabel, 0, 10);
		GridPane.setConstraints(pwdLastSetLabel, 0,11);
		GridPane.setConstraints(passwordExpiredLabel, 0,12);
		GridPane.setConstraints(changePasswordLabel, 0,13);
		GridPane.setConstraints(dNameLabel, 0,14);
		GridPane.setConstraints(userGroupList, 1,15);
			GridPane.setColumnSpan(userGroupList, 2);
		
		GridPane.setConstraints(resultNameTitle, 1, 1);
			GridPane.setColumnSpan(resultNameTitle, 1);
		GridPane.setConstraints(resultManager, 1, 2);
			GridPane.setColumnSpan(resultManager, 2);
		GridPane.setConstraints(resultLocation, 1, 3);
			GridPane.setColumnSpan(resultLocation, 2);
		GridPane.setConstraints(resultPhone, 1, 4);
			GridPane.setColumnSpan(resultPhone, 2);
		GridPane.setConstraints(resultEmail, 1, 5);
			GridPane.setColumnSpan(resultEmail, 2);	
		GridPane.setConstraints(resultDescription, 1, 6);
			GridPane.setColumnSpan(resultDescription, 2);
		GridPane.setConstraints(resultCreated, 1, 7);
			GridPane.setColumnSpan(resultCreated, 2);
		GridPane.setConstraints(resultLoginScript, 1, 8);
			GridPane.setColumnSpan(resultLoginScript, 2);
		GridPane.setConstraints(unlockHB, 1, 9);
			GridPane.setColumnSpan(unlockHB, 2);
		GridPane.setConstraints(enableHB, 1, 10);
			GridPane.setColumnSpan(enableHB, 2);
		GridPane.setConstraints(resultPWDLastSet, 1, 11);
			GridPane.setColumnSpan(resultPWDLastSet, 2);
		GridPane.setConstraints(resultPasswordExpired, 1, 12);
			GridPane.setColumnSpan(resultPasswordExpired, 2);
		GridPane.setConstraints(resetPasswordButton, 1, 13);
			GridPane.setColumnSpan(resetPasswordButton, 2);
			GridPane.setHalignment(resetPasswordButton, HPos.LEFT);
		GridPane.setConstraints(resultDN, 1, 14);
			GridPane.setColumnSpan(resultDN, 2);
			
		/****************************************
		 * COMPUTER RESULTS LAYOUT SETTINGS
		 *******************************************/
		computerResultsGPane.setVisible(false);
		//computerResultsGPane.setGridLinesVisible(true);
		
		lastLogonHB.getChildren().addAll(getLastLogonButton, lastLogonResult);
		
		rdpON = new RadioButton("ON");
		rdpOFF = new RadioButton("OFF");
		rdpLabel = new Label("Remote Desktop:");
		rdpHB.getChildren().addAll(rdpLabel, rdpON, rdpOFF);
		rdpHB.getStyleClass().add("rdp-HB");
		computerResultsGPane.getChildren().addAll(computerResultHB, computerCMDHB, lastLogonHB, computerLastLogon, getSysInfo, getMobileInfo, getNetworkAdapterInfo, computerCreated, computerCMDText,
												  operatingSystem, computerLastChanged, resultOS, resultComputerLastLogon, resultComputerCreated, resultComputerChanged, rdpHB);
		
		GridPane.setConstraints(computerResultHB, 1, 0);
			GridPane.setColumnSpan(computerResultHB, 5);
		GridPane.setConstraints(computerCMDHB, 1, 1);
			GridPane.setColumnSpan(computerCMDHB, 2);
		GridPane.setConstraints(rdpHB, 3, 1);
			//GridPane.setColumnSpan(rdpHB, 2);
		GridPane.setConstraints(lastLogonHB, 1, 2);
			GridPane.setColumnSpan(lastLogonHB, 3);
			GridPane.setMargin(lastLogonHB, new Insets(5,0,0,0));
		GridPane.setConstraints(getSysInfo, 1, 3);
			GridPane.setColumnSpan(getSysInfo, 3);
			GridPane.setMargin(getSysInfo, new Insets(5,50,0,0));
		GridPane.setConstraints(getMobileInfo, 3, 3);
		GridPane.setConstraints(getNetworkAdapterInfo, 1, 4);
		GridPane.setConstraints(operatingSystem, 1, 5);
		GridPane.setConstraints(computerLastLogon, 1, 6);
		GridPane.setConstraints(computerCreated, 1, 7);
		GridPane.setConstraints(computerLastChanged, 1, 8);
		GridPane.setConstraints(computerCMDText, 0, 9);
			GridPane.setColumnSpan(computerCMDText, 7);
		
		
		GridPane.setConstraints(resultOS, 2, 5);
		GridPane.setConstraints(resultComputerLastLogon, 2, 6);
		GridPane.setConstraints(resultComputerCreated, 2, 7);
		GridPane.setConstraints(resultComputerChanged, 2, 8);	
			
			computerCMDComboBox = new ComboBox<String>(FXCollections.observableArrayList("Ping -t", "Logoff", "Restart", "Shutdown", "SFC /Scannow", "Restart Spooler", "Open C$", "Open Computer Managment", "Open Remote Desktop", "Remote Registry ON"));
			computerCMDComboBox.getStyleClass().add("CMD-dropdown");
			computerCMDComboBox.setValue("Select a Command");
			computerCMDButton.getStyleClass().add("RUN-button");
		
			getLastLogonButton.getStyleClass().add("computer-button");
			getSysInfo.getStyleClass().add("sysinfo-button");
			getSysInfo.setTextAlignment(TextAlignment.LEFT);
			getNetworkAdapterInfo.getStyleClass().add("sysinfo-button");
			getNetworkAdapterInfo.setTextAlignment(TextAlignment.LEFT);
			getMobileInfo.getStyleClass().add("sysinfo-button");
			lastLogonResult.setVisible(false);
			
			computerCMDText.setEditable(false);
			computerCMDText.setMinSize(476, 275);
			computerCMDHB.getChildren().addAll(computerCMDComboBox, computerCMDButton);
			computerResultHB.getChildren().addAll(resultComputerStamp, statusCircle);
		
		
		/**********************************************
		 * PRINTER RESULTS LAYOUT SETTINGS
		 *********************************************/
		printerResultsGPane.setVisible(false);
		//printerResultsGPane.setGridLinesVisible(true);
		
		printerIPButton.getStyleClass().add("ip-button");
			printerIPButton.setAlignment(Pos.CENTER_LEFT);
		
		printerStampHBox.getChildren().addAll(resultPrinterStamp, printerIPButton, printerTimeStamp, printerStatusCircle);
		printerResultsGPane.getChildren().addAll(printerStampHBox, printerLocation, resultPrinterLocation, printerDriver, resultPrinterDriver, printerCreated, resultPrinterCreated,
												 printerDN, resultPrinterDN, resultPrinterStatus, resultPrinterJobs);
		
		GridPane.setConstraints(printerStampHBox, 0,0);
			GridPane.setColumnSpan(printerStampHBox, 3);
		GridPane.setConstraints(printerLocation, 0,1);
		GridPane.setConstraints(printerDriver, 0,2);
		GridPane.setConstraints(printerCreated, 0,3);
		GridPane.setConstraints(printerDN, 0,4);
		GridPane.setConstraints(resultPrinterStatus, 0,5);
			GridPane.setColumnSpan(resultPrinterStatus, 2);
		GridPane.setConstraints(resultPrinterJobs, 0,6);
			GridPane.setColumnSpan(resultPrinterJobs, 2);
		
		
		GridPane.setConstraints(resultPrinterLocation, 1,1);
		GridPane.setConstraints(resultPrinterDriver, 1,2);
		GridPane.setConstraints(resultPrinterCreated, 1,3);
		GridPane.setConstraints(resultPrinterDN, 1,4);
		/************************************************
		 * MAIN SCENE LAYOUT SETTINGS
		 ************************************************/
		//mainGPane.getChildren().addAll(searchHB, scrolly, userResultsGPane, computerResultsGPane, printerResultsGPane);
		
		mainGPane.getChildren().addAll(searchHB, mainSplitPane);
		mainGPane.getStyleClass().add("resultGPane");
		Pane pane = new Pane();
		pane.getChildren().addAll(userResultsGPane, computerResultsGPane, printerResultsGPane);
		mainSplitPane.getItems().addAll(scrolly, pane);
		SplitPane.setResizableWithParent(scrolly, true);
		
		
		
		mainSplitPane.getStyleClass().add("-fx-background-color: transparent;");
		mainSplitPane.setDividerPosition(0, 0.23);
		//mainGPane.setGridLinesVisible(true);
		
		GridPane.setConstraints(searchHB, 0, 0);
		GridPane.setColumnSpan(searchHB, 3);
		GridPane.setConstraints(mainSplitPane, 0, 1);
		GridPane.setColumnSpan(mainSplitPane, 3);
		/*
		GridPane.setConstraints(scrolly, 0, 1);
		GridPane.setConstraints(userResultsGPane, 1, 1);
		GridPane.setMargin(userResultsGPane, new Insets(5,0,0,10));
		GridPane.setConstraints(computerResultsGPane, 1, 1);
		GridPane.setMargin(computerResultsGPane, new Insets(5,0,0,10));
		GridPane.setConstraints(printerResultsGPane, 1, 1);
		GridPane.setMargin(printerResultsGPane, new Insets(5,0,0,10));
		*/
		searchHB.getStyleClass().add("searchHB");
		GridPane.setMargin(searchHB, new Insets(5,0,0,0));
		searchScene = new Scene(mainGPane, 850,664);
		
		
		searchTF.setOnKeyPressed(ke -> {
			KeyCode keyCode = ke.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				searchButton.fire();
			}
		});
		
		searchButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			
			lastLogonResult.setVisible(false);
			String param = searchTF.getText();
			
			ArrayList<String> usercmds = new ArrayList<>();
			ArrayList<String> computercmds = new ArrayList<>();
			ArrayList<String> printercmds = new ArrayList<>();
			
			userArray.clear();
			computerArray.clear();
			printerArray.clear();
			
			userResultVB.getChildren().clear();
			computerResultVB.getChildren().clear();
			printerResultVB.getChildren().clear();
			
			userResultVB.getChildren().addAll(adUserlabel);
			computerResultVB.getChildren().addAll(adComputerlabel);
			printerResultVB.getChildren().addAll(adPrinterlabel);
			
			ArrayList<String> cmdOutput = new ArrayList<>();
			ArrayList<String> listOfObjects = new ArrayList<>();
			
			adUserlabel.setText("AD Users (0)");
			adComputerlabel.setText("AD Computers (0)");
	        adPrinterlabel.setText("AD Printers (0)");
			
			
			try {
				
			//Get AD User Results
				
				if(userSearch.isSelected() == true) {
					
					usercmds.add("Get-ADUser -Filter 'Name -like " + "''*" + param + "*''' | Format-table SamAccountName -A");
					usercmds.add("Get-ADUser -Filter 'SamAccountName -like " + "''*" + param + "*''' | Format-table SamAccountName -A"); 
				cmdOutput = removeDuplicates(powershellRunCommands(usercmds));
				String getChar = null;
				
				if(!cmdOutput.isEmpty()) {
				 getChar = cmdOutput.get(0);
				}else { getChar = "";}
				
				for(String output : cmdOutput) {
					
					if((output.contains("SamAccountName")) || (output.contains("--------------")) || (output.equals(getChar))) {
					}else {
						output = output.replaceAll("\\s+", "");
						listOfObjects.add(output);}
				}
				
				adUserlabel.setText("AD Users " + "("+listOfObjects.size()+")");
				System.out.println("Users " + "("+listOfObjects.size()+")");
				java.util.Collections.sort(listOfObjects);
				
				for(String output : listOfObjects) {
					userArray.add(new User(output, new Button(output)));
				}
				
					for(User user : userArray) {
					
					userResultVB.getChildren().add(user.getUserButton());
					user.getUserButton().getStyleClass().add("user-button");
					user.getUserButton().setAlignment(Pos.CENTER_LEFT);
					
					userButton(user);
					
					if(userArray.size() == 1) {
						user.getUserButton().fire();
					}
					}
				}
				//Get AD Computer Results
				if(computerSearch.isSelected() == true) {
					computercmds.add("Get-ADComputer -Filter 'SamAccountName -like " + "''*" + param + "*''' | Format-table Name -A");
				cmdOutput.clear();
				listOfObjects.clear();
				cmdOutput = removeDuplicates(powershellRunCommands(computercmds));
				String getChar = null;
				
				if(!cmdOutput.isEmpty()) {
					 getChar = cmdOutput.get(0);
					}else { getChar = "";}
				
				for(String output : cmdOutput) {
					System.out.println(output);
					if((output.contains("Name")) || (output.contains("----")) || (output.equals(getChar))) {
					}else {
						output = output.replaceAll("\\s+", "");
						listOfObjects.add(output);}
				}
				
				adComputerlabel.setText("AD Computers " + "("+listOfObjects.size()+")");
				System.out.println("Computers " + "("+listOfObjects.size()+")");
				java.util.Collections.sort(listOfObjects);
				
				for(String output : listOfObjects) {
					computerArray.add(new Computer(output, new Button(output)));
					//System.out.println(output);
				}
				
				
				for(Computer computer : computerArray) {
					
					computerResultVB.getChildren().add(computer.getComputerButton());
					computer.getComputerButton().getStyleClass().add("user-button");
					computer.getComputerButton().setAlignment(Pos.CENTER_LEFT);
					
					computerButton(computer);
					
					if(computerArray.size() == 1) {
						computer.getComputerButton().fire();
					}
					
				}
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
				System.out.println("SQLException: "+e1.getMessage());
			}
			
			if(printerSearch.isSelected() == true) {
				printercmds.add("Get-ADObject -Filter 'printername -like " + "''*" + param + "*''' -Properties printername | select printername | Format-Table -Property * ");
				
				cmdOutput.clear();
				listOfObjects.clear();
				
				try {
					cmdOutput = removeDuplicates(powershellRunCommands(printercmds));
					
					String getChar = null;
					
					if(!cmdOutput.isEmpty()) {
						 getChar = cmdOutput.get(0);
						}else { getChar = "";}
					
					for(String output : cmdOutput) {
						System.out.println(output);
						if((output.contains("printername")) || (output.contains("----")) || (output.equals(getChar))) {
						}else {
							output = output.replaceAll("\\s+", "");
							listOfObjects.add(output);}
					}
						adPrinterlabel.setText("AD Printers " + "("+listOfObjects.size()+")");
						System.out.println("Printers " + "("+listOfObjects.size()+")");
						java.util.Collections.sort(listOfObjects);
						
						for(String output : listOfObjects) {
							printerArray.add(new Printer(output, new Button(output)));
							//System.out.println(output);
						}
						
						
						for(Printer printer : printerArray) {
							
							printerResultVB.getChildren().add(printer.getPrinterButton());
							printer.getPrinterButton().getStyleClass().add("user-button");
							printer.getPrinterButton().setAlignment(Pos.CENTER_LEFT);
							
							printerButton(printer);
							
							if(printerArray.size() == 1) {
								printer.getPrinterButton().fire();
							}
							
						}
						
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		
		unlockButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			
			String cmds = "Search-ADAccount -LockedOut | Unlock-ADAccount";
			try {
				powershellExecute(false,cmds);
				Thread.sleep(500);
				activeUser.getUserButton().fire();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		computerCMDButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			
			try {
				computerCMDRun(computerCMDComboBox.getValue());
			} catch (InterruptedException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		getLastLogonButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			ArrayList<String> resultList = new ArrayList<>();
			
			if (statusCircle.getFill().equals(javafx.scene.paint.Color.GREEN)) {
			try {
				resultList = powershellRunCommand("Get-ChildItem \"\\\\"+ activeComputer.getComputerName() +"\\c$\\Users\" | Sort-Object LastWriteTime -Descending | Select-Object Name -first 1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(resultList.contains(" Cannot find path ")) {
			
			}else {
			resultList = removeDuplicates(resultList);
			
			User user = new User(resultList.get(resultList.size()-1), new Button(resultList.get(resultList.size()-1)));
			
			activeUser = user;
			try {
				userButton(user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			lastLogonResult.setVisible(true);
			lastLogonResult.setText(resultList.get(resultList.size()-1));
			lastLogonResult.getStyleClass().add("user-button");
			}
			}else {Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Computer is Offline");
			alert.setHeaderText("The computer is offline");
			alert.setContentText("The computer you are trying to reach is offline.");
			alert.showAndWait();
					};
			
		});
		
		lastLogonResult.addEventHandler(ActionEvent.ACTION, (e) -> {
			activeUser.getUserButton().fire();
			lastLogonResult.setVisible(false);
		});
		
		resetPasswordButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			TextInputDialog enterPasswordDialog1 = new TextInputDialog();
			TextInputDialog enterPasswordDialog2 = new TextInputDialog();
			
			enterPasswordDialog1.setTitle("Password Reset");
			enterPasswordDialog1.setHeaderText("Resetting Password for " + activeUser.getUserName() );
			enterPasswordDialog1.setContentText("Please enter a password:");
			
			
			
			Optional<String> result1 = enterPasswordDialog1.showAndWait();
			
			
			if (result1.isPresent() && (result1.get().length()>7) && (checkString(result1.get()) == true)) {
				enterPasswordDialog2.setTitle("Password Confirm");
				enterPasswordDialog2.setHeaderText("Confirming Password for " + activeUser.getUserName());
				enterPasswordDialog2.setContentText("Please confirm the password:");
				Optional<String> result2 = enterPasswordDialog2.showAndWait();
				if(result1.get().equals(result2.get())){
					
					
					try {
					//System.out.println("Set-ADAccountPassword " + activeUser.getUserName() + " -NewPassword \"" + result1.get() + "\" –Reset");
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Password Reset Succes");
					alert.setHeaderText("The password reset was a great success!" );
					alert.setContentText("Should " + activeUser.getUserName() + " be prompted to choose a new password?");

					ButtonType buttonTypeOne = new ButtonType("Yes");
					ButtonType buttonTypeTwo = new ButtonType("No");
					alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
					
					powershellExecute(false,"Set-ADAccountPassword " + activeUser.getUserName() + " -Reset -NewPassword (ConvertTo-SecureString –String ‘" + result1.get() + "’ –AsPlainText –Force) ");
						Optional<ButtonType> result = alert.showAndWait();
						
						if(result.get() == buttonTypeOne) {
							powershellExecute(false,"Get-ADUser -Identity " + activeUser.getUserName() + " | Set-ADUser -ChangePasswordAtLogon:$true" );
							Thread.sleep(500);
							activeUser.getUserButton().fire();
						}else {powershellExecute(false,"Get-ADUser -Identity " + activeUser.getUserName() + " | Set-ADUser -ChangePasswordAtLogon:$false" );
							   Thread.sleep(500);
							   activeUser.getUserButton().fire();}
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					
					
					
					System.out.println("Resetting password....");
					
					
					}
				}else {Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Password Error");
				alert.setHeaderText("Issue with chosen password");
				alert.setContentText("The passwords did not match.");
				
				alert.showAndWait();}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Password Error");
				alert.setHeaderText("There was an issue with the chosen password");
				alert.setContentText("The selected password does not meet password complexity requirements");
				
				alert.showAndWait();
			}
			
		});
		
		enableDisableButton.addEventHandler(ActionEvent.ACTION, (e) -> {
			if(enableDisableButton.getText().equals("enable")) {
			try {
				powershellExecute(false,"Enable-ADAccount " + activeUser.getUserName());
				
				Thread.sleep(500);
				activeUser.getUserButton().fire();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else if(enableDisableButton.getText().equals("disable")) {
				try {
					powershellExecute(false,"Disable-ADAccount " + activeUser.getUserName());
					
					Thread.sleep(500);
					activeUser.getUserButton().fire();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				}
		});
		
		
		getSysInfo.addEventHandler(ActionEvent.ACTION, (e) -> {
			computerCMDText.setFont(javafx.scene.text.Font.font("Verdana", 12));
			
			if(statusCircle.getFill().equals(javafx.scene.paint.Color.GREEN)) {
				ArrayList<String> output = new ArrayList<String>();
				computerCMDText.clear(); 
				output = removeDuplicates(activeComputer.getSystemInfo());
				
				for(String line : output) {
					System.out.println(line);
					if(line.contains("Manufacturer")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setManufacturer(line);
					}
					if(line.contains("Model")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setModel(line);
					}
					if(line.contains("SerialNumber")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setSerialNumber(line);
					}
					if(line.contains("Name")) {
						if(line.contains("Core")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setCpu(line);
						}
					}
					if(line.contains("Size ")) {
						System.out.println(line);
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setDriveCapacity(Float.parseFloat(line)/1000000000);
						System.out.println(line);
					}
					if(line.contains("FreeSpace")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setDriveSpace(Float.parseFloat(line)/1000000000);
					}
					if(line.contains("TotalPhysicalMemory")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setRam(Float.parseFloat(line)/1000000000);
					}
					if(line.contains("FreePhysicalMemory")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setFreeRam(Float.parseFloat(line)/1000000);
					}
					if(line.contains("LastBootUpTime")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setLastReboot(convertTimestamp(line));
					}
					if(line.contains("LoadPercentage")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setCpuLoad(line);
					}
					if(line.contains("UserName")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setCurrentLoggedinUser(line);
					}
					if(line.contains("CurrentClockSpeed")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setCurrentClockSpeed(line);
					}
					if(line.contains("MaxClockSpeed")) {
						line = line.substring(line.indexOf(":")+2, line.length());
						activeComputer.setMaxClockSpeed(line);
					}
					
					 //System.out.println(line);
					 
				 }
				
				float a = activeComputer.getDriveCapacity();
				float b = activeComputer.getDriveSpace();
				float hdfreepercent = (b / a) * 100 ;
				
				float totalram = activeComputer.getRam();
				float freeram = activeComputer.getFreeRam();
				float ramfreepercent = (freeram / totalram) * 100;
				
				
				computerCMDText.appendText("SYSTEM INFO FOR: " + activeComputer.getComputerName() + " as of " + timestamp.getTimestamp() + "\n");
				computerCMDText.appendText("------------------------------------------------------------------------------------\n");
				computerCMDText.appendText("MANUFACTURER:	" + activeComputer.getManufacturer() + "\n");
				computerCMDText.appendText("MODEL:			" + activeComputer.getModel() + "\n");
				computerCMDText.appendText("SERIAL NUMBER:	" + activeComputer.getSerialNumber() + "\n\n");
				computerCMDText.appendText("CPU:				" + activeComputer.getCpu() + "\n");
				computerCMDText.appendText("CPU LOAD %:		" + activeComputer.getCpuLoad() + "%\n");
				computerCMDText.appendText("CLOCKSPEED:		" + activeComputer.getCurrentClockSpeed() + "/" + activeComputer.getMaxClockSpeed() + "\n\n");
				computerCMDText.appendText("HDD CAPACITY:		" + String.format("%.2f", activeComputer.getDriveCapacity()) + " GB" + "\n");
				computerCMDText.appendText("HDD SPACE:		" + Float.toString(hdfreepercent).substring(0, 5) + "% FREE (" + String.format("%.2f",activeComputer.getDriveSpace()) + " GB)\n\n" );
				computerCMDText.appendText("RAM:				" + String.format("%.2f",activeComputer.getRam()) + "GB\n");
				computerCMDText.appendText("RAM FREE:			" + Float.toString(ramfreepercent).substring(0, 5) + "% FREE (" + String.format("%.2f",activeComputer.getFreeRam()) + " GB)\n\n" );
				computerCMDText.appendText("Last Reboot:    		" + activeComputer.getLastReboot() + "\n");
				computerCMDText.appendText("Logged in User:    	" + activeComputer.getCurrentLoggedinUser()); 
				 
			}else {Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Computer is Offline");
			alert.setHeaderText("The computer is offline");
			alert.setContentText("The computer you are trying to reach is offline.");
			alert.showAndWait();
					};
		});
		
		/**********************************************
		 * Mobile info button on Computer Scene
		 *********************************************/
		getMobileInfo.addEventHandler(ActionEvent.ACTION, (e) -> { 
			ArrayList<String> output = new ArrayList<String>();
			//computerCMDText.clear();

			//activeComputer.getMobileInfo();
			output = activeComputer.getMobileInfoTest();
			
			for(String line : output) {
				System.out.println(line);
			}
		});
		
		/*************************************************
		 * Email address button opens new email in default
		 * email client
		 * User Scene
		 *************************************************/
		resultEmail.addEventHandler(ActionEvent.ACTION, (e) -> { 
			Desktop desktop = Desktop.getDesktop();
			String message = "mailto:" + activeUser.getEmail();// + "?subject=First%20Email";
			URI uri = URI.create(message);
			try {
				desktop.mail(uri);
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		/**************************************************
		 * Printer IP address opens in default web browser
		 * Printer Scene
		 *************************************************/
		printerIPButton.addEventHandler(ActionEvent.ACTION, (e) -> { 
			Desktop desktop = Desktop.getDesktop();
			
			try {
					desktop.browse(new URI("http://" + activePrinter.getIpAddress()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					System.out.println("URL Syntax is not good.");
					e1.printStackTrace();
				}
			});
		
		/*************************************************
		 * Radio button toggles remote desktop ON
		 * Computer scene
		 * ***********************************************/
		
		rdpON.addEventHandler(ActionEvent.ACTION, (e) -> {
			rdpOFF.setSelected(false);
			rdpON.setSelected(true);	
			
			activeComputer.rdpON();
			
		});
		
		/*************************************************
		 * Radio button toggles remote desktop OFF
		 * Computer scene
		 * ***********************************************/
		rdpOFF.addEventHandler(ActionEvent.ACTION, (e) -> { 
	
		
			rdpON.setSelected(false);	
			rdpOFF.setSelected(true);	
			
			activeComputer.rdpOFF();
			
		});
		
		/******************************************************
		 * Network Adapter Info button
		 * Computer Scene
		 ******************************************************/
		getNetworkAdapterInfo.addEventHandler(ActionEvent.ACTION, (e) -> { 
			computerCMDText.clear();
			for(String output : activeComputer.getNetworkAdapterInfo()) {
				computerCMDText.appendText(output + "\n");
			};
		});
		
		/*********************************************
		 * Goes back to previously viewed object
		 * Button next to search bar
		 * *******************************************/
		backButton.addEventHandler(ActionEvent.ACTION, (e) -> { 
			Button nextButton;
			
			if(!backArray.isEmpty() == true) {
			nextButton = backArray.pop();
			System.out.println("BackArray removed: " + nextButton.getText());
			forwardArray.push(nextButton);
			System.out.println("ForwardArray added: " + nextButton.getText());
			}
			if(!backArray.isEmpty() == true) {
				backArray.peek().fire();
				
				}
			if(forwardArray.isEmpty() == true) {
				 forwardButton.setDisable(true);
			 }else {forwardButton.setDisable(false);}
		});
		
		/***************************************************
		 * Goes forward to object before hitting Back button
		 * Button next to search bar
		 * *************************************************/
		forwardButton.addEventHandler(ActionEvent.ACTION, (e) -> { 
			Button nextButton;
			
			if(!forwardArray.isEmpty() == true) {
				forwardArray.peek().fire();
				nextButton = forwardArray.pop();
				System.out.println("Forward Array removed: " + nextButton.getText());
				if(!backArray.isEmpty() == true) {
					if(nextButton != backArray.peek()) {
					
					backArray.push(nextButton);
					System.out.println("BackArray added: " + nextButton.getText());
					}
				}
				}
			if(forwardArray.isEmpty() == true) {
				 forwardButton.setDisable(true);
			 }else {forwardButton.setDisable(false);}
			});
		
		/*userGroupList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		        	listView.setItems(FXCollections.observableArrayList(activeUser.getGroups()));
		        }
		    } 
		    });*/
		
		userGroupList.expandedProperty().addListener((obs, oldVal,  newVal) -> {
			
			if(listView.isVisible() == false) {
				
				ArrayList<String> groups = new ArrayList<>();
				groups = activeUser.getGroups();
				
				listView.setItems(FXCollections.observableArrayList(groups));
				listView.setVisible(true);
			}
		});
		
		
		
		searchScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		
		primaryStage.setScene(searchScene);
		
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			double textAreaWidth = computerCMDText.getWidth();
			double stageWidth = primaryStage.getWidth();
			double percentageWidth = textAreaWidth / stageWidth * 100;
			
			 System.out.println( "TextArea is " + String.format("%.2f", percentageWidth)  + "% of Stage Width: " + primaryStage.getWidth());
			 
			 if(percentageWidth != 70.00) {
				 computerCMDText.setMinWidth(computerCMDText.getWidth() + (((70.00 - percentageWidth) / 100)*stageWidth));
			 }
		});
		
		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			double textAreaHeight = computerCMDText.getHeight();
			double stageHeight = primaryStage.getHeight();
			double percentageHeight = textAreaHeight / stageHeight * 100;
			
			 System.out.println( "TextArea is " + String.format("%.2f", percentageHeight)  + "% of Stage Height: " + primaryStage.getHeight());
			 
			 if(percentageHeight != 39.00) {
				 computerCMDText.setMinHeight(computerCMDText.getHeight() + (((39.00 - percentageHeight) / 100)*stageHeight));
				 
				
			 }
			 
			// mainGPane.setMinHeight(primaryStage.getHeight());
			 
			// adResultList.setMinHeight(mainGPane.getHeight());
			 
			// scrolly.setMinHeight(adResultList.getHeight());
		});
		
		
		userResultsGPane.visibleProperty().addListener((obs, oldVal, newVal) -> {
			
			primaryStage.setTitle("Active Directory Assitant - " + activeUser.getUserName().toUpperCase());
		});
		
		computerResultsGPane.visibleProperty().addListener((obs, oldVal, newVal) -> {
			
			primaryStage.setTitle("Active Directory Assitant - " + activeComputer.getComputerName().toUpperCase());
		});
		
		
		primaryStage.getIcons().add(new Image("file:blue.png"));
		primaryStage.setTitle("Active Directory Assistant");
		
		primaryStage.show();
	}
}
