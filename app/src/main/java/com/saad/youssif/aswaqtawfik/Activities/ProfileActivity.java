package com.saad.youssif.aswaqtawfik.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Adapter.ProfileAdapter;
import com.saad.youssif.aswaqtawfik.Model.User;
import com.saad.youssif.aswaqtawfik.Presenter.UserPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.SharedPrefManager;
import com.saad.youssif.aswaqtawfik.View.ProfileView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    ListView profListView;
    ProfileAdapter profileAdapter;
    ArrayList<String>profileList;
    CircleImageView prof_img;
    TextView prof_name,prof_phone,prof_address,prof_date;
    UserPresenter userPresenter;
    ProgressDialog progressDialog;
    ImageButton name_imgBtn;
    String password;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profListView=findViewById(R.id.profile_listView);
        prof_img=findViewById(R.id.profile_image);
        prof_name=findViewById(R.id.profile_usernameTv);
        prof_phone=findViewById(R.id.profile_phoneTv);
        prof_address=findViewById(R.id.profile_addressTv);
        prof_date=findViewById(R.id.profile_dateOfBirthTv);
        name_imgBtn=findViewById(R.id.name_imgBtn);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading......");
        progressDialog.show();
        userPresenter=new UserPresenter(this,this);
        userPresenter.getProfile(getId());
        profileList=new ArrayList<>();
        profileList.add("edit profile");
        profileList.add("send suggestion");
        profileList.add("logout");
        profileAdapter=new ProfileAdapter(this,profileList);
       //ArrayAdapter<String>myAdapter=new ArrayAdapter<String>(this,R.layout.profile_list_layout,R.id.edit_profile,profileList);
       profListView.setAdapter(profileAdapter);

       profListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               if(i==0)
               {
                   Intent updateIntent=new Intent(ProfileActivity.this,ProfileUpdating.class);
                   updateIntent.putExtra("username",prof_name.getText().toString());
                   updateIntent.putExtra("phone",prof_phone.getText().toString());
                   updateIntent.putExtra("address",prof_address.getText().toString());
                   updateIntent.putExtra("date",prof_date.getText().toString());
                   updateIntent.putExtra("user_id",getId());
                   updateIntent.putExtra("password",password);
                   startActivity(updateIntent);
               }
               if(i==1)
               {
                   Intent privateIntent=new Intent(ProfileActivity.this,PrivateRequest.class);
                   startActivity(privateIntent);

               }
               if(i==2)
                   logout();

           }
       });

    }


    public void logout()
    {
        sharedPrefManager.clearSharedData();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        ProfileActivity.this.finish();
    }

    public int getId()
    {
        password=sharedPrefManager.getPassword();
        return Integer.valueOf( sharedPrefManager.getId());
    }

    @Override
    public void showList(List<User> profileResult) {
    if(profileResult==null)
    {
     Toast.makeText(ProfileActivity.this,"List is Empty",Toast.LENGTH_SHORT).show();
     progressDialog.dismiss();
    }
    else
    {
        prof_name.setText(profileResult.get(0).getName());
        prof_phone.setText(profileResult.get(0).getPhone());
        prof_address.setText(profileResult.get(0).getAddress());
        prof_date.setText(profileResult.get(0).getAge());
        Picasso.with(ProfileActivity.this).load(profileResult.get(0).getImage_path()).into(prof_img);
        progressDialog.dismiss();

    }
    }
}
