public class Person {

	private String name, address;

	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		String returnString = "Personal Information\n";
		returnString += "Name: "+name+"\n";
		returnString += "Address: "+address+"\n";
		return returnString;
	}

	public void print() {
		System.out.println(toString());
	}

}
