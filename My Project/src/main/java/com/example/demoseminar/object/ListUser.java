package com.example.demoseminar.object;

public class ListUser {
//    private String userid;
    private String name;
    private String age;
    private String numclass;
    private String email;


    public ListUser() {
    }

    public ListUser( String name, String age, String numclass, String email) {
        //this.userid = userid;
        this.name = name;
        this.age = age;
        this.numclass = numclass;
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumclass() {
        return numclass;
    }

    public void setNumclass(String numclass) {
        this.numclass = numclass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ListUser{" +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", numclass='" + numclass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
