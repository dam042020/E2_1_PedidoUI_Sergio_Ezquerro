package com.example.e2_1_pedidoui_sergio_ezquerro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class SegundaPantalla extends AppCompatActivity {

//Atbs
    int count_black;
    int count_milk;
    int count_macchiato;

//priv aux final atbs
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";

//Aux

    //Calendario (Obtener fecha & hora)
    public final Calendar c = Calendar.getInstance();

    //Tiempo
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

//Elems
    TextView tvHora;
    TextView tvFecha;

//Cons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);

        tvHora = findViewById(R.id.SP_tv_time);
        tvFecha = findViewById(R.id.SP_tv_date);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        count_black = (int) extras.get("count_black");
        count_macchiato = (int) extras.get("count_macchiato");
        count_milk = (int) extras.get("count_milk");

        // Asignar tvFecha
        {
            //Formateo dia
            String diaFormateado = (dia < 10) ? CERO + (dia) : String.valueOf(dia);

            //Formateo mes
            String mesFormateado = (mes < 10) ? CERO + (mes) : String.valueOf(mes);

            String nuevaFecha = diaFormateado + BARRA + mesFormateado + BARRA + anio;
            tvFecha.setText(nuevaFecha);
        }

        // Asignar tvHora
        {
            //Formateo hora
            String horaFormateada =  (hora < 10)? (CERO + hora) : String.valueOf(hora);

            //Formateo minuto
            String minutoFormateado = (minuto < 10)? (CERO + minuto):String.valueOf(minuto);

            String nuevaHora = horaFormateada + DOS_PUNTOS + minutoFormateado;
            tvHora.setText(nuevaHora);
        }

        //TODO Asignar nuevos campos a enviar
    }

//Methods
    public void goToThirdScreen(View view) {
        Intent intent = new Intent(SegundaPantalla.this, TerceraPantalla.class);

        intent.putExtra("count_black",count_black);
        intent.putExtra("count_macchiato",count_macchiato);
        intent.putExtra("count_milk",count_milk);
        //TODO Enviar nuevos campos
        //intent.putExtra("","");
        //intent.putExtra("","");
        //intent.putExtra("","");

        startActivity(intent);
    }

    public void selectTime(View view) {
        TimePickerDialog recogerHora = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
            //Formateo la hora
            String horaFormateada =  (hourOfDay < 10)? (CERO + hourOfDay) : String.valueOf(hourOfDay);

            //Formateo el minuto
            String minutoFormateado = (minute < 10)? (CERO + minute):String.valueOf(minute);

            //Muestro la hora con el formato deseado
            String nuevaHora = horaFormateada + DOS_PUNTOS + minutoFormateado;
            tvHora.setText(nuevaHora);

        }, hora, minuto, true);

        recogerHora.show();
    }

    public void selectDate(View view) {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, (view1, year, month, dayOfMonth) -> {
            //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
            final int mesActual = month + 1;

            //Formateo el día obtenido
            String diaFormateado = (dayOfMonth < 10)? CERO + (dayOfMonth):String.valueOf(dayOfMonth);

            //Formateo el mes obtenido
            String mesFormateado = (mesActual < 10)? CERO + (mesActual):String.valueOf(mesActual);

            //Muestro la fecha con formato
            String nuevaFecha = diaFormateado + BARRA + mesFormateado + BARRA + year;
            tvFecha.setText(nuevaFecha);
        },anio, mes, dia);

        recogerFecha.show();

    }
}