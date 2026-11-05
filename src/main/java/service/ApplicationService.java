package service;

import model.Application;
import model.Status;
import repository.ApplicationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public void addApplication(String company, Long payment, String position) {
        Application app = repository.save(company, position, payment);
        System.out.println(app);
    }

    public void changeStatus(long id, Status status) throws ApplicationNotFoundException {
        repository.updateStatus(id, status);
    }

    public void deleteApplication(long id) {
        repository.deleteById(id);
    }

    public void writeAllApplications() {
        List<Application> apps = repository.findAll();
        apps.forEach(System.out::println);
    }

    public Map<Status, Long> getStatistickByStatus() {
        return repository.getStatistic();
    }


}
