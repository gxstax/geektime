package com.ant.lesson40;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * <p>
 * disruptor示例
 * </p>
 *
 * @author Ant
 * @since 2021/4/9 10:00 上午
 */
public class DisruptorDemo {

    public static void main(String[] args) throws InterruptedException {
        // 指定 RingBuffer 大小（必须是2的次方）
        int bufferSize = 1024;

        // 构建 Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // 注册事件处理器
        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> {
            System.out.println("E" + event);
        }));

        // 启动 Disruptor
        disruptor.start();

        // 获取 RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 生产 Event
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            //生产者生产消息
            ringBuffer.publishEvent((event, sequence, buffer) ->
                    event.setValue(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }

}
