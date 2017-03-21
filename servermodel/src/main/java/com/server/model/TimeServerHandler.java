package com.server.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by lucifer on 17/3/9.
 */

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;

            while (true) {
                body = in.readLine();
                if(body == null) {
                    break;
                }

                System.out.println("TimeServer线程接收到:" + body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

                System.out.println(currentTime);
            }


        } catch (Exception e) {

            if(in != null) {
                try {
                    in.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            if(out != null) {
                out.close();
                out = null;
            }

            if(this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            this.socket = null;

        } finally {

        }

    }

}
