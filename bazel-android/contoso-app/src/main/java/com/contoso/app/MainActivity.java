package com.contoso.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.contoso.common.CommonHelper;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);
    }
}
