//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;

// Superclass
class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Method yang akan dioverride oleh subclass
    public String getProductDetails() {
        return "Product: " + name + "\nPrice: $" + price;
    }
}

// Subclass Book
class Book extends Product {
    private String author;
    private int pages;

    public Book(String name, double price, String author, int pages) {
        super(name, price);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String getProductDetails() {
        return "Book: " + name +
                "\nAuthor: " + author +
                "\nPages: " + pages +
                "\nPrice: $" + price;
    }
}

// Subclass Electronics
class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronics(String name, double price, String brand, int warrantyMonths) {
        super(name, price);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getProductDetails() {
        return "Electronics: " + name +
                "\nBrand: " + brand +
                "\nWarranty: " + warrantyMonths + " months" +
                "\nPrice: $" + price;
    }
}

// Main Class untuk demonstrasi
public class Main {
    public static void main(String[] args) {
        // Membuat collection (ArrayList) dari Product
        ArrayList<Product> products = new ArrayList<>();

        // Tambahkan berbagai tipe produk (Book & Electronics)
        products.add(new Book("Clean Code", 35.99, "Robert C. Martin", 464));
        products.add(new Electronics("Smartphone", 499.99, "Samsung", 24));
        products.add(new Book("The Pragmatic Programmer", 29.99, "Andrew Hunt", 352));
        products.add(new Electronics("Laptop", 899.00, "ASUS", 12));

        // Polymorphism: memanggil method yang sesuai dengan tipe objek aslinya
        for (Product p : products) {
            System.out.println(p.getProductDetails());
            System.out.println("------------------------------");
        }
    }
}
