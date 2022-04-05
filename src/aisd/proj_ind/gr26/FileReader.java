package aisd.proj_ind.gr26;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {

    private final Map<Integer, Manufacturer> manufacturers = new HashMap<>();
    private final Map<Integer, Pharmacy> pharmacies = new HashMap<>();
    private final List<Connection> connections = new ArrayList<>();

    public Map<Integer, Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void loadData(String filePath) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new java.io.FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku");
        }
        String line;
        int counter = 0;
        while ((line = br.readLine()) != null) {
            if (line.charAt(0) == '#') {
                counter++;
                continue;
            }
            switch (counter) {
                case 1 -> addManufacture(line);
                case 2 -> addPharmacy(line);
                case 3 -> addConnection(line);
            }
        }

    }

    private void addConnection(String line) {
        String[] data = line.split(" \\| ");
        if (data.length != 4)
            wrongDataException();
        int idM = Integer.parseInt(data[0]);
        int idP = Integer.parseInt(data[1]);
        int daily = Integer.parseInt(data[2]);
        double price = Double.parseDouble(data[3]);
        Manufacturer manufacturer = manufacturers.get(idM);
        Pharmacy pharmacy = pharmacies.get(idP);
        if (manufacturer == null || pharmacy == null)
            wrongDataException();
        connections.add(new Connection(manufacturer, pharmacy, daily, price));
    }

    private void wrongDataException() {
        throw new NullPointerException("Błędnie wprowadzone dane");
    }

    private void addPharmacy(String line) {
        String[] data = line.split(" \\| ");
        if (data.length != 3)
            wrongDataException();
        int idP = Integer.parseInt(data[0]);
        String n = data[1];
        int daily = Integer.parseInt(data[2]);
        pharmacies.put(idP, new Pharmacy(idP, n, daily));
    }

    private void addManufacture(String line) {
        String[] data = line.split(" \\| ");
        if (data.length != 3)
            wrongDataException();
        int idM = Integer.parseInt(data[0]);
        String n = data[1];
        int daily = Integer.parseInt(data[2]);
        manufacturers.put(idM, new Manufacturer(idM, n, daily));
    }
}
