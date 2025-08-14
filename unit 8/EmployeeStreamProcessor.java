import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class StaffMember {
    private String fullName;
    private int employeeAge;
    private String workDivision;
    private double monthlyEarnings;

    public StaffMember(String fullName, int employeeAge, String workDivision, double monthlyEarnings) {
        this.fullName = fullName;
        this.employeeAge = employeeAge;
        this.workDivision = workDivision;
        this.monthlyEarnings = monthlyEarnings;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public String getWorkDivision() {
        return workDivision;
    }

    public double getMonthlyEarnings() {
        return monthlyEarnings;
    }
}

public class EmployeeStreamProcessor {
    public static void main(String[] args) {
        List<StaffMember> teamRoster = new ArrayList<>();

        // Reading data from file
        try {
            List<String> dataRows = Files.readAllLines(Paths.get("employees.txt"));
            for (String row : dataRows) {
                String[] tokens = row.split(",");
                if (tokens.length == 4) {
                    String empName = tokens[0].trim();
                    int empYears = Integer.parseInt(tokens[1].trim());
                    String deptTitle = tokens[2].trim();
                    double empIncome = Double.parseDouble(tokens[3].trim());
                    teamRoster.add(new StaffMember(empName, empYears, deptTitle, empIncome));
                }
            }
        } catch (IOException e) {
            System.out.println(" Failed to read file: " + e.getMessage());
            return;
        }

        // Function interface to map employee to "Name - Department"
        Function<StaffMember, String> labelByRole = staff ->
                staff.getFullName() + " - " + staff.getWorkDivision();

        // Creating a new list of concatenated strings
        List<String> labeledEntries = teamRoster.stream()
                .map(labelByRole)
                .collect(Collectors.toList());

        System.out.println(" Name & Department Tags:");
        labeledEntries.forEach(System.out::println);

        // Compute average monthly earnings
        double computedAverage = teamRoster.stream()
                .mapToDouble(StaffMember::getMonthlyEarnings)
                .average()
                .orElse(0.0);

        System.out.printf("\n Average Salary: %.2f\n", computedAverage);

        // Filteri,ng employees aged above 30
        List<StaffMember> veteranWorkers = teamRoster.stream()
                .filter(staff -> staff.getEmployeeAge() > 30)
                .collect(Collectors.toList());

        System.out.println("\n Employees over 30:");
        for (StaffMember veteran : veteranWorkers) {
            System.out.printf("%s, Age: %d, Dept: %s, Salary: $%.2f\n",
                    veteran.getFullName(), veteran.getEmployeeAge(),
                    veteran.getWorkDivision(), veteran.getMonthlyEarnings());
        }
    }
}

