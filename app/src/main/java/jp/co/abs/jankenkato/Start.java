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
private Spinner mGameNumberSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        mGameNumberSpinner = findViewById(R.id.gameNumberSpinner);
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
