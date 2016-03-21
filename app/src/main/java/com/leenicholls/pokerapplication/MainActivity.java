package com.leenicholls.pokerapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random;

    ImageView cardImage;
    TextView playerHandTextView;

    int padding = 0;
    int cardCount = 0;
    int deckValue;  // holds the value of the card for the deck

    int[] value;


    LinearLayout layout;
    HorizontalScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);
        scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        playerHandTextView = (TextView) findViewById(R.id.playerHandTextView);

        random = new Random();
        value = new int[6];


    }

    public Card drawCard(View view){

        deckValue = random.nextInt(51) + 1;
        Card card = new Card(deckValue);

        // Assign the image to the card
        cardImage = new ImageView(this);
        Context context = cardImage.getContext();
        int id = context.getResources().getIdentifier(card.toStringBrief(), "drawable", context.getPackageName());
        cardImage.setImageResource(id);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(padding, 0, 0, 0);
        cardImage.setLayoutParams(layoutParams);
        layout.addView(cardImage);
        padding = -80;
        cardCount++;

        return card;

    }

}



