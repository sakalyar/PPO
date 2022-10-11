package serie04;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class StdContact implements Contact, Comparable {

	Contact contact;
	String firstName;
	String lastName;
	Civ civility;
	
	public StdContact(String n, String p) {
		if (n != null && p != null &&
			n != "" && p != "") {
			firstName = n;
			lastName = p;
			civility = Civ.UKN;
			//?????
			contact = this;
		}
	}
	
	public StdContact(Civ c, String n, String p) {
		if (n != null && p != null &&
				n != "" && p != "") {
				this.firstName = n;
				this.lastName = p;
				this.civility = c;
			}
		}
	
	@Override
	public int compareTo(Object o) {
//		try {
//			Contact newContact = (Contact) o;
//			if (sgn(((StdContact) contact).compareTo(newContact)) == -sgn(newContact.compareTo(contact)))
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		return 1;
	}

	@Override
	public Civ getCivility() {
		return this.civility;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public Contact evolve(Civ civility) {
		this.civility = civility;
		return this;
	}
}
