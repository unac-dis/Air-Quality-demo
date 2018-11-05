package co.edu.unac.iotunac.task;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URI;

import co.edu.unac.iotunac.objects.User;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/*** Created by Kevin Ortiz on 05/10/2018.*/

public class TaskRegistro extends AsyncTask<User, Void, String> {
    static int code;
    private Context Context;

    public TaskRegistro() { }

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        TaskRegistro.code = code;
    }

    public Context getContext() {
        return Context;
    }

    public void setContext(Context Context) {
        this.Context = Context;
    }

    @Override
    protected String doInBackground(User... users) {
        try {
            User user = users[0];
            HttpClient httpClient = new DefaultHttpClient();
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("170.238.226.93")
                    .setPort(80)
                    .setPath("/home/add")
                    .addParameter("peso", user.getPeso().toString())
                    .addParameter("email", user.getCorreo())
                    .addParameter("numerodehoras", String.valueOf(user.getHorassueño()))
                    .addParameter("numerodepasos", String.valueOf(user.getNumpasos()))
                    .addParameter("imc", String.valueOf(user.getImc()))
                    .addParameter("estatura", String.valueOf(user.getEstatura()))
                    .addParameter("edad", String.valueOf(user.getEdad()))
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            HttpResponse resp = httpClient.execute(httpGet);
            String respStr = EntityUtils.toString(resp.getEntity());
            EntityUtils.toString(resp.getEntity());
            code = resp.getStatusLine().getStatusCode();
            return respStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Ok";
    }
}