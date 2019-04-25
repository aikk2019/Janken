package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

//コメント追加テスト

public class Select extends AppCompatActivity {
    ImageView mgu;
    ImageView mch;
    ImageView mpa;
    int mComputer;  //コンピュータの手

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        //グー　チョキ　パー　にクリックリスナーをつける
        mgu = findViewById(R.id.gu);
        mch = findViewById(R.id.ch);
        mpa = findViewById(R.id.pa);
        mgu.setOnClickListener(clickListener);
        mch.setOnClickListener(clickListener);
        mpa.setOnClickListener(clickListener);

        //コンピュータ側の手をランダムに選択する
        long seed = System.currentTimeMillis();
        Random rnd = new Random(seed);
        mComputer = rnd.nextInt(3);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int player = -1;    //プレイヤーの手
            if (v.getId() == mgu.getId()) {
                player = 0;
            } else if (v.getId() == mch.getId()) {
                player = 1;
            } else if (v.getId() == mpa.getId()) {
                player = 2;
            }
            //結果表示画面に遷移する
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("computer",mComputer);
            intent.putExtra("player",player);
            startActivityForResult(intent, 0);
        }
    };

}
