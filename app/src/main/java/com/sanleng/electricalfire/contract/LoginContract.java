package com.sanleng.electricalfire.contract;

public class LoginContract {

    public interface LoginModel {
        void getLogin(String username, String password, String platformkey);
    }

}
