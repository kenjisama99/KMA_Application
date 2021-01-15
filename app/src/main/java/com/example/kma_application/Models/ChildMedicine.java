package com.example.kma_application.Models;

public class ChildMedicine {
    private String childName;
    private String listMedicine;

    public ChildMedicine(String childName, String listMedicine) {
        this.childName = childName;
        this.listMedicine = listMedicine;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getListMedicine() {
        return listMedicine;
    }

    public void setListMedicine(String listMedicine) {
        this.listMedicine = listMedicine;
    }
}
