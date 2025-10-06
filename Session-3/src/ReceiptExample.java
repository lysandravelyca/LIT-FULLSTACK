import java.util.ArrayList;
import java.util.Iterator;

class ReceiptExample {
    public static void main(String[] args) {
        ArrayList<ReceiptItem> receiptList = new ArrayList<>();

        receiptList.add(new ReceiptItem("Nasi Goreng Spesial", 25000));
        receiptList.add(new ReceiptItem("Ayam Geprek", 20000));
        receiptList.add(new ReceiptItem("Es Teh Manis", 5000));
        receiptList.add(new ReceiptItem("Jus Alpukat", 12000));

//        menu pertama
        System.out.println(receiptList.getFirst());

//        menu terakhir
        System.out.println(receiptList.getLast());

        System.out.println("===== Daftar Pesanan =====");
        for (ReceiptItem item : receiptList) {
            System.out.println(item);
        }

        double total = 0;
        for (ReceiptItem item : receiptList) {
            total += item.getPrice();
        }

        System.out.println("==========================");
        System.out.println("Total Harga: Rp" + total);
    }
}