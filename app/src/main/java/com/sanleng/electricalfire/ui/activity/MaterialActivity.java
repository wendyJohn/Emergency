package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.sanleng.electricalfire.Presenter.MaterialsListRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.dialog.PromptDialog;
import com.sanleng.electricalfire.model.MaterialsListModel;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.adapter.BottomMenuAdapter;
import com.sanleng.electricalfire.ui.adapter.MaterialAdapter;
import com.sanleng.electricalfire.ui.bean.ArchitectureBean;
import com.sanleng.electricalfire.ui.bean.StationBean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 物资列表
 *
 * @author Qiaoshi
 */
public class MaterialActivity extends BaseActivity implements MaterialsListModel {

    private RelativeLayout r_back;
    private ListView materiallistview;
    private String ids;
    private String format;
    private BottomMenuAdapter bottomMenuAdapter;
    private List<StationBean> slists;
    double distance;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        this.setContentView(R.layout.materialactivity);
        initview();
        loadData();
    }

	@Override
	protected int getLayoutRes() {
		return R.layout.materialactivity;
	}

	private void initview() {
        Intent intent = getIntent();
        ids = intent.getStringExtra("ids");
        format= intent.getStringExtra("format");
        r_back =  findViewById(R.id.r_back);
        r_back.setOnClickListener(new MyOnClickListener(0));
    }

    // 加载数据
    private void loadData() {
        slists=new ArrayList<>();
        MaterialsListRequest.getMaterialsList(MaterialActivity.this,getApplicationContext(),ids,null,null,null,distance,slists,format);
    }

    @Override
    public void MaterialsListSuccess(List<StationBean> list, String stationId, String mac, String name, String address, double distance) {
        materiallistview=findViewById(R.id.materiallistview);
        bottomMenuAdapter = new BottomMenuAdapter(MaterialActivity.this, list, name, address, distance, stationId, mac, null);
        materiallistview.setAdapter(bottomMenuAdapter);
    }

    @Override
    public void MaterialsListFailed() {

    }


    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            switch (index) {
                case 0:
                    finish();
                    break;
            }

        }
    }

}
