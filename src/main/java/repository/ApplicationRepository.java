package repository;

import model.Application;
import model.Status;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {
    Application save(String company, String position, long payment);
    Optional<Application> findById(long id);
    List<Application> findAll();
    void updateStatus (long id, Status status);
    void deleteById(long id);
}
