package aisd.proj_ind.gr26;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

public class Solver {
    private final String filePath;

    public Solver(String filePath) {
        this.filePath = filePath;
    }

    FileReader fileReader = new FileReader();

    private List<Connection> connections;
    private final Map<Connection, Integer> usedConnections = new HashMap<>();
    private double sum;

    public double getSum() {
        return sum;
    }

    public void calculate() {
        try {
            fileReader.loadData(filePath);
        } catch (IOException e) {
            System.err.println("Błędnie wczytany plik!");
        }

        Map<Integer, Pharmacy> pharmacies = fileReader.getPharmacies();
        connections = fileReader.getConnections();
        Collections.sort(connections);

        for (Connection connection : connections)
            usedConnections.put(connection, 0);

        for (Connection c : connections) {
            Pharmacy p = c.getPharmacy();
            Manufacturer m = c.getManufacture();
            if (m.getDailyRemains() > 0 || p.getDailyRemains() > 0)
                putIntoConnection(c, m, p);
        }

        for (Connection c : connections) {
            Pharmacy p = c.getPharmacy();
            Manufacturer m = c.getManufacture();
            if (p.getDailyRemains() != 0 && m.getDailyRemains() == 0) {
                setManufacturerConnection(c, m, p, p.getDailyRemains());
                putIntoConnection(c, m, p);
            }
        }

        boolean succseed = true;
        for (Pharmacy pharmacy : pharmacies.values()) {
            if (pharmacy.getDailyRemains() != 0) {
                System.out.println("Nie udało się wypełnić zapotrzebowania dla apteki: " + pharmacy.getName() + ", zabrakło: " +
                        pharmacy.getDailyRemains() + " szczepionek");
                succseed = false;
            }
        }
        writeAnwser();
        if (succseed) System.out.println("Konfiguracja wykonana pomyślnie!");

    }

    private void putIntoConnection(Connection c, Manufacturer m, Pharmacy p) {
        if (c == null || m == null || p == null) return;
        int minValue = minValueOfThree(c.getMaxDaily(), m.getDailyRemains(), p.getDailyRemains());
        usedConnections.put(c, usedConnections.get(c) + minValue);
        m.decreaseDailyRemains(minValue);
        p.decreaseDailyRemains(minValue);
    }

    private void setManufacturerConnection(Connection con, Manufacturer manufacturer, Pharmacy pharmacy, int remain) {
        List<Connection> connectionsOfManufacturer = connections.stream()
                .filter(connection -> connection.getManufacture().equals(manufacturer))
                .sorted().collect(Collectors.toList());
        int remaining = remain;
        while (remaining > 0 && con.getMaxDaily() > usedConnections.get(con) && !cannotSwap(connectionsOfManufacturer, con)) {
            double minDelta = Double.MAX_VALUE;
            Connection destination = con;
            Connection source = con;
            for (Connection cOM : connectionsOfManufacturer) {
                Manufacturer m = cOM.getManufacture();
                Pharmacy p = cOM.getPharmacy();
                if (p.equals(pharmacy) || usedConnections.get(cOM) == 0) {
                    continue;
                }
                double price = cOM.getPrice();
                Connection minPossible = cOM;
                double minPriceDelta = Double.MAX_VALUE;
                List<Connection> connectionsOfPharmacy = makeConnetionOfPharmacy(p);
                for (Connection cp : connectionsOfPharmacy) {
                    int vaccines = usedConnections.get(cp);
                    if (cp.getManufacture().equals(m) || isFull(vaccines, cp))
                        continue;
                    double priceDelta = cp.getPrice() - price;
                    minPossible = priceDelta < minPriceDelta ? cp : minPossible;
                    minPriceDelta = Math.min(priceDelta, minPriceDelta);
                }

                if (minPriceDelta < minDelta) {
                    destination = minPossible;
                    source = cOM;
                }
                minDelta = Math.min(minPriceDelta, minDelta);
            }

            Manufacturer mMim = destination.getManufacture();
            int freeDestinationSpace = destination.getMaxDaily() - usedConnections.get(destination);
            int minValue = minValueOfFour(mMim.getDailyRemains(), freeDestinationSpace, remaining, usedConnections.get(source));
            swapConnection(minValue, destination, source);
            remaining -= minValue;
        }
    }

    private int minValueOfFour(int a, int b, int c, int d) {
        if (a <= b && a <= c && a <= d)
            return a;
        else if (b <= a && b <= c && b <= d)
            return b;
        else if (c <= a && c <= b && c <= d)
            return c;
        else return d;
    }

    private boolean cannotSwap(List<Connection> connectionsOfManufacturer, Connection con) {
        if (connectionsOfManufacturer == null) return true;
        for (Connection cm : connectionsOfManufacturer) {
            Pharmacy p = cm.getPharmacy();
            if (p.equals(con.getPharmacy()))
                continue;
            List<Connection> connectionsOfPharmacy = makeConnetionOfPharmacy(p);
            for (Connection cp : connectionsOfPharmacy) {
                Manufacturer m = cp.getManufacture();
                if (m.equals(con.getManufacture()))
                    continue;
                if (usedConnections.get(cm) != 0 && usedConnections.get(cp) < cp.getMaxDaily() && m.getDailyRemains() != 0)
                    return false;
            }
        }
        return true;
    }

    private void swapConnection(int minValue, Connection destination, Connection source) {
        usedConnections.put(destination, usedConnections.get(destination) + minValue);
        usedConnections.put(source, usedConnections.get(source) - minValue);

        source.getManufacture().addDailyReamins(minValue);
        source.getPharmacy().addDailyReamins(minValue);

        destination.getManufacture().decreaseDailyRemains(minValue);
        destination.getPharmacy().decreaseDailyRemains(minValue);
    }

    private boolean isFull(int vaccines, Connection cp) {
        if (cp == null) return true;
        return cp.getMaxDaily() == vaccines || cp.getManufacture().getDailyRemains() == 0;
    }

    private List<Connection> makeConnetionOfPharmacy(Pharmacy pharmacy) {
        return connections.stream()
                .filter(connection -> connection.getPharmacy().equals(pharmacy))
                .collect(Collectors.toList());
    }

    private void writeAnwser() {
        Writer output;
        sum = 0;
        List<String> lines = new ArrayList<>();
        for (Map.Entry<Connection, Integer> entry : usedConnections.entrySet()) {
            if (entry.getValue() != 0) {
                Connection c = entry.getKey();
                Manufacturer m = c.getManufacture();
                Pharmacy p = c.getPharmacy();
                double cost = entry.getValue() * c.getPrice();
                sum += cost;
                lines.add(m.getName() + " -> " + p.getName() + " [Koszt = " + entry.getValue() + " * " + c.getPrice() + " = " + cost + " zł]");
            }
        }
        try {
            output = new BufferedWriter(new FileWriter("wynik.txt"));
            for (String s : lines)
                output.append(s).append("\n");
            output.append("Opłaty całkowite: ").append(String.valueOf(sum)).append(" zł");
            output.close();
        } catch (IOException e) {
            System.err.println("Błąd przy zapisywaniu do pliku wynikowego");
        }
    }

    private int minValueOfThree(int a, int b, int c) {
        if (a <= b && a <= c)
            return a;
        else if (b <= a && b <= c)
            return b;
        else return c;
    }
}
