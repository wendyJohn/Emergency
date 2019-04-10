package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sanleng.electricalfire.R;

/**
 * 消防安全
 *
 * @author Qiaoshi
 */
public class FirsafetyAtivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout firsLineara;
    private LinearLayout firsLinearb;
    private LinearLayout firsLinearc;
    private LinearLayout firsLineard;
    private LinearLayout firsLineare;
    private LinearLayout firsLinearf;
    private RelativeLayout back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_firsafety);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_firsafety;
    }

    private void initView() {
        back = findViewById(R.id.back);
        firsLineara = findViewById(R.id.firs_lineara);
        firsLinearb = findViewById(R.id.firs_linearb);
        firsLinearc = findViewById(R.id.firs_linearc);
        firsLineard = findViewById(R.id.firs_lineard);
        firsLineare = findViewById(R.id.firs_lineare);
        firsLinearf = findViewById(R.id.firs_linearf);

        back.setOnClickListener(this);
        firsLineara.setOnClickListener(this);
        firsLinearb.setOnClickListener(this);
        firsLinearc.setOnClickListener(this);
        firsLineard.setOnClickListener(this);
        firsLineare.setOnClickListener(this);
        firsLinearf.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.back:
                finish();
                break;
            // 火灾报警
            case R.id.firs_lineara:
                Intent HostMonitoring=new Intent(FirsafetyAtivity.this,HostMonitoringActivity.class);
                startActivity(HostMonitoring);
                break;
            // 水系统
            case R.id.firs_linearb:
                Intent WaterSystemintent=new Intent(FirsafetyAtivity.this,WaterSystemActivity.class);
                startActivity(WaterSystemintent);
                break;
            // 防排烟
            case R.id.firs_linearc:

                break;
            // 防火门
            case R.id.firs_lineard:

                break;
            // 视频应用
            case R.id.firs_lineare:


                break;
            // 主机自检
            case R.id.firs_linearf:

                break;
            default:
                break;
        }
    }
}
