package com.conormeaney.galileo;
/**
 * Created by CONOR MEANEY K00178122 on 13/04/2016.
 */
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DataHelper helper = new DataHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.SignIn)
        {EditText a = (EditText)findViewById(R.id.user);
            String use = a.getText().toString();
              EditText b = (EditText)findViewById(R.id.pass);
                String pass = b.getText().toString();
            String password = helper.PasswordCheck(use);

        if(pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this, ArduinoActivity.class);
                i.putExtra("Username",use);
                startActivity(i);
            }
        else
            {
                Toast temp = Toast.makeText(MainActivity.this, "Username and password are wrong ", Toast.LENGTH_SHORT);
                temp.show();
            }
        }
        if(v.getId() == R.id.SignUp)
        {
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }
                                             @Override
         public boolean onOptionsItemSelected(MenuItem item) {
                                           int id = item.getItemId();
          if (id == R.id.action_settings) {
                                                     return true;
                                                                             }
          return super.onOptionsItemSelected(item);
    }
}
