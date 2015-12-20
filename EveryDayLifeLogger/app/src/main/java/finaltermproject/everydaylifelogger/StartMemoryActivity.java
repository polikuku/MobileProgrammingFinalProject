package finaltermproject.everydaylifelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class StartMemoryActivity extends AppCompatActivity {

    static final int happy = 0, funny = 2, sad=1, excited=3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmemory);
    }

    public void onClickedHappy(View v) {                             //EventRecord 버튼 클릭 시.
        Intent intent = new Intent(getApplicationContext(), HappyMemActivity.class);
        intent.putExtra("emotion", happy);
        startActivity(intent);
    }

    public void onClickedFunny(View v) {                             //EventRecord 버튼 클릭 시.
        Intent intent = new Intent(getApplicationContext(), FunnyMemActivity.class);
        intent.putExtra("emotion", funny);
        startActivity(intent);
    }
    public void onClickedSad(View v) {                             //EventRecord 버튼 클릭 시.
        Intent intent = new Intent(getApplicationContext(), SadMemActivity.class);
        intent.putExtra("emotion", sad);
        startActivity(intent);
    }
    public void onClickedExcited(View v) {                             //EventRecord 버튼 클릭 시.
        Intent intent = new Intent(getApplicationContext(), ExcitedMemActivity.class);
        intent.putExtra("emotion", excited);
        startActivity(intent);
    }

}
