package com.saad.youssif.aswaqtawfik.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Model.User;
import com.saad.youssif.aswaqtawfik.Presenter.UserPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.SharedPrefManager;
import com.saad.youssif.aswaqtawfik.View.LoginView;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements LoginView {

    Button createAccountBtn,loginBtn;
    UserPresenter userPresenter;
    EditText usernameEt,passwordEt;
    ConnectivityManager connectivityManager;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*createAccountBtn=findViewById(R.id.log_createAccountBtn);
        usernameEt=findViewById(R.id.login_username_editText);
        passwordEt=findViewById(R.id.login_password_editText);
        loginBtn=findViewById(R.id.loginBtn);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        progressDialog=new ProgressDialog(this);
        register_from_shrd();


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        userPresenter =new UserPresenter(this,this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkConnection()&&validateData())
                {
                    progressDialog.setMessage("Please Wait......");
                    progressDialog.show();
                    userPresenter.checkUser(usernameEt.getText().toString(),passwordEt.getText().toString());
                }
            }
        });*/

    }


    public void save_user_data(String username,String id,String password)
    {
        sharedPrefManager.setId(id.trim());
        sharedPrefManager.setName(username.trim());
        sharedPrefManager.setPassword(password.trim());
    }
    public void register_from_shrd()
    {
        String email=sharedPrefManager.getName();
        String pass=sharedPrefManager.getPassword();
        if(!(TextUtils.isEmpty(email))&&!(TextUtils.isEmpty(pass)))
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
        }
    }


    @Override
    public void showList(List<User> loginResults) {

        if(loginResults.size()==0)
        {
            Toast.makeText(LoginActivity.this,"Please try again....",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

        }
        else
        {
            //Toast.makeText(LoginActivity.this,loginResults.get(0).getName(),Toast.LENGTH_LONG).show();

            progressDialog.dismiss();
            save_user_data(usernameEt.getText().toString(),loginResults.get(0).getId(),passwordEt.getText().toString());
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            this.finish();
        }

    }
    public boolean checkConnection()
    {
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }

    public boolean validateData()
    {
        if(usernameEt.getText().toString().trim().equals(""))
        {
            usernameEt.setError("Required Failed");
            return false;
        }
        else if(passwordEt.getText().toString().trim().equals(""))
        {
            passwordEt.setError("Required Failed");
            return false;
        }
        else
        {
            return true;
        }

    }


}
