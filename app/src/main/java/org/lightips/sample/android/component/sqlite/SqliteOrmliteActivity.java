package org.lightips.sample.android.component.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import org.lightips.sample.android.component.R;
import org.lightips.sample.android.component.sqlite.ormlite.DatabaseHelper;
import org.lightips.sample.android.component.sqlite.ormlite.Person;

import java.sql.SQLException;
import java.util.List;

public class SqliteOrmliteActivity extends Activity implements View.OnClickListener {
    protected static final String TAG = SqliteOrmliteActivity.class.getSimpleName();
    private Button btnSubmit;
    private Button btnSearch;

    private EditText nameEt;
    private EditText ageEt;

    private TextView textView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_ormlite);

        this.btnSearch = (Button) this.findViewById(R.id.ormBtnSearch);
        this.btnSearch.setOnClickListener(this);
        this.btnSubmit = (Button) this.findViewById(R.id.ormBtnSubmit);
        this.btnSubmit.setOnClickListener(this);

        this.nameEt = (EditText) this.findViewById(R.id.ormName);
        this.ageEt = (EditText) this.findViewById(R.id.ormAge);
        this.textView = (TextView) this.findViewById(R.id.ormTv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite_ormlite, menu);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ormBtnSearch:
                String values = this.queryAll();
                this.textView.setText(values);
                break;
            case R.id.ormBtnSubmit:
                String name = this.nameEt.getText().toString();
                int age = Integer.valueOf(this.ageEt.getText().toString());
                this.insert(name, age);
                break;
        }
    }

    private String queryAll() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Dao<Person, Integer> personDao = getHelper().getPersonDao();
            List<Person> personList=personDao.queryForAll();
            for (Person person : personList){
                stringBuilder.append(person.getName()+" "+person.getAge()+ "\n");
            }
        } catch (SQLException e) {
            Log.e(TAG, "", e);
        }
        return stringBuilder.toString();
    }


    private void insert(String name, int age){
        try {
            Dao<Person, Integer> personDao = getHelper().getPersonDao();
            Person person = new Person();
            person.setId(1);
            person.setName(name);
            person.setAge(age);
            personDao.create(person);
        } catch (SQLException e) {
            Log.e(TAG, "", e);
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = DatabaseHelper.getHelper(this);
        }
        return databaseHelper;
    }
}
