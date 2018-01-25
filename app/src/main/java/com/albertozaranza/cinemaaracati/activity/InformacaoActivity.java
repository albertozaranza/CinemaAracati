package com.albertozaranza.cinemaaracati.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertozaranza.cinemaaracati.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

public class InformacaoActivity extends AppCompatActivity {

    private String censura, duracao, genero, horario, imagem, nome, sala,  sinopse, tipo;
    private AdView adView;
    private InterstitialAd interstitialAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cinema Aracati");
        //toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_intersticial_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        adView = findViewById(R.id.admobBanner);
        AdRequest bannerRequest = new AdRequest.Builder().build();
        adView.loadAd(bannerRequest);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            censura = extra.getString("censura");
            duracao = extra.getString("duracao");
            genero = extra.getString("genero");
            horario = extra.getString("horario");
            imagem = extra.getString("imagem");
            nome = extra.getString("nome");
            sala = extra.getString("sala");
            sinopse = extra.getString("sinopse");
            tipo = extra.getString("tipo");
        }

        ImageView imageView = findViewById(R.id.imageView);

        TextView textViewNome = findViewById(R.id.textViewNome);
        TextView textViewTipo = findViewById(R.id.textViewTipo);
        TextView textViewSala = findViewById(R.id.textViewSala);
        TextView textViewHorario = findViewById(R.id.textViewHorarioEdit);
        TextView textViewGenero = findViewById(R.id.textViewGeneroEdit);
        TextView textViewCensusa = findViewById(R.id.textViewCensuraEdit);
        TextView textViewDucaracao = findViewById(R.id.textViewDuracaoEdit);
        TextView textViewSinopse = findViewById(R.id.textViewSinopse);

        Picasso.with(getApplicationContext()).load(imagem).into(imageView);
        textViewNome.setText(nome);
        textViewTipo.setText(tipo);
        textViewSala.setText(sala);
        textViewHorario.setText(horario);
        textViewGenero.setText(genero);
        textViewCensusa.setText(censura);
        textViewDucaracao.setText(duracao);
        textViewSinopse.setText(sinopse);

    }

    @Override
    protected void onPause() {
        if(adView!=null){
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(adView!=null){
            adView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if(adView!=null){
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }
    }
}
