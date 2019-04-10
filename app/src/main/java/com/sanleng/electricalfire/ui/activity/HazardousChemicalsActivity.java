package com.sanleng.electricalfire.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.R;

/**
 *危化品柜
 * @author Qiaoshi
 */
public class HazardousChemicalsActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout r_back;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hazardouschemicalsactivity);
        initView();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.hazardouschemicalsactivity;
    }

    private void initView() {
        Intent intent=getIntent();
        r_back = findViewById(R.id.r_back);
        name= findViewById(R.id.name);
        r_back.setOnClickListener(this);
        name.setText(intent.getStringExtra("name"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;
        }
    }

}
