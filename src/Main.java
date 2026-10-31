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
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                consoleAddApp(scanner, service);
                break;
            case ("2"):
                consoleChangeStatus(scanner, service);
                break;

        }

        }
    }
    private static void consoleAddApp(Scanner scanner, ApplicationService service) {
        System.out.println("Write company, payment, position");
        String company = scanner.nextLine();
        String payment = scanner.nextLine();
        String position = scanner.nextLine();
        service.addApplication(company, payment, position);
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
        } catch (ApplicationNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException  e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}