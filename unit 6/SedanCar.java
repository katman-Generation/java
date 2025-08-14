public class SedanCar implements VehicleInfo, CarDetails {
    private String carBrand;
    private String carModel;
    private int carYear;
    private int doorTotal;
    private String fuelVariant;

    public SedanCar(String brand, String model, int year) {
        this.carBrand = brand;
        this.carModel = model;
        this.carYear = year;
    }

    public String retrieveMake() { return carBrand; }
    public String retrieveModel() { return carModel; }
    public int retrieveManufactureYear() { return carYear; }

    public void assignDoorCount(int doorCount) { this.doorTotal = doorCount; }
    public int fetchDoorCount() { return doorTotal; }

    public void assignFuelType(String fuelCategory) { this.fuelVariant = fuelCategory; }
    public String fetchFuelType() { return fuelVariant; }
}
