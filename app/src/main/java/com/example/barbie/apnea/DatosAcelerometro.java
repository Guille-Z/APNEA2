package com.example.barbie.apnea;

import java.text.DecimalFormat;

public class DatosAcelerometro {
    private float x;
    private float y;
    private float z;
    DecimalFormat dosdecimales = new DecimalFormat("###.###");

    public DatosAcelerometro() {
        x = y = z = 0;
    }

    synchronized public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    synchronized public float getX() {
        return x;
    }

    synchronized public float getY() {
        return y;
    }

    synchronized public float getZ() {
        return z;
    }


    synchronized public String toString() {
        return dosdecimales.format(x) + ", " + dosdecimales.format(y) + ", " + dosdecimales.format(z) + " [" + Math.sqrt(x*x+y*y+z*z) + "]";
    }
}
