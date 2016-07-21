package com.apps.webpouyaco.bookstore.models;

/**
 * Created by Ashkan on 7/21/2016.
 */
public class BookModel {

    String name;
    String email;
    PhoneModel phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneModel getPhone() {
        return phone;
    }

    public void setPhone(PhoneModel phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        String jsonResponseBook = "";
        jsonResponseBook += "Name: " + name + "\n\n";
        jsonResponseBook += "Email: " + email + "\n\n";
        jsonResponseBook += "Home: " + phone.getHome() + "\n\n";
        jsonResponseBook += "Mobile: " + phone.getMobile() + "\n\n";
        return jsonResponseBook;
    }
}
