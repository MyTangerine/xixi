package com.example.tangerine.demo;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Tangerine on 16/8/29.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
