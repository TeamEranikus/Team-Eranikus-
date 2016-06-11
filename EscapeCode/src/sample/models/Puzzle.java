package sample.models;

import javafx.scene.image.Image;

public class Puzzle {
    private String question;
    private String correctAnswer;
    private String hint;
    private Image image;
    private String nextClue;


    public Puzzle(String question, String correctAnswer, String hint, Image image, String nextClue) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.hint = hint;
        this.image = image;
        this.nextClue = nextClue;
    }

    public String getHint() {
        return hint;
    }

    public Image getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getNextClue() {
        return nextClue;
    }

    public boolean checkCorrectAnswer(String answer){
        if(answer.equals(getCorrectAnswer())){
            return true;
        }
        return false;
    }
}

