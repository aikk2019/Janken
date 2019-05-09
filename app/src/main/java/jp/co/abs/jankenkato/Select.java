package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Select extends AppCompatActivity {
    private ImageView mgu;
    private ImageView mch;
    private ImageView mpa;
    private TextView mTopText;
    private int mComputer;  //コンピュータの手
    private Random rnd;
    private int mGameNumber;    //タイトル画面で設定した対戦回数
    private int mWin;
    private int mLose;
    private int mDraw;

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

        long seed = System.currentTimeMillis();
        rnd = new Random(seed);

        //Start画面から送られた値を取得する
        Intent intent = this.getIntent();
        mGameNumber = intent.getIntExtra("gameNumber",-1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //コンピュータ側の手をランダムに選択する
        mComputer = rnd.nextInt(3);

        //
        String topText = mWin + mLose + mDraw + 1 + "戦目じゃーんけーん...";
        mTopText.setText(topText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        //Result画面から送られた値を取得する
        mGameNumber = intent.getIntExtra("gameNumber",-1);
        mWin = intent.getIntExtra("win",0);
        mLose = intent.getIntExtra("lose",0);
        mDraw = intent.getIntExtra("draw",0);

        onResume();
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
                //戻るボタンが押されたときデフォルトの処理をしない
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
