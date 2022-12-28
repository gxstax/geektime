package com.ant.lesson39;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>
 * Echo 案例 socket 连接处理器
 * </p>
 *
 * @author Ant
 * @since 2021/4/8 10:01 上午
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * <p>
     * 处理读事件
     * </p>
     *
     * @param ctx
     * @param msg
     * @return void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("读异常");
        ctx.write(msg);
    }

    /**
     * <p>
     * 处理读完成事件
     * </p>
     *
     * @param ctx
     * @return void
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("完成");
        ctx.flush();
    }

    /**
     * <p>
     * 处理异常事件
     * </p>
     *
     * @param ctx
     * @param cause
     * @return void
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常");
        cause.printStackTrace();
        ctx.close();
    }
}
