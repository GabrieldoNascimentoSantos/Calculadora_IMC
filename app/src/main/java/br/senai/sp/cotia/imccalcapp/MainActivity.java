package br.senai.sp.cotia.imccalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editPeso, editAltura;
    private Button btCalcular, btLimpar;
    private TextView tvValor, tvClassificacao;
    private RadioGroup groupGenero;
    private Spinner spinnerGenero;
    private String genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referenciando os componentes na tela
        editPeso = findViewById(R.id.edit_peso);
        editAltura = findViewById(R.id.edit_altura);
        btCalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);
        tvValor = findViewById(R.id.tv_valor);
        tvClassificacao = findViewById(R.id.tv_classificacao);
        spinnerGenero = findViewById(R.id.spinner_genero);
        groupGenero = findViewById(R.id.group_gereno);

        btCalcular.setOnClickListener(v -> {
            // validar os campos
            if (editPeso.getText().toString().isEmpty()){
                editPeso.setError(getString(R.string.valida_peso));
                Toast.makeText(getBaseContext(), R.string.valida_peso, Toast.LENGTH_SHORT).show();
            }else if (editAltura.getText().toString().isEmpty()){
                editAltura.setError((getString(R.string.valida_altura)));
                Snackbar.make(editAltura, R.string.valida_altura, Snackbar.LENGTH_SHORT).show();
            }else{
                double peso, altura, imc;
                peso = Double.parseDouble(editPeso.getText().toString());
                altura = Double.parseDouble(editAltura.getText().toString());
                imc = peso / (altura * altura);

                if (imc < 18.5){
                    tvClassificacao.setText("Abaixo do peso");
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.amarelo));
                }else if(imc < 24.9 ){
                    tvClassificacao.setText(R.string.pesoNormal);
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.verde));
                }else if(imc < 29.9){
                    tvClassificacao.setText(R.string.sobrepeso);
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.salmao));
                }else if(imc < 34.9){
                    tvClassificacao.setText(R.string.obesidadeI);
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.laranja));
                }else if(imc < 39.9){
                    tvClassificacao.setText(R.string.obsidadeII);
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.vermelho));
                }else {
                    tvClassificacao.setText(R.string.obesiidadeIII);
                    tvClassificacao.setBackgroundColor(getResources().getColor(R.color.vermeEscuro));
                }
                tvValor.setText(getString(R.string.imc, imc));

                // pegar o valor selecionado na Spinner
                if(spinnerGenero.getSelectedItemPosition() == 0){
                    genero = "masculino";

                }else{
                    genero = "feminino";
                }
                // pegar o radioButton selecionado
                switch (groupGenero.getCheckedRadioButtonId()){
                    case R.id.radio_feminino:
                        genero = "feminino";
                        break;
                    case R.id.radio_masculino:
                        genero = "masculino";
                        break;
                }

                //abrir activity resultado ex:abrindo outra activity
                Intent intent = new Intent(this, ResultadoActivity.class);
                intent.putExtra("imc", imc);
                intent.putExtra("genero", genero);
                startActivity(intent);
                //finish();
            }
        });
        btLimpar.setOnClickListener(view -> {
            editPeso.setText("");
            editAltura.setText("");
            tvValor.setText("");
            tvClassificacao.setText("");
        });
    }
}