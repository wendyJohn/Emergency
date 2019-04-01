package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.LoginPresenter;
import com.sanleng.electricalfire.dialog.PromptDialog;
import com.sanleng.electricalfire.model.LoginRequest;
import com.sanleng.electricalfire.util.PreferenceUtils;
import com.sanleng.electricalfire.util.StringUtils;

/**
 * 登陆界面
 * 2019/1/9
 * @author qiaoshi
 */
public class LoginActivity extends BaseActivity implements OnClickListener, LoginPresenter {
    private EditText login_number;
    private EditText login_password;
    private Button login_btn;
    private RelativeLayout scrollviewRootLayout;
    private TextView login_questions;
    private String userName;
    private String password;
    private String lastAccount;
    private String lastPwd;
    private CheckBox whether_contact;
    private PromptDialog promptDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.login_activity);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.login_activity;
    }

    private void initView() {
        // 创建对象
        promptDialog = new PromptDialog(this);
        // 设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(2000);
        login_btn = findViewById(R.id.login_btn);
        login_number = findViewById(R.id.login_number);
        login_password = findViewById(R.id.login_password);
        login_questions = findViewById(R.id.login_questions);
        scrollviewRootLayout = findViewById(R.id.scrollviewRootLayout);
        login_btn.setOnClickListener(this);
        login_password.setOnClickListener(this);
        controlKeyboardLayout(scrollviewRootLayout, login_btn);
        whether_contact = findViewById(R.id.whether_contact);
        whether_contact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    PreferenceUtils.setInt(LoginActivity.this, "state", 1);
                } else {
                    PreferenceUtils.setInt(LoginActivity.this, "state", 0);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        login_number.setText( PreferenceUtils.getString(this, "ElectriFire_username"));
        login_password.setText(PreferenceUtils.getString(this, "ElectriFire_password"));
        int state = PreferenceUtils.getInt(LoginActivity.this, "state");
        if (state == 1) {
            // 记住上次登录的信息
            lastAccount = PreferenceUtils.getString(this, "ElectriFire_usernames");
            lastPwd = PreferenceUtils.getString(this, "ElectriFire_passwords");
            if (!StringUtils.isEmpty(lastAccount) && !StringUtils.isEmpty(lastPwd)) {
                login_number.setText(lastAccount);
                login_password.setText(lastPwd);
                Intent intent_pwdchange = new Intent(LoginActivity.this, MainTabActivity.class);
                startActivity(intent_pwdchange);
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                userName = login_number.getText().toString().trim();
                password = login_password.getText().toString().trim();
                promptDialog.showLoading("正在登录...");
                LoginRequest.GetLogin(LoginActivity.this, getApplicationContext(), userName, password, "app_firecontrol_owner");
                break;
            default:
                break;
        }
    }

    @Override
    public void LoginSuccess(String msg) {
        if (msg.equals("登录成功")) {
            promptDialog.showSuccess("登录成功");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    // 等待2000毫秒后销毁此页面，并提示登陆成功
                    Intent intent_pwdchange = new Intent(LoginActivity.this, MainTabActivity.class);
                    startActivity(intent_pwdchange);
                    finish();
                }
            }, 1000);
        } else {
            promptDialog.showError(msg);
        }
    }

    @Override
    public void LoginFailed() {
        promptDialog.showSuccess("登录失败");
    }


}
