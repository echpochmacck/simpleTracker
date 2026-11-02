import model.Status;
import service.ApplicationNotFoundException;
import service.ApplicationService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationService service = new ApplicationService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. add new Application");
            System.out.println("2. change status");
            System.out.println("3. delete application");
            System.out.println("4. to see statistic");
            System.out.println("0. to close program");
            String command = scanner.nextLine();
            switch (command) {
                case ("1"):
                    consoleAddApp(scanner, service);
                    break;
                case ("2"):
                    consoleChangeStatus(scanner, service);
                    break;
                case ("3"):
                    consoleDelete(scanner, service);
                    break;
                    case ("4"):
                    consoleStatistick(service);
                    break;
                case ("0"):
                    return;
                default:
                    System.out.println("There is no such command");
                    break;
            }

        }
    }

    private static void consoleAddApp(Scanner scanner, ApplicationService service) {
        try {
            System.out.println("Write company, payment, position");
            String company = scanner.nextLine();
            Long payment = Long.valueOf(scanner.nextLine());
            String position = scanner.nextLine();
            service.addApplication(company, payment, position);
            System.out.println("All applications");
            service.writeAllApplications();
        }catch(NumberFormatException e) {
            System.out.println("Payment should be numeric");
        }
    }

    private static void consoleDelete(Scanner scanner, ApplicationService service) {

        try {
            System.out.println("Write id to delete application");
            String id = scanner.nextLine();
            Long formatted = Long.valueOf(id);
            service.deleteApplication(formatted);
        } catch(NumberFormatException e) {
            System.out.println("Id should be numeric");
        }
        System.out.println("All applications");
        service.writeAllApplications();
    }

    private static void consoleChangeStatus(Scanner scanner, ApplicationService service) {
        try {
            System.out.println("Write id, status");
            long id = Long.parseLong(scanner.nextLine());
            String status = scanner.nextLine().trim().toUpperCase();
            Status st = Status.valueOf(status);
            service.changeStatus(id, st);
            service.writeAllApplications();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void consoleStatistick(ApplicationService service) {
        System.out.println(service.getStatistickByStatus());
    }
}