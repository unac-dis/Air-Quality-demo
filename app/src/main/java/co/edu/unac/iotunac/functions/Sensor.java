package co.edu.unac.iotunac.functions;

import java.util.Date;

/*** Created by Kevin Ortiz on 10/09/2018.*/

public class Sensor {
    int idsensor;
    double temperatura;
    double humedad;
    Date fecha;
    double co2;

    public Sensor() { }

    public int getSensor() {
        return idsensor;
    }

    public void setSensor(int sensor) {
        this.idsensor = sensor;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }
}