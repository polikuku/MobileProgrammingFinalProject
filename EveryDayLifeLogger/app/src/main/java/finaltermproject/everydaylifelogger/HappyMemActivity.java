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

public class HappyMemActivity extends AppCompatActivity {

    SQLiteDatabase db;
    int dbMode = Context.MODE_PRIVATE;

    ListView list;
    ArrayAdapter<String> baseAdapter;
    ArrayList<String> MemList;
    String TableName;
    static final int happy = 0, funny = 1, sad=2, excited=3;
    int emotion;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happymem);
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
                Intent intent = new Intent(HappyMemActivity.this, MemMapActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("emotion",0);

                startActivity(intent);
            }
        });



    }
    public void selectAll() {
        String sql = "select * from " + "happyListTable" + ";";
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
