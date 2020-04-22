//package com.im.miaosha.rabbitmq;
//
//import com.im.miaosha.redis.RedisServer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class MqSender {
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    /**
//     * 直连模式
//     *
//     * @param obj obj
//     */
//    public void send(Object obj) {
//        String message = RedisServer.objectToString(obj);
//        log.info("发送信息："+message);
//        amqpTemplate.convertAndSend(MqConfig.QUEUE,message);
//    }
//
//    /**
//     * 发送topic 模式
//     *
//     * @param obj obj
//     */
//    public void sendTopic(Object obj) {
//        String message = RedisServer.objectToString(obj);
//        log.info("发送信息topic ;发送给交换机 然后又key进行匹配："+message);
//    }
//
//
//}
