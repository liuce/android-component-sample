package org.lightips.sample.android.component.sqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.lightips.sample.android.component.R;
import org.w3c.dom.Text;

public class SqliteNativeActivity extends Activity implements View.OnClickListener {

    private Button btnSubmit;
    private Button btnSearch;
    private SqlNativeHelper sqlNativeHelper;

    private EditText nameEt;
    private EditText ageEt;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_native);
        sqlNativeHelper = new SqlNativeHelper(this);

        this.btnSearch = (Button)this.findViewById(R.id.nativeBtnSearch);
        this.btnSearch.setOnClickListener(this);
        this.btnSubmit = (Button)this.findViewById(R.id.nativeBtnSubmit);
        this.btnSubmit.setOnClickListener(this);

        this.nameEt = (EditText)this.findViewById(R.id.nativeName);
        this.ageEt = (EditText)this.findViewById(R.id.nativeAge);
        this.textView = (TextView)this.findViewById(R.id.nativeTv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite_native, menu);
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
        switch (v.getId()){
            case R.id.nativeBtnSearch:
                String values = this.sqlNativeHelper.queryPersonAll();
                this.textView.setText(values);
                break;
            case R.id.nativeBtnSubmit:
                String name = this.nameEt.getText().toString();
                int age = Integer.valueOf(this.ageEt.getText().toString());
                this.sqlNativeHelper.insertPersonSQL(name, age);
                break;
        }
    }


}
