package br.senai.sp.cotia.imccalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView imageView;
    private double imc = 0;
    private String genero = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // referencia o imageView
        imageView = findViewById(R.id.image_view);

        // verifica se existe o imc na Intent
        if (getIntent().hasExtra("imc")){
            // fazendo desse jeito nao precisa do if
            imc =  getIntent().getDoubleExtra("imc", 0);
        }

        //verifica se existe o genero na Intent
        if (getIntent().hasExtra("genero")){
            genero = getIntent().getStringExtra("genero");
        }

        if(genero.equals("masculino")){
            if (imc <= 21){
                imageView.setImageResource(R.drawable.magro);
            } else if (imc <= 26) {
                imageView.setImageResource(R.drawable.brad);
            }else{
                imageView.setImageResource(R.drawable.josoares);
            }
        }else{
            if (imc <= 21){
                imageView.setImageResource(R.drawable.olivia);
            } else if (imc <= 26) {
                imageView.setImageResource(R.drawable.agenlina);
            }else{
                imageView.setImageResource(R.drawable.thais);
            }
        }
    }
}