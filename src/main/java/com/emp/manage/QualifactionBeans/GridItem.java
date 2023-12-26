package com.emp.manage.QualifactionBeans;

import java.io.Serializable;

public class GridItem implements Serializable {
    private static final long serialVersionUID = 1L; // optional, but recommended for Serializable classes

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}