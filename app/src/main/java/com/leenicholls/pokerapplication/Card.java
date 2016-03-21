package com.leenicholls.pokerapplication;

import android.util.Log;

import java.util.Random;

/**
 * Created by leeni_000 on 2/19/2016.
 */
public class Card implements Comparable<Card> {


    // Suit value constants
    public static final int CLUBS = 1;
    public static final int DIAMONDS = 2;
    public static final int HEARTS = 3;
    public static final int SPADES = 4;

    // Suit name constants
    private final String[] suitNames = new String[]{"clubs", "diamonds", "hearts", "spades"};

    // Face value constants
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    // Face name constants
    private final String[] faceNames = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    // Card values:
    private int deckValue;  // 1-52 (C, D, H, S)
    private int faceValue;  // 2-14 (2-10, J, Q, K, A)
    private int suitValue;  // 1-4 (C, D, H, S)

    int fuckYouGitHub = 0;


    public Card(int deckValue){

        if (deckValue < 1 || deckValue > 52){
            throw new IllegalArgumentException("Card is invalid");
        }

        this.deckValue = deckValue;
        faceValue = toFaceValue(deckValue);
        suitValue = toSuitValue(deckValue);

    }

    public Card (int faceValue, int suitValue){

        if (faceValue < 2 || faceValue > 14){
            throw new IllegalArgumentException("Face value is invalid");
        }

        if (suitValue < 1 || suitValue > 4){
            throw new IllegalArgumentException("Suit value is invalid");
        }

        this.faceValue = faceValue;
        this.suitValue = suitValue;
        this.deckValue = toDeckValue(faceValue, suitValue);
    }

    public boolean equals (Object other){

        if (!(other instanceof Card)){
            return false;
        }
        Card cardOther = (Card) other;
        if (deckValue != cardOther.deckValue){
            return false;
        }
        return true;

    }

    public int getDeckValue(){
        return deckValue;
    }

    public String getFaceName(){

        return faceNames[faceValue - 2];
    }

    public int getFaceValue(){

        return faceValue;
    }

    public String getSuitName(){

        return suitNames[suitValue - 1];
    }

    public int getSuitValue(){

        return suitValue;
    }


    public static int toDeckValue(int faceValue, int suitValue){

        if (faceValue < 2 || faceValue > 14){
            throw new IllegalArgumentException("Face value is invalid");
        }

        if (suitValue < 1 || suitValue > 4){
            throw new IllegalArgumentException("Suit value is invalid");
        }

        int deckValue = (13 * (suitValue -1)) + faceValue - 1;
        return deckValue;

    }

    public static int toFaceValue(int deckValue){

        int faceValue = (deckValue % 13) + 1;
        if (faceValue == 1){
            faceValue = 14;
        }
        return faceValue;

    }

    public String toString(){

        String string = getFaceName() + " of " + getSuitName();
        return string;
    }


    public String toStringBrief(){

        String faceBrief;
        if (faceValue <= 10){
            faceBrief = getFaceName();
        }else {

            faceBrief = Integer.toString(getFaceValue());

        }
        String suitBrief = getSuitName().substring(0, 1);
        String brief = suitBrief + faceBrief;
        return brief;

    }

    public static int toSuitValue (int deckValue){

        int suitValue = deckValue / 13;
        if (deckValue % 13 != 0){
            suitValue++;
        }
        return suitValue;
    }

    @Override
    public int compareTo(Card otherCard) {
        if (otherCard == null) {

            throw new NullPointerException("Attempted to compare " + this + " to null");
        } else if (this.faceValue < otherCard.faceValue){
            return 1;
        } else if (this.faceValue > otherCard.faceValue){
            return -1;
        }

        return 0;
    }






    /*

    private Random random = new Random();


    public String cardValue(){

        String suitName;
        int cardValue;
        String cardName;
        int deckValue;

        // draw a random card and determine which card it is based on value
        deckValue = random.nextInt(50) + 2;

        Log.i("Deck Value", Integer.toString(deckValue));

        if (deckValue >=1 && deckValue <= 13){

            suitName = "c";
            cardValue = (deckValue % 13) + 1;
            Log.i("Card Value", Integer.toString(cardValue));
            Log.i("Card Suit", suitName);

        } else if (deckValue >=14 && deckValue <= 26){

            suitName = "d";
            cardValue = (deckValue % 13) + 1;
            Log.i("Card Value", Integer.toString(cardValue));
            Log.i("Card Suit", suitName);

        } else if (deckValue >=26 && deckValue <= 39) {

            suitName = "h";
            cardValue = (deckValue % 13) + 1;
            Log.i("Card Value", Integer.toString(cardValue));
            Log.i("Card Suit", suitName);
        } else {

            suitName = "s";
            cardValue = (deckValue % 13) + 1;
            Log.i("Card Value", Integer.toString(cardValue));
            Log.i("Card Suit", suitName);

        }

        // Set the card image string in order to pull the appropriate image
        cardName = suitName + cardValue;

        return cardName;

    }

    */

}
