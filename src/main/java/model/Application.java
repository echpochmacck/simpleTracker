package model;

import java.time.LocalDate;

public class Application {
    private final long id;
    private String company;
    private String position;
    private Long payment;
    private Status status;
    private final LocalDate createdAt;

    public Application( String company, long id, Status status, Long payment, String position, LocalDate createdAt) {
        this.status = status;
        this.payment = payment;
        this.position = position;
        this.company = company;
        this.createdAt = createdAt ;
        this.id = id;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return " Application - {" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", payment='" + payment + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
