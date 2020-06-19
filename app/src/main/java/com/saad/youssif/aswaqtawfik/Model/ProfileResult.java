package com.saad.youssif.aswaqtawfik.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResult {

    @SerializedName("user_result")
    @Expose
    private List<User> result = null;

    public List<User> getUserResult() {
        return result;
    }


}
