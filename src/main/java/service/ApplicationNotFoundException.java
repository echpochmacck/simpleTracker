package service;

public class ApplicationNotFoundException extends Exception {
    public ApplicationNotFoundException(Long id) {
        super("Application with id "+ id + " wasn't found");
    }
}
