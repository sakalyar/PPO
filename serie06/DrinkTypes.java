package serie06;

import java.util.HashMap;

public enum DrinkTypes {
	COFFEE (30), 
	CHOCOLATE (45),
	ORANGE_JUICE (110);
	
	private DrinkTypes(int price) {
		this.price = price;
	}
	
	private int price;
	private String DrinkName;
	
	public int getPrice() {
		return price;
	}
	@Override
	public String toString() {
		System.out.println(this.name().replace("_", " ").toLowerCase());
		return this.name().replace("_", " ").toLowerCase();
	}
	
}
