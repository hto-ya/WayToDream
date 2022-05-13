package com.example.waytodream;

public class CaseC {
    private String caseName;
    private Boolean check = false;

    CaseC (String name){
        this.caseName = name;
    }

    CaseC (String name, Boolean check){
        this.caseName = name;
        this.check = check;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String name) {
        this.caseName = name;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

}
