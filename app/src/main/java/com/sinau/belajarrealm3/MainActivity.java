package com.sinau.belajarrealm3;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    ArrayList<String> spacecrafts;
    MyAdapter adapter;
    RecyclerView rv;
    EditText nameEditTxt;
    String[] a = {"1","2","3"};
    private RealmHelper help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //SETUP RECYCLERVIEW
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //SETUP REEALM
//        RealmConfiguration config=new RealmConfiguration.Builder(this).build();
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);

        realm=Realm.getInstance(realmConfiguration);
        //RETRIEVE
//        RealmHelper helper=new RealmHelper(realm);
//        spacecrafts=helper.retrieve();
        Log.w("GETITEMCOUNT", "onCreate: "+spacecrafts );
        if (realm.isEmpty()){
            Spacecraft s=new Spacecraft();
            for (int b=0;b<a.length;b++){
                s.setName(a[b]);

                //SAVE
                help=new RealmHelper(realm);
                help.save(s);
                //BIND
                spacecrafts = help.retrieve();
                adapter = new MyAdapter(this, spacecrafts);
                rv.setAdapter(adapter);

            }

        }else{

//        if (adapter.getItemCount()!=0){
            a=null;
            RealmHelper helpo=new RealmHelper(realm);
            spacecrafts = helpo.retrieve();
            adapter = new MyAdapter(this, spacecrafts);
            rv.setAdapter(adapter);
        }

        Log.w("a", "a: "+a );


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }
    //SHOW DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Realm");
        d.setContentView(R.layout.input_dialog);
        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA

                Spacecraft s=new Spacecraft();
                for (int b=0;b<a.length;b++){
                    s.setName(a[b]);
                    //SAVE
                    RealmHelper helper=new RealmHelper(realm);
                    helper.save(s);
                    nameEditTxt.setText("");
                    spacecrafts=helper.retrieve();

                }


                //REFRESH
                adapter=new MyAdapter(MainActivity.this,spacecrafts);
                rv.setAdapter(adapter);
            }
        });
        d.show();
    }
}
