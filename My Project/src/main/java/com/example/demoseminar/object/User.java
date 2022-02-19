package com.example.demoseminar.object;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String age;
    private String gender;
    private String email;
    private String numclass;
    private String shoolyear;
    private String address;
    private int    phonenumber;


    public User() {
    }

    public User( String name, String age, String gender, String email, String numclass, String shoolyear, String address, int phonenumber) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.numclass = numclass;
        this.shoolyear = shoolyear;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumclass() {
        return numclass;
    }

    public void setNumclass(String numclass) {
        this.numclass = numclass;
    }


    public String getShoolyear() {
        return shoolyear;
    }

    public void setShoolyear(String shoolyear) {
        this.shoolyear = shoolyear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Map<String,Object> toMap(){
//        HashMap<String,Object> result = new HashMap<>();
//        result.put("name",name);
//        result.put("age",age);
//        result.put("gender",gender);
//        result.put("school_year",shoolyear);
//        result.put("class",numclass);
//        result.put("address",address);
//        result.put("phone_number",phonenumber);
//        result.put("email",email);
//
//        return result;
//    }
}


