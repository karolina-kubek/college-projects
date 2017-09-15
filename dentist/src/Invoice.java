import java.util.ArrayList;
import java.util.Date;

public class Invoice {

	private static int newInvoiceNumber = 1;
	private int invoiceNumber;
	private double invoiceAmount = 0;
	private Date invoiceDate;
	private double amountPaid = 0;

	//Procedure/treatment list
	private ArrayList<Procedure> procList = new ArrayList<Procedure>();
	//Payment List
	private ArrayList<Payment> paymentList = new ArrayList<Payment>();

	//Constructor for the invoice takes in the date, sets it and makes an id for the invoice
	public Invoice(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
		invoiceNumber = newInvoiceNumber++;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public ArrayList<Procedure> getProcedureList() {
		return procList;
	}
	
	public ArrayList<Payment> getPaymentList() {
		return paymentList;
	}
	
	public boolean isPaid() {
		if(invoiceAmount <= amountPaid) {
			return true;
		}
		return false;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	//Adds a new procedure to the list and adds its cost to the bill
	public void addProcedure(Procedure procedure) {
		procList.add(procedure);
		invoiceAmount += procedure.getProcCost();
	}

	//Add a new payment to the list and add the amount to the total
	public void addPayment(Payment payment) {
		paymentList.add(payment);
		amountPaid += payment.getPaymentAmount();
	}
	
	public double getAmountPaid() {
		return amountPaid;
	}
	
	public String toString() {
		String returnString = "Invoice Information\n";
		returnString += "Invoice Number: "+invoiceNumber+"\n";
		returnString += "Invoice Amount: "+invoiceAmount+"\n";
		returnString += "Invoice Date: "+invoiceDate.toString()+"\n";
		returnString += "Invoice Status: "+(isPaid() ? "Paid" : "Not Paid")+"\n";
		for(int i = 0; i < procList.size(); i++) {
			returnString += procList.get(i).toString();
		}
		for(int i = 0; i < paymentList.size(); i++) {
			returnString += paymentList.get(i).toString();
		}
		return returnString;
	}

	public void print() {
		System.out.println(toString());
	}

}
