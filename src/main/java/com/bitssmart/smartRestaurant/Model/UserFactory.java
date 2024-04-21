package com.bitssmart.smartRestaurant.Model;

public class UserFactory {

    public static User createUser(UserRoles role, String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
        switch (role) {
            case CUSTOMER:
                return createCustomer(user);
            case DELIVERY_GUY:
                return createDeliveryGuy(user);
            default:
                throw new IllegalArgumentException("Invalid user role");
        }
    }

    private static User createCustomer(User user) {
        Customer customer = new Customer();
        customer.setUserid(user);
        customer.setIsVIP(false); // Default value
        user.setCustomer(customer); // Set customer to the user
        return user;
    }

    private static User createDeliveryGuy(User user) {
        DeliveryGuy deliveryGuy = new DeliveryGuy();
        deliveryGuy.setUserid(user);
        deliveryGuy.setIsApproved(false); // Default value
        user.setDeliveryGuy(deliveryGuy); // Set deliveryGuy to the user
        return user;
    }
}
