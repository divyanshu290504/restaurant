package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;
import java.util.List;

public class OrderObserver {
    public interface Observer {
    void update(OrderItemObserver orderItem);
}

// Subject (OrderItem)
public static class OrderItemObserver {
    private List<Observer> observers = new ArrayList<>();
    private Long id;
    private String orderStatus;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        notifyObservers();
    }
}

// Concrete Observer (Customer)
public static class CustomerObserver implements Observer {
    @Override
    public void update(OrderItemObserver orderItem) {
        // Update customer based on order item status
        System.out.println("Customer notified: Order Item " + orderItem.getId() + " status changed to " + orderItem.getOrderStatus());
    }
}

// Concrete Observer (Delivery Personnel)
public static class DeliveryGuyObserver implements Observer {
    @Override
    public void update(OrderItemObserver orderItem) {
        // Update delivery personnel based on order item status
        System.out.println("Delivery Personnel notified: Order Item " + orderItem.getId() + " status changed to " + orderItem.getOrderStatus());
    }
}
}
