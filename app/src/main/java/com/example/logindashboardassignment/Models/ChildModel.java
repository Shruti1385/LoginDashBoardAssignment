package com.example.logindashboardassignment.Models;

//Data object class for child items - image,feedback,name
//using hard code image so not a part of object
public class ChildModel {
    private String clientName;
    private String clientFeedback;

    public ChildModel(String client_name, String client_feedback){
        this.clientName = client_name;
        this.clientFeedback = client_feedback;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientFeedback() {
        return clientFeedback;
    }

    public void setClientFeedback(String clientFeedback) {
        this.clientFeedback = clientFeedback;
    }
}
