package com.example.eliezer.selfdoctor;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details extends AppCompatActivity {

    private TextView details_desc,details_symptoms,details_prevention,details_cure;
    private ImageView details_image,close;
    private DatabaseReference mDatabase;
    private String link_id, root;
    private Toolbar mToolbar;
    private BottomNavigationView bottomNav;
    private BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_cure = findViewById(R.id.cure_here);
        details_desc = findViewById(R.id.description_here);
        details_prevention = findViewById(R.id.prevention_here);
        details_symptoms = findViewById(R.id.syptoms_here);
        details_image = findViewById(R.id.image_here);
        close = findViewById(R.id.close);

        //Bottom navigation view initialization and declaration
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.contact_doctor:
                        dialogBox();

                }
                return true;
            }
        });

        //Bottom sheet view initialization and declaration
        View bottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        //Close ImageView implementation. close bottom sheet
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });


        //Toolbar initialization and declaration
        mToolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        root = getIntent().getStringExtra("root");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Disease").child(root);
        mDatabase.keepSynced(true);

        link_id = getIntent().getStringExtra("link");

        mDatabase.child(link_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String cure = dataSnapshot.child("cure").getValue().toString();
                String desc = dataSnapshot.child("description").getValue().toString();
                String symptoms = dataSnapshot.child("syptoms").getValue().toString();
                String prevention = dataSnapshot.child("prevention").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();

                getSupportActionBar().setTitle(name);

                initComponent(symptoms,desc,cure,prevention);
                Glide
                        .with(getApplicationContext())
                        .load(image).apply(new RequestOptions()
                        .placeholder(R.drawable.doctor)
                        .dontAnimate()
                        .dontTransform())
                        .into(details_image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void initComponent(String symptoms,String desc,String cure,String prevention){
        details_symptoms.setText(symptoms);
        details_desc.setText(desc);
        details_cure.setText(cure);
        details_prevention.setText(prevention);
    }

    public void dialogBox(){

        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomNav.setVisibility(View.GONE);
        }else{
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
}
