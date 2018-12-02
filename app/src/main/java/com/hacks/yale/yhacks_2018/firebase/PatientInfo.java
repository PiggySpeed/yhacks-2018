package com.hacks.yale.yhacks_2018.firebase;

import java.util.ArrayList;

public class PatientInfo {

        private String name;
        private int age;
        private String sex;
        private int GFR;
        private ArrayList<String> allergies = new ArrayList<>();
        private ArrayList<String> conditions = new ArrayList<>();
        private ArrayList<String> medications = new ArrayList<>();

        public PatientInfo() {
        }

        public PatientInfo(String name, int age, String sex, int GFR, ArrayList<String> allergies, ArrayList<String> conditions, ArrayList<String> medications) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.GFR = GFR;
            this.allergies = allergies;
            this.conditions = conditions;
            this.medications = medications;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getGFR() {
            return GFR;
        }

        public void setGFR(int GFR) {
            this.GFR = GFR;
        }

        public ArrayList<String> getAllergies() {
            return allergies;
        }

        public void setAllergies(ArrayList<String> allergies) {
            this.allergies = allergies;
        }

        public ArrayList<String> getConditions() {
            return conditions;
        }

        public void setConditions(ArrayList<String> conditions) {
            this.conditions = conditions;
        }

        public ArrayList<String> getMedications() {
            return medications;
        }

        public void setMedications(ArrayList<String> medications) {
            this.medications = medications;
        }
}
