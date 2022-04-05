package aisd.proj_ind.gr26;

public class Manufacturer {
    private final int id;
    private final String name;
    private int dailyRemains;

    public Manufacturer(int id, String name, int dailyProduction) {
        this.id = id;
        this.name = name;
        this.dailyRemains = dailyProduction;
    }

    public void addDailyReamins(int value) {
        this.dailyRemains += value;
    }

    public void decreaseDailyRemains(int value) {
        this.dailyRemains -= value;
    }

    public String getName() {
        return name;
    }

    public int getDailyRemains() {
        return dailyRemains;
    }
}
