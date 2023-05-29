class Sale extends InventoryItem {
    private String itemName;
    private int quantity;

    public Sale(String itemName, int quantity, double price) {
        super(itemName, price);
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return "Sale";
    }

    // Override the getName() method to include the item name
    @Override
    public String getName() {
        return "Sale: " + itemName;
    }
}

