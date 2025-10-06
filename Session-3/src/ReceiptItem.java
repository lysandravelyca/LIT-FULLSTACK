class ReceiptItem {
    private String menu;
    private double price;

    public ReceiptItem(String inMenu, double inPrice) {
        this.menu = inMenu;
        this.price = inPrice;
    }

    public String getMenu() {
        return this.menu;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return this.menu + " - Rp" + this.price;
    }
}
