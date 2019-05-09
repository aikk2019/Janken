package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class End extends AppCompatActivity {
    Button mTitleButton;
    TextView mEndText;
    TextView mWinText;
    TextView mLoseText;
    TextView mDrawText;
    final String Draw = "引き分け";
    final String Win = "あなたの勝ち!!";
    final String Lose = "あなたの負け..";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        mTitleButton = findViewById(R.id.titleButton);
        mEndText = findViewById(R.id.endText);
        mWinText = findViewById(R.id.win);
        mLoseText = findViewById(R.id.lose);
        mDrawText = findViewById(R.id.draw);

        //Result画面から送られた値を取得する
        Intent intent = getIntent();
        int win = intent.getIntExtra("win",0);
        int lose = intent.getIntExtra("lose",0);
        int draw = intent.getIntExtra("draw",0);

        String winText = win+"勝";
        String loseText = lose+"敗";
        String drawText = draw+"引き分け";
        mWinText.setText(winText);
        mLoseText.setText(loseText);
        mDrawText.setText(drawText);

        if(win == lose){
            mEndText.setText(Draw);
        }else if(win > lose){
            mEndText.setText(Win);
        }else {
            mEndText.setText(Lose);
        }

        mTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Start.class);
                startActivity(intent);
            }
        });
    }
}
