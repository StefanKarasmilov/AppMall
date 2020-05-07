package com.proyecto.appmall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;


public class StepViewActivity extends AppCompatActivity {

    TextView txtContenido;
    ImageView imgHospi;
    Button btNext, btBack;
    private int currentStep = -1;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_main);
        txtContenido = findViewById(R.id.txtIndicacion);
        imgHospi = findViewById(R.id.imageView);
        btNext = findViewById(R.id.next);
        btBack = findViewById(R.id.back);

        // Se instancia el objeto StepView
        final StepView stepView = findViewById(R.id.step_view);

        // Metodo Intent para pasar variables entre activity
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        final String cadena = extra.getString("respuesta");
        final String valor = extra.getString("message");
        final int contador_dos = Integer.parseInt(valor);

        if (contador_dos > 4) {

            stepView.setLayoutParams(new LinearLayout.LayoutParams(1500,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        stepView.getState()
                // Array donde introducimos los states o los pasos introducidos
                .steps(new ArrayList<String>() {{
                    for (int i = 1; i <= contador_dos; i++) {
                        add("Paso " + i);
                    }
                }})
                .animationDuration(getResources().getInteger(android.R.integer.config_longAnimTime))
                .stepsNumber(contador_dos)
                .commit();

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    txtContenido.setVisibility(View.VISIBLE);
                    imgHospi.setVisibility(View.VISIBLE);
                    a = 0;

                    // bucle para que recorra todos los states creados
                    for (int i = 0; i <= contador_dos; i++) {

                        // Condicionales para que se desplace el scroll hacia right
                        if (currentStep >= 3) {
                            HorizontalScrollView sv = findViewById(R.id.horizontalScrollView);
                            sv.smoothScrollTo(400, sv.getRight());
                        }
                        if (currentStep >= 4) {
                            HorizontalScrollView sv = findViewById(R.id.horizontalScrollView);
                            sv.smoothScrollTo(800, sv.getRight());
                        }

                        // Condionales para activar/desactivar boton de cada step
                        if (currentStep == 0) {
                            btNext.setText(getString(R.string.iniciar));
                        }
                        if (currentStep > 0 && currentStep < contador_dos - 1) {
                            btBack.setVisibility(View.VISIBLE);
                            btNext.setText(getString(R.string.avanzar));
                        }
                        if (currentStep >= contador_dos - 1) {
                            btNext.setText(getString(R.string.finalizar));
                        }

                        // fijar cada paso con imagen y String
                        if (currentStep == i) {
                            char read = cadena.charAt(a);
                            String c = Integer.toString(a);

                            if (read == 'h') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.hall));
                                imgHospi.setImageResource(R.drawable.hall_entrada);
                            }
                            if (read == 'l') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.lavabo));
                                imgHospi.setImageResource(R.drawable.lavabos);
                            }
                            if (read == 'z') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.zona_comercial));
                                imgHospi.setImageResource(R.drawable.zona_comercial);
                            }
                            if (read == 'a') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ascensor));
                                imgHospi.setImageResource(R.drawable.ascensor);
                            }
                            if (read == 's') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.supermercado));
                                imgHospi.setImageResource(R.drawable.supermercado);
                            }
                            if (read == 'e') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.escaleras));
                                imgHospi.setImageResource(R.drawable.escaleras);
                            }
                            if (read == 'p') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.parking));
                                imgHospi.setImageResource(R.drawable.parking);
                            }
                            if (read == 'o') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.out));
                                imgHospi.setImageResource(R.drawable.out);
                            }

                        }
                        a++;
                    }
                } else {
                    stepView.done(true);
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            // metodo para ir hacia tras en Step
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                    txtContenido.setVisibility(View.VISIBLE);
                    imgHospi.setVisibility(View.VISIBLE);
                    int e = currentStep;

                    // bucle para que recorra todos los states creados
                    for (int i = currentStep; i <= contador_dos; i++) {

                        // Condicionales para que se desplace el scroll hacia left
                        if (currentStep <= 4) {
                            HorizontalScrollView sv = findViewById(R.id.horizontalScrollView);
                            sv.smoothScrollTo(800, sv.getRight());
                        }
                        if (currentStep <= 3) {
                            HorizontalScrollView sv = findViewById(R.id.horizontalScrollView);
                            sv.smoothScrollTo(400, sv.getRight());
                        }
                        if (currentStep <= 2) {
                            HorizontalScrollView sv = findViewById(R.id.horizontalScrollView);
                            sv.smoothScrollTo(0, sv.getRight());
                        }

                        // Condionales para activar/desactivar boton de cada step
                        if (currentStep <= contador_dos - 1) {
                            btNext.setText(getString(R.string.avanzar));
                        }
                        if (currentStep == 0) {
                            btBack.setVisibility(View.INVISIBLE);
                        }

                        // fijar cada paso con imagen y String
                        if (currentStep == i) {
                            char read = cadena.charAt(e);
                            String c = Integer.toString(e);

                            if (read == 'h') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.hall));
                                imgHospi.setImageResource(R.drawable.hall_entrada);
                            }
                            if (read == 'l') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.lavabo));
                                imgHospi.setImageResource(R.drawable.lavabos);
                            }
                            if (read == 'z') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.zona_comercial));
                                imgHospi.setImageResource(R.drawable.zona_comercial);
                            }
                            if (read == 'a') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ascensor));
                                imgHospi.setImageResource(R.drawable.ascensor);
                            }
                            if (read == 's') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.supermercado));
                                imgHospi.setImageResource(R.drawable.supermercado);
                            }
                            if (read == 'e') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.escaleras));
                                imgHospi.setImageResource(R.drawable.escaleras);
                            }
                            if (read == 'p') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.parking));
                                imgHospi.setImageResource(R.drawable.parking);
                            }
                            if (read == 'o') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor
                                                (getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.out));
                                imgHospi.setImageResource(R.drawable.out);
                            }

                        }
                        e--;
                    }
                }
                stepView.done(false);
            }
        });
    }
}

// Listener para cuando clickas (Modo Tactil) en cada estado o paso avance el step
        /*stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                //Toast.makeText(StepViewActivity.this, "Step " + step, Toast.LENGTH_LONG).show();
                txtContenido.setVisibility(View.VISIBLE);
                imgHospi.setVisibility(View.VISIBLE);

                // bucle para que recorra todos los states creados
                int a = 2;
                for (int i = 0; i <= contador_dos; i++) {

                    // Condionales para cada step
                    if (step == i) {
                        char read = cadena.charAt(a);

                        if (read == 'r') {

                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor
                                    (getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_uno));
                            imgHospi.setImageResource(R.drawable.pasillo_uno);
                        }
                        if (read == 'u') {

                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor
                                    (getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_dos));
                            imgHospi.setImageResource(R.drawable.pasillo_dos);
                     }
                    }
                    a++;
                }
            }
        });*/


