import java.util.ArrayList;

public class Dentist extends Person{

	private String password;

	//List of Patients that belongs to a doctor
	private ArrayList<Patient> patientList = new ArrayList<Patient>();

	//Constructor taking name, address and password for a doctor and setting it
	public Dentist(String name, String address, String password) {
		super(name, address);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Authenticating if given password matches stored passwors, returns a boolean
	public boolean authenticate(String password) {
		return this.password.compareTo(password) == 0;
	}

	//Adding a patient to a patient list, takes a Patient object
	public void addPatient(Patient patient) {
		patientList.add(patient);
	}

	//Adds a new invoice by patient id
	public void addInvoice(int id, Invoice invoice){
		patientList.get(id-1).addInvoice(invoice);
	}

	public String toString() {
		String returnString = "Dentist Information:\n";
		returnString += super.toString()+"\n";
		for(int i = 0; i < patientList.size(); i++) {
			returnString += patientList.get(i).toString();
		}
		return returnString ;
	}

	public void print() {
		System.out.println(toString());
	}

}
