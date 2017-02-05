package com.study.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = new Intent();
        intent.setAction("com.study.study.service.MYSERVICE");
        intent.setPackage("com.study.study");

        Button start = (Button) findViewById(R.id.start);
        Button end = (Button) findViewById(R.id.end);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "开始监视", Toast.LENGTH_SHORT).show();
                startService(intent);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopService(intent);
                Toast.makeText(MainActivity.this, "结束监控", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
