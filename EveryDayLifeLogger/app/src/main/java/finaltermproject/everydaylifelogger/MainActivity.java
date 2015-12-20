package finaltermproject.everydaylifelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
* Created by Xnote on 2015-12-18.
        */
public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EventRecordButtonClicked(View v) {                             //EventRecord 버튼 클릭 시.
        Intent intent = new Intent(getApplicationContext(), StartRecordActivity.class);
        startActivity(intent);
    }

    public void MyMemoriesButtonClicked(View v) {                             //MyMemories 버튼 클릭 시.

        Intent intent = new Intent(getApplicationContext(), StartMemoryActivity.class);
        startActivity(intent);
    }

}
