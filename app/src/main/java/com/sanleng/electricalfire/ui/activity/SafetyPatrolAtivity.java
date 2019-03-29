package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.adapter.FunctionAdapter;
import com.sanleng.electricalfire.myview.MyGridView;

/**
 * 安全巡查
 *
 * @author Qiaoshi
 */
public class SafetyPatrolAtivity extends BaseActivity implements OnItemClickListener, View.OnClickListener {

    private MyGridView itemGrid;
    private FunctionAdapter adapter;
    private RelativeLayout back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_safetypatrol);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_safetypatrol;
    }


    private void initView() {
        itemGrid = findViewById(R.id.item_grid);
        back = findViewById(R.id.back);
        adapter = new FunctionAdapter(SafetyPatrolAtivity.this, R.array.myfunction_safetypatrolname,
                R.array.myfunction_safetypatrolicon);
        itemGrid.setAdapter(adapter);
        itemGrid.setOnItemClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            // 主机监测
            case 0:

                break;
            // 水系统
            case 1:


                break;
            // 防排烟
            case 2:

                break;
            // 防火门
            case 3:

                break;
            // 视频应用
            case 4:


                break;
            // 主机自检
            case 5:

                break;
            default:
                break;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
