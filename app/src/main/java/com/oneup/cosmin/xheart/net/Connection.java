package com.oneup.cosmin.xheart.net;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Connection {
    private static final Connection singleton = new Connection();
    public static Connection getConnection(){ return singleton; }


    private BluetoothSocket socket;
    private InputStream is = null;
    private OutputStream os = null;
    private Thread connectionThread = null;
    private String address = null;

    public void setAddress(String address) {
        this.address = address;
    }

    private Connection(){
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UUID uuid = UUID.fromString("20858d36-9700-4246-9d97-e0a38d9f36f0");
                    BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice device = bta.getRemoteDevice(address);
                    socket = device.createRfcommSocketToServiceRecord(uuid);
                    socket.connect();
                    onConnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connectionThread.setDaemon(true);
    }

    public String read(){
        byte[] countArray = new byte[64];
        if(is==null) return "inputstream_null";
        try {
            is.read(countArray);
            int count = Integer.parseInt(
                    new String(countArray).trim()
            );
            byte[] msg = new byte[count];
            is.read(msg);
            return new String(msg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (is != null) {
                is.close();
            }
            if(os != null){
                os.close();
            }
            if(socket.isConnected()){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void onConnect(){
        try {
            if (socket.isConnected()) {
                is = socket.getInputStream();
                os = socket.getOutputStream();
            } else throw new IOException("Socket is not connected");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void connect(){
        connectionThread.start();
    }
}