package org.lightips.sample.android.component;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SpNativeActivity extends Activity implements Button.OnClickListener {

    private String SP_1 = "SP_1";
    private String KEY_1 = "SP_KEY_1";
    private SharedPreferences sharedPreferences;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_native);
        sharedPreferences = getSharedPreferences(SP_1, Context.MODE_PRIVATE);
        String key1Value = sharedPreferences.getString(KEY_1, "");

        editText = (EditText)this.findViewById(R.id.editText);
        editText.setText(key1Value);
        Button btnInput = (Button)this.findViewById(R.id.btnInput);
        btnInput.setOnClickListener(this);
        Button btnGet = (Button)this.findViewById(R.id.btnGet);
        btnGet.setOnClickListener(this);

        textView = (TextView)this.findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sp_native, menu);
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
        int vId = v.getId();
        switch (vId){
            case R.id.btnInput:
                String key1Value = editText.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(KEY_1,key1Value);
                editor.commit();
                break;
            case R.id.btnGet:
                String key1Value1 = sharedPreferences.getString(KEY_1, "");
                textView.setText(key1Value1);
                break;
        }
    }

}
