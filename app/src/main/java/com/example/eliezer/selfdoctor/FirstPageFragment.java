package com.example.eliezer.selfdoctor;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment {

    private RecyclerView mCancerView;
    private RecyclerView mCutaneousView,mEndocrine;
    private DatabaseReference dataCancer;
    private DatabaseReference dataCutaneous, dataEndocrine;

    public FirstPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_first_page, container, false);

        dataCancer = FirebaseDatabase.getInstance().getReference().child("Disease").child("cancer");
        dataCancer.keepSynced(true);
        dataCutaneous = FirebaseDatabase.getInstance().getReference().child("Disease").child("cutaneous");
        dataCutaneous.keepSynced(true);
        dataEndocrine = FirebaseDatabase.getInstance().getReference().child("Disease").child("endocrine");
        dataEndocrine.keepSynced(true);

        mCancerView = mView.findViewById(R.id.rv_cancer);
        mCancerView.setHasFixedSize(true);
        mCancerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        mCutaneousView = mView.findViewById(R.id.rv_cutaneous);
        mCutaneousView.setHasFixedSize(true);
        mCutaneousView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        mEndocrine = mView.findViewById(R.id.rv_endocrine);
        mEndocrine.setHasFixedSize(true);
        mEndocrine.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));




        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        populateCancerView();

        populateCutaneousView();

        populateEndocrine();
    }

    public void populateCancerView(){
        FirebaseRecyclerAdapter<DiseaseModel,CancerViewHolder> cancerAdapter = new FirebaseRecyclerAdapter <DiseaseModel, CancerViewHolder>(
                DiseaseModel.class,
                R.layout.linear_model,
                CancerViewHolder.class,
                dataCancer
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
                        goDetails.putExtra("root","cancer");
                        startActivity(goDetails);
                    }
                });
            }
        };
        mCancerView.setAdapter(cancerAdapter);
    }

    public void populateCutaneousView(){
        FirebaseRecyclerAdapter<DiseaseModel,CancerViewHolder> cutaneousAdapter = new FirebaseRecyclerAdapter <DiseaseModel, CancerViewHolder>(
                DiseaseModel.class,
                R.layout.linear_model,
                CancerViewHolder.class,
                dataCutaneous
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
        mCutaneousView.setAdapter(cutaneousAdapter);
    }

    public void populateEndocrine(){
        FirebaseRecyclerAdapter<DiseaseModel,CancerViewHolder> cutaneousAdapter = new FirebaseRecyclerAdapter <DiseaseModel, CancerViewHolder>(
                DiseaseModel.class,
                R.layout.linear_model,
                CancerViewHolder.class,
                dataEndocrine
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
                        goDetails.putExtra("root","endocrine");
                        startActivity(goDetails);
                    }
                });
            }
        };
        mEndocrine.setAdapter(cutaneousAdapter);
    }

    //view holder class
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
