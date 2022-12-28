package com.ant.lesson39;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>
 * Netty 模拟 echo 示例
 * </p>
 *
 * @author Ant
 * @since 2021/4/8 9:56 上午
 */
public class NettyEcho {

    public void echo() throws InterruptedException {
        // 事件处理器
        final EchoServerHandler serverHandler = new EchoServerHandler();

        // boss 线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        // worker 线程组(默认 2*cpu核数个EventLoop)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            // 绑定服务端端口
            ChannelFuture cf = bootstrap.bind(8090).sync();
            cf.channel().closeFuture().sync();
        } finally {
            // 终止工作线程组
            workerGroup.shutdownGracefully();
            // 终止boss线程组
            boosGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        NettyEcho echo = new NettyEcho();
        echo.echo();

    }

}
