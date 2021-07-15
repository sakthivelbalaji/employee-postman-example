package com.em.app.model;
import org.springframework.stereotype.Service;

@Service
public class EmployeeModel {

    public boolean validateUser(String userid, String password) {
        return userid.equalsIgnoreCase("sakthi") && password.equalsIgnoreCase("welcome");

    }

}
