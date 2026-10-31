package service;

import model.Application;
import model.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class ApplicationService {
    private HashMap<Long, Application> appCollection = new HashMap<>();
    private long nextId = 1;
    public HashMap<Long, Application> getAppCollection() {
        return appCollection;
    }

    private void setInMap(Application model) {
        this.getAppCollection().put(model.getId(), model);

    }

    public void addApplication(String company, String payement, String position) {
        Application app = new Application(Status.APPLIED, payement, position, company, ++this.nextId);
        this.setInMap(app);
    }

}
