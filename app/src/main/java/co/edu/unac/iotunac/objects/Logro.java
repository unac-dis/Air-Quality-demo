package co.edu.unac.iotunac.objects;

import java.util.Date;

public class Logro {
    Date fecha;
    int pasoslogrados;
    int horaslogradas;

    public Logro() {
    }

    public Logro(Date fecha, int pasoslogrados, int horaslogradas) {
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

    public int getHoraslogradas() {
        return horaslogradas;
    }

    public void setHoraslogradas(int horaslogradas) {
        this.horaslogradas = horaslogradas;
    }
}
