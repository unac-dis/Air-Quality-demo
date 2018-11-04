package co.edu.unac.iotunac.objects;

import java.util.Date;

/*** Created by Kevin Ortiz on 10/09/2018.*/

public class AirStatus {
    private String status;
    private Double humedad;
    private Double temperatura;
    private Double co2;

    public AirStatus() {

    }

    public AirStatus(String status, Double humedad, Double temperatura, Double co2) {
        super();
        this.status = status;
        this.humedad = humedad;
        this.temperatura = temperatura;
        this.co2 = co2;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Double getHumedad() {
        return humedad;
    }
    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }
    public Double getCo2() {
        return co2;
    }
    public void setCo2(Double co2) {
        this.co2 = co2;
    }
    public Double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

}