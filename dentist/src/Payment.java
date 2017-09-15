import java.util.Date;

public class Payment {

	//A static variable for making payment ids
	private static int newPaymentNumber = 1;
	private int paymentNumber;
	private double paymentAmount;
	private Date paymentDate;

	//Constructor takes payment amount and the day of payment and generated an id of the payment
	public Payment(double paymentAmount, Date paymentDate) {
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		paymentNumber = newPaymentNumber++;
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String toString() {
		String returnString = "Payment Information\n";
		returnString += "Payment Number: "+paymentNumber+"\n";
		returnString += "Payment Amount: "+paymentAmount+"\n";
		returnString += "Payment Date: "+paymentDate.toString()+"\n";
		return returnString;
	}

	public void print() {
		System.out.println(toString());
	}

}
