package co.edu.unac.iotunac.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class AirQualityTask extends AsyncTask<String, Integer, AirStatus> {
    private Context mContext;

    public AirQualityTask(Context context) {
        mContext = context;
    }

    @Override
    protected AirStatus doInBackground(String... voids) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet del = new HttpGet("http://170.238.226.93/info/status");
        del.setHeader("content-type", "application/json");

        try {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());
            JSONObject json = new JSONObject(respStr);
            return new AirStatus(json.getString("status"), json.getDouble("humedad"), json.getDouble("temperatura"), json.getDouble("co2"));
        } catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
        }
        return new AirStatus();
    }

    @Override
    protected void onPostExecute(AirStatus status) {
        try {
            super.onPostExecute(status);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
