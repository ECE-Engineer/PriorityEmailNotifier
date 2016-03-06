package com.pen.smashers.pen;
//import PEN;//will be the in the project
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Map;

public class

        MainActivity extends AppCompatActivity {
    String NaMe;
    String Keyword;
    String Email;
    String Pass;
    Map<Map<String,String>,Map<String,String>> inputMap;
    Map<String,String> inputMapNameKey;
    Map<String,String> inputMapEmailPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void buttonOnClickEmail(LoginFilter.UsernameFilterGMail input) {
        CharSequence target = findViewById(R.id.EMail).toString();
        if(isValidEmail(target)){
            Email = target.toString();
            submit();
        }
        else {
            notify();
        }

    }
    public void buttonOnClickName(LoginFilter.UsernameFilterGeneric input) {
        CharSequence target = findViewById(R.id.Name).toString();
        if(isValidName(target)){
            NaMe = target.toString();
            submit();
        }
        else {
            notify();
        }

    }
    public void buttonOnClickKeyWord(String input) {
        CharSequence target = findViewById(R.id.KeyWords).toString();
        if(isValidKeyword(target)){

            Keyword = target.toString();
            submit();
        }
        else {
            notify();
        }

    }
    public void submit(){
        inputMapEmailPass.put(Email,Pass);
        inputMapNameKey.put(NaMe,Keyword);
        }

    // Credit :user1737884 on Stack overFlow
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public final static boolean isValidName(CharSequence target) {
        int i = 0 ;
            while(i < target.length()) {
                if (target.charAt(i) >= 'a' | target.charAt(i) <= 'z' | target.charAt(i) >= 'A' | target.charAt(i) >= 'Z') {
                    i++;
                } else {
                    return false;
                }
            }
        return true;
            }

    public final static boolean isValidKeyword(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return true;
        }
    }

}