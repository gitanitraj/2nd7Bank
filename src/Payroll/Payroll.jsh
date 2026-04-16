import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Timecard {
    String Name;
    double HoursWorked;
    double HourlyRate;

    public Timecard(String name, double hoursWorked, double hourlyRate) {
        this.Name = name;
        this.HoursWorked = hoursWorked;
        this.HourlyRate = hourlyRate;
    }
 
    public String payrollString() {
        // // do the math
        double tax_rate = 0.2;
        double gross_pay = this.HourlyRate * this.HoursWorked;
        double taxes = gross_pay * tax_rate;
        double net_pay = gross_pay - taxes;
        // produce the output string
        return String.format("%s, %.2f, %.2f, %.2f", this.Name, gross_pay, taxes, net_pay);
        //return "";
    }

   public String toString() {
        return String.format("%s, %.1f, %.1f", this.Name,
        this.HoursWorked, this.HourlyRate);
    }
}

public class Payroll {
    public static ArrayList<Timecard> readData(String fileName) {
        Timecard t = null;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Timecard> data = new ArrayList<>();

            // Skip header line
            if (scanner.hasNextLine()) {
                //System.out.println("-");
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0].trim();
                //System.out.printf("* %s\n", name);
                double hoursWorked = Double.parseDouble(parts[1].trim());
                //System.out.printf("* %f\n", hoursWorked);
                double hourlyRate = Double.parseDouble(parts[2].trim());
                //System.out.printf("* %f\n", hourlyRate);
                t = new Timecard(name, hoursWorked, hourlyRate);
                data.add(t);
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        } catch (Exception e) {
            System.out.print("ERRORERROR ");
            System.out.println(e);
            return null;
        }
    }
}
// Start with the reading of data into a list
ArrayList<Timecard> input_data = Payroll.readData("input.data");

public class ReadData {
    public static ArrayList<Timecard> readData(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Timecard> data = new ArrayList<>();

            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double hoursWorked = Double.parseDouble(parts[1].trim());
                double hourlyRate = Double.parseDouble(parts[2].trim());
                data.add(new Timecard(name, hoursWorked, hourlyRate));
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }
}


String outp;
// print out the data in the list. See `toString()` method above.
System.out.println("Name,Total Pay,Taxes, Net Pay");
for (Timecard tc : input_data) {
    outp = tc.payrollString();
    System.out.println(outp);
}

// TOTALS GO HERE
double totalGross = 0;
double totalTaxes = 0;
double totalNet = 0;
double totalPayroll = 0;

for (Timecard t : input_data) {
    double gross = t.HoursWorked * t.HourlyRate;
    double taxes = gross * 0.2;
    double net = gross - taxes;
    totalGross += gross;
    totalTaxes += taxes;
    totalNet += net;
}

System.out.println("----------------------------------");
System.out.println(String.format("Total Gross Pay: $%,.2f", totalGross));
System.out.println(String.format("Total Taxes:     $%,.2f", totalTaxes));
System.out.println(String.format("Total Net Pay:   $%,.2f", totalNet));

}

