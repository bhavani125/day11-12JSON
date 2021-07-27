import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockManagement {
    //Main Method
    public static void main(String[] args) {
        //creating object for StockManagement class
        StockManagement stockOperation = new StockManagement();
        stockOperation.performStockManagement();
    }

    public static JSONArray stockList = new JSONArray();
    public static JSONArray stockBuySell = new JSONArray();
    public static Scanner sc = new Scanner(System.in);
    StockBuySellDetails stocks = new StockBuySellDetails();

    //Creating performStockManagement Method to take user input
    public void performStockManagement() {
        System.out.println("\nEnter the operation.\n1)Add stock.\n2)Display all stock.\n3)Buy share.\n4)Sell Share."
                + "\n5) Display all stocks values.\n6) Display your Account\n7) Exit");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                addStockAccount();
                break;
            case 2:
                displayStocks();
                break;
            case 3:
                buyShare();
                break;
            case 4:
                sellShare();
                break;
            case 5:
                displayStockValues();
                break;
            case 6:
                displayAccount();
                break;
            case 7:
                System.out.println("Exited");
                System.exit(0);
                break;
            default:
                System.out.println("Enter valid option");

        }
    }

    //Creating displayStocks method to display previous stocks details
    public void displayStocks() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject stockObject = new JSONObject();
            //reading json file of stockItems
            FileReader reader = new FileReader("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems");
            JSONArray stockArray = (JSONArray) parser.parse(reader);
            for (int i = 0; i < stockArray.size(); i++) {
                stockObject = (JSONObject) stockArray.get(i);
                String name = (String) stockObject.get("stockName");
                long noOfShare = (long) stockObject.get("numberOfShare");
                double price = (double) stockObject.get("price");

                System.out.println("\nShare name is: " + name);
                System.out.println("Number of share is: " + noOfShare);
                System.out.println("Share price is: " + price);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        performStockManagement();
    }

    // Method write the JSON file
    public void writeFile() {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems");
            writer.write(stockList.toJSONString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // creating addStockAccount method  with number of share and it's price.
    public void addStockAccount() {
        // Creating JSONObject.
        JSONObject jsonObj = new JSONObject();
        // Taking user input and storing it in JSONObject.
        System.out.println("Enter stock name: ");
        String stockName = sc.next();
        jsonObj.put("stockName", stockName);

        System.out.println("Enter number of share: ");
        long numOfShare = sc.nextLong();
        jsonObj.put("numberOfShare", numOfShare);

        System.out.println("Enter price of share: ");
        double price = sc.nextDouble();
        jsonObj.put("price", price);

        // here we are adding JSONObject to JSONArray.
        stockList.add(jsonObj);
        // Calling  writeFile method.
        writeFile();
        System.out.println("Added: " + jsonObj);
        performStockManagement();

    }

    //Creating buyShare method to buy shares from previous stocks
    public void buyShare() {
        for (int i = 0; i < stockList.size(); i++) {
            JSONObject jsonObj = new JSONObject();
            jsonObj = (JSONObject) stockList.get(i);
            String presentName = (String) jsonObj.get("stockName");
            System.out.println("Enter the stock name that you want to buy: ");
            String buyStock = sc.next();
            // checking weather stock is present or not
            if (presentName.equalsIgnoreCase(buyStock)) {
                // Generating the previous number of shares.
                long sharesNumber = (long) jsonObj.get("numberOfShare");
                System.out.println("Enter the number of shares you want to buy: ");
                long shareBuy = sc.nextLong();
                // Condition checking that number of shares is within the previous share limit or not.
                if (sharesNumber >= shareBuy) {
                    sharesNumber = sharesNumber - shareBuy;
                    jsonObj.replace("numberOfShare", sharesNumber);
                    String message = "Successfully you buyed " + shareBuy + "shares  from " + buyStock;
                    getBuySellDetails(message);
                } else
                    System.out.println("Enter share less than equal to " + sharesNumber);
            }
        }
        writeFile();
        System.out.println("Final stocks: " + stockList);

        performStockManagement();

    }

    //Creating sellShare method
    public void sellShare() {
        for (int i = 0; i < stockList.size(); i++) {
            JSONObject jsonObj = new JSONObject();
            jsonObj = (JSONObject) stockList.get(i);
            String presentName = (String) jsonObj.get("stockName");

            System.out.println("Enter the stock name that you want to sell  : ");
            String sellStock = sc.next();

            if (presentName.equalsIgnoreCase(sellStock)) {
                long sharesNumber = (long) jsonObj.get("numberOfShare");
                System.out.println("Enter the number of shares you want to sell: ");
                long sellShare = sc.nextLong();

                sharesNumber = sharesNumber + sellShare;
                jsonObj.replace("numberOfShare", sharesNumber);
                String message = "Successfully you sold shares of " + sellShare + " to " + sellStock;
                getBuySellDetails(message);

                writeFile();
                System.out.println("Stocks after selling is :" + stockList);
            }
            performStockManagement();
        }
    }

   //Creating displayStockValues Method
    public void displayStockValues() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject stockObj = new JSONObject();
            FileReader reader = new FileReader("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems");
            JSONArray stockArray = (JSONArray) parser.parse(reader);
            for (int i = 0; i < stockArray.size(); i++) {
                stockObj = (JSONObject) stockArray.get(i);
                String name = (String) stockObj.get("stockName");
                long noOfShare = (long) stockObj.get("numberOfShare");
                double price = (double) stockObj.get("price");

                System.out.println("Total stocks value of " + name + " is " + noOfShare * price);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        performStockManagement();

    }


     // Purpose: Method to create users account. Users account will be stored with


    public void getBuySellDetails(String message) {
        Date date = new Date();
        stocks.setMessage(message);
        stocks.setDate(date);

        JSONObject stockObj = new JSONObject();

        stockObj.put("message", stocks.getMessage());
        stockObj.put("DateAndTime", stocks.getDate());

        stockBuySell.add(stockObj);
        try {
            FileWriter writer = new FileWriter("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems");
            writer.write(stockBuySell.toJSONString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stockBuySell);

    }

    /* Purpose: Method to display the users account. */
    public void displayAccount() {
        System.out.println(stockBuySell);
        performStockManagement();
    }

}


