package com.example.quiz;

public class User {
    private String nom;
    private int lastScore;

    public User() {
    }

    public User(String nom, int lastScore) {
        this.nom = nom;
        this.lastScore = lastScore;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }
}
