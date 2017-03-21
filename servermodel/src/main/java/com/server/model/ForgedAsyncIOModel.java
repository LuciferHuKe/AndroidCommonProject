package com.server.model;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lucifer on 17/3/9.
 *
 * 伪异步I/O模型
 *
 */

public class ForgedAsyncIOModel {

    private static final int SERVER_PORT = 6088;

    public static void main(String[] args) {

        ServerSocket server = null;

        try {

            server = new ServerSocket(SERVER_PORT);

            Socket socket = null;

            // 创建I/O任务线程池
            TimeServerHandlerExecutorPool singleExecutor = new TimeServerHandlerExecutorPool(50, 10000);

            while (true) {
                socket = server.accept();

                singleExecutor.executor(new TimeServerHandler(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(server != null) {
                System.out.println("服务端程序关闭");
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
