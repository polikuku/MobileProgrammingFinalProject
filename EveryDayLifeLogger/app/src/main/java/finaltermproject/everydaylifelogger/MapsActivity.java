package finaltermproject.everydaylifelogger;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static final LatLng SEOUL = new LatLng( 37.56, 126.97);

    static final int happy = 0,  sad=1, funny = 2,excited=3;
    int emotion;   //0=happy; 1=funny 2=sad  3=excited;

    //Database관련 객체들
    SQLiteDatabase Db;
    String DbName = "emotions.db";
    String TableName;

    int dbMode= Context.MODE_PRIVATE;

    EditText input;
    Button saveButton;
    ListView mList;
    ArrayAdapter<String> baseAdapter;
    ArrayList<String> nameList;

    String longitude;
    String latitude;
    String incident;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //emotion을 전달받는다.
        Intent intent = this.getIntent();
        String temp = intent.getStringExtra("emotion");
        emotion =Integer.parseInt(temp);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMap = mapFragment.getMap();
        //현재 위치로 가는 버튼 표시
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));//초기 위치...수정필요

        GpsInfoActivity.LocationResult locationResult = new GpsInfoActivity.LocationResult() {
            @Override
            public void gotLocation(Location location) {

                String msg = "lon: " + location.getLongitude() + " -- lat: " + location.getLatitude();
                longitude=Double.toString(location.getLongitude());
                latitude=Double.toString(location.getLatitude());
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                drawMarker(location);
            }
        };
        GpsInfoActivity myLocation = new  GpsInfoActivity();
        myLocation.getLocation(getApplicationContext(), locationResult);

        if(emotion == happy){
            TableName = "happyListTable";
        }else if(emotion == funny){
            TableName = "funnyListTable";
        }else if(emotion == sad){
            TableName = "sadListTable";
        }else if(emotion == excited){
            TableName = "excitedListTable";
        }else{
            return;
        }

            // // Database 생성 및 열기
            Db = openOrCreateDatabase(DbName,dbMode,null);
            // 테이블 생성
            createTable();
        input = (EditText) findViewById(R.id.editText);
        saveButton = (Button) findViewById(R.id.saveB);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incident = input.getText().toString();

                insertData();
            }
        });




    }
    private void drawMarker(Location location) {

        //기존 마커 지우기
        mMap.clear();
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

        //currentPosition 위치로 카메라 중심을 옮기고 화면 줌을 조정한다. 줌범위는 2~21, 숫자클수록 확대
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 17));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);

        //마커 추가
        mMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .snippet("Lat:" + location.getLatitude() + "Lng:" + location.getLongitude())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("현재위치"));
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //database 관련 함수들
    public void createTable() {

        try {
            Log.d(" 테이블이름@@@@@@",  TableName);
            String sql = "create table " + TableName + "(id integer primary key autoincrement, "  + "incident text,"+" longitude text,"+" latitude text)";
            Db.execSQL(sql);
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite", "error: " + e);
        }
    }
    public void insertData(){
        Log.d(TableName, incident + " " + longitude + " " + latitude);
        String sql = "insert into " + TableName + " values(NULL, '" + incident + "', '" + longitude + "', '" + latitude+ "');";
        Db.execSQL(sql);
    }

}
