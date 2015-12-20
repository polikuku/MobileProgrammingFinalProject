package finaltermproject.everydaylifelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
* Created by Xnote on 2015-12-18.
        */
public class StartRecordActivity extends AppCompatActivity {

    final static String happy = "0";
    static final String sad = "1";
    static final String funny = "2";
    static final String excited = "3";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startrecord);
    }
    public void HappyButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("emotion", happy);
        startActivity(intent);

    }
    public void SadButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("emotion", sad);
        startActivity(intent);
     }
    public void FunnyButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("emotion", funny);
        startActivity(intent);

    }
    public void ExcitedButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("emotion", excited);
        startActivity(intent);

    }



}
