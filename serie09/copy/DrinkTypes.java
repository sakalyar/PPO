package serie09.copy;

public enum DrinkTypes {
	COFFEE("COFFEE", 30),
	CHOCOLATE("CHOCOLATE", 45),
	ORANGE_JUICE("ORANGE_JUICE", 110);
	
	String name;
	private int price;
	private DrinkTypes(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return name.toLowerCase().replace('_', ' ');
	}
	
}
