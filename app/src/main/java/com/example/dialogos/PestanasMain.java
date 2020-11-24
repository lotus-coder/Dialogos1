package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

public class PestanasMain extends AppCompatActivity {


    TextView num1,num2;
    EditText resultado;
    Button btnResuelve;
    private NotificationManager notificationManager;
    static final String CANAL_ID ="mi_canal";
    static final int NOTIFICACION_ID =1;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_main);

        //Referencia al control principal TabHost y preparación para su configuración
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        //Objeto de tipo TabSpec para cada una de las pestañas
        //asignación layout correspondiente a la pestaña (setContent())
        //Indicaremos texto y/o icono de la pestaña (setIndicator(texto, icono))
        TabHost.TabSpec spec = tabs.newTabSpec("miTab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAB 1", getResources().getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB 2", getResources().getDrawable(android.R.drawable.ic_dialog_info));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab3");
        spec.setContent(R.id.tab3);
        //spec.setIndicator("TAB 3", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        spec.setIndicator("", getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        cont = 0 ;
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        resultado = findViewById(R.id.resultado);
        btnResuelve = findViewById(R.id.button);
        num1.setText(""+(dameAleatorio(30)));
        num2.setText(""+dameAleatorio(30));
        //Pestaña activa al arrancar la aplicacion
        tabs.setCurrentTab(0);
        eventos();
    }
    private int dameAleatorio(int max){
        return (int)(Math.random() * (max + 1));
    }

    private void eventos() {
        btnResuelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText(""+dameAleatorio(30));
                num2.setText(""+dameAleatorio(30));
                if(Integer.parseInt(resultado.getText().toString()) == Integer.parseInt(num1.getText().toString())+Integer.parseInt(num2.getText().toString())){
                    cont++;
                    System.out.println(cont+"");
                    if (cont == 1){
                        //Creamos notificacion
                        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        //Creamos el canal. SOLO puede hacerse en dispositivos con ver.8 o más.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID, "Mis notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
                            notificationChannel.setDescription("Descripción del canal");
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(PestanasMain.this,CANAL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Has acertado").setContentText("10 veces");
                        notificationManager.notify(NOTIFICACION_ID, notificacion.build());
                        PendingIntent intencionPendiente = PendingIntent.getActivity(PestanasMain.this, 0,new Intent(PestanasMain.this, PestanasMain.class), 0);
                        notificacion.setContentIntent(intencionPendiente);
                    }
                }
                resultado.setText("");
            }
        });
    }
}