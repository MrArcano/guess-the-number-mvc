package com.exercises.guessthenumbermvc.model;

import java.util.Random;

public class Game {
    private int maxAttempts;
    private int remaingAttempts;
    private int usedAttempts;
    private int maxNumber;
    private int minNumber;
    private int checkNumber;
    private int nRand;

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getRemaingAttempts() {
        return remaingAttempts;
    }

    public int getUsedAttempts() {
        return usedAttempts;
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public int getnRand() {
        return nRand;
    }

    public void startGame(){
        // setting field
        usedAttempts = 0;
        minNumber = 1;
        remaingAttempts = maxAttempts;

        // Genero un valore random tra 1 e nMax
        Random random = new Random();
        nRand = random.nextInt(maxNumber) + 1;
        System.out.println(nRand); //debug

    }

    public int checkRandomNumberMatch(int value){
        // se hai finito i tentativi o hai già trovato il numero, non fare nulla
        if (remaingAttempts == 0 || checkNumber == nRand) return 0;

        System.out.println("dopo controllo vinto o perso");

        // controllo che il valore sia differente da quello precedente
        if(checkNumber == value) return 1;

        // salvo il nuovo valore
        checkNumber = value;

        // aggiorno i contatori dei tentativi
        usedAttempts++;
        remaingAttempts--;

        // troppo alto
        if (checkNumber > nRand) {
            maxNumber = checkNumber;
            return 2;
        }

        // troppo basso
        if (checkNumber < nRand) {
            minNumber = checkNumber;
            return 3;
        }

        // controllo se hai vinto o perso
        if (checkNumber == nRand) {
            return 4; // vinto
        } else if (remaingAttempts == 0) {
            return 5; // perso
        }

      return 0;
    }
}
