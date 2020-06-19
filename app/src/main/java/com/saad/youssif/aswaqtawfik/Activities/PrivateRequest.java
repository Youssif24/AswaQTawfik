package com.saad.youssif.aswaqtawfik.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Presenter.RequestPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.SharedPrefManager;
import com.saad.youssif.aswaqtawfik.View.RequestView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PrivateRequest extends AppCompatActivity implements RequestView {

    EditText requestEt;
    Button requestBtn;
    RequestPresenter requestPresenter;
    SharedPreferences shrd;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_request);
        this.setFinishOnTouchOutside(false);
        requestEt=findViewById(R.id.requestEt);
        requestBtn=findViewById(R.id.requestBtn);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        requestPresenter=new RequestPresenter(this,this);
        progressDialog=new ProgressDialog(this);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Sending......");
                progressDialog.show();
                requestPresenter.sendRequest(getId(),requestEt.getText().toString(),"28-9-2018");
            }
        });


    }

    public int getId()
    {
        return Integer.valueOf( sharedPrefManager.getId());
    }

    @Override
    public void getRequestResponse(String result) {

        if(result.equals("success"))
        {
            Toast.makeText(PrivateRequest.this,result,Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            PrivateRequest.this.finish();
        }
        else
        {
            Toast.makeText(PrivateRequest.this,"sorry,can you try again ? ",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }

    }
}
