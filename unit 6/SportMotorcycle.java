public class SportMotorcycle implements VehicleInfo, MotorcycleSpecs {
    private String motorcycleBrand;
    private String motorcycleModel;
    private int motorcycleYear;
    private int totalWheels;
    private String categoryType;

    public SportMotorcycle(String brand, String model, int year) {
        this.motorcycleBrand = brand;
        this.motorcycleModel = model;
        this.motorcycleYear = year;
    }

    public String retrieveMake() { return motorcycleBrand; }
    public String retrieveModel() { return motorcycleModel; }
    public int retrieveManufactureYear() { return motorcycleYear; }

    public void specifyWheelCount(int wheelTotal) { this.totalWheels = wheelTotal; }
    public int retrieveWheelCount() { return totalWheels; }

    public void specifyMotorcycleStyle(String styleCategory) { this.categoryType = styleCategory; }
    public String retrieveMotorcycleStyle() { return categoryType; }
}
