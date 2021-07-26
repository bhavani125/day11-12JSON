import java.util.Scanner;

public class MainStockManagement {
        //Main method
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            //Creating obj for stockDetails
            StockDetails stockObj = new StockDetails();
            System.out.print("Enter the number of stocks: ");
            int num = sc.nextInt();
            while(num != 0) {
                stockObj.addStocks();
                num--;
            }
            stockObj.getShareReport();
        }
}
