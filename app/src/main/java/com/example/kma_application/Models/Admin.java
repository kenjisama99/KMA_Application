package com.example.kma_application.Models;

import java.io.Serializable;

public class Admin extends Person implements Serializable {
    public Admin(String phone, String name, String email) {
        super(phone, name, email);
    }
}
