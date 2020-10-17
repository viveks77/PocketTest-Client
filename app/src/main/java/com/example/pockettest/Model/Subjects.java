package com.example.pockettest.Model;

public class Subjects {

    private String subjects;
    private int thumbnail;

    public Subjects() {
    }

    public Subjects(String subjects, int thumbnail) {
        this.subjects = subjects;
        this.thumbnail=thumbnail;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
