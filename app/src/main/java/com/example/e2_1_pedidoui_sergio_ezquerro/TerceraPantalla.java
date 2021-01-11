package com.example.e2_1_pedidoui_sergio_ezquerro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class TerceraPantalla extends AppCompatActivity {

    //Atbs
    int count_black;
    int count_milk;
    int count_macchiato;
    String delivery_method;
    String time;
    String date;

    //Elems
    EditText etName;
    EditText etDir;
    EditText etTfn;
    Spinner spTfnType;

    //Cons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_pantalla);

        etName = findViewById(R.id.TP_ET_name);
        etDir = findViewById(R.id.TP_ET_dir);
        etTfn = findViewById(R.id.TP_ET_tfn);
        spTfnType = findViewById(R.id.TP_spinner_phoneType);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        count_black = (int) extras.get("count_black");
        count_macchiato = (int) extras.get("count_macchiato");
        count_milk = (int) extras.get("count_milk");
        delivery_method = (String) extras.get("delivery_method");
        time = (String) extras.get("time");
        date = (String) extras.get("date");
    }

    //Methods
    public void confirmarPedido(View view) {
        if (etTfn.getText().toString().isEmpty() || etDir.getText().toString().isEmpty() || etName.getText().toString().isEmpty()){
            String msg = getResources().getString(R.string.fill_all_fields);
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder alertaCofirmar = new AlertDialog.Builder(TerceraPantalla.this);
            alertaCofirmar.setMessage(R.string.alert_Message);

            alertaCofirmar.setNegativeButton(R.string.cancel_button, (dialog, which) -> {
                String msg = getResources().getString(R.string.check_the_order);
                Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_SHORT).show();
            });

            alertaCofirmar.setPositiveButton(R.string.positive_button, (dialog, which) -> enviarCorreo());

            alertaCofirmar.show();
        }
    }

    private void enviarCorreo(){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.my_email_adress)}); //destinatario

        String langCode = Locale.getDefault().getLanguage();

        if (langCode.equals("es")){
            intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + etName.getText().toString()); //sujeto del mensaje
            String cuerpo = "Mi pedido es: \n" +
                    (count_milk + " Cafés con Leche \n") +
                    (count_black + " Cafés Solos \n") +
                    (count_macchiato + " Cafés Cortados \n") +
                    ("La fecha de entrega es " + date + " y la hora de entrega " + time + "\n") +
                    ("Modo de entrega: " + delivery_method + "\n") +
                    ("Dirección: " + etDir.getText().toString() + "\n") +
                    ("Número de teléfono: " + etTfn.getText().toString() + "(" + spTfnType.getSelectedItem().toString() + ")");
            intent.putExtra(Intent.EXTRA_TEXT, cuerpo); //Cuerpo de mensaje

        } else{
            intent.putExtra(Intent.EXTRA_SUBJECT, etName.getText().toString() + "'s Order"); //sujeto del mensaje
            String cuerpo = "My order is: \n" +
                    (count_milk + " Milk Coffee \n") +
                    (count_black + " Black Coffee \n") +
                    (count_macchiato + " Macchiato Coffee \n") +
                    ("The delivery date is " + date + " and the delivery time is " + time + "\n") +
                    ("Delivery method: " + delivery_method + "\n") +
                    ("Direction: " + etDir.getText().toString() + "\n") +
                    ("Phone number: " + etTfn.getText().toString() + "(" + spTfnType.getSelectedItem().toString() + ")");
            intent.putExtra(Intent.EXTRA_TEXT, cuerpo); //Cuerpo de mensaje
        }

        startActivity(Intent.createChooser(intent, getResources().getString(R.string.send_Email)));
    }
}