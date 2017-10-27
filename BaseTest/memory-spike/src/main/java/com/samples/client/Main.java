package com.samples.client;

/**
 * Created by aharon on 12/13/15.
 */
//Usage: java com.gigaspaces.lrmi.nio.filters.Main host port

import java.nio.ByteOrder;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Logger;

/**
 * Created by Barak Bar Orion
 * on 12/10/15.
 *
 * @since 11.0
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];
        for(int i = 0; i < 1000; ++i) {
            try {
                Socket socket = new Socket(host, Integer.valueOf(port));
                ByteBuffer bb = ByteBuffer.allocate(4);
                bb.order(ByteOrder.BIG_ENDIAN);
                bb.putInt(1024 * 1024 * 100);

                byte[] bytes = bb.array();
                socket.getOutputStream().write(bytes);
                socket.close();
                System.out.println("Iteration : " + i);
            }catch(Exception ignored){
                ignored.printStackTrace();
            }
        }

    }
}