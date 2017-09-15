import java.util.ArrayList;

public class Patient extends Person {

	//A static variable for generating a unique id for a patient
	private static int newPatientNumber = 1;

	private int patientNumber;
	//Invoice list for storing all of the invoices
	private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

	public Patient(String name, String address) {
		super(name, address);
		patientNumber = newPatientNumber++;
	}

	public int getPatientNumber() {
		return patientNumber;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	//Adding a new invoice to the list
	public void addInvoice(Invoice invoice){
		invoiceList.add(invoice);
	}

	public String toString() {
		String returnString = "Patient Information\n";
		returnString += "Patient Number: "+patientNumber+"\n";
		for(int i = 0; i < invoiceList.size(); i++) {
			returnString += invoiceList.get(i).toString();
		}
		returnString += super.toString();
		return returnString;
	}

	public void print() {
		System.out.println(toString());
	}

}
