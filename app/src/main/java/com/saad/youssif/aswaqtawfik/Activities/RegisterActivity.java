package com.saad.youssif.aswaqtawfik.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Presenter.UserPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.View.RegisterView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private Calendar calendar;
    TextView dateOfBirth;
    Spinner genderSpinner;
    String gender;
    String strImageProfileName,strImageProfilePath;
    CircleImageView profileImg;
    Button regCreateBtn;
    EditText regUsernameEt,regPhoneEt,regAddressEt,regPasswordEt;
    private static final int SELECTED_PICTURE=1,CROP_PICTURE=2;
    private static final int RC_SIGN_IN=9001 ,PLACE_PICKER_REQUEST=100;
    Uri imageUri;
    UserPresenter userPresenter;
    ProgressDialog progressDialog;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dateOfBirth=findViewById(R.id.dateOfBirth);
        genderSpinner=findViewById(R.id.genderSpinner);
        profileImg=findViewById(R.id.profile_img);
        regCreateBtn=findViewById(R.id.reg_createNewAccountBtn);
        regUsernameEt=findViewById(R.id.reg_first_username);
        regAddressEt=findViewById(R.id.register_address);
        regPhoneEt=findViewById(R.id.reg_phone);
        regPasswordEt=findViewById(R.id.reg_password);
        progressDialog=new ProgressDialog(this);
        userPresenter=new UserPresenter(this,this);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(photoPickerIntent, getString(R.string.selectImage)), SELECTED_PICTURE);

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genderArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(adapter);









        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(RegisterActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datePickerDialog,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        datePickerDialog=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                dateOfBirth.setText(year+"-"+month+"-"+dayOfMonth);

            }
        };


        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    gender="Male";
                }
                else
                {
                    gender="Female";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegisterActivity.this,"Please Select Gender!..!..!",Toast.LENGTH_LONG).show();
            }
        });


        regCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Creating........");
                progressDialog.show();
                userPresenter.registerNewUser(regUsernameEt.getText().toString().trim(),regPhoneEt.getText().toString().trim()
                        ,regAddressEt.getText().toString().trim(),regPasswordEt.getText().toString().trim()
                        ,dateOfBirth.getText().toString(),gender,strImageProfileName,strImageProfilePath);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTED_PICTURE && resultCode== RESULT_OK) {
            try {
                imageUri = data.getData();

                Intent photoPickerCrop =new Intent("com.android.camera.action.CROP");
                photoPickerCrop.setDataAndType(imageUri,"image/*");
                photoPickerCrop.putExtra("crop", "true");
                // indicate aspect of desired crop
                photoPickerCrop.putExtra("aspectX", 1);
                photoPickerCrop.putExtra("aspectY", 1);
                // indicate output X and Y
                photoPickerCrop.putExtra("outputX", 360);
                photoPickerCrop.putExtra("outputY", 360);
                // retrieve data on return
                photoPickerCrop.putExtra("scaleUpIfNeeded", true);
                photoPickerCrop.putExtra("return-data", true);

                startActivityForResult(photoPickerCrop, CROP_PICTURE);
            }catch (ActivityNotFoundException ex){

            }
        }

        else if (requestCode == CROP_PICTURE) {

            if(data !=null) {
                Bundle bundle = data.getExtras();
                Bitmap selectedImage = bundle.getParcelable("data");
                profileImg.setImageBitmap(selectedImage);
                ByteArrayOutputStream byteArrayOutputStreamObject;
                byteArrayOutputStreamObject = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
                strImageProfileName = "USER_IMG_" + String.valueOf(System.currentTimeMillis());
                strImageProfilePath = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
            }
            else {
                Toast.makeText(RegisterActivity.this, getString(R.string.cropImage),Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    public void getResponseString(String result) {
        if(result.equals("success"))
        {
            progressDialog.dismiss();
            Toast.makeText(RegisterActivity.this,"success",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            RegisterActivity.this.finish();

        }
        else
        {
            Toast.makeText(RegisterActivity.this,"failed",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

        }

    }
}
