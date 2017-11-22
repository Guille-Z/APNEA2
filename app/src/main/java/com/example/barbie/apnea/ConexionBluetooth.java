package com.example.barbie.apnea;

import android.bluetooth.*;
import android.os.*;



import java.util.*;

public class ConexionBluetooth implements Runnable {
    private final BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice dispositivoConectado;

    public ConexionBluetooth() throws Exception {
        //Se inicializa el manejador del adaptador bluetooth
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new Exception("No se encontró Bluetooth");
        }
    }

    private void empezarComunicacion() {
    }

    private void pararComunicacion() {
    }


    @Override
    public void run() {

    }

    public Set<BluetoothDevice> getDispositivosVinculados() {
        if ( mBluetoothAdapter == null ) {
            return new TreeSet<BluetoothDevice>();
        }
        return  mBluetoothAdapter.getBondedDevices();
    }

    private void postToastMessage(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean desactivarBT() {
        if ( tieneBluetooth() )
            return true;
        return mBluetoothAdapter.disable();
    }


    public void empezarBusqueda() {
        if ( tieneBluetooth() )
            mBluetoothAdapter.startDiscovery();
    }

    public boolean estaActivadoElBT () {
        return mBluetoothAdapter.isEnabled();
    }

    public boolean tieneBluetooth() {
        if (mBluetoothAdapter == null)
            return false;
        return true;
    }

    public boolean vincular(BluetoothDevice device) {
        return device.createBond();
    }

    public void conectarDispositivo(BluetoothDevice device) {
        dispositivoConectado = device;
        //TODO: threads
    }

    public boolean estaConectado() {

        if ( dispositivoConectado != null && (dispositivoConectado.getBondState() == BluetoothDevice.BOND_BONDED || dispositivoConectado.getBondState() == BluetoothDevice.BOND_BONDING))
            return true;
        return false;
    }

    public String nombreDispositivo() {
        if (estaConectado()) {
            return dispositivoConectado.getName();
    }
        return "";
    }


    public String macDispositivo() {
        if (estaConectado()) {
            return dispositivoConectado.getAddress();
        }
        return "";
    }
}
