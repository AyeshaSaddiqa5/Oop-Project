public class Flat extends House{
    public Flat(int houseNumber, HouseStatus status, double rentAmount) {
        super(houseNumber, status, rentAmount);
    }

    @Override
    public void displayDetails() {
        System.out.println("Flat Number: " + getHouseNumber() +
                ", Status: " + getStatus() +
                ", Rent Amount: $" + getRentAmount() +
                ", Tenant: " + (getTenant() != null ? getTenant().getName() : "None") +
                ", Rent Paid: " + isRentPaid());
    }
}
