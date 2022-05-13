package com.example.waytodream;

import java.util.ArrayList;

public class Goal {

    private String name;

    private String nameCases;
    private ArrayList <CaseC> cases;

    private Byte check;
    private Byte progres = 0;

    Goal (String name){
        this.name = name;
    }

    public void Calculate() {

        Byte count = 0;
        for (CaseC caseI : cases) {
            if (caseI.getCheck()) {
                count++;
            }
        }

        if(count == cases.size()) {
            check = 2;
        } else if (count == 0) {
            check = 0;
        } else {
            check = 1;
        }
        int result = count/cases.size();
        progres = (byte) result;
    }

    public boolean getStatus() {
        for (CaseC caseI : cases) {
            if (caseI.getCaseName() == "") {
                return false;
            }
        }
        return true;
    }

    public String getGoalName() {
        return name;
    }

    public void setGoalName(String name) {
        this.name = name;
    }

    public String getNameCases() {
        return nameCases;
    }

    public void setNameCases(String name) {
        this.nameCases = name;
    }

    public ArrayList <CaseC> getCases() {
        return cases;
    }

    public void setCases(ArrayList <CaseC> cases) {
        this.cases = cases;
    }

    public void addCase(CaseC casec) {
        cases.add(casec);
    }

    public Byte getCheck() {
        return check;
    }

    public void setCheck(boolean bool) {
        check = 2;
        for (CaseC caseI : cases) {
           caseI.setCheck(bool);
        }
    }

    public Byte getProgres() {
        return progres;
    }
}
