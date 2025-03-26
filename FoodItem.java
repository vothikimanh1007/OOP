// FoodItem.java
class FoodItem {
    private String name;
    private double price;

    public FoodItem() {}

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FoodItem) {
            FoodItem other = (FoodItem) obj;
            return this.name.equals(other.name) && this.price == other.price;
        }
        return false;
    }
}

// OrderItem.java
class OrderItem {
    private String itemName;
    private int quantity;
    private double unitPrice;

    public OrderItem() {}

    public OrderItem(String itemName, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public String toString() {
        return itemName + " - Qty: " + quantity + " - Price: " + (unitPrice * quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderItem) {
            OrderItem other = (OrderItem) obj;
            return this.itemName.equals(other.itemName) && this.unitPrice == other.unitPrice;
        }
        return false;
    }
}

// Invoice.java
class Invoice {
    private OrderItem[] items = new OrderItem[100];
    private int count = 0;

    public void addItem(OrderItem item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) {
                System.out.println("Món này đã có trong hóa đơn.");
                return;
            }
        }
        items[count++] = item;
    }

    public void printInvoice() {
        double total = 0;
        System.out.println("\n--- Hóa đơn ---");
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
            total += items[i].getQuantity() * items[i].getUnitPrice();
        }
        System.out.println("Tổng cộng: " + total);
    }
}

// RestaurantManagementSystem.java
import java.util.Scanner;

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodItem[] menu = {
            new FoodItem("Phở", 30000),
            new FoodItem("Bánh mì", 15000),
            new FoodItem("Trà sữa", 25000),
            new FoodItem("Cà phê", 20000)
        };

        Invoice invoice = new Invoice();

        while (true) {
            System.out.println("\n--- MENU ---");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i]);
            }
            System.out.println("5. In hóa đơn");
            System.out.println("6. Thanh toán và thoát");

            System.out.print("Chọn món (1-6): ");
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= 4) {
                FoodItem item = menu[choice - 1];
                System.out.print("Nhập số lượng: ");
                int qty = scanner.nextInt();
                OrderItem order = new OrderItem(item.getName(), qty, item.getPrice());
                invoice.addItem(order);
            } else if (choice == 5) {
                invoice.printInvoice();
            } else if (choice == 6) {
                invoice.printInvoice();
                System.out.println("Cảm ơn quý khách!");
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }

        scanner.close();
    }
}