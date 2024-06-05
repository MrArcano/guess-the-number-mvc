package com.exercises.guessthenumbermvc.model;

public class Difficulty {
    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    private int maxAttempts;
    private Level level;

    public Difficulty(Level level, int maxNumber) {
        int maxIteration = (int) Math.ceil(Math.log(maxNumber) / Math.log(2));

        this.level = level;

        switch (level){
            case EASY:
                this.maxAttempts = (int)Math.ceil(maxIteration * 1.5);
                break;
            case MEDIUM:
                this.maxAttempts = maxIteration;
                break;
            case HARD:
                this.maxAttempts = (int)Math.ceil(maxIteration * 0.5);
                break;
        }
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
