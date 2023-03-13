package fr.snake.beans;

import fr.snake.dto.GameDTO;

public class Game {
    private int id;
    private int userID;
    private boolean won;
    private int score;
    private String date;

    public Game(int id, int userID, boolean won, int score, String date) {
        this.id = id;
        this.userID = userID;
        this.won = won;
        this.score = score;
        this.date = date;
    }

    public Game(GameDTO game) {
        userID = game.getUser_id();
        won = game.isWon();
        score = game.getScore();
        date = game.getDate();
    }

    public Game() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
