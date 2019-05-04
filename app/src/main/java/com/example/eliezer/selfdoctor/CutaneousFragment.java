package com.example.eliezer.selfdoctor;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CutaneousFragment extends Fragment {

    private  View mView;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    public CutaneousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_cutaneous, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Disease").child("cutaneous");
        mDatabase.keepSynced(true);

        recyclerView = mView.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DiseaseModel,CancerViewHolder> adapter = new FirebaseRecyclerAdapter <DiseaseModel, CancerViewHolder>(
                DiseaseModel.class,
                R.layout.layout_model,
                CancerViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(CancerViewHolder viewHolder, DiseaseModel model, int position) {
                final String link_id = getRef(position).getKey().toString();

                viewHolder.setName(model.name.toString(),model.description.toString());
                viewHolder.setImage(getContext(),model.image.toString());
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goDetails = new Intent(getContext(),Details.class);
                        goDetails.putExtra("link",link_id);
                        goDetails.putExtra("root","cutaneous");
                        startActivity(goDetails);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }

    public static class CancerViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imgDisease;
        public CancerViewHolder(View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setName(String name,String description){
            TextView txt = view.findViewById(R.id.disease_name);
            txt.setText(name);

            TextView txtDesc = view.findViewById(R.id.disease_syptoms);
            txtDesc.setText(description);
        }

        public void setImage(Context context, String url){
            imgDisease = view.findViewById(R.id.disease_img);
            Glide
                    .with(context)
                    .load(url).apply(new RequestOptions()
                    .placeholder(R.drawable.doctor)
                    .dontAnimate()
                    .dontTransform())
                    .into(imgDisease);
        }
    }
}
