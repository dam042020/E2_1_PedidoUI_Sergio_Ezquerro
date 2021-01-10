package com.example.e2_1_pedidoui_sergio_ezquerro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_blackCofee_count;
    TextView tv_milkCofee_count;
    TextView tv_macchiatoCofee_count;

    int count_black;
    int count_milk;
    int count_macchiato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_blackCofee_count = findViewById(R.id.MA_tv_black_coffee_count);
        tv_milkCofee_count = findViewById(R.id.MA_tv_milk_coffee_count);
        tv_macchiatoCofee_count = findViewById(R.id.MA_tv_macchiato_coffee_count);

        count_black = 0;
        count_milk = 0;
        count_macchiato = 0;

        tv_blackCofee_count.setText(String.valueOf(count_black));
        tv_milkCofee_count.setText(String.valueOf(count_milk));
        tv_macchiatoCofee_count.setText(String.valueOf(count_macchiato));
    }

    public void add(View view) {
        Button btn = (Button) view;
        String id = btn.getTag().toString();
        switch (id) {
            case "black":
                count_black++;
                tv_blackCofee_count.setText(String.valueOf(count_black));
                break;
            case "milk":
                count_milk++;
                tv_milkCofee_count.setText(String.valueOf(count_milk));
                break;
            case "macchiato":
                count_macchiato++;
                tv_macchiatoCofee_count.setText(String.valueOf(count_macchiato));
                break;
            default:
                break;
        }
    }

    public void subtract(View view) {
        Button btn = (Button) view;
        String id = btn.getTag().toString();
        switch (id) {
            case "black":
                if (count_black > 0) {
                    count_black--;
                    tv_blackCofee_count.setText(String.valueOf(count_black));
                }
                break;
            case "milk":
                if (count_milk > 0) {
                    count_milk--;
                    tv_milkCofee_count.setText(String.valueOf(count_milk));
                }
                break;
            case "macchiato":
                if (count_macchiato > 0) {
                    count_macchiato--;
                    tv_macchiatoCofee_count.setText(String.valueOf(count_macchiato));
                }
                break;
            default:
                break;
        }
    }

    public void resetCofeeCounts(View view) {
        count_black = 0;
        count_milk = 0;
        count_macchiato = 0;
        tv_blackCofee_count.setText(String.valueOf(count_black));
        tv_milkCofee_count.setText(String.valueOf(count_milk));
        tv_macchiatoCofee_count.setText(String.valueOf(count_macchiato));
    }

    public void goToSegundaPantalla(View view) {
        Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);

        intent.putExtra("count_black",count_black);
        intent.putExtra("count_macchiato",count_macchiato);
        intent.putExtra("count_milk",count_milk);

        startActivity(intent);
    }
}