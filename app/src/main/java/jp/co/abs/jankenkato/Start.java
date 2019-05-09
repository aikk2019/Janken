package jp.co.abs.jankenkato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Start extends AppCompatActivity {
Spinner mGameNumberSpinner;
Button mStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        mGameNumberSpinner = findViewById(R.id.gameNumberSpinner);
        mStartButton = findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLog",""+mGameNumberSpinner.getSelectedItem().toString());
                String temp = mGameNumberSpinner.getSelectedItem().toString();
                temp = temp.substring(0,temp.length()-1);
                Intent intent = new Intent();
                intent.putExtra("gameNumber",Integer.parseInt(temp));
                intent.setClass(getApplicationContext(),Select.class);
                startActivity(intent);
            }
        });
    }
}
