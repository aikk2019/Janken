package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    ImageView mTopImage;
    ImageView mComputerImage;
    ImageView mPlayerImage;
    TextView mWOrLText;
    final String Draw = "引き分け";
    final String Win = "あなたの勝ち!!";
    final String Lose = "あなたの負け..";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        mTopImage = findViewById(R.id.topImage);
        mComputerImage = findViewById(R.id.computerImage);
        mPlayerImage = findViewById(R.id.playerImage);
        mWOrLText = findViewById(R.id.wOrLText);

        //Selectから送られてきた手の情報を変数に代入する
        Intent intent = this.getIntent();
        int player = intent.getIntExtra("player",-1);
        int computer = intent.getIntExtra("computer",-1);
        Log.d("myLog","player:"+player+" computer:"+computer);

        //プレイヤーの手の画像を設定する
        switch (player){
            case 0:
                mPlayerImage.setImageResource(R.drawable.j_gu02);
                break;
            case 1:
                mPlayerImage.setImageResource(R.drawable.j_ch02);
                break;
            case 2:
                mPlayerImage.setImageResource(R.drawable.j_pa02);
                break;
        }

        //コンピュータの手の画像を設定する
        switch (computer){
            case 0:
                mComputerImage.setImageResource(R.drawable.j_gu02);
                break;
            case 1:
                mComputerImage.setImageResource(R.drawable.j_ch02);
                break;
            case 2:
                mComputerImage.setImageResource(R.drawable.j_pa02);
                break;
        }

        //勝敗を判定して画面上の画像と中央の文字列を設定する
        if(player == computer){
            Log.d("myLog",Draw);
            mTopImage.setImageResource(R.drawable.draw);
            mWOrLText.setText(Draw);
        }else if((player+1)%3 == computer){
            Log.d("myLog",Win);
            mTopImage.setImageResource(R.drawable.win);
            mWOrLText.setText((Win));
        }else{
            Log.d("myLog",Lose);
            mTopImage.setImageResource(R.drawable.lose);
            mWOrLText.setText(Lose);
        }
    }
}
