package model;

import javax.swing.*;

public class person {
    String FirstName;
    String LastName;
    String DOB;
    String Age;
    String number;
    String Email;
    String StreetLine1;
    String StreetLine2="";
    String City;
    String Country;
    String Affiliation="";
    String Major="";
    String Career="";
    String Degree1="";
    String Degree1Startdate="";
    String Degree1End="";
    String Degree2="";
    String Degree2Startdate="";
    String Degree2End="";
    ImageIcon photo;

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setStreetLine1(String streetLine1) {
        StreetLine1 = streetLine1;
    }

    public void setStreetLine2(String streetLine2) {
        StreetLine2 = streetLine2;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setAffiliation(String affiliation) {
        Affiliation = affiliation;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public void setCareer(String career) {
        Career = career;
    }

    public void setDegree1(String degree1) {
        Degree1 = degree1;
    }

    public void setDegree1Startdate(String degree1Startdate) {
        Degree1Startdate = degree1Startdate;
    }

    public void setDegree1End(String degree1End) {
        Degree1End = degree1End;
    }

    public void setDegree2(String degree2) {
        Degree2 = degree2;
    }

    public void setDegree2Startdate(String degree2Startdate) {
        Degree2Startdate = degree2Startdate;
    }

    public void setDegree2End(String degree2End) {
        Degree2End = degree2End;
    }



    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAge() {
        return Age;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return Email;
    }

    public String getStreetLine1() {
        return StreetLine1;
    }

    public String getStreetLine2() {
        return StreetLine2;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getAffiliation() {
        return Affiliation;
    }

    public String getMajor() {
        return Major;
    }

    public String getCareer() {
        return Career;
    }

    public String getDegree1() {
        return Degree1;
    }

    public String getDegree1Startdate() {
        return Degree1Startdate;
    }

    public String getDegree1End() {
        return Degree1End;
    }

    public String getDegree2() {
        return Degree2;
    }

    public String getDegree2Startdate() {
        return Degree2Startdate;
    }

    public String getDegree2End() {
        return Degree2End;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public ImageIcon getPhoto() {
        return photo;
    }
}
