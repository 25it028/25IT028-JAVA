abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    abstract double monthlySalary();
}
class FullTime extends Employee {
    double salary;
    FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }
    double monthlySalary() {
        return salary;
    }
}
class PartTime extends Employee {
    double hours;
    double rate;
    PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }
    double monthlySalary() {
        return hours * rate;
    }
}
class Intern extends Employee {
    double stipend;
    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }
    double monthlySalary() {
        return stipend;
    }
}
public class Payroll {
    public static void main(String[] args) {

        Employee[] employees = {
            new FullTime("John", 101, 50000),
            new PartTime("Sara", 102, 80, 200),
            new Intern("Mike", 103, 10000)
        };
        double total = 0;
        for (int i = 0; i < employees.length; i++) {
            double salary = employees[i].monthlySalary();
            System.out.println("Name: " + employees[i].name);
            System.out.println("Salary: " + salary);
            total = total + salary;
            if (employees[i] instanceof Intern) {
                System.out.println("Note: This employee is an Intern");
            }
            System.out.println();
        }
        System.out.println("Total Payroll = " + total);
    }
}