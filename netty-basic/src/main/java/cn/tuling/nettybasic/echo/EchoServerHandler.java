package cn.tuling.nettybasic.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 作者：Mark
 * 类说明：服务端的业务Handler
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取到网络数据后进行业务处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        System.out.println("server accept :" + in.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(in);
        //ctx.close();
    }

    /**
     * channel活跃后，做业务处理
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接已建立");
        super.channelActive(ctx);
    }

    /**
     * 读取完成后的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
