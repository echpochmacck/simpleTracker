package service;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long id) {
        super("Application with id "+ id + " wasn't found");
    }
}
