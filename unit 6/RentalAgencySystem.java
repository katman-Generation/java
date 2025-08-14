import java.util.Scanner;

public class RentalAgencySystem {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        try {
            // Car input
            System.out.println("Enter Car Brand, Model, Year:");
            String carBrand = inputScanner.next();
            String carModel = inputScanner.next();
            int carYear = inputScanner.nextInt();
            SedanCar userCar = new SedanCar(carBrand, carModel, carYear);

            System.out.println("Enter Door Count and Fuel Type:");
            userCar.assignDoorCount(inputScanner.nextInt());
            userCar.assignFuelType(inputScanner.next());

            // Motorcycle input
            System.out.println("Enter Motorcycle Brand, Model, Year:");
            String bikeBrand = inputScanner.next();
            String bikeModel = inputScanner.next();
            int bikeYear = inputScanner.nextInt();
            SportMotorcycle userMotorcycle = new SportMotorcycle(bikeBrand, bikeModel, bikeYear);

            System.out.println("Enter Number of Wheels and Motorcycle Style:");
            userMotorcycle.specifyWheelCount(inputScanner.nextInt());
            userMotorcycle.specifyMotorcycleStyle(inputScanner.next());

            // Truck input
            System.out.println("Enter Truck Brand, Model, Year:");
            String truckBrand = inputScanner.next();
            String truckModel = inputScanner.next();
            int truckYear = inputScanner.nextInt();
            FreightTruck userTruck = new FreightTruck(truckBrand, truckModel, truckYear);

            System.out.println("Enter Cargo Capacity (tons) and Transmission Mode:");
            userTruck.defineCargoCapacity(inputScanner.nextDouble());
            userTruck.defineTransmissionMode(inputScanner.next());

            // Display Results
            System.out.println("\n--- Vehicle Information Summary ---");

            System.out.println("Car  " + userCar.retrieveMake() + " " + userCar.retrieveModel() +
                               ", Year: " + userCar.retrieveManufactureYear() +
                               ", Doors: " + userCar.fetchDoorCount() +
                               ", Fuel: " + userCar.fetchFuelType());

            System.out.println("Motorcycle  " + userMotorcycle.retrieveMake() + " " + userMotorcycle.retrieveModel() +
                               ", Year: " + userMotorcycle.retrieveManufactureYear() +
                               ", Wheels: " + userMotorcycle.retrieveWheelCount() +
                               ", Style: " + userMotorcycle.retrieveMotorcycleStyle());

            System.out.println("Truck  " + userTruck.retrieveMake() + " " + userTruck.retrieveModel() +
                               ", Year: " + userTruck.retrieveManufactureYear() +
                               ", Capacity: " + userTruck.getCargoCapacity() + " tons" +
                               ", Transmission: " + userTruck.getTransmissionMode());

        } catch (Exception exception) {
            System.out.println("⚠️ Input error. Please enter correct data types as prompted.");
        }

        inputScanner.close();
    }
}

