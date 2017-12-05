package com.sinau.belajarrealm3;

import io.realm.RealmObject;

/**
 * Created by addin on 05/12/17.
 */

public class Spacecraft extends RealmObject {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}