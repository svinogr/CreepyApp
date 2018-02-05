package info.upump.creepyapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;

import info.upump.creepyapp.db.DBHelper;
import info.upump.creepyapp.reader.ReaderJson;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper helper = DBHelper.getHelper(getApplicationContext());
       helper.create_db();
     /*   try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
     /*   ReaderJson readerJson = new ReaderJson(this);
        try {
            readerJson.starr();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
