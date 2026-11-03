package service;

import model.Application;
import model.Status;
import repository.ApplicationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public void addApplication(String company, Long payment, String position) {
       Application app =  repository.save(company, position, payment);
       System.out.println(app);
    }


    private HashMap<Long, Application> appCollection = new HashMap<>();
    private long nextId = 1;
    public HashMap<Long, Application> getAppCollection() {
        return appCollection;
    }

    private void setInMap(Application model) {
        this.getAppCollection().put(model.getId(), model);

    }



    public void changeStatus(long id, Status status) throws ApplicationNotFoundException {
        Application app = this.appCollection.get(id);
        if (app == null) {
            throw new ApplicationNotFoundException(id);
        }
        app.setStatus(status);
    }

 public void deleteApplication(long id) {
            this.appCollection.remove(id);
    }

    public void writeAllApplications(){
        this.appCollection.values().stream()
                .forEach(System.out::println);
    }

    public Map<Status, Long> getStatistickByStatus() {

        return this.appCollection.values().stream().collect(Collectors.groupingBy(x->x.getStatus(), Collectors.counting()));
    }


}
