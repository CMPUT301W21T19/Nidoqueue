package com.example.nidoqueue;

public class User {

    private String userName;
    private String email;
    private String phoneNumber;
    private ArrayList<Experiment> createdExperiments;
    private ArrayList<Experiment> subscribedExperiments;

    public User() {
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
        createdExperiments = new ArrayList<>();
        subscribedExperiments = new ArrayList<>();
    }

    public User(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        createdExperiments = new ArrayList<>();
        subscribedExperiments = new ArrayList<>();
    }

    public void createExp(Experiment experiment) {
        createdExperiments.add(experiment);
    }

    public void subscribeExp(Experiment experiment) {
        subscribedExperiments.add(experiment);
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Experiment> getCreatedExp() {
        return createdExperiments;
    }

    public ArrayList<Experiment> getSubscribedExp() {
        return subscribedExperiments;
    }

    public void setCreatedExp(ArrayList<Experiment> createdExperiments) {
        this.createdExperiments = createdExperiments;
    }

    public void setSubscribedExp(ArrayList<Experiment> subscribedExperiments) {
        this.subscribedExperiments = subscribedExperiments;
    }
}
