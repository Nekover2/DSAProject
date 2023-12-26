package neko.dsa.bus;

public class Passenger implements Comparable<Passenger>{

    static int idCounter = 0;
    String id;
    boolean isPregnant;
    int age;
    String pickupLocation;
    String destination;

    long timeRegistered;

    int priority;
    public Passenger(boolean isPregnant, int age, String pickupLocation, String destination, long timeRegistered) {
        this.id = String.valueOf(idCounter++);
        this.isPregnant = isPregnant;
        this.age = age;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.timeRegistered = timeRegistered;
        this.priority = calculatePriority();
    }

    private int calculatePriority() {
        int priority = 0;
        if (age >= 65) {
            priority += 15;
        }
        if (age >= 50) {
            priority += 10;
        }
        if (isPregnant) {
            priority += 20;
        }
        return priority;
    }

    public String getId() {
        return id;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(long timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public int getPriority() {
        return priority;
    }
    @Override
    public int compareTo(Passenger o) {
        return o.getPriority() - this.getPriority() - (o.getTimeRegistered() - this.getTimeRegistered() < 0 ? 30 : -30);
    }
}
