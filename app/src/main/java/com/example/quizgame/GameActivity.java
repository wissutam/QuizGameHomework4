package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizgame.model.WordItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
   private ImageView mQuestionImageView;
   private Button[] mButtons = new Button[4];

   private String mAnswerWord;
   private Random mRandom;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);

      mQuestionImageView = findViewById(R.id.question_image_view);
      mButtons[0] = findViewById(R.id.choice_1_button);
      mButtons[1] = findViewById(R.id.choice_2_button);
      mButtons[2] = findViewById(R.id.choice_3_button);
      mButtons[3] = findViewById(R.id.choice_4_button);

      mButtons[0].setOnClickListener(this);
      mButtons[1].setOnClickListener(this);
      mButtons[2].setOnClickListener(this);
      mButtons[3].setOnClickListener(this);

      mRandom = new Random();
      newQuiz();
   }

   private void newQuiz() {
      List<WordItem> mItemList = new ArrayList<>(Arrays.asList(WordListActivity.items));

      // สุ่ม index ของคำตอบ (คำถาม)
      int answerIndex = mRandom.nextInt(mItemList.size());
      // เข้าถึง WordItem ตาม index ที่สุ่มได้
      WordItem item = mItemList.get(answerIndex);
      // แสดงรูปคำถาม
      mQuestionImageView.setImageResource(item.imageResId);

      mAnswerWord = item.word;

      // สุ่มตำแหน่งปุ่มที่จะแสดงคำตอบ
      int randomButton = mRandom.nextInt(4);
      // แสดงคำศัพท์ที่เป็นคำตอบ
      mButtons[randomButton].setText(item.word);
      // ลบ WordItem ที่เป็นคำตอบออกจาก list
      mItemList.remove(item);

      // เอา list ที่เหลือมา shuffle
      Collections.shuffle(mItemList);

      // เอาคำศัพท์จาก list ที่ตำแหน่ง 0 ถึง 3 มาแสดงที่ปุ่ม 3 ปุ่มที่เหลือ โดยข้ามปุ่มที่เป็นคำตอบ
      for (int i = 0; i < 4; i++) {
         if (i == randomButton) { // ถ้า i คือ index ของปุ่มคำตอบ ให้ข้ามไป
            continue;
         }
         mButtons[i].setText(mItemList.get(i).word);
      }
   }

   @Override
   public void onClick(View view) {
      Button b = findViewById(view.getId());
      String buttonText = b.getText().toString();

      if (buttonText.equals(mAnswerWord)) {
         Toast.makeText(GameActivity.this, "ถูกต้องครับ", Toast.LENGTH_SHORT).show();
      } else {
         Toast.makeText(GameActivity.this, "ผิดครับ", Toast.LENGTH_SHORT).show();
      }

      newQuiz();
   }
}
