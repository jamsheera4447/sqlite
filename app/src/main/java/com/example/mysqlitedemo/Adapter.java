package com.example.mysqlitedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<Profile> profileList;

    public Adapter(Context context, List<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(photoView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Profile profile = profileList.get(position);
        holder.tv_name.setText(profile.getName());
        holder.tv_mobile.setText(profile.getMobile());
        holder.tv_email.setText(profile.getEmail());

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_mobile, tv_email, tv_phone;
        EditText et_name, et_email;
        ImageView iv_delete;
        ImageButton ib_close;
        LinearLayout ll_main;
        Button btn_update;

        AlertDialog dialog;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_email = itemView.findViewById(R.id.tv_email);
            iv_delete = itemView.findViewById(R.id.iv_delete);

            tv_phone = itemView.findViewById(R.id.tv_phone);
            ll_main = itemView.findViewById(R.id.ll_main);



            ll_main.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle("Data Updated")
//                            .setCancelable(false)
//                            .setMessage("The data has been updated.")
//                            .setPositiveButton("OK", null)
//                            .show();


//                    return false;

//                    String name = tv_name.getText().toString();
//                    String email = tv_email.getText().toString();


                    Profile profile = profileList.get(getAdapterPosition());
                    System.out.println(profile);


                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.updatelayout, null);
                    builder.setView(dialogView);
                    builder.setCancelable(false);
                    builder.create();
                    dialog = builder.show();
//                    dialog = builder.create();

                    btn_update = dialog.findViewById(R.id.btn_update);
                    et_name = dialog.findViewById(R.id.et_name);
                    et_email = dialog.findViewById(R.id.et_email);
                    tv_phone=dialog.findViewById(R.id.tv_phone);
                    ib_close = dialog.findViewById(R.id.ib_close);
                    ib_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });


                    btn_update.setOnClickListener(new View.OnClickListener() {
                      @Override
                       public void onClick(View v) {
                           String newName = et_name.getText().toString();
                           String newEmail = et_email.getText().toString();
                            String newMobile=tv_mobile.getText().toString();

//                            int position = getAdapterPosition();

//                           if (position != RecyclerView.NO_POSITION) {
//                                Profile profile = profileList.get(position);
//                                String name = profile.getName();
//                                String email = profile.getEmail();
//                                String mobile = profile.getMobile();
//                                DataBaseHelper dbHelper = new DataBaseHelper(context);
//                       dbHelper.updateRegisterData(newName,newEmail,newMobile);
//                               profile.setName(newName);
//                                profile.setEmail(newEmail);
//                                notifyItemChanged(position);
//                            }


                          DataBaseHelper  dataBaseHelper=new DataBaseHelper(context);

                          String email=et_email.getText().toString();
                          String name=et_name.getText().toString();

                          dataBaseHelper.updateData(profile.getMobile(),email,name);


                            dialog.dismiss();

                        }
                    });


//                    EditText et_name = dialogView.findViewById(R.id.et_name);
//                    EditText et_email = dialogView.findViewById(R.id.et_email);
                    et_name.setText(profile.getName().toString());
                    et_email.setText(profile.getEmail().toString());
                    tv_phone.setText(profile.getMobile());



//                    dataBaseHelper.updateRegisterData(profile.getMobile(),email,name);

                   return false;


                }
            });


            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Profile profile = profileList.get(position);
                        String name = profile.getName();
                        String mobile = profile.getMobile();
                        String email = profile.getEmail();
                        DataBaseHelper dbHelper = new DataBaseHelper(context);
                        dbHelper.deleteRegisterData(name, mobile, email);
                        dbHelper.updateRegisterData("newName", "newMobile", "newEmail");
                        profileList.remove(position);
                        notifyItemRemoved(position);

                    }
                }
            });

        }
    }
}

