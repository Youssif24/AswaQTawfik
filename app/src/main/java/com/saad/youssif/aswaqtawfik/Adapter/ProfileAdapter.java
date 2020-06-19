package com.saad.youssif.aswaqtawfik.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.saad.youssif.aswaqtawfik.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends ArrayAdapter<String> {

    Context context;
    LayoutInflater myInflater;
    ArrayList<String>profList;

    public ProfileAdapter(@NonNull Context context,ArrayList<String> profList) {
        super(context, 0,profList);
        this.context=context;
        this.profList=profList;
    }


    @Override
    public int getViewTypeCount() {
        return profList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(profList.get(position).equals("edit profile"))
            return 0;
        else if(profList.get(position).equals("send suggestion"))
            return 1;
        else
            return 2;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        TextView editTv, logoutTv, sugTv;
        View view=convertView;
        //view = myInflater.inflate(R.layout.profile_list_layout, null);
        int type = getItemViewType(i);
        if (view == null) {
            // Inflate the layout according to the view type
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == 0) {
                // Inflate the layout with image
                view = inflater.inflate(R.layout.profile_list_edit, viewGroup, false);
            }
            else if(type==1)
            {
                view = inflater.inflate(R.layout.profile_list_send, viewGroup, false);
            }

            else {
                view = inflater.inflate(R.layout.profile_list_logout, viewGroup, false);
            }
        }
        /*editTv=view.findViewById(R.id.edit_profile);
        logoutTv=view.findViewById(R.id.logout_profile);
        sugTv=view.findViewById(R.id.suggest_profile);*/
        return view;
    }
}
