package com.saad.youssif.aswaqtawfik.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Presenter.UserPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.View.RegisterView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileUpdating extends AppCompatActivity implements RegisterView {

    EditText update_name,update_phone,update_address,update_password;
    Button submitBtn;
    UserPresenter userPresenter;
    int id;
    ProgressDialog progressDialog;
    String password;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_updating);
        this.setFinishOnTouchOutside(false);
        update_name=findViewById(R.id.update_profile_nameEt);
        update_phone=findViewById(R.id.update_profile_phoneEt);
        update_address=findViewById(R.id.update_profile_addressEt);
        update_password=findViewById(R.id.update_profile_passwordEt);
        submitBtn=findViewById(R.id.submitBtn);
        progressDialog=new ProgressDialog(this);
        getIntentData();
        userPresenter=new UserPresenter(this,this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Updating......");
                progressDialog.show();
                userPresenter.updateUser(id,
                                        update_name.getText().toString(),
                                        update_phone.getText().toString(),
                                        update_address.getText().toString(),
                                        update_password.getText().toString()
                        );

            }
        });
    }


    public void getIntentData()
    {
        Intent intent=getIntent();
        update_name.setText(intent.getExtras().getString("username"));
        update_phone.setText(intent.getExtras().getString("phone"));
        update_address.setText(intent.getExtras().getString("address"));
        id=Integer.valueOf(intent.getExtras().getInt("user_id"));
        password=intent.getExtras().getString("password");
        update_password.setText(password);
    }

    @Override
    public void getResponseString(String result) {
        if(result.equals("Success"))
        {
            progressDialog.dismiss();
            Toast.makeText(ProfileUpdating.this,result,Toast.LENGTH_SHORT).show();
            ProfileUpdating.this.finish();
        }
        else
        {
            Toast.makeText(ProfileUpdating.this,"Failed",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }


    }
}
