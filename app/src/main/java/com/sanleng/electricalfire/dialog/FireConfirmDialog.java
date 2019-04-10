package com.sanleng.electricalfire.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.Presenter.FireConfirmationPresenter;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.FireConfirmationRequest;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 火警确认
 */
public class FireConfirmDialog extends Dialog implements OnClickListener, FireConfirmationPresenter {
    Context context;
    private ImageView bt_cancel;
    private TextView bt_confire;
    private String staus = "101";
    private String taskId;
    private Handler mHandler;

    public FireConfirmDialog(Context context, String taskId, Handler mHandler) {
        super(context);
        this.context = context;
        this.taskId = taskId;
        this.mHandler = mHandler;
    }

    public FireConfirmDialog(Context context, int taskID) {
        super(context, taskID);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.setContentView(R.layout.fireconfirmdialog);
        initview();
    }

    private void initview() {
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_confire = findViewById(R.id.bt_confire);
        bt_cancel.setOnClickListener(this);
        bt_confire.setOnClickListener(this);
        // 有效
        RadioGroup group = this.findViewById(R.id.radioGroup1);
        // 绑定一个匿名监听器
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                // 获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                // 根据ID获取RadioButton的实例
                RadioButton rb = findViewById(radioButtonId);
                String s = rb.getText().toString();
                if (s.equals("真实火警")) {
                    staus = "101";
                }
                if (s.equals("误报")) {
                    staus = "102";
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_cancel) {
            dismiss();
        } else if (v.getId() == R.id.bt_confire) {
            FireConfirmationRequest.GetFireConfirmation(FireConfirmDialog.this, context, taskId, staus, PreferenceUtils.getString(context, "MobileFig_username"), "app_firecontrol_owner");
            dismiss();
        }
    }

    @Override
    public void FireConfirmationSuccess(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String msgs = jsonObject.getString("msg");
            if (msgs.equals("警情处理成功")) {
                Message mymsg = new Message();
                mymsg.what = MyApplication.MSGConfirmSuccess;
                mHandler.sendMessage(mymsg);
            } else {
                Message mymsg = new Message();
                mymsg.what = MyApplication.MSGConfirmFailure;
                mHandler.sendMessage(mymsg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void FireConfirmationFailed() {
        Message mymsg = new Message();
        mymsg.what = MyApplication.MSGConfirmFailure;
        mHandler.sendMessage(mymsg);
    }

}
