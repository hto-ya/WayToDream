package com.example.waytodream;

public class UserDream {

    private String dream;
    private String motivation;

    UserDream(String dream, String motivation){
        this.dream = dream;
        this.motivation = motivation;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getAge() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    @Override
    public  String toString(){
        return dream + " : " + motivation;
    }
}
