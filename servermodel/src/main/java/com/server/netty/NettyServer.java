package com.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by lucifer on 17/2/24.
 *
 * Netty框架服务类
 */

public class NettyServer {

    private static final int SERVER_PORT = 6088;


    public NettyServer() {

    }

    // ----------------------------- Public Method -------------------------------

    /**
     * 启动服务
     */
    public void startServer() {

        // 创建服务端主线程
        EventLoopGroup boss = new NioEventLoopGroup();
        // 创建工作线程
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap server = new ServerBootstrap();

            // 创建一个handler
            server.group(boss, work).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    // 获取客户端的请求流
                    socketChannel.pipeline().addLast(new HttpResponseEncoder())
                                            .addLast(new HttpRequestDecoder())
                                            .addLast(new HttpServerInboundHandler());


                }

            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            // 开启服务线程
            ChannelFuture future = server.bind(SERVER_PORT).sync();
            // 注册反应堆，异步接收每一个客户端
            future.channel().closeFuture().sync();

        } catch (Exception e) {

            System.out.println("Netty服务启动异常");

        } finally {

        }
    }


    public static void main(String[] args) {

        System.out.println("Netty 服务初始化");

        NettyServer nettyServer = new NettyServer();
        nettyServer.startServer();

        System.out.println("Netty HTTP服务已启动");
    }


}
