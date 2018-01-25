package com.albertozaranza.cinemaaracati.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.albertozaranza.cinemaaracati.R;
import com.albertozaranza.cinemaaracati.activity.InformacaoActivity;
import com.albertozaranza.cinemaaracati.model.Filme;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Alberto Zaranza on 28/12/2017.
 */

public class FilmeAdapter extends ArrayAdapter<Filme> {

    private ArrayList<Filme> filmes;
    private Context context;
    private Intent intent;

    public FilmeAdapter(@NonNull Context c, @NonNull ArrayList<Filme> objects) {
        super(c, 0, objects);
        this.filmes = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(filmes != null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.lista_filme, parent, false);

            AppCompatButton buttonInformacao = view.findViewById(R.id.btn_informacao);
            AppCompatButton buttonTrailer = view.findViewById(R.id.btn_trailer);

            ImageView imageViewIcon = view.findViewById(R.id.imageViewIcon);
            ImageView imageViewBanner = view.findViewById(R.id.imageViewBanner);

            TextView textViewTitulo = view.findViewById(R.id.textViewTitulo);
            TextView textViewSubtitulo = view.findViewById(R.id.textViewSubtitulo);

            final Filme filme = filmes.get(position);

            Picasso.with(context).load(filme.getImagem_card()).into(imageViewBanner);
            imageViewIcon.setImageResource(verificarGenero(filme.getGenero()));

            String subtitulo = filme.getSala() + " / " + filme.getTipo();

            textViewTitulo.setText(filme.getNome());
            textViewSubtitulo.setText(subtitulo);

            buttonInformacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getContext(), InformacaoActivity.class);
                    intent.putExtra("censura", filme.getCensura());
                    intent.putExtra("duracao", filme.getDuracao());
                    intent.putExtra("genero", filme.getGenero());
                    intent.putExtra("horario", filme.getHorario());
                    intent.putExtra("imagem", filme.getImagem());
                    intent.putExtra("nome", filme.getNome());
                    intent.putExtra("sala", filme.getSala());
                    intent.putExtra("sinopse", filme.getSinopse());
                    intent.putExtra("tipo", filme.getTipo());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            buttonTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = filme.getTrailer();
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }else {
            Toast.makeText(context, "Informações não disponíveis", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private int verificarGenero(String genero){
        switch (genero) {
            case "Ação":
                return R.drawable.ic_action;
            case "Aventura":
                return R.drawable.ic_adventures;
            case "Comédia":
                return R.drawable.ic_comedy;
            case "Documentário":
                return R.drawable.ic_documentary;
            case "Drama":
                return R.drawable.ic_drama;
            case "Horror":
                return R.drawable.ic_horror;
            case "Ficção Científica":
                return R.drawable.ic_sci_fi;
            case "Esporte":
                return R.drawable.ic_sport;
            case "Terror":
                return R.drawable.ic_triller;
        }
        return 0;
    }
}
