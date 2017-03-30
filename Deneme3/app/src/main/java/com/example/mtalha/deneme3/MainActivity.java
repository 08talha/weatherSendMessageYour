package com.example.mtalha.deneme3;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.net.Uri;
import android.preference.PreferenceManager;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {
    TextView yer, havaDetayi, havaninDurumu, derece, nemOrani, weatherIcon, ulkeKodu, textView,txtview7,deneme;



    Typeface weatherFont;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = preferences.edit();

        boolean GirdiSayfasi = preferences.getBoolean("GirdiSayfasi", false);
        if(GirdiSayfasi==false){
            Intent girdisaydasinagit = new Intent(MainActivity.this, GirdiSayfasi.class);
            startActivity(girdisaydasinagit);

        }
        final MsgDb mbdata;
        mbdata = new MsgDb(getApplicationContext());

        final String gpsAcildi = "GPS Acıldı";
        final String gpsKapatildi = "GPS Kapalı";
        final String[] lat = {""};
        final String[] lon = {""};

        boolean Konumbtnu = preferences.getBoolean("konum", false);
        editor.commit();
        if(Konumbtnu==false){
            lat[0]="41.032657";
            lon[0]="28.788122";
        }

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        String[] st;

        Button knmAl = (Button) findViewById(R.id.button);
        txtview7=(TextView) findViewById(R.id.textView7) ;
        yer = (TextView) findViewById(R.id.city_field);
        ulkeKodu = (TextView) findViewById(R.id.updated_field);
        havaninDurumu = (TextView) findViewById(R.id.details_field);
        derece = (TextView) findViewById(R.id.current_temperature_field);
        havaDetayi = (TextView) findViewById(R.id.humidity_field);
        nemOrani = (TextView) findViewById(R.id.pressure_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        textView = (TextView) findViewById(R.id.textView);
        final FunctionDeneme function = new FunctionDeneme();
        deneme=(TextView)findViewById(R.id.textView3);
        mbdata.insertContact("Hava güzel hazırlan fırla sahiller seni bekler :D",
                "Ara ara göz at dışarıya okul tatil olsun diye de dua et :D",
                "Yağmur yağayi yağmur, yaylanun çimenine, Al şemsuyeni yanuna…",
                "Yol neredeyse yok eğlenecek bir şeyde yok gibi kardeş.");


        final String[] derecec = {"0"};

        knmAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] stt;
                LocationManager konumYoneticisi = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                LocationListener konumDinleyicisi = new LocationListener() {

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                        Toast.makeText(getApplicationContext(), gpsAcildi, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Toast.makeText(getApplicationContext(), gpsKapatildi, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onLocationChanged(Location loc) {
                        lat[0] = "" + loc.getLatitude();
                        lon[0] = "" + loc.getLongitude();
                        editor.putBoolean("GirdiSayfasi", true);
                        editor.putBoolean("Konumbtnu", true);
                        editor.commit();

                    }
                };


                konumYoneticisi.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, konumDinleyicisi);


                String veri = function.getWeatherJSON(lat[0], lon[0]);
                deneme.setText( " "+lat[0]+ " "+lon[0]);
                //String[] derecec={"0"};
                if(veri == null) {
                    veri = "empty string";
                }
                else{

                    stt = veri.split(" ");
                    yer.setText(stt[0]);
                    ulkeKodu.setText(stt[1]);
                    havaDetayi.setText(stt[2]);
                    havaninDurumu.setText(stt[3]);
                    derece.setText(stt[4]);
                    nemOrani.setText(stt[5] + " nem oranı");
                    derecec[0] =stt[3];
                    //txtview7.setText(derecec[0]);
                }



            }
        });


        String veri = function.getWeatherJSON(lat[0], lon[0]);


        if(veri == null) {
            veri = "empty string";
        }
        else{
        st = veri.split(" ");


            yer.setText(st[0]);
            ulkeKodu.setText(st[1]);
            havaDetayi.setText(st[2]);
            havaninDurumu.setText(st[3]);
            derece.setText(st[4]);
            nemOrani.setText(st[5] + " nem oranı");

            derecec[0] = st[3];

        }

        txtview7.setText(derecec[0]);

        if(derecec[0] == "clear") {
            Cursor rsGunes = mbdata.getData(1, "gunesli");


            rsGunes.moveToFirst();


            String mesaj1 = rsGunes.getString(rsGunes.getColumnIndex(mbdata.CONTACTS_COLUMN_Gunesli));

            txtview7.setText(mesaj1);
        }
        else if(derecec[0] == "rain" ) {

            Cursor rsYagmur = mbdata.getData(1,  "yagmurlu");
            rsYagmur.moveToFirst();

            String mesaj2 = rsYagmur.getString(rsYagmur.getColumnIndex(mbdata.CONTACTS_COLUMN_Yahmurlu));


            txtview7.setText(mesaj2);
        }
        else if(derecec[0] == "snow") {
            Cursor rsKarli = mbdata.getData(1, "karli");
            rsKarli.moveToFirst();

            String mesaj3 = rsKarli.getString(rsKarli.getColumnIndex(mbdata.CONTACTS_COLUMN_Karli));

            txtview7.setText(mesaj3);
        }

        deneme.setText(function.bug_Feedback);
    }



    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }



    @Override
    public void onStart() {
        super.onStart();


        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
