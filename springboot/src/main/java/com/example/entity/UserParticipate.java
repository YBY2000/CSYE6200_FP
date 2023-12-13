package com.example.entity;

public class UserParticipate {
    private int id;
    private String title;
    private int user_id;
    private int exercise_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getExerciseId() {
        return exercise_id;
    }

    public void setExerciseId(int exercise_id) {
        this.exercise_id = exercise_id;
    }
}
