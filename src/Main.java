import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private String orderDescription;
    private boolean isTaken;

    public Order(String orderDescription) {
        this.orderId = nextOrderId++;
        this.orderDescription = orderDescription;
        this.isTaken = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}

class User {
    protected String role;
    protected String name;
    protected String number;

    public User(String role, String name, String number) {
        this.role = role;
        this.name = name;
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}

class Client extends User {
    private List<Order> orders;

    public Client(String name, String number) {
        super("Client", name, number);
        this.orders = new ArrayList<>();
    }

    public void placeOrder(String orderDescription) {
        Order order = new Order(orderDescription);
        orders.add(order);
        System.out.println("Order placed successfully. Order ID: " + order.getOrderId());
    }
}

class Manager extends User {
    private List<Order> orders;

    public Manager(String name, String number) {
        super("Manager", name, number);
        this.orders = new ArrayList<>();
    }

    public void viewOrders() {
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() + ", Description: " + order.getOrderDescription());
        }
    }

    public void deleteOrder(int orderId) {
        orders.removeIf(order -> order.getOrderId() == orderId);
        System.out.println("Order deleted successfully.");
    }
}

class Driver extends User {
    public Driver(String name, String number) {
        super("Driver", name, number);
    }

    public void takeOrder(Order order) {
        order.setTaken(true);
        System.out.println("Order taken successfully.");
    }

    public void completeOrder(Order order) {
        order.setTaken(false);
        System.out.println("Order completed successfully.");
    }
}

class TransportCompanyProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a role: \n 1. Client \n 2. Manager \n 3. Driver \n 4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter your name:");
                    String clientName = scanner.next();
                    System.out.println("Enter your phone number");
                    String clientNumber = scanner.next();
                    Client client = new Client(clientName, clientNumber);
                    System.out.println("Welcome, " + client.getRole() + "!");
                    System.out.println("1. Place order, 2. Switch role");
                    int clientOption = scanner.nextInt();
                    if (clientOption == 1) {
                        System.out.println("Enter order description:");
                        String orderDescription = scanner.next();
                        client.placeOrder(orderDescription);
                    }
                    break;
                case 2:
                    System.out.println("Enter your name:");
                    String managerName = scanner.next();
                    System.out.println("Enter your phone number");
                    String managerNumber = scanner.next();
                    Manager manager = new Manager(managerName, managerNumber);
                    System.out.println("Welcome, " + manager.getRole() + "!");
                    System.out.println("1. View orders, 2. Delete order, 3. Switch role");
                    int managerOption = scanner.nextInt();
                    if (managerOption == 1) {
                        manager.viewOrders();
                    } else if (managerOption == 2) {
                        System.out.println("Enter order ID to delete:");
                        int orderId = scanner.nextInt();
                        manager.deleteOrder(orderId);
                    }
                    break;
                case 3:
                    System.out.println("Enter your name:");
                    String driverName = scanner.next();
                    System.out.println("Enter your phone number");
                    String driverNumber = scanner.next();
                    Driver driver = new Driver(driverName, driverNumber);
                    System.out.println("Welcome, " + driver.getRole() + "!");
                    System.out.println("1. Take order, 2. Complete order, 3. Switch role");
                    int driverOption = scanner.nextInt();
                    if (driverOption == 1) {
                        Order selectedOrder = new Order("Sample Order");
                        driver.takeOrder(selectedOrder);
                    } else if (driverOption == 2) {
                        Order completedOrder = new Order("Sample Order");
                        driver.completeOrder(completedOrder);
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
