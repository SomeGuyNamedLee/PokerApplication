package com.leenicholls.pokerapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by leeni_000 on 2/19/2016.
 */




public class Hand {

    ArrayList<Card> playerHand = new ArrayList<>();

    boolean isStraightFlush = false;

    int cardCount = 0;

    int suitValue;  // holds the value of the suit
    int topFlushValue = 0;


    int[] value;
    int[] ranks = new int[13];
    int[] straightRanks = new int[4];
    int[] straightFlushRanks = new int[4];
    int[] flushRanks = new int[4];
    int[] flushValues = new int [4];
    int[] orderedRanks = new int[5];
    int index = 0;
    int flushIndex = 1;
    int topFlushValueIndex = -1;


    Hand(Card c){

        playerHand.add(c);
        Collections.sort(playerHand);


        boolean isStraight = false;
        boolean isFlush = false;

        int sameCards = 1;
        int sameCards2 = 1;
        int largeGroupRank = 0;
        int smallGroupRank = 0;
        int topStraightValue = 0;



        for (int i=0; i<13; i++){
            ranks[i] = 0;
        }

        //******** Add the cards in the player's hand to the array ***********
        for (int i=0; i < playerHand.size(); i++){
            ranks[playerHand.get(i).getFaceValue() - 2]++;
        }

        for (int x=0; x<13; x++){
            Log.i("Ranks", Integer.toString(ranks[x]));
        }

        //******** Check the array for any pairs or full house ****************
        for (int i=12; i >= 0; i--){

            if (ranks[i] > sameCards){

                if (sameCards != 1){

                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;

                }

                sameCards = ranks[i];
                largeGroupRank = i;
            } else if (ranks[i] > sameCards2){

                sameCards2 = ranks[i];
                smallGroupRank = i;

            }

        }


        //************* Check for a Flush *******************

        //*** Zero out the Array ****
        for (int i=0; i < 4; i++){
            flushValues[i] = 0;
        }

        for (int i = 0; i < playerHand.size(); i++) {
            suitValue = playerHand.get(i).getSuitValue() - 1;
            flushValues[suitValue]++;
        }


        for (int i=0; i<4; i++){
            Log.i("Flush Value", Integer.toString(flushValues[i]));
            if (flushValues[i] >= 5){
                isFlush = true;
            }
        }

        if (isFlush){

            int flushIndex = -1;

            for (int i=0; i<4; i++) {

                if (flushValues[i] >= 5){
                    flushIndex = i;
                }

                // ****** Determine the highest flush card ******
                if (flushIndex == 0) {

                    for (i = 0; i < playerHand.size(); i++) {

                        if (playerHand.get(i).getSuitValue() == 1 && playerHand.get(i).getFaceValue() > topFlushValue) {
                            topFlushValue = playerHand.get(i).getFaceValue();
                            topFlushValueIndex = 1;
                        }
                    }

                }else if (flushIndex == 1) {

                    for (i = 0; i < playerHand.size(); i++) {
                        if (playerHand.get(i).getSuitValue() == 2 && playerHand.get(i).getFaceValue() > topFlushValue) {
                            topFlushValue = playerHand.get(i).getFaceValue();
                            topFlushValueIndex = 2;
                        }
                    }

                }else if (flushIndex == 2) {

                    for (i = 0; i < playerHand.size(); i++) {
                        if (playerHand.get(i).getSuitValue() == 3 && playerHand.get(i).getFaceValue() > topFlushValue) {
                            topFlushValue = playerHand.get(i).getFaceValue();
                            topFlushValueIndex = 3;
                        }
                    }

                }else if (flushIndex == 3) {

                    for (i = 0; i < playerHand.size(); i++) {
                        if (playerHand.get(i).getSuitValue() == 4 && playerHand.get(i).getFaceValue() > topFlushValue) {
                            topFlushValue = playerHand.get(i).getFaceValue();
                            topFlushValueIndex = 4;
                        }
                    }

                }

                flushRanks[0] = topFlushValue;

            }

            //************* Check for a straight *******************
            if (cardCount > 4) {

                for (int i = 0; i < 8; i++) {

                    if (ranks[i] != 0 && ranks[i + 1] != 0 && ranks[i + 2] != 0 && ranks[i + 3] != 0 && ranks[i + 4] != 0) {

                        if (playerHand.get(i).getSuitValue() == playerHand.get(i+1).getSuitValue() &&
                                playerHand.get(i).getSuitValue() == playerHand.get(i+2).getSuitValue() &&
                                playerHand.get(i).getSuitValue() == playerHand.get(i+3).getSuitValue() &&
                                playerHand.get(i).getSuitValue() == playerHand.get(i+4).getSuitValue()){

                            isStraightFlush = true;
                            topStraightValue = i + 6;
                            straightFlushRanks[0] = ranks[i + 4];
                            straightFlushRanks[1] = ranks[i + 3];
                            straightFlushRanks[2] = ranks[i + 2];
                            straightFlushRanks[3] = ranks[i + 1];
                            straightFlushRanks[5] = ranks[i];

                        }else {

                            isStraight = true;
                            topStraightValue = i + 6;
                            straightRanks[0] = ranks[i + 4];
                            straightRanks[1] = ranks[i + 3];
                            straightRanks[2] = ranks[i + 2];
                            straightRanks[3] = ranks[i + 1];
                            straightRanks[5] = ranks[i];
                        }

                    }

                }
            }

            //***** Check to see if the straight is Ace low **********
            if (ranks[0] !=0 && ranks[1] !=0 && ranks[2] !=0 && ranks[3] !=0 && ranks[12] != 0){
                isStraight = true;
                topStraightValue = 5;
                straightRanks[0] = ranks[0];
                straightRanks[1] = ranks[1];
                straightRanks[2] = ranks[2];
                straightRanks[3] = ranks[3];
                straightRanks[5] = ranks[4];
            }
        }

        Log.i("Line Break", "/n");


        for (int x=13; x>=2; x--) {
            if (ranks[x]==1)
            //we have already written code to handle the case
            //of their being two cards of the same rank
            {
                orderedRanks[index]=x;
                index++;
            }
        }

        //start hand evaluation
        if ( sameCards==1 ) {    //if we have no pair...
            value[0]=1;          //this is the lowest type of hand, so it gets the lowest value
            value[1]=orderedRanks[0];  //the first determining factor is the highest card,
            value[2]=orderedRanks[1];  //then the next highest card,
            value[3]=orderedRanks[2];  //and so on
            value[4]=orderedRanks[3];
            value[5]=orderedRanks[4];
        }

        if (sameCards==2 && sameCards2==1) //if 1 pair
        {
            value[0]=2;                //pair ranked higher than high card
            value[1]=largeGroupRank;   //rank of pair
            value[2]=orderedRanks[0];  //next highest cards.
            value[3]=orderedRanks[1];
            value[4]=orderedRanks[2];
        }

        if (sameCards==2 && sameCards2==2) //two pair
        {
            value[0]=3;    //rank of greater pair
            value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank;    //rank of smaller pair
            value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank;
            value[3]=orderedRanks[0];  //extra card
        }

        if (sameCards==3 && sameCards2!=2)    //three of a kind (not full house)
        {
            value[0]=4;
            value[1]= largeGroupRank;
            value[2]=orderedRanks[0];
            value[3]=orderedRanks[1];
        }

        if (isStraight)
        {
            value[0]=5;
            value[1]=straightRanks[0];
            value[2]=straightRanks[1];
            value[3]=straightRanks[2];
            value[4]=straightRanks[3];
            value[5]=straightRanks[4];

            //if we have two straights,
            //the one with the highest top cards wins
        }

        if (isFlush)
        {

            for (int x=13; x>=2; x--){
                if (ranks[x] != 1 && playerHand.get(x).getSuitValue() == topFlushValueIndex){
                    orderedRanks[flushIndex]=x;
                    flushIndex++;
                }
            }

            value[0]=6;
            value[1]=orderedRanks[0]; //tie determined by ranks of cards
            value[2]=orderedRanks[1];
            value[3]=orderedRanks[2];
            value[4]=orderedRanks[3];
            value[5]=orderedRanks[4];
        }

        if (sameCards==3 && sameCards2==2)  //full house
        {
            value[0]=7;
            value[1]=largeGroupRank;
            value[2]=smallGroupRank;
        }

        if (sameCards==4)  //four of a kind
        {
            value[0]=8;
            value[1]=largeGroupRank;
            value[2]=orderedRanks[0];
        }

        if (isStraightFlush)  //straight flush
        {
            value[0]=9;
            value[1]=straightFlushRanks[0];
            value[2]=straightFlushRanks[1];
            value[3]=straightFlushRanks[2];
            value[4]=straightFlushRanks[3];
            value[5]=straightFlushRanks[4];

        }

        if (sameCards==5)  //five of a kind
        {
            value[0]=10;
            value[1]=largeGroupRank;
            value[2]=orderedRanks[0];
        }



    }


}

