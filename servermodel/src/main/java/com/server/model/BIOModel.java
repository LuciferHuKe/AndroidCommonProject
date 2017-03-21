package com.server.model;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lucifer on 17/3/9.
 *
 * 传统I/O模型
 */

public class BIOModel {

    private static final int SERVER_PORT = 6088;

    public static void main(String[] args) {

        ServerSocket server = null;

        try {

            server = new ServerSocket(SERVER_PORT);

            System.out.println("服务端启动，监听端口:" + SERVER_PORT);

            Socket socket = null;

            while (true) {
                socket = server.accept();

                // 每一个客户端访问都启动一个线程
                new Thread(new TimeServerHandler(socket)).start();
            }

        } catch (Exception e) {

        } finally {

            if(server != null) {
                System.out.println("服务端关闭");

                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                server = null;
            }

        }

    }

}
