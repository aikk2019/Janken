package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        Button titleButton = findViewById(R.id.titleButton);
        TextView endTextView = findViewById(R.id.endText);
        TextView winTextView = findViewById(R.id.win);
        TextView loseTextView = findViewById(R.id.lose);
        TextView drawTextView = findViewById(R.id.draw);

        //Result画面から送られた値を取得する
        Intent intent = getIntent();
        int win = intent.getIntExtra("win",0);
        int lose = intent.getIntExtra("lose",0);
        int draw = intent.getIntExtra("draw",0);

        //勝敗内訳のテキストを設定する
        String winText = win+"勝";
        String loseText = lose+"敗";
        String drawText = draw+"引き分け";
        winTextView.setText(winText);
        loseTextView.setText(loseText);
        drawTextView.setText(drawText);

        //合計結果を判定
        final String Draw = "引き分け";
        final String Win = "あなたの勝ち!!";
        final String Lose = "あなたの負け..";
        if(win == lose){
            endTextView.setText(Draw);
        }else if(win > lose){
            endTextView.setText(Win);
        }else {
            endTextView.setText(Lose);
        }

        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Start.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
