public class InventoryDetails {
    //instance variables
    String type;
    String name;
    long totalWeight;
    long pricePerkg;
    // creating  constructor for it
    public InventoryDetails(String type, String name, long totalWeight, long pricePerkg)
    {
        this.type= type;
        this.name = name;
        this.totalWeight = totalWeight;
        this.pricePerkg=pricePerkg;
    }
   //Generating getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public long getPricePerkg() {
        return pricePerkg;
    }

    public void setPricePerkg(long pricePerkg) {
        this.pricePerkg = pricePerkg;
    }
}
