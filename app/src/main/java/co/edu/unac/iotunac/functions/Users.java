package co.edu.unac.iotunac.functions;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

import co.edu.unac.iotunac.auth.SingInActivity;

public class Users implements Serializable{
    int id;
    String correo;
    int edad;
    Double peso;
    Double estatura;
    int horassueño;
    int numpasos;
    double imc;
    String name;

    public Users(int id,String correo, int edad, Double peso, Double estatura, int horassueño, int numpasos, double imc) {
        this.correo = correo;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.horassueño = horassueño;
        this.numpasos = numpasos;
        this.imc = imc;
        this.imc = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
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
