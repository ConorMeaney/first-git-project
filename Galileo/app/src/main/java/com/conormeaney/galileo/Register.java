package com.conormeaney.galileo;
/**
 * Created by CONOR MEANEY K00178122 on 13/04/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {

    DataHelper helper = new DataHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void register(View v)
    {
        if(v.getId()== R.id.RegisterButton)
        {
            EditText name = (EditText)findViewById(R.id.name);
            EditText username = (EditText)findViewById(R.id.username);
            EditText password = (EditText)findViewById(R.id.password);
            EditText password2 = (EditText)findViewById(R.id.password2);

            String nameuse = name.getText().toString();
            String unameuse = username.getText().toString();
            String pass1use = password.getText().toString();
            String pass2use = password2.getText().toString();

            if(!pass1use.equals(pass2use))
            {

                Toast pass = Toast.makeText(Register.this, "passwords not the same", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                database c = new database();
                c.setName(nameuse);
                c.setUsername(unameuse);
                c.setPassword(pass1use);

                helper.insertData(c);
            }

        }

    }


}
