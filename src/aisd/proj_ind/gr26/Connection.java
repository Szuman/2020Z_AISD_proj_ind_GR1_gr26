package aisd.proj_ind.gr26;

public class Connection implements Comparable<Connection> {

    private final Manufacturer manufacturer;
    private final Pharmacy pharmacy;
    private final int maxDaily;
    private final double price;

    public Connection(Manufacturer manufacturer, Pharmacy pharmacy, int maxDaily, double price) {
        this.manufacturer = manufacturer;
        this.pharmacy = pharmacy;
        this.maxDaily = maxDaily;
        this.price = price;
    }

    public Manufacturer getManufacture() {
        return manufacturer;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public int getMaxDaily() {
        return maxDaily;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Connection o) {
        int priceCompare = Double.compare(price, o.price);
        if (priceCompare == 0)
            return -1 * Integer.compare(maxDaily, o.maxDaily);
        return priceCompare;
    }
}
