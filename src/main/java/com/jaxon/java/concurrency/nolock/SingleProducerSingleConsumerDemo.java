package com.jaxon.java.concurrency.nolock;

import com.jaxon.java.concurrency.lock.QueueConstants;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import java.util.concurrent.Executors;

/**
 * @author koala
 */
public class SingleProducerSingleConsumerDemo {

    public static void main(String[] args) {
        // 创建Disruptor
        Disruptor<Event> disruptor = new Disruptor<>(new EventFactory(), 1024, Executors.defaultThreadFactory());

        // 连接事件处理器
        disruptor.handleEventsWith(new SimpleEventHandler());

        // 启动Disruptor
        RingBuffer<Event> ringBuffer = disruptor.start();

        // 生产事件
        EventProducer eventProducer = new EventProducer(ringBuffer);

        long start = System.currentTimeMillis();

//        new Thread(() -> {
            for (int i = 0; i < QueueConstants.ONE_BILLION_NUM; i++) {
                eventProducer.produce("task [" + i +"]");
            }
            System.out.println("put耗时：" + ((System.currentTimeMillis() - start)) + "ms");
//        }).start();


        // 关闭Disruptor
        disruptor.shutdown();
    }

    // 创建事件类
    static class Event {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // 创建事件处理器
    static class SimpleEventHandler implements EventHandler<Event> {

        private int i;

        private long startTime = System.currentTimeMillis();

        @Override
        public void onEvent(Event event, long sequence, boolean endOfBatch) {
//            System.out.println("Consumed: " + event.getMessage());
            i++;
            if (i == QueueConstants.ONE_BILLION_NUM) {
                System.out.println("get耗时：" + ((System.currentTimeMillis() - startTime)) + "ms");
            }

        }
    }

    // 创建RingBuffer的工厂
    static class EventFactory implements com.lmax.disruptor.EventFactory<Event> {
        @Override
        public Event newInstance() {
            return new Event();
        }
    }

    // 事件生产者
    static class EventProducer {
        private final RingBuffer<Event> ringBuffer;

        public EventProducer(RingBuffer<Event> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void produce(String message) {
            long sequence = ringBuffer.next();
            try {
                Event event = ringBuffer.get(sequence);
                event.setMessage(message);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }
}
