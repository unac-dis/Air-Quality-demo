package co.edu.unac.iotunac.functions;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.unac.iotunac.objects.AirStatus;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/*** Created by Kevin Ortiz on 02/10/2018.*/

public class TaskQualityAir extends AsyncTask<String, Integer, List<AirStatus>> {
    private Context mContext;

    public TaskQualityAir(Context context) {
        mContext = context;
    }


    @Override
    protected List<AirStatus> doInBackground(String... String) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet del = new HttpGet("http://170.238.226.93/info/status");
        del.setHeader("content-type", "application/json");

        try {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());
            JSONObject respJSON = new JSONObject(respStr);
            List<AirStatus> productsList = new ArrayList<>();


                JSONObject obj = respJSON.getJSONObject(respStr);
                AirStatus sensor = new AirStatus();
                sensor.setStatus(obj.getString("status"));
                sensor.setTemperatura(obj.getDouble("temperatura"));
                sensor.setHumedad(obj.getDouble("humedad"));
                sensor.setCo2(obj.getDouble("co2"));
                productsList.add(sensor);

            return productsList;
        } catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
        }
        return new ArrayList<>();
    }
    @Override
    protected void onPostExecute(List<AirStatus> status) {
        try {
            super.onPostExecute(status);
        }catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
