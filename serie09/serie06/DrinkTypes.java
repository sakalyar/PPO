package serie06;


public enum DrinkTypes {
    COFFEE(30),
    CHOCOLATE(45),
    ORANGE_JUICE(110);
    
    private int price;
    
    DrinkTypes(int price) {
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
