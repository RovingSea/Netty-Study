package com.wu.study.netty.advance.protocol;

import com.wu.study.netty.advance.chat.LoginRequestMessage;
import com.wu.study.netty.advance.protocol.div.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Haixin Wu
 * @date 2022/1/29 16:41
 * @since 1.0
 */
@Slf4j
public class MessageCodecTest {
    public static void main(String[] args) throws Exception {
            EmbeddedChannel channel = new EmbeddedChannel();
            // 添加解码器，避免粘包半包问题
        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                    1024,
                    12,
                    4,
                    0,
                    0));
            channel.pipeline().addLast(new MessageCodec());
            LoginRequestMessage user = new LoginRequestMessage("Masker", "123");

            // 测试编码与解码
            ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
            new MessageCodec().encode(null, user, byteBuf);

            ByteBuf byteBuf1 = byteBuf.slice(0, 100);
            ByteBuf byteBuf2 = byteBuf.slice(100,  byteBuf.readableBytes() - 100);

            byteBuf1.retain(); // 让引用计数 + 1
            channel.writeInbound(byteBuf1); // 会调用 release 使至引用计数 - 1
            channel.writeInbound(byteBuf2);
        }
}
