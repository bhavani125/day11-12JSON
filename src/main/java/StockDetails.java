import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockDetails {
    Scanner sc = new Scanner(System.in);

    // Inventory data management
    public void InventoryDataManagement() {

        try {
            JSONParser jsonparser = new JSONParser();
            //Read the InventoryDetails.json file
            FileReader reader = new FileReader("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\InventoryDetails");
            // Java Object variable(MAIN OBJ which includes JSONParser, JSONObject, JSONArray)
            Object object = jsonparser.parse(reader);
            // creating jsonObj for JsonObject(here JSON File parses object to JSONObject)
            JSONObject jsonObject = (JSONObject) object;
            JSONArray itemList = (JSONArray) jsonObject.get("listOfItem");
            System.out.println(itemList);
            Iterator iterator = itemList.iterator();
            long amount , totalAmount = 0;
            while (iterator.hasNext()){
                JSONObject jsonObj1 = (JSONObject) iterator.next();
                System.out.println("=======>"+ jsonObj1);
                long w1 =(long) jsonObj1.get("NumberOfShares");
                long p1 =(long) jsonObj1.get("price");

                amount = w1*p1;
                System.out.println("amount of " + jsonObj1.get("name") +" = " + amount);
                totalAmount = amount+totalAmount;
            }
            System.out.println("total amount = "+totalAmount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    // Stock account management
    // Creating addStockMethod
    JSONArray stockArr = new JSONArray();
    public void addStocks() {
        JSONObject stockJsonObj = new JSONObject();

        System.out.print("Enter Stock name: ");
        String stockName = sc.next();
        System.out.print("Enter number os shares: ");
        int numShare = sc.nextInt();
        System.out.print("Enter share price: ");
        int sharePrice = sc.nextInt();
        stockJsonObj.put("stockName", stockName);
        stockJsonObj.put("sharePrice", sharePrice);
        stockJsonObj.put("numShare", numShare);
        stockArr.add(stockJsonObj);
        System.out.println(stockArr);
        try (FileWriter file = new FileWriter("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems")) {
            file.write(stockArr.toJSONString());
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----Data added to Json File SuccessFully----");
        stockJsonObj = null;
    }
    //Creating getShareReport Method
    public void getShareReport() {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\StockItems")) {
            Object obj = jsonParser.parse(reader);
            Iterator iterator = ((ArrayList) obj).iterator();
            long shareAmount , totalAmount = 0;
            System.out.println("Share Report of all companies: ");
            while (iterator.hasNext()){
                JSONObject jsonObj1 = (JSONObject) iterator.next();
                long sharePrice =(long) jsonObj1.get("sharePrice");
                long numShare =(long) jsonObj1.get("numShare");
                shareAmount = sharePrice * numShare;
                System.out.println("Total share cost of " + jsonObj1.get("stockName") +" = " + shareAmount);
                totalAmount = shareAmount+totalAmount;
            }
            System.out.println("Total shares of all companies: "+totalAmount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
