package com.example.mtalha.deneme3;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by M.TaLha on 28.11.2016.
 */

public class FunctionDeneme {
    private static String APIkey="a26d26aa2d7b42b27524a3fe389b136f";
    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&APPID=%s";
    private static final String LOG_TAG = "";
    static String jsonString="";
    public String Veri="";
    public static String bug_Feedback="";
    /*public static String setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = "&#xf00d;";
            } else {
                icon = "&#xf02e;";
            }
        } else {
            switch(id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }
        }
        return icon;
    }*/


    public static String jsonPars(JSONObject json){

        try {
            bug_Feedback=bug_Feedback+" pars try";
        if(json != null){

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            DateFormat df = DateFormat.getDateTimeInstance();


            String city = json.getString("name") + ", " + json.getJSONObject("sys").getString("country");
            String description = details.getString("description");
            String temperature = String.format("%.2f", main.getDouble("temp"))+ "°";
            String humidity = "%"+ main.getString("humidity") ;
            String pressure = main.getString("pressure") + " hPa";
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            /* String iconText = setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000); */

            jsonString=(city+" "+ description+" "+ temperature+" "+humidity+" "+ pressure+" "+ updatedOn+"  iconText  "+ (json.getJSONObject("sys").getLong("sunrise") * 1000));

        }
    } catch (JSONException e) {

            bug_Feedback=bug_Feedback+"pars catch ";
        Log.e(LOG_TAG, "Cannot process JSON results", e);

        }
        return jsonString;
    }


    public String getWeatherJSON(String lat, String lon){

        try {

            bug_Feedback=bug_Feedback+"get try ";

            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, lat, lon,APIkey));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            connection.addRequestProperty("a26d26aa2d7b42b27524a3fe389b136f", OPEN_WEATHER_MAP_API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)

            json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }


            Veri=FunctionDeneme.jsonPars(data);

        }catch(Exception e){
            bug_Feedback=bug_Feedback+"get catch ";
            return null;
        }


        return Veri;
    }
}
