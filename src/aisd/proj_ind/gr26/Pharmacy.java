package aisd.proj_ind.gr26;

public class Pharmacy {
    private final int id;
    private final String name;
    private int dailyRemains;

    public Pharmacy(int id, String name, int dailyRequired) {
        this.id = id;
        this.name = name;
        this.dailyRemains = dailyRequired;
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
