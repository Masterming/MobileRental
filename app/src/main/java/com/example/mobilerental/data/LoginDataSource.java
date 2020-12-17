package com.example.mobilerental.data;

import com.example.mobilerental.Customer;
import com.example.mobilerental.Rental;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<Customer> login(String username, String password) {

        try {
            Customer c = Rental.login(Integer.parseInt(username));
            if(c == null)
                throw new Exception("User not found");
            return new Result.Success<>(c);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        Rental.logout();
    }
}