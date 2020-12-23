package com.example.kma_application.Models;

import java.io.Serializable;

public class Admin extends InfoModel implements Serializable {
    public Admin(String phone, String name, String id) {
        super(phone, name, id);
    }

    public Admin(String _id, String phone, String name, String id) {
        super(_id, phone, name, id);
    }
}
