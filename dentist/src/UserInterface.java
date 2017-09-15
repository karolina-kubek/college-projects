//********************************************************************
//Karolina Kubek R00143325
//DNET2
//
//For testing purpose:
//Username: Doctor Strange
//Password: 1234
//
//I will clean up the code for the next submission.
//For now the GUI an basic functionality is done.
// No saving and no input validation.
//*******************************************************************
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserInterface extends Application {

	private Stage window;
	private ArrayList<Dentist> dentistList = new ArrayList<Dentist>();
	private ArrayList<Procedure> procList = new ArrayList<Procedure>();
	private Dentist loggedInDentist;
	private GridPane mainContent;
	private int currentIndex;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Patient selectedPatient;

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    	// populate the database
    	dentistList.add(new Dentist("Doctor Lilly Bean", "Cork", "mypass"));
    	dentistList.get(0).addPatient(new Patient("John Doe", "Unknown"));
    	dentistList.add(new Dentist("Doctor Strange", "Cork", "1234"));
    	dentistList.get(1).addPatient(new Patient("Tito Puente", "Hawaii"));
    	dentistList.get(1).addPatient(new Patient("Bill Murray", "Dublin"));
    	dentistList.get(1).addInvoice(1, new Invoice(new Date()));
    	dentistList.get(1).addInvoice(1, new Invoice(new Date()));
    	dentistList.get(1).addInvoice(1, new Invoice(new Date()));
    	// dentistList.get(1).getPatientList().get(1).getInvoiceList().get(1).addPayment(new Payment(300.0 , (new Date()));
    	procList.add(new Procedure("Crown", 250));
    	procList.add(new Procedure("Filling", 150));
    	procList.add(new Procedure("Amalgam", 200));
    	procList.add(new Procedure("Scale and Polish", 50));
    	procList.add(new Procedure("Whitening", 100));

        window = primaryStage;
        login();
        // this method call is just for testing. Change back to login once finished testing
        //dentistMain(dentistList.get(1));
        window.show();
    }

    private void login() {
    	window.setTitle("Dental System Login");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #fffdf7;");

        Text scenetitle = new Text("Welcome!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Text scenetitle2 = new Text("Login");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle2, 0, 0, 2, 6);

        grid.add(new Label("User Name:"), 0, 5);

        TextField username = new TextField();
        grid.add(username, 1, 5);

        grid.add(new Label("Password:"), 0, 6);

        PasswordField password = new PasswordField();
        grid.add(password, 1, 6);

        Button loginButton = new Button("Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 1, 8);

        final Text errorOutput = new Text();
        grid.add(errorOutput, 1, 10);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	int i;
            	for(i = 0; i < dentistList.size(); i++) {
            		if(dentistList.get(i).getName().compareTo(username.getText()) == 0 && dentistList.get(i).authenticate(password.getText())) {
            			break;
            		}
            	}
            	if(i < dentistList.size()) {
            		dentistMain(dentistList.get(i));
            	} else {
	                errorOutput.setFill(Color.FIREBRICK);
	                errorOutput.setText("Invalid username or password.");
            	}
//                dentistMain(dentistList.get(0));
            }
        });

        window.setScene(new Scene(grid, 800, 600));
    }

    private void dentistMain(Dentist dentist) {
    	loggedInDentist = dentist;
    	window.setTitle("Dental System");

    	BorderPane border = new BorderPane();

    	// top bar
    	HBox topMenu = new HBox();
    	topMenu.setPadding(new Insets(10));
    	topMenu.setSpacing(10);
    	topMenu.setStyle("-fx-background-color: #e07f00;");
    	border.setTop(topMenu);

    	// add the logged in status to the left of the HBox (the VBox is used for alignment of the title)
    	VBox loggedInStatusArea = new VBox();
    	Text loggedInTitle = new Text("Logged in as: "+dentist.getName());
    	loggedInTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
    	loggedInTitle.setFill(Color.WHITE);
    	loggedInStatusArea.getChildren().addAll(loggedInTitle);
    	loggedInStatusArea.setAlignment(Pos.CENTER_LEFT);
        topMenu.getChildren().add(loggedInStatusArea);

        // add the logout button to the right of the HBox (the VBox is used for alignment of the button)
        HBox logoutArea = new HBox();
        Button saveButton = new Button(" Save ");
        // TODO: Event for saving information
        Text space = new Text("\t");
        Button logoutButton = new Button(" Exit ");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: save the data from the current session to disk
                window.close();
            }
        });
        logoutArea.getChildren().addAll(saveButton, space, logoutButton);
        logoutArea.setAlignment(Pos.CENTER_RIGHT);
        topMenu.getChildren().add(logoutArea);
        HBox.setHgrow(logoutArea, Priority.ALWAYS);

        // VBox side menu
        VBox sideMenu = new VBox();
        sideMenu.setPadding(new Insets(20));
        sideMenu.setSpacing(8);
        sideMenu.setStyle("-fx-background-color: #fcb353;");
        ToggleButton options[] = new ToggleButton[] {
            new ToggleButton("Patients"),
            new ToggleButton("Procedures")
        };

        // create a general Pane to be filled with another depending on what menu option is selected
        mainContent = new GridPane();
    	border.setCenter(mainContent);

        final ToggleGroup group = new ToggleGroup();

        for(int i = 0; i < options.length; i++) {
        	options[i].setToggleGroup(group);
        	options[i].setMinWidth(78);
        	options[i].setAlignment(Pos.CENTER_LEFT);
            VBox.setMargin(options[i], new Insets(0, 0, 15, 0));
            options[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    changeMainPane(((ToggleButton) e.getSource()).getText(), 0);
                }
            });
            sideMenu.getChildren().add(options[i]);
        }
        border.setLeft(sideMenu);

    	// set the default pane
        changeMainPane("Patients", 0);

        window.setScene(new Scene(border, 800, 600));
    }


    private void changeMainPane(String option, int index) {

    	currentIndex = index;

    	if(mainContent.getChildren().size() > 0) {
			mainContent.getChildren().remove(mainContent.getChildren().get(mainContent.getChildren().size() - 1));
		}
    	GridPane activePane = new GridPane();
    	switch(option) {
	    	case "Edit Invoice": {
	    		currentIndex = index;
	    		ArrayList<Invoice> invoices = selectedPatient.getInvoiceList();

	    		VBox invoiceEditBox = new VBox();

	    		Button goBack = new Button("< Go Back");
    			goBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	changeMainPane("View Patient", currentIndex);
                    }
                });
    			Text space = new Text("\n");
    			Text space2 = new Text("\n");
    			Text invPatTitle = new Text("Patient Name: "+selectedPatient.getName());
    			invPatTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
    			VBox returnBox = new VBox();
    			returnBox.getChildren().addAll(goBack, space, space2, invPatTitle);
    			Text invoiceID = new Text("\nInvoice ID: "+invoices.get(index).getInvoiceNumber());
    			invoiceID.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
    			Text invoiceDate = new Text("\nDate: "+dateFormat.format(invoices.get(index).getInvoiceDate()));
    			invoiceDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    			invoiceEditBox.getChildren().addAll(returnBox, invoiceID, invoiceDate);

	    		 GridPane procedureGrid = new GridPane();
	    		 procedureGrid.setHgap(30);
	    		 procedureGrid.setVgap(10);
	    		 procedureGrid.setPadding(new Insets(20, 10, 10, 10));
	    		 procedureGrid.setStyle("-fx-background-color: #ededed;");

	    		 // Procedure number column 1, row 1
	    		    Text procedureID = new Text("Procedure ID");
	    		    procedureID.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    procedureGrid.add(procedureID, 0, 0);


	    		 // Procedure name column 2, row 1
	    		    Text procedure = new Text("Description");
	    		    procedure.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    procedureGrid.add(procedure, 1, 0);


	       		 // Procedure cost column 3, row 1
	    		    Text procedureCost = new Text("Cost");
	    		    procedureCost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    procedureGrid.add(procedureCost, 2, 0);

	    		    Text action = new Text(" \t\t\t ");
	    		    procedureGrid.add(action, 3, 0);

	    		    Button deleteProcedure;


	    		    for(int i = 0; i < invoices.get(index).getProcedureList().size(); i++) {
	    		    	procedureGrid.add(new Text(Integer.toString(invoices.get(index).getProcedureList().get(i).getProcNumber())), 0, i+1);
	    		    	procedureGrid.add(new Text(invoices.get(index).getProcedureList().get(i).getProcName()), 1, i+1);
	    		    	procedureGrid.add(new Text(Double.toString(invoices.get(index).getProcedureList().get(i).getProcCost())), 2, i+1);
	    		    	deleteProcedure = new Button("Delete");
	    		    	deleteProcedure.setUserData(i);
	    		    	deleteProcedure.setOnAction(new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent e) {
	                        	loggedInDentist.getPatientList().get(currentIndex).getInvoiceList().get(index).getProcedureList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
	                        	changeMainPane("Edit Patient", currentIndex);
	                        }
	                    });
	    		    	procedureGrid.add(deleteProcedure, 3, i+1);
	    		    }

	    		    VBox addProcedureBox = new VBox();
	    		    addProcedureBox.setPadding(new Insets(10, 0, 0, 0));

	    		    VBox addProcedureBoxInner = new VBox();
	    		    addProcedureBoxInner.setPadding(new Insets(15, 15, 15, 15));
	    		    addProcedureBoxInner.setStyle("-fx-background-color: #ffdfb7;");

	    		    Text newProc = new Text("Add a procedure to the invoice\n");
	    		    newProc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));


	    		    ObservableList<String> options = FXCollections.observableArrayList();
    	            for(int i = 0; i < procList.size(); i++){
    	            	options.add(procList.get(i).getProcName());
    	            }
    	            ComboBox<String> proceduresComboBox = new ComboBox<String>(options);

    	            VBox viewPaymentBox = new VBox();
    	            viewPaymentBox.setPadding(new Insets(30, 0, 0, 80));

    	            Button viewPayment = new Button("View Payments");
    	            viewPaymentBox.getChildren().add(viewPayment);

    	            viewPayment.setUserData(index);
    	            viewPayment.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	changeMainPane("Payments", Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                        }
                    });

	    		    Button addProcedure = new Button("+ Add Procedure");
	    		    addProcedure.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	int i;
	                    	for(i = 0; i < procList.size(); i++) {
	                    		if(proceduresComboBox.getValue() == procList.get(i).getProcName()) {
	                    			invoices.get(index).addProcedure(procList.get(i));
	                    			break;
	                    		}
	                    	}
	                    	if(i < procList.size()) {
	                    		changeMainPane("Edit Invoice", currentIndex);
	                    	}
	                    }
	                });

	    		    addProcedureBoxInner.getChildren().addAll(newProc, proceduresComboBox, space, addProcedure);
	    		    addProcedureBox.getChildren().addAll(addProcedureBoxInner, viewPaymentBox);
	    		    HBox summary = new HBox();
	    		    Text paymentTotal = new Text("Total: "+invoices.get(index).getInvoiceAmount());
	    		    paymentTotal.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

	    		    String paidORnot;
	    		    if(invoices.get(index).isPaid()){
	    		    	paidORnot = "Paid";
	    		    } else {
	    		    	paidORnot = "Not Paid";
	    		    }
	    		    Text paidStatus = new Text("\tStatus: "+paidORnot);
	    		    paidStatus.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
	    		    summary.getChildren().addAll(paymentTotal, paidStatus);
	    		    summary.setPadding(new Insets(10, 10, 10, 10));

	    		    GridPane totalGrid = new GridPane();
	    		    totalGrid.setHgap(30);
	    		    totalGrid.setVgap(10);
	    		    totalGrid.add(procedureGrid, 0, 0);
	    		    totalGrid.add(summary, 0, 1);

	    		    invoiceEditBox.getChildren().add(totalGrid);
	    		    HBox setOfTwo = new HBox();
	    		    setOfTwo.getChildren().addAll(invoiceEditBox, addProcedureBox);
	    		    activePane.getChildren().addAll(setOfTwo);

	    		break;
	    	}
    		case "Patients":
    		default: {
    			GridPane patgrid = new GridPane();
    			patgrid.setHgap(15);
    			patgrid.setVgap(10);
    			patgrid.setPadding(new Insets(10, 10, 0, 10));
    			patgrid.setStyle("-fx-background-color: #ededed;");

    		 // Procedure number column 1, row 1
    		    Text patid = new Text("ID");
    		    patid.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    patgrid.add(patid, 0, 0);

    		    Text patName = new Text("Patient Name");
    		    patName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    patgrid.add(patName, 1, 0);

    		    Text patAddr = new Text("Address");
    		    patAddr.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    patgrid.add(patAddr, 2, 0);

    		    Button viewPatient, removePatient;
    		    for(int i = 0; i < loggedInDentist.getPatientList().size(); i++){
    		    	int patientID = i + 1;
    		    	patgrid.add(new Text(Integer.toString(loggedInDentist.getPatientList().get(i).getPatientNumber())), 0, patientID);
    		    	patgrid.add(new Text(loggedInDentist.getPatientList().get(i).getName()), 1, patientID);
    		    	patgrid.add(new Text(loggedInDentist.getPatientList().get(i).getAddress()), 2, patientID);
    		    	viewPatient = new Button("View");
    		    	patgrid.add(viewPatient, 3, patientID);
    		    	viewPatient.setUserData(i);
    		    	viewPatient.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	changeMainPane("View Patient", Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                        }
                    });
    		    	removePatient = new Button("Remove");
    		    	patgrid.add(removePatient, 4, patientID);
    		    	removePatient.setUserData(i);
    		    	removePatient.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	loggedInDentist.getPatientList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                        	// reload the patient screen
                        	changeMainPane("Patients", 0);
                        }
                    });
    		    }

    		    VBox newPatBox = new VBox();
    		    newPatBox.setPadding(new Insets(10, 0, 0, 30));

    		    GridPane newPatGrid = new GridPane();
    		    newPatGrid.setHgap(10);
    		    newPatGrid.setVgap(10);
    		    newPatGrid.setPadding(new Insets(10, 10, 10, 10));
    		    newPatGrid.setStyle("-fx-background-color: #ffdfb7;");


    		 // New procedure label and input field number column 1, row 1
    		    Text newPat = new Text("New Patient");
    		    newPat.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    newPatGrid.add(newPat, 0, 0);

    		    Text newPatName = new Text("Name:");
    		    newPatName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    		    newPatGrid.add(newPatName, 0, 1);

    		    TextField textFieldPatName = new TextField ();
    		    newPatGrid.add(textFieldPatName, 1, 1);

    		    Text newPatAddr = new Text("Address:");
    		    newPatAddr.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    		    newPatGrid.add(newPatAddr, 0, 2);

    		    TextField textFieldPatAddr = new TextField ();
    		    newPatGrid.add(textFieldPatAddr, 1, 2);

    		  // Defining the Submit button
    		    Button submitNewPatient = new Button("+ Create New Patient");
    		    newPatGrid.add(submitNewPatient, 1, 3);
    		    submitNewPatient.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	// put an exception on this to catch the parseDouble failing
                    	loggedInDentist.addPatient(new Patient(textFieldPatName.getText(), textFieldPatAddr.getText()));
                    	textFieldPatName.setText("");
                    	textFieldPatAddr.setText("");
                    	int newest_index = loggedInDentist.getPatientList().size() - 1;
                    	patgrid.add(new Text(Integer.toString(loggedInDentist.getPatientList().get(newest_index).getPatientNumber())), 0, newest_index + 1);
        		    	patgrid.add(new Text(loggedInDentist.getPatientList().get(newest_index).getName()), 1, newest_index + 1);
        		    	patgrid.add(new Text(loggedInDentist.getPatientList().get(newest_index).getAddress()), 2, newest_index + 1);
        		    	Button viewPatient = new Button("View");



        		    	patgrid.add(viewPatient, 3, newest_index + 1);
        		    	viewPatient.setUserData(newest_index);
        		    	viewPatient.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                            	changeMainPane("View Patient", Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                            }
                        });
        		    	Button removePatient = new Button("Remove");
        		    	patgrid.add(removePatient, 4, newest_index + 1);
        		    	removePatient.setUserData(newest_index);
        		    	removePatient.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                            	loggedInDentist.getPatientList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                            	// reload the patient screen
                            	changeMainPane("Patients", 0);
                            }
                        });
                    }
                });

    		    newPatBox.getChildren().addAll(patgrid, newPatGrid);

    		    // Horizontal box
    		    HBox patientsAlign = new HBox();
    		    patientsAlign.getChildren().addAll(patgrid, newPatBox);


    		    activePane.getChildren().addAll(patientsAlign);
    			break;
    		}
    		case "Payments": {

    			ArrayList<Invoice> invoices = selectedPatient.getInvoiceList();

    			VBox all = new VBox();
    			VBox paymentsTitle = new VBox();
    			paymentsTitle.setPadding(new Insets(10, 10, 10, 10));

    			Button goBack = new Button("< Go Back");
    			goBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	changeMainPane("Edit Invoice", currentIndex);
                    }
                });
    			Text space = new Text("\n");
    			Text invPatTitle = new Text("Patient Name: "+selectedPatient.getName());
    			invPatTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
    			Text invoiceID = new Text("\nInvoice ID: "+invoices.get(index).getInvoiceNumber());
    			invoiceID.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
    			Text invoiceDate = new Text("\nDate: "+dateFormat.format(invoices.get(index).getInvoiceDate()));
    			invoiceDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    			Text payTitle = new Text("\nPayments List ");
    			payTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
    			paymentsTitle.getChildren().addAll(goBack, space, invPatTitle, invoiceID, invoiceDate, payTitle);


    			VBox paymentBox = new VBox();
    			paymentBox.setPadding(new Insets(0, 0, 0, 10));

	    		 GridPane paymentGrid = new GridPane();
	    		 paymentGrid.setHgap(30);
	    		 paymentGrid.setVgap(10);
	    		 paymentGrid.setPadding(new Insets(20, 10, 10, 10));

	    		 paymentBox.getChildren().add(paymentGrid);
	    		 paymentGrid.setStyle("-fx-background-color: #ededed;");
	    		 // Procedure number column 1, row 1
	    		    Text paymentID = new Text("Payment ID");
	    		    paymentID.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    paymentGrid.add(paymentID, 0, 0);


	    		 // Procedure name column 2, row 1
	    		    Text procedureDate = new Text("Date");
	    		    procedureDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    paymentGrid.add(procedureDate, 1, 0);


	       		 // Procedure cost column 3, row 1
	    		    Text paymentAmount = new Text("Amount");
	    		    paymentAmount.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    paymentGrid.add(paymentAmount, 2, 0);

	    		    Text delete = new Text(" \t\t\t ");
	    		    paymentGrid.add(delete, 3, 0);

	    		    Button deletePayment;


	    		    for(int i = 0; i < invoices.get(index).getPaymentList().size(); i++) {
	    		    	paymentGrid.add(new Text(Integer.toString(invoices.get(index).getPaymentList().get(i).getPaymentNumber())), 0, i+1);
	    		    	paymentGrid.add(new Text(dateFormat.format(invoices.get(index).getPaymentList().get(i).getPaymentDate())), 1, i+1);
	    		    	paymentGrid.add(new Text(Double.toString(invoices.get(index).getPaymentList().get(i).getPaymentAmount())), 2, i+1);
	    		    	deletePayment = new Button("Delete");
	    		    	deletePayment.setUserData(i);
	    		    	deletePayment.setOnAction(new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent e) {
	                        	loggedInDentist.getPatientList().get(currentIndex).getInvoiceList().get(index).getPaymentList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
	                        	changeMainPane("Payments", currentIndex);
	                        }
	                    });
	    		    	paymentGrid.add(deletePayment, 3, i+1);
	    		    }
	    		    HBox summary = new HBox();
	    		    summary.setPadding(new Insets(10, 10, 10, 10));
	    		    Text paymentTotal = new Text("Total: "+invoices.get(index).getAmountPaid());
	    		    paymentTotal.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
	    		    summary.getChildren().add(paymentTotal);
	    		    paymentBox.getChildren().add(summary);


	    		    VBox newPaymentBox = new VBox();
	    		    newPaymentBox.setPadding(new Insets(10, 0, 0, 10));
	    		    GridPane newPaymentGrid = new GridPane();
	    		    newPaymentGrid.setHgap(10);
	    		    newPaymentGrid.setVgap(10);
	    		    newPaymentGrid.setPadding(new Insets(10, 10, 10, 10));
	    		    newPaymentGrid.setStyle("-fx-background-color: #ffdfb7;");

	    		 // New payment label, input field and Submit button

	    		    Text newPayment = new Text("New Payment");
	    		    newPayment.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
	    		    newPaymentGrid.add(newPayment, 0, 0);

	    		    Text newPaymentAmount = new Text("Amount:");
	    		    newPaymentAmount.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
	    		    newPaymentGrid.add(newPaymentAmount, 0, 1);

	    		    TextField textFieldAmount = new TextField ();
	    		    newPaymentGrid.add(textFieldAmount, 1, 1);

	    		  // Defining the Submit button
	    		    Button submitNewPayment = new Button("Submit Payment");
	    		    newPaymentGrid.add(submitNewPayment, 1, 3);
	    		    submitNewPayment.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	// TODO: Submitting new payments
	                    	// put an exception on this to catch the parseDouble failing
	                    	//procList.add(new Procedure(textFieldName.getText(), Double.parseDouble(textFieldCost.getText())));
	                    	invoices.get(index).addPayment(new Payment(Double.parseDouble(textFieldAmount.getText()), new Date()));
	                    	//textFieldName.setText("");
	                    	textFieldAmount.setText("");
	                    	//int newestIndex = procList.size() - 1;
	                    	changeMainPane("Payments", currentIndex);
	                    }
	                });

	    			all.getChildren().addAll(paymentsTitle, paymentBox);
	    		    newPaymentBox.getChildren().add(newPaymentGrid);
	    		    HBox two = new HBox(all ,newPaymentBox);
	    		    activePane.getChildren().addAll(two);
	    		    break;
    		}


    		case "Procedures": {
    			VBox procBox = new VBox();

    		    GridPane procgrid = new GridPane();
    		    procgrid.setHgap(30);
    		    procgrid.setVgap(10);
    		    procgrid.setPadding(new Insets(10, 10, 10, 10));
    		    procgrid.setStyle("-fx-background-color: #ededed;");

    		 // Procedure number column 1, row 1
    		    Text procid = new Text("ID");
    		    procid.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    procgrid.add(procid, 0, 0);


    		 // Procedure name column 2, row 1
    		    Text category = new Text("Procedure");
    		    category.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    procgrid.add(category, 1, 0);


       		 // Procedure cost column 3, row 1
    		    Text proccost = new Text("Cost");
    		    proccost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    procgrid.add(proccost, 2, 0);

    		    for(int i = 1; i <= procList.size(); i++){
    		    	procgrid.add(new Text(Integer.toString(procList.get(i-1).getProcNumber())), 0, i);
    		    	procgrid.add(new Text(procList.get(i-1).getProcName()), 1, i);
    		    	procgrid.add(new Text(Double.toString(procList.get(i-1).getProcCost())), 2, i);
    		    }
    		    procBox.getChildren().addAll(procgrid);

    		    VBox newProcBox = new VBox();
    		    newProcBox.setPadding(new Insets(10, 0, 0, 140));
    		    GridPane newProcGrid = new GridPane();
    		    newProcGrid.setHgap(10);
    		    newProcGrid.setVgap(10);
    		    newProcGrid.setPadding(new Insets(10, 10, 10, 10));
    		    newProcGrid.setStyle("-fx-background-color: #ffdfb7;");



    		 // New procedure label, input field and Submit button

    		    Text newProc = new Text("New Procedure");
    		    newProc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    newProcGrid.add(newProc, 0, 0);

    		    Text newProcName = new Text("Name:");
    		    newProcName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    		    newProcGrid.add(newProcName, 0, 1);

    		    TextField textFieldName = new TextField ();
    		    newProcGrid.add(textFieldName, 1, 1);

    		    Text newProcCost = new Text("Cost:");
    		    newProcCost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    		    newProcGrid.add(newProcCost, 0, 2);

    		    TextField textFieldCost = new TextField ();
    		    newProcGrid.add(textFieldCost, 1, 2);

    		  // Defining the Submit button
    		    Button submitNewProcedure = new Button("Create New Procedure");
    		    newProcGrid.add(submitNewProcedure, 1, 3);
    		    submitNewProcedure.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	// put an exception on this to catch the parseDouble failing
                    	procList.add(new Procedure(textFieldName.getText(), Double.parseDouble(textFieldCost.getText())));
                    	textFieldName.setText("");
                    	textFieldCost.setText("");
                    	int newestIndex = procList.size() - 1;
                    	procgrid.add(new Text(Integer.toString(procList.get(newestIndex).getProcNumber())), 0, newestIndex + 1);
        		    	procgrid.add(new Text(procList.get(newestIndex).getProcName()), 1, newestIndex + 1);
        		    	procgrid.add(new Text(Double.toString(procList.get(newestIndex).getProcCost())), 2, newestIndex + 1);
                    }
                });

    		    newProcBox.getChildren().addAll(newProcGrid);
    		    //newProcBox.setAlignment(Pos.TOP_RIGHT);


    		    // Horizontal box
    		    HBox proceduresAlign = new HBox();
    		    proceduresAlign.getChildren().addAll(procBox, newProcBox);

    		    activePane.getChildren().addAll(proceduresAlign);
    			break;
    		}
    		case "View Patient": {
    			VBox invoiceBox = new VBox();

    			selectedPatient = loggedInDentist.getPatientList().get(index);

    			VBox returnBox = new VBox();
    			Text invTitle = new Text("Patient Name: "+selectedPatient.getName());
    			invTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
    			Button goBack = new Button("< Go Back");
    			goBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	changeMainPane("Patients", 0);
                    }
                });

    			Text space = new Text("\n");
    			returnBox.getChildren().addAll(goBack, space, invTitle);
    			Text invList = new Text("\nInvoice List:");
    			invList.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
    			invoiceBox.getChildren().addAll(returnBox, invList);

    		    GridPane invoiceGrid = new GridPane();
    		    invoiceGrid.setHgap(30);
    		    invoiceGrid.setVgap(10);
    		    invoiceGrid.setPadding(new Insets(20, 10, 10, 10));
    		    invoiceGrid.setStyle("-fx-background-color: #ededed;");

    		 // Procedure number column 1, row 1
    		    Text invoiceID = new Text("ID");
    		    invoiceID.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    invoiceGrid.add(invoiceID, 0, 0);


    		 // Procedure name column 2, row 1
    		    Text invoiceDate = new Text("Date");
    		    invoiceDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    invoiceGrid.add(invoiceDate, 1, 0);


       		 // Procedure cost column 3, row 1
    		    Text invoiceCost = new Text("Cost");
    		    invoiceCost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    		    invoiceGrid.add(invoiceCost, 2, 0);

    		    Button editInvoice, deleteInvoice;

    		    ArrayList<Invoice> invoices = selectedPatient.getInvoiceList();
    		    for(int i = 0; i < invoices.size(); i++) {
    		    	invoiceGrid.add(new Text(Integer.toString(invoices.get(i).getInvoiceNumber())), 0, i+1);
    		    	invoiceGrid.add(new Text(dateFormat.format(invoices.get(i).getInvoiceDate())), 1, i+1);
    		    	invoiceGrid.add(new Text(Double.toString(invoices.get(i).getInvoiceAmount())), 2, i+1);
    		    	editInvoice = new Button("Edit");
    		    	editInvoice.setUserData(i);
    		    	editInvoice.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	changeMainPane("Edit Invoice", Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                        }
                    });
    		    	invoiceGrid.add(editInvoice, 3, i+1);
    		    	deleteInvoice = new Button("Delete");
    		    	deleteInvoice.setUserData(i);
    		    	deleteInvoice.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	loggedInDentist.getPatientList().get(currentIndex).getInvoiceList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                        	changeMainPane("View Patient", currentIndex);
                        }
                    });
    		    	invoiceGrid.add(deleteInvoice, 4, i+1);

    		    }
    		    invoiceBox.getChildren().add(invoiceGrid);

    		    VBox addInvoiceBox = new VBox();
    		    addInvoiceBox.setPadding(new Insets(20, 0, 0, 100));

    		    VBox addInvoiceBoxInner = new VBox();
    		    addInvoiceBoxInner.setPadding(new Insets(15, 15, 15, 15));
    		    addInvoiceBoxInner.setStyle("-fx-background-color: #ffdfb7;");

    		    Text newInv = new Text("New Invoice\n");
    		    newInv.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

    		    Button addInvoice = new Button("+ Create New Invoice");
    		    addInvoice.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	// put an exception on this to catch the parseDouble failing
                    	Patient selectedPatient = loggedInDentist.getPatientList().get(index);
                    	selectedPatient.addInvoice(new Invoice(new Date()));
                    	int newestIndex = selectedPatient.getInvoiceList().size() - 1;
                    	Invoice newInvoice = selectedPatient.getInvoiceList().get(newestIndex);
                    	invoiceGrid.add(new Text(Integer.toString(newInvoice.getInvoiceNumber())), 0, newestIndex + 1);
                    	invoiceGrid.add(new Text(dateFormat.format(newInvoice.getInvoiceDate())), 1, newestIndex + 1);
                    	invoiceGrid.add(new Text(Double.toString(newInvoice.getInvoiceAmount())), 2, newestIndex + 1);
        		    	Button editInvoice = new Button("Edit");
        		    	invoiceGrid.add(editInvoice, 3, newestIndex + 1);
        		    	editInvoice.setUserData(newestIndex);
        		    	editInvoice.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                            	changeMainPane("Edit Invoice", Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                            }
                        });
        		    	Button removeInvoice = new Button("Delete");
        		    	invoiceGrid.add(removeInvoice, 4, newestIndex + 1);
        		    	removeInvoice.setUserData(newestIndex);
        		    	removeInvoice.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                            	loggedInDentist.getPatientList().get(currentIndex).getInvoiceList().remove(Integer.parseInt(((Button) e.getSource()).getUserData().toString()));
                            	changeMainPane("View Patient", currentIndex);
                            }
                        });
                    }
                });

    		    addInvoiceBoxInner.getChildren().addAll(newInv, addInvoice);
    		    addInvoiceBox.getChildren().add(addInvoiceBoxInner);

    		 // Horizontal box
    		    HBox hAlign = new HBox();
    		    hAlign.getChildren().addAll(invoiceBox, addInvoiceBox);

    		    activePane.getChildren().addAll(hAlign);

    			break;
    		}
    	}
    	activePane.setPadding(new Insets(10));
    	activePane.setStyle("-fx-background-color: #fffdf7;");
    	GridPane.setHgrow(activePane, Priority.ALWAYS);
        GridPane.setVgrow(activePane, Priority.ALWAYS);
        mainContent.getChildren().add(activePane);

    }
}
