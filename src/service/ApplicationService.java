package service;

import model.Application;
import model.Status;

import java.util.HashMap;

public class ApplicationService {
    private HashMap<Long, Application> appCollection = new HashMap<>();
    private long nextId = 1;
    public HashMap<Long, Application> getAppCollection() {
        return appCollection;
    }

    private void setInMap(Application model) {
        this.getAppCollection().put(model.getId(), model);

    }

    public void addApplication(String company, String payment, String position) {
        Application app = new Application(company, ++this.nextId, Status.APPLIED, payment, position);
        this.setInMap(app);
    }

    public void changeStatus(long id, Status status) throws ApplicationNotFoundException {
        Application app = this.appCollection.get(id);
        if (app == null) {
            throw new ApplicationNotFoundException(id);
        }
        app.setStatus(status);
    }

    public void writeAllApplications(){
        this.appCollection.values().stream()
                .forEach(System.out::println);
    }

}
