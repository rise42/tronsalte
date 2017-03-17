package com.translate.forsenboyz.rise42.translate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "14tag88";

    DatabaseHandler databaseHandler;

    private ListView listMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = DatabaseHandler.getInstance(MainActivity.this);

        listMain = (ListView) findViewById(R.id.listMain);

        Log.d(TAG, "onCreate: Created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Start?");
        fillList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_main_add){
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }

        return false;
    }

    private void fillList(){
        List<Map<String,String>> list = databaseHandler.getAll();
        if(list == null){
            return;
        }
        listMain.setAdapter(
                new SimpleAdapter(
                        MainActivity.this,
                        databaseHandler.getAll(),
                        R.layout.list_item,
                        new String[]{DatabaseHandler.KEY_COLUMN, DatabaseHandler.VALUE_COLUMN},
                        new int[]{R.id.textItemTitle, R.id.textItemValue}
                )
        );
    }

}