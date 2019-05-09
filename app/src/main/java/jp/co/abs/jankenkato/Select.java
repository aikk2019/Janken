package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Select extends AppCompatActivity {
    ImageView mgu;
    ImageView mch;
    ImageView mpa;
    TextView mTopText;
    int mComputer;  //コンピュータの手
    Random rnd;
    int mGameNumber;    //タイトル画面で設定した対戦回数
    int mWin;
    int mLose;
    int mDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        mgu = findViewById(R.id.gu);
        mch = findViewById(R.id.ch);
        mpa = findViewById(R.id.pa);
        mTopText = findViewById(R.id.topText);

        //グー　チョキ　パー　にクリックリスナーをつける
        mgu.setOnClickListener(clickListener);
        mch.setOnClickListener(clickListener);
        mpa.setOnClickListener(clickListener);

        //
        long seed = System.currentTimeMillis();
        rnd = new Random(seed);

        //Start画面、またはResult画面から送られた値を取得する
        Intent intent = this.getIntent();
        mGameNumber = intent.getIntExtra("gameNumber",-1);
        mWin = intent.getIntExtra("win",0);
        mLose = intent.getIntExtra("lose",0);
        mDraw = intent.getIntExtra("draw",0);
        Log.d("myLog","gameNumber:"+mGameNumber);

        String topText = mWin+mLose+mDraw+1+"戦目じゃーんけーん...";
        mTopText.setText(topText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //コンピュータ側の手をランダムに選択する
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
            intent.putExtra("gameNumber",mGameNumber);
            intent.putExtra("win",mWin);
            intent.putExtra("lose",mLose);
            intent.putExtra("draw",mDraw);
            startActivityForResult(intent, 0);
        }
    };

}
