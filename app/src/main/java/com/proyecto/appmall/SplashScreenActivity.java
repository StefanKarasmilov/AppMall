package com.proyecto.appmall;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

            // Clase para realizar un Splash cuando se inicia la app
            //Duración del splash
            private static final long SPLASH_SCREEN_DELAY = 3000;

            @TargetApi(19)
            private void hideVirtualButtons() {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }

            @SuppressLint("ResourceType")
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                //Establece orientación
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //Oculta la barra
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                //Quitamos barra de notificaciones
                this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //Esconder botones en el splash (solo para versiones superiores a kitkat)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    hideVirtualButtons();
                }

                setContentView(R.layout.splash);

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        //Empieza la siguiente actividad
                        Intent mainIntent = new Intent().setClass(
                                SplashScreenActivity.this, LoginActivity.class);
                        startActivity(mainIntent);

                        // Cierra la actividad
                        finish();
                    }
                };

                //Simula el tiempo de espera
                Timer timer = new Timer();
                timer.schedule(task, SPLASH_SCREEN_DELAY);
            }

            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                super.onWindowFocusChanged(hasFocus);
                if (hasFocus) {
                    // In KITKAT (4.4) and next releases, hide the virtual buttons
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        hideVirtualButtons();
                    }
                }
            }


        }
