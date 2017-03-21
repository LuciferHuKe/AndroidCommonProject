package com.server.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lucifer on 17/2/24.
 *
 * JAVA 原生NIO服务类
 */

public class NIOServer {

    private static final int SERVER_PORT = 6088;

    private ServerSocketChannel mServer;  // 服务端通道服务类

    private Selector mSelector;  // 选择器

    // 接受数据缓冲区
    private ByteBuffer recBuffer = ByteBuffer.allocate(1024);
    // 发送数据缓冲区
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private Map<SelectionKey, String> sessionMaps = new HashMap<SelectionKey, String>();

    public NIOServer() throws Exception {

        // 初始化通道
        this.mServer = ServerSocketChannel.open();
        // 绑定监听端口
        this.mServer.socket().bind(new InetSocketAddress(SERVER_PORT));
        // 设置通道服务为非阻塞(默认为阻塞模式)
        this.mServer.configureBlocking(false);
        // 初始化选择器
        this.mSelector = Selector.open();
        // 将通道服务注册到选择器中
        this.mServer.register(this.mSelector, SelectionKey.OP_ACCEPT);

        System.out.print("NIO服务已经启动，监听端口为:" + SERVER_PORT);

    }

    // ******************************* 业务方法 ********************************

    /**
     * 监听请求
     * @throws Exception
     */
    public void listener() throws Exception {

        while (true) {

            // 获取选择器中的key
            int eventCount = mSelector.select();
            if(eventCount == 0) {
                // 当前没有事件
                continue;
            }

            // 线程轮询selector获取到事件标签
            Set<SelectionKey> keys = mSelector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                // 通过事件类型标签来处理业务
                handleProcess(iterator.next());
                // 处理完成后移除iterator中已完成的key
                iterator.remove();
            }

        }

    }

    /**
     * 业务处理方法
     * @param key
     */
    private void handleProcess(SelectionKey key) {

        SocketChannel client = null;

        try {

            if(key.isValid() && key.isAcceptable()) {
                // 通过判断key获取连接进来的客户端
                client = mServer.accept();
                // 设置socketchannel为非阻塞
                client.configureBlocking(false);
                client.register(mSelector, SelectionKey.OP_READ);
            } else if(key.isValid() && key.isReadable()) {
                // 读取客户端的信息
                recBuffer.clear();
                client = (SocketChannel) key.channel();

                // 从缓冲区中读取信息
                int length = client.read(recBuffer);
                if (length > 0) {
                    String msg = new String(recBuffer.array(), 0, length);

                    // 根据key和msg维护缓存信息
                    sessionMaps.put(key, msg);

                    System.out.println("收到来自客户ID" + Thread.currentThread().getId() + "的消息:" + msg);

                    client.register(mSelector, SelectionKey.OP_WRITE);
                }
            } else if(key.isValid() && key.isWritable()) {
                // 响应客户端的请求，返回数据
                if(!sessionMaps.containsKey(key)) {
                    return;
                }

                client = (SocketChannel) key.channel();

                sendBuffer.clear();
                sendBuffer.put(new String(sessionMaps.get(key) + ",您好，您的请求已处理完成").getBytes());
                // 切换缓冲区操作位
                sendBuffer.flip();

                client.write(sendBuffer);
                client.register(mSelector, SelectionKey.OP_READ);

            }

        } catch (Exception e) {
            try {
                key.cancel();
                client.socket().close();
                client.close();
                return;
            } catch (Exception e2) {

            }

        }

    }

    // ******************************* 业务方法 ********************************

    public static void main(String[] args) {

        System.out.println("/****************服务器程序启动*******************/");

        try {
            NIOServer serverStart = new NIOServer();
            // 启动服务器的监听
            serverStart.listener();
        } catch (Exception e) {
            System.out.println("服务器启动异常，异常信息:" + e.getMessage());
        }

    }


}
