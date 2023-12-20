package com.MarekLadziak.WebApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Admin extends User{

    @Column
    private boolean roleAdmin;


    public boolean isRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(boolean roleAdmin) {
        this.roleAdmin = roleAdmin;
    }
}
