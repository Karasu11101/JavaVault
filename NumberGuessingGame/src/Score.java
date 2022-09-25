public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    protected void increasePlayerScore() {
        score +=1;
    }

    public int getScore() {
        return score;
    }
}
