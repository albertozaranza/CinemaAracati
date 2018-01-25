package com.albertozaranza.cinemaaracati.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albertozaranza.cinemaaracati.R;
import com.albertozaranza.cinemaaracati.adapter.FilmeAdapter;
import com.albertozaranza.cinemaaracati.model.Filme;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("filmes");
    private Intent intent;

    private ArrayAdapter arrayAdapter;
    private ArrayList<Filme> filmes;
    private ValueEventListener valueEventListenerFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cinema Aracati");

        filmes = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);
        arrayAdapter = new FilmeAdapter(getApplicationContext(), filmes);
        listView.setAdapter(arrayAdapter);

        valueEventListenerFilmes = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                filmes.clear();
                for(DataSnapshot dados : dataSnapshot.getChildren()){
                    Filme filme = dados.getValue(Filme.class);
                    filmes.add(filme);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListenerFilmes);
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListenerFilmes);
    }

    // TODO: 27/12/2017 VERIFICAR NECESSIDADE DO MENU 

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_feedback:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"albertozaranza@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback - Cinema Aracati (APP)");
                try {
                    startActivity(Intent.createChooser(intent, "Enviando e-mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Não há clientes de e-mail instalados.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_contato:
                String url = "https://linktr.ee/alberto_zaranza";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
