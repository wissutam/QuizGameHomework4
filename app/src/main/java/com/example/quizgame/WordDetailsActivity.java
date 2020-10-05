package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizgame.model.WordItem;
import com.google.gson.Gson;

public class WordDetailsActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_word_details);

      Intent intent = getIntent();
      String itemJson = intent.getStringExtra("item");
      WordItem item = new Gson().fromJson(itemJson, WordItem.class);

      /*String word = intent.getStringExtra("word");
      int img = intent.getIntExtra("image", 0);*/

      ImageView iv = findViewById(R.id.image_view);
      TextView tv = findViewById(R.id.word_text_view);

      iv.setImageResource(item.imageResId);
      tv.setText(item.word);
   }
}
