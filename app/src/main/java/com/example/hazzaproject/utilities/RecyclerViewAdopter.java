package com.example.hazzaproject.utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import com.example.hazzaproject.R;
import com.example.hazzaproject.models.Profile;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdopter extends RecyclerView.Adapter<RecyclerViewAdopter.ViewHolder> {

    private ArrayList<Profile> ProfilesList = new ArrayList<>();
    private Context rcontext;
    private int EVEN_TYPE_LAYOUT = 0 ;
    private int ODD_TYPE_LAYOUT = 1 ;
    private String getName,getImageName,getJob , getMail;
    private  Profile getProfile;
    private  Dialog detailsDialog;




    public RecyclerViewAdopter(ArrayList<Profile> profiles , Context context) {
        ProfilesList= profiles;
        rcontext = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return EVEN_TYPE_LAYOUT;
        } else {
            return ODD_TYPE_LAYOUT;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  // place the view in their place.


        View view = LayoutInflater.from(rcontext).inflate(R.layout.layout_listitem, parent , false);
        ViewHolder holder = new ViewHolder(view);

        //init dialog
         detailsDialog= new Dialog(rcontext);
        detailsDialog.setContentView(R.layout.dialog_details);
        detailsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        getProfile  = ProfilesList.get(position);
        getName = getProfile.getName();
        getImageName = getProfile.getImageName();

        holder.rname.setText(getName);


        final int id = rcontext.getResources().getIdentifier(getImageName, "drawable", rcontext.getPackageName());

        holder.rimage.setImageResource(id);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       getProfile  = ProfilesList.get(position);
                                                       getName = getProfile.getName();
                                                       getJob = getProfile.getJobTitle();
                                                       getMail = getProfile.getEmail();
                                                       Toast.makeText(rcontext, getName,Toast.LENGTH_SHORT).show();
                                                       CircleImageView dimage = detailsDialog.findViewById(R.id.dpic);
                                                       TextView nameDialog = detailsDialog.findViewById(R.id.dname);
                                                       TextView jobDialog = detailsDialog.findViewById(R.id.djob);
                                                       TextView mailDialog = detailsDialog.findViewById(R.id.dmail);
                                                       dimage.setImageResource(id);
                                                       nameDialog.setText(getName);
                                                       jobDialog.setText(getJob);
                                                       mailDialog.setText(getMail);
                                                       detailsDialog.show();
                                                   }
                                               }

        );
    }

    @Override
    public int getItemCount() {
        return ProfilesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView rimage ;
        TextView rname ;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            rimage = itemView.findViewById(R.id.rpic);
            rname = itemView.findViewById(R.id.rname);
            parentLayout = itemView.findViewById(R.id.parentRL);
            //IN THE DIALOG

        }
    }


    public void clear(){
        ProfilesList.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Profile> profiles){
        ProfilesList.addAll(profiles);
        notifyDataSetChanged();
    }
}
