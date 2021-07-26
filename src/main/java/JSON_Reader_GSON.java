import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class JSON_Reader_GSON {

    public  void readJsonFile() throws IOException, ParseException ,org.json.simple.parser.ParseException {
        JSONParser jsonparser = new JSONParser();
        //Read the InventoryDetails.json file
        FileReader reader = new FileReader("C:\\Users\\HARISH VIVARAMNENI\\eclipse-workspace\\day11-JSONProject\\JSONFiles\\InventoryDetails");
        // Java Object variable(MAIN OBJ which includes JSONParser, JSONObject, JSONArray)
        Object obj = jsonparser.parse(reader);
        // creating inventoryObj for JsonObject(here JSON File parses object to JSONObject)
        JSONObject inventoryobj = (JSONObject) obj;
        // here we change JSON Object to JSON Array.
        JSONArray Ricearray = (JSONArray) inventoryobj.get("Rice");
        JSONArray Wheatarray = (JSONArray) inventoryobj.get("Wheats");
        JSONArray Pulsesarray = (JSONArray) inventoryobj.get("Pulses");
        //details of rice
        System.out.println("\n Details of rice are:");
        for (int i = 0; i < Ricearray.size(); i++) {
            JSONObject riceDetails = (JSONObject) Ricearray.get(i);
            String type = (String) riceDetails.get("Type");
            String name = (String) riceDetails.get("Name");
            long totalWeight = (long) riceDetails.get("TotalWeight");
            long pricePerKg = (long) riceDetails.get("PricePerKg");
            System.out.println("Type: " + type);
            System.out.println("Name: " + name);
            System.out.println("TotalWeight: " + totalWeight);
            System.out.println("PricePerKg: " + pricePerKg);

            long ricePrice = totalWeight * pricePerKg;
            System.out.println("total price: "   +  ricePrice);
            System.out.println("\n");

        }
        //details of wheat
        System.out.println("\n Details of wheat are:");
        for (int i = 0; i < Wheatarray.size(); i++) {
            JSONObject wheatDetails= (JSONObject) Wheatarray.get(i);
            String type = (String) wheatDetails.get("Type");
            String name = (String) wheatDetails.get("Name");
            long totalWeight = (long) wheatDetails.get("TotalWeight");
            long pricePerKg = (long) wheatDetails.get("PricePerKg");
            System.out.println("Type: " + type);
            System.out.println("Name: " + name);
            System.out.println("TotalWeight: " + totalWeight);
            System.out.println("PricePerKg: " + pricePerKg);

            long wheatPrice = totalWeight * pricePerKg;
            System.out.println("total price: "   +  wheatPrice);
            System.out.println("\n");
        }
        // details of pulses
        System.out.println("\nDetails of pulses are:");
        for (int i = 0; i < Pulsesarray.size(); i++) {
            JSONObject pulsesDetails = (JSONObject) Pulsesarray.get(i);
            String type = (String) pulsesDetails.get("Type");
            String name = (String) pulsesDetails.get("Name");
            long totalWeight = (long) pulsesDetails.get("TotalWeight");
            long pricePerKg = (long) pulsesDetails.get("PricePerKg");
            System.out.println("Type: " + type);
            System.out.println("Name: " + name);
            System.out.println("TotalWeight: " + totalWeight);
            System.out.println("PricePerKg: " + pricePerKg);

            long pulsesPrice = totalWeight * pricePerKg;
            System.out.println("total price: "   +  pulsesPrice);
            System.out.println("\n");
        }
    }
    //main method
    public static void main(String[] args) throws IOException, ParseException,org.json.simple.parser.ParseException{
        JSON_Reader_GSON inventoryDetails = new JSON_Reader_GSON();
        inventoryDetails.readJsonFile();
    }
}
