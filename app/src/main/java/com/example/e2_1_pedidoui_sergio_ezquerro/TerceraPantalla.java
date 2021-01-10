package com.example.e2_1_pedidoui_sergio_ezquerro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TerceraPantalla extends AppCompatActivity {

    //Atbs
    int count_black;
    int count_milk;
    int count_macchiato;

    //Cons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_pantalla);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        count_black = (int) extras.get("count_black");
        count_macchiato = (int) extras.get("count_macchiato");
        count_milk = (int) extras.get("count_milk");
    }

    //Methods
    public void confirmarPedido(View view) {
        AlertDialog.Builder alertaCofirmar = new AlertDialog.Builder(TerceraPantalla.this);
        alertaCofirmar.setMessage(R.string.alert_Message);

        alertaCofirmar.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = getResources().getString(R.string.check_the_order);
                Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

        alertaCofirmar.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));

                //TODO
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""}); //destinatario
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject)); //sujeto del mensaje
                //TODO
                intent.putExtra(Intent.EXTRA_TEXT, ""); //Cuerpo de mensaje

                startActivity(Intent.createChooser(intent, getResources().getString(R.string.send_Email)));
            }
        });

        alertaCofirmar.show();
    }
}