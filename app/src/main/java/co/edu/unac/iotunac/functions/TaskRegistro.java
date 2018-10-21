package co.edu.unac.iotunac.functions;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class TaskRegistro extends AsyncTask<Users, Void, String> {

    @Override
    protected String doInBackground(Users... users) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost del = new HttpPost("http://192.168.40.141/home/add");
        del.setHeader("content-type", "application/json");

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", users[0].getCorreo());
            jsonObject.put("peso", users[0].getPeso());
            jsonObject.put("edad",users[0].getEdad());
            jsonObject.put("estatura", users[0].getEstatura());
            jsonObject.put("numerodepasos", users[0].getNumpasos());
            jsonObject.put("imc", users[0].getImc());
            jsonObject.put("numerodehoras", users[0].getHorassueño());
            del.setEntity(new StringEntity(jsonObject.toString()));
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());

            return respStr;

        } catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
        }
        return "";
    }
}
