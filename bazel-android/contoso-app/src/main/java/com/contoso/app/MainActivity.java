package com.contoso.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);
        okButton = (Button) findViewById(R.id.button_ok);
    }

    public void onOkClick(View view) {
       TextView textView = (TextView) findViewById(R.id.textView_result);
       textView.setText("got click event!");
    }
}
