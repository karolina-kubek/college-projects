public class Procedure {

	//Static variable for generating the id for a procedure
	private static int newProcNumber = 1;

	private int procNumber;
	private String procName;
	private double procCost;

	//Constructor taking procedure name and cost, setting them and setting the procedure number/id
	public Procedure(String procName, double procCost) {
		this.procName = procName;
		this.procCost = procCost;
		procNumber = newProcNumber++;
	}

	public int getProcNumber() {
		return procNumber;
	}

	public String getProcName() {
		return procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	public String toString() {
		String returnString = "Procedure Information\n";
		returnString += "Procedure Number: "+procNumber+"\n";
		returnString += "Procedure Name: "+procName+"\n";
		returnString += "Procedure Cost: "+procCost+"\n";
		return returnString;
	}

	public void print() {
		System.out.println(toString());
	}

}
