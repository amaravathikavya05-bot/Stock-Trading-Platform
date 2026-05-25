import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Available Stocks
        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("TCS", 3500));
        market.add(new Stock("INFOSYS", 1500));
        market.add(new Stock("WIPRO", 450));
        market.add(new Stock("HCL", 1200));

        // User Portfolio
        HashMap<String, Integer> portfolio = new HashMap<>();

        double balance = 10000;

        int choice;

        do {

            System.out.println("\n=====================================");
            System.out.println("      STOCK TRADING PLATFORM");
            System.out.println("=====================================");

            System.out.println("Available Balance: ₹" + balance);

            System.out.println("\nAvailable Stocks:");
            for (Stock s : market) {
                System.out.println("- " + s.name + " : ₹" + s.price);
            }

            System.out.println("\n1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Stock Name to Buy: ");
                    String buyStock = sc.nextLine().toUpperCase();

                    Stock selectedStock = null;

                    for (Stock s : market) {
                        if (s.name.equals(buyStock)) {
                            selectedStock = s;
                            break;
                        }
                    }

                    if (selectedStock != null) {

                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        double totalCost = selectedStock.price * qty;

                        if (totalCost <= balance) {

                            balance -= totalCost;

                            portfolio.put(
                                    buyStock,
                                    portfolio.getOrDefault(buyStock, 0) + qty
                            );

                            System.out.println("Stock Purchased Successfully!");

                        } else {
                            System.out.println("Insufficient Balance!");
                        }

                    } else {
                        System.out.println("Stock Not Found!");
                    }

                    break;

                case 2:

                    System.out.print("Enter Stock Name to Sell: ");
                    String sellStock = sc.nextLine().toUpperCase();

                    if (portfolio.containsKey(sellStock)) {

                        System.out.print("Enter Quantity to Sell: ");
                        int sellQty = sc.nextInt();

                        int ownedQty = portfolio.get(sellStock);

                        if (sellQty <= ownedQty) {

                            double stockPrice = 0;

                            for (Stock s : market) {
                                if (s.name.equals(sellStock)) {
                                    stockPrice = s.price;
                                    break;
                                }
                            }

                            balance += stockPrice * sellQty;

                            if (sellQty == ownedQty) {
                                portfolio.remove(sellStock);
                            } else {
                                portfolio.put(sellStock, ownedQty - sellQty);
                            }

                            System.out.println("Stock Sold Successfully!");

                        } else {
                            System.out.println("Not Enough Shares!");
                        }

                    } else {
                        System.out.println("You Do Not Own This Stock!");
                    }

                    break;

                case 3:

                    System.out.println("\n========== YOUR PORTFOLIO ==========");

                    if (portfolio.isEmpty()) {

                        System.out.println("Portfolio is Empty!");

                    } else {

                        for (String stock : portfolio.keySet()) {

                            System.out.println(
                                    stock + " : " +
                                    portfolio.get(stock) + " shares"
                            );
                        }
                    }

                    System.out.println("Available Balance: ₹" + balance);

                    break;

                case 4:

                    System.out.println("Thank You for Using Stock Trading Platform!");

                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
