package co.edu.unac.iotunac.functions;

import java.io.Serializable;

public class Users implements Serializable{
    String correo;
    int peso;
    int estatura;
    int horassueño;
    int numpasos;
    double imc;

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public Users() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public int getHorassueño() {
        return horassueño;
    }

    public void setHorassueño(int horassueño) {
        this.horassueño = horassueño;
    }

    public int getNumpasos() {
        return numpasos;
    }

    public void setNumpasos(int numpasos) {
        this.numpasos = numpasos;
    }
}
