package com.example.waytodream;

import java.util.ArrayList;

public class Stage {
    private String name;

    private ArrayList<Goal> goals = new ArrayList<Goal>();

    private Byte check;

    Stage (String name){
        this.name = name;
        goals.add(new Goal(""));
    }

    public String getStageName() {
        return name;
    }

    public void setStageName(String name) {
        this.name = name;
    }

    public ArrayList <Goal> getGoals() {
        return goals;
    }

    public int getGoalSize()
    {
        return goals.size();
    }

    public void setGoals(ArrayList <Goal> goals) {
        this.goals = goals;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public Byte getCheck() {
        calculate();
        return check;
    }

    public void setCheck(boolean bool) {
        check = 2;
        for (Goal goal : goals) {
            goal.setCheck(bool);
        }
    }

    private void calculate() {

        Byte count = 0;
        for (Goal goal : goals) {
            if (goal.getCheck() == 2) {
                count++;
            }
        }

        if(count == goals.size()) {
            check = 2;
        } else if (count == 0) {
            check = 0;
        } else {
            check = 1;
        }
    }
}
