package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private int mGameNumber;
    private int mWin;
    private int mLose;
    private int mDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        ImageView topImage = findViewById(R.id.topImage);
        ImageView computerImage = findViewById(R.id.computerImage);
        ImageView playerImage = findViewById(R.id.playerImage);
        TextView wOrLText = findViewById(R.id.wOrLText);
        Button nextButton = findViewById(R.id.next);
        TextView restGameText = findViewById(R.id.restGameText);
        TextView winText = findViewById(R.id.win);
        TextView loseText = findViewById(R.id.lose);
        TextView drawText = findViewById(R.id.draw);

        //Selectから送られてきた手の情報を変数に代入する
        Intent intent = this.getIntent();
        int player = intent.getIntExtra("player",-1);
        int computer = intent.getIntExtra("computer",-1);
        mGameNumber = intent.getIntExtra("gameNumber",-1);
        mWin = intent.getIntExtra("win",0);
        mLose = intent.getIntExtra("lose",0);
        mDraw = intent.getIntExtra("draw",0);

        //プレイヤーの手の画像を設定する
        switch (player){
            case 0:
                playerImage.setImageResource(R.drawable.j_gu02);
                break;
            case 1:
                playerImage.setImageResource(R.drawable.j_ch02);
                break;
            case 2:
                playerImage.setImageResource(R.drawable.j_pa02);
                break;
        }

        //コンピュータの手の画像を設定する
        switch (computer){
            case 0:
                computerImage.setImageResource(R.drawable.j_gu02);
                break;
            case 1:
                computerImage.setImageResource(R.drawable.j_ch02);
                break;
            case 2:
                computerImage.setImageResource(R.drawable.j_pa02);
                break;
        }

        //勝敗を判定して画面上の画像と中央の文字列を設定する
        final String Draw = "引き分け";
        final String Win = "あなたの勝ち!!";
        final String Lose = "あなたの負け..";

        if(player == computer){
            Log.d("myLog",Draw);
            topImage.setImageResource(R.drawable.draw);
            wOrLText.setText(Draw);
            mDraw++;
        }else if((player+1)%3 == computer){
            Log.d("myLog",Win);
            topImage.setImageResource(R.drawable.win);
            wOrLText.setText((Win));
            mWin++;
        }else{
            Log.d("myLog",Lose);
            topImage.setImageResource(R.drawable.lose);
            wOrLText.setText(Lose);
            mLose++;
        }

        //
        String restGame = "残り対戦回数"+(mGameNumber-(mWin+mLose+mDraw))+"回";
        String win = mWin+"勝";
        String lose = mLose+"敗";
        String draw = mDraw+"引き分け";
        restGameText.setText(restGame);
        winText.setText(win);
        loseText.setText(lose);
        drawText.setText(draw);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //次の画面へ値を送る
                Intent intent = new Intent();
                intent.putExtra("gameNumber",mGameNumber);
                intent.putExtra("win",mWin);
                intent.putExtra("lose",mLose);
                intent.putExtra("draw",mDraw);

                if(mGameNumber == (mWin + mLose + mDraw)){
                    //合計結果表示画面へ遷移
                    intent.setClass(getApplicationContext(),End.class);
                    startActivity(intent);
                }else if (mGameNumber > (mWin + mLose + mDraw)){
                    //手選択画面へ遷移
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

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
