package com.server.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by lucifer on 17/2/23.
 *
 * JAVA 原生NIO客户端类
 */

public class NIOClient {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6088;

    private SocketChannel mClient;

    private InetSocketAddress mServerAdd = new InetSocketAddress(SERVER_IP, SERVER_PORT);

    private Selector mSelector;

    private ByteBuffer recBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public NIOClient() throws Exception {

        mClient = SocketChannel.open();
        mClient.configureBlocking(false);
        mClient.connect(mServerAdd);

        mSelector = Selector.open();

        mClient.register(mSelector, SelectionKey.OP_CONNECT);

    }

    public void session() throws Exception {

        if(mClient.isConnectionPending()) {
            mClient.finishConnect();

            mClient.register(mSelector, SelectionKey.OP_WRITE);

            System.out.println("已经连接到服务器，可以在控制台登记");
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 键盘输入内容
            String name = scanner.nextLine();
            if("".equals(name)) {
                continue;
            }
            if("finish".equals(name)) {
                System.exit(0);
            }

            process(name);
        }

    }

    private void process(String name) throws Exception {

        boolean waitHelp = true;

        Iterator<SelectionKey> iterator = null;
        Set<SelectionKey> keys = null;

        while (waitHelp) {

            try {

                int readys = mSelector.select();

                // 没有客人，继续轮询
                if(readys == 0) {
                    continue;
                }

                keys = mSelector.selectedKeys();

                iterator = keys.iterator();

                // 一个一个key迭代检查
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();

                    if(key.isValid() && key.isWritable()) {

                        // 判断是否可写，可写即代表客户端要向服务端发送数据
                        sendBuffer.clear();
                        sendBuffer.put(name.getBytes());
                        sendBuffer.flip();

                        mClient.write(sendBuffer);

                        mClient.register(mSelector, SelectionKey.OP_READ);
                    } else if(key.isValid() && key.isReadable()) {

                        // 服务端给客户端发送消息，去读
                        recBuffer.clear();

                        int len = mClient.read(recBuffer);
                        if (len > 0) {
                            recBuffer.flip();
                            System.out.print("服务端反馈消息:" + new String(recBuffer.array(), 0, len));

                            mClient.register(mSelector, SelectionKey.OP_WRITE);
                            waitHelp = false;
                        }

                    }

                    // 检查完，删除当前客户
                    iterator.remove();

                }

            } catch (Exception e) {

                ((SelectionKey) keys).cancel();
                mClient.socket().close();
                mClient.close();
                return;

            }
        }


    }

    public static void main(String[] args) {

        System.out.println("客户端启动");

        try {
            new NIOClient().session();
        } catch (Exception e) {

        }
    }

}
