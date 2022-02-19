package com.example.demoseminar.subject;

public class Subject {
    private String K2016;
    private String K2017;
    private String K2018;
    private String K2019;
    private String K2020;

    public Subject() {
    }

    public Subject(String k2016, String k2017, String k2018, String k2019, String k2020) {
        K2016 = k2016;
        K2017 = k2017;
        K2018 = k2018;
        K2019 = k2019;
        K2020 = k2020;
    }

    public String getK2016() {
        return K2016;
    }

    public void setK2016(String k2016) {
        K2016 = k2016;
    }

    public String getK2017() {
        return K2017;
    }

    public void setK2017(String k2017) {
        K2017 = k2017;
    }

    public String getK2018() {
        return K2018;
    }

    public void setK2018(String k2018) {
        K2018 = k2018;
    }

    public String getK2019() {
        return K2019;
    }

    public void setK2019(String k2019) {
        K2019 = k2019;
    }

    public String getK2020() {
        return K2020;
    }

    public void setK2020(String k2020) {
        K2020 = k2020;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "K2016='" + K2016 + '\'' +
                ", K2017='" + K2017 + '\'' +
                ", K2018='" + K2018 + '\'' +
                ", K2019='" + K2019 + '\'' +
                ", K2020='" + K2020 + '\'' +
                '}';
    }
}
