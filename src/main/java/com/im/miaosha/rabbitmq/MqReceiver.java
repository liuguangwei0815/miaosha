//package com.im.miaosha.rabbitmq;
//
//import com.im.miaosha.redis.RedisServer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 消息队列接受
// *
// * @author liuwei
// * @date 2020/04/21
// */
//@Component
//@Slf4j
//public class MqReceiver {
//
//    /**
//     * 接受消息队列
//     *
//     * @param obj obj
//     */
//    @RabbitListener(queues = MqConfig.QUEUE)
//    public void accept(Object obj) {
//        log.info("接受信息obj:" + obj);
//    }
//
//}
