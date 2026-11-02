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
        Application app = new Application(company, this.nextId++, Status.APPLIED, payment, position);
        this.setInMap(app);
    }

    public void changeStatus(long id, Status status) throws ApplicationNotFoundException {
        Application app = this.appCollection.get(id);
        if (app == null) {
            throw new ApplicationNotFoundException(id);
        }
        app.setStatus(status);
    }

 public void deleteApplication(long id) throws ApplicationNotFoundException {
     if (!this.appCollection.containsKey(id)) {
         throw new ApplicationNotFoundException(id);
     }
            this.appCollection.remove(id);
    }

    public void writeAllApplications(){
        this.appCollection.values().stream()
                .forEach(System.out::println);
    }

    public HashMap<Status, Integer> getStatistickByStatus() {
        HashMap<Status, Integer> statistickMap = new HashMap<>();

        this.appCollection.values().stream().forEach((val) -> {
            if (!statistickMap.containsKey(val.getStatus())) {
                statistickMap.put(val.getStatus(), 1);
            } else {
                Integer i = statistickMap.get(val.getStatus());
                statistickMap.put(val.getStatus(), ++i);
            }
        });
        return statistickMap;
    }


}
