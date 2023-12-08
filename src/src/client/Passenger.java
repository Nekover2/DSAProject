package src.src.client;

// Đại diện cho một hành khách, chứa thông tin như tuổi, có phải là phụ nữ có thai, thời
// gian đặt vé, và quãng đường đến đích.
public class Passenger {
    private String id;
    private int age;
    private boolean isPregnant;
    private Long bookingTimeMillis; // Use Long to allow null value
    private int destinationDistance;
    private int priority;
    // Priority attributes are removed from here

    // Constructor without priority calculation
    public Passenger(String id, int age, boolean isPregnant) {
        this.id = id;
        this.age = age;
        this.isPregnant = isPregnant;
        // Initialize bookingTimeMillis and destinationDistance with default values
        this.bookingTimeMillis = null;
        this.destinationDistance = -1; // -1 indicates no destination set
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public void setBookingTimeMillis(Long bookingTimeMillis) {
        this.bookingTimeMillis = bookingTimeMillis;
    }

    public void setDestinationDistance(int destinationDistance) {
        this.destinationDistance = destinationDistance;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public Long getBookingTimeMillis() {
        return bookingTimeMillis;
    }

    public int getDestinationDistance() {
        return destinationDistance;
    }

    private int priorityValueBeforeBooking() {
        if (this.age > 65) {
            priority += 20;
        }
        if (this.age > 50) {
            priority += 10;
        }
        if (this.isPregnant) {
            priority += 30;
        }
        return priority;
    }
}
