package com.company.mrbig.Model;

public class Student {
    private String firstname; // имя студента
    private String secondname; // фамилия студента
    private String patronymic; // отчество студента

    private String street; // улица
    private String home; // номер дома
    private String flat; // номер квартиры

    private int familySize; // количество членов семьи
    private double livingSquare; // жилое пространство на семью
    private double onePersonSquare; // жилое пространство на одного человека

    public Student (){

    }

    public Student(Student student){
        this.firstname = student.firstname;
        this.secondname = student.secondname;
        this.patronymic = student.patronymic;
        this.street = student.street;
        this.home = student.home;
        this.flat = student.flat;
        this.familySize = student.familySize;
        this.livingSquare = student.livingSquare;
        this.onePersonSquare = student.onePersonSquare;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public double getLivingSquare() {
        return livingSquare;
    }

    public void setLivingSquare(double livingSquare) {
        this.livingSquare = livingSquare;
    }

    public double getOnePersonSquare() {
        return onePersonSquare;
    }

    public void setOnePersonSquare(double onePersonSquare) {
        this.onePersonSquare = onePersonSquare;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getName(){
        return (getSecondname() + " " + getFirstname().charAt(0) + ". " + getPatronymic().charAt(0) + ".");
    }

    public String getAdress(){
        return (getStreet() + ", д." + getHome() + ", кв." + getFlat());
    }
}
