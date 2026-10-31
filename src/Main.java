import service.ApplicationService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationService service = new ApplicationService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
        System.out.println("Write company, payement, position");
        String company = scanner.nextLine();
        String payement = scanner.nextLine();
        String position = scanner.nextLine();
        service.addApplication(company, payement, position);
        System.out.println("Your applicatoins - " + service.getAppCollection());
        }

    }
}