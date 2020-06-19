package com.saad.youssif.aswaqtawfik.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResult {

    @SerializedName("result")
    @Expose
    private List<User> result = null;

    public List<User> getUserResult() {
        return result;
    }

    public void setUserResult(List<User> result) {
        this.result = result;
    }

}
