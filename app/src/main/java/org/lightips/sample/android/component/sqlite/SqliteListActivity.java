package org.lightips.sample.android.component.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.lightips.sample.android.component.R;

public class SqliteListActivity extends Activity implements AdapterView.OnItemClickListener {
    protected static final String TAG = SqliteListActivity.class.getSimpleName();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_list);
        listView = (ListView)this.findViewById(R.id.sqlitelist);
        listView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                new String[]{
                        getString(R.string.sqlite_list_native),
                        getString(R.string.sqlite_list_ormlite),
                        getString(R.string.sqlite_list_greendao),
                        getString(R.string.sqlite_list_activeandroid)
                }));
        listView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position){
            case 0:
                intent = new Intent(this,SqliteNativeActivity.class);
                break;
            case 1:
                intent = new Intent(this,SqliteOrmliteActivity.class);
                break;
            case 2:
                intent = new Intent(this,SqliteGreenDaoActivity.class);
                break;
            case 3:
                intent = new Intent(this,SqliteActiveAndroidActivity.class);
                break;

        }
        if(intent!=null){
            this.startActivity(intent);
        }
    }
}
