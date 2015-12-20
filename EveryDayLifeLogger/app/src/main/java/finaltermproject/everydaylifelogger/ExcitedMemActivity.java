package finaltermproject.everydaylifelogger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
* Created by Xnote on 2015-12-18.
        */
public class ExcitedMemActivity extends AppCompatActivity {

    SQLiteDatabase db;
    int dbMode = Context.MODE_PRIVATE;

    ListView list;
    ArrayAdapter<String> baseAdapter;
    ArrayList<String> MemList;
    static final int happy = 0, funny = 1, sad=2, excited=3;
    int emotion;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excitedmem);
        db = openOrCreateDatabase("emotions.db",dbMode,null);

        list = (ListView) findViewById(R.id.list_view);

        MemList = new ArrayList<String>();
        baseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, MemList);
        list.setAdapter(baseAdapter);

        MemList.clear();
        selectAll();
        baseAdapter.notifyDataSetChanged();




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExcitedMemActivity.this, MemMapActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("emotion",3);

                startActivity(intent);
            }
        });



    }
    public void selectAll() {
        String sql = "select * from " + "excitedListTable" + ";";
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();

        while (!result.isAfterLast()) {
            int id = result.getInt(0);

            String all = result.getString(1);//  + " " + result.getString(2) + " " + result.getString(3);
//            Toast.makeText(this, "index= " + id + " name=" + name, Toast.LENGTH_LONG).show();


            MemList.add(all);
            result.moveToNext();
        }
        result.close();
    }



}
