public class FreightTruck implements VehicleInfo, TruckFeatures {
    private String truckBrand;
    private String truckModel;
    private int truckYear;
    private double loadCapacity;
    private String gearSystem;

    public FreightTruck(String brand, String model, int year) {
        this.truckBrand = brand;
        this.truckModel = model;
        this.truckYear = year;
    }

    public String retrieveMake() { return truckBrand; }
    public String retrieveModel() { return truckModel; }
    public int retrieveManufactureYear() { return truckYear; }

    public void defineCargoCapacity(double tonCapacity) { this.loadCapacity = tonCapacity; }
    public double getCargoCapacity() { return loadCapacity; }

    public void defineTransmissionMode(String gearType) { this.gearSystem = gearType; }
    public String getTransmissionMode() { return gearSystem; }
}
