package co.edu.unac.iotunac.functions;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unac.iotunac.objects.Sensor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/*** Created by Kevin Ortiz on 02/10/2018.*/

public class TaskQualityAir extends AsyncTask<Sensor, Void, List<Sensor>> {


    @Override
    protected List<Sensor> doInBackground(Sensor... sensors) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet del = new HttpGet("http://170.238.226.93/info/see/");
        del.setHeader("content-type", "application/json");

        try {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());

            JSONArray respJSON = new JSONArray(respStr);

            List<Sensor> productsList = new ArrayList<>();

            for (int i = 0; i < respJSON.length(); i++) {
                JSONObject obj = respJSON.getJSONObject(i);

                Sensor sensor = new Sensor();
                sensor.setFecha((Date) obj.get("fecha"));
                sensor.setTemperatura(obj.getDouble("temperatura"));
                sensor.setHumedad(obj.getDouble("humedad"));
                sensor.setCo2(obj.getDouble("co2"));
                sensor.setSensor(obj.getInt("idsensor"));

                productsList.add(sensor);
            }

            return productsList;
        } catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
        }
        return new ArrayList<>();
    }

}
