package com.company.mrbig.Model;

import java.util.ArrayList;
import java.util.Random;

public class GeneratorDB {
    public GeneratorDB() {
    }

    public ArrayList<Student> genStudList(int count){
        ArrayList<Student> students = new ArrayList<>();
        Student addStud = new Student();

        for(int number = 0; number < count; number++){
            addStud.setFirstname(genFirstname());
            addStud.setSecondname(genSecondname());
            addStud.setPatronymic(genPatronymic());
            addStud.setStreet(genStreet());
            addStud.setHome(String.valueOf(genDigital(200)));
            addStud.setFlat(String.valueOf(genDigital(500)));
            addStud.setFamilySize(genDigital(8));
            addStud.setLivingSquare(genSquare());
            addStud.setOnePersonSquare
                    (round(addStud.getLivingSquare()/addStud.getFamilySize()));
            students.add(addStud);
            addStud = new Student();
        }

        return students;
    }

    public String genSecondname() {
        Random randNumber = new Random();
        int rand = randNumber.nextInt(10) + 1;
        switch (rand){
            case 1:
                return "Иванов";
            case 2:
                return "Петров";
            case 3:
                return "Криг";
            case 4:
                return "Сидоров";
            case 5:
                return "Козлов";
            case 6:
                return "Лазовский";
            case 7:
                return "Дудюк";
            case 8:
                return "Херманович";
            case 9:
                return "Мамцевич";
            case 10:
                return "Шестель";
            case 11:
                return "Новодворский";
            case 12:
                return "Батуро";
            case 13:
                return "Градович";
            case 14:
                return "Васюкевич";
            case 15:
                return "Мицкевич";
            case 16:
                return "Голенков";
            case 17:
                return "Гикович";
            case 18:
                return "Вайнерович";
            case 19:
                return "Шпак";
            case 20:
                return "Кулешов";
        }
        return "Admin";
    }

    public String genFirstname() {
        Random randNumber = new Random();
        int rand = randNumber.nextInt(10) + 1;
        switch (rand){
            case 1:
                return "Иван";
            case 2:
                return "Петр";
            case 3:
                return "Сергей";
            case 4:
                return "Анатолий";
            case 5:
                return "Дмитрий";
            case 6:
                return "Антон";
            case 7:
                return "Максим";
            case 8:
                return "Даниил";
            case 9:
                return "Николай";
            case 10:
                return "Влад";
        }
        return "Admin";
    }

    public String genPatronymic() {
        Random randNumber = new Random();
        int rand = randNumber.nextInt(10) + 1;
        switch (rand){
            case 1:
                return "Иванович";
            case 2:
                return "Петрович";
            case 3:
                return "Артёмович";
            case 4:
                return "Дмитриевич";
            case 5:
                return "Антонович";
            case 6:
                return "Максимович";
            case 7:
                return "Татьянович";
            case 8:
                return "Николаеыич";
            case 9:
                return "Сергеевич";
            case 10:
                return "Павлович";
        }
        return "Admin";
    }

    public String genStreet(){
        Random randNumber = new Random();
        int rand = randNumber.nextInt(10) + 1;
        switch (rand){
            case 1:
                return "ул.Якуба Коласа";
            case 2:
                return "пр-т Держинского";
            case 3:
                return "ул. Ленина";
            case 4:
                return "пр-т Независимости";
            case 5:
                return "ул. Чурлёниса";
            case 6:
                return "ул. Грачёва";
            case 7:
                return "ул. Трусова";
            case 8:
                return "пр-т Машерова";
            case 9:
                return "пр-т Победы";
            case 10:
                return "ул. Уличная";
        }
        return "Admin";
    }

    public int genDigital(int dig){
        Random randNumber = new Random();
        int rand = randNumber.nextInt(dig)+1;
        return rand;
    }

    public double genSquare(){
        Random randNumber = new Random();
        int rand = randNumber.nextInt(200)+50;
        return (double)rand;
    }

    private double round(double dig){
        dig *= 100;
        int magic = (int) Math.round(dig);
        dig = (double)magic/100;
        return dig;
    }
}
