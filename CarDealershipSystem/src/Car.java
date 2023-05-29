class Car extends Vehicle 
{
    private String vin;

    public Car(String make, String model, int year, double price, String vin) 
    {
        super(make, model, year, price);
        this.vin = vin;
    }

    public String getVin() 
    {
        return vin;
    }

    public String getType()
    {
        return "Car";
    }

    // Overridden method
    @Override
    public String getName() 
    {
        return "Car: " + super.getName();
    }

    // Overloaded method
    public void displayInfo() 
    {
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("VIN: " + vin);
        System.out.println("Price: $" + getPrice());
    }
}



