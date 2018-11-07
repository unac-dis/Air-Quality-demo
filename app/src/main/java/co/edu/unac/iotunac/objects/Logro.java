package co.edu.unac.iotunac.objects;

import java.util.Date;

public class Logro {
    Date fecha;
    int pasoslogrados;
    Double horaslogradas;

    public Logro() {
    }

    public Logro(Date fecha, int pasoslogrados, Double horaslogradas) {
        this.fecha = fecha;
        this.pasoslogrados = pasoslogrados;
        this.horaslogradas = horaslogradas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPasoslogrados() {
        return pasoslogrados;
    }

    public void setPasoslogrados(int pasoslogrados) {
        this.pasoslogrados = pasoslogrados;
    }

    public Double getHoraslogradas() {
        return horaslogradas;
    }

    public void setHoraslogradas(Double horaslogradas) {
        this.horaslogradas = horaslogradas;
    }
}
