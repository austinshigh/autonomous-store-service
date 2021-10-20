package com.cscie97.store.model;

public interface Client {

    public void detach(Observer observer);

    public void attach(Observer observer);
}
