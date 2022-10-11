package serie04;

public enum Civ {
	UKN {boolean test(Civ c) {return c == MR || c == MRS || c == MS;}},
	MR {boolean test(Civ c) {return c == MR;}},
	MRS {boolean test(Civ c) {return c == MRS || c == MS;}},
	MS {boolean test(Civ c) {return c == MRS || c == MS;}};

//	boolean canEvolveTo(Civ civility) {
//		return 
//	}
//	
//	public String toString() {
//		return this.toString();
//	}
	
}
