//package com.im.miaosha.rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * mq配置
// *
// * @author liuwei
// * @date 2020/04/21
// */
//@Configuration
//@EnableRabbit
//public class MqConfig {
//
//    public static final String QUEUE = "queue";
//    public static final String TOPICQUEUE1 = "topicqueue1";
//    public static final String TOPICQUEUE2 = "topicqueue2";
//    public static final String EXCHANGE = "exchange";
//    public static final String R_KEY_1 = "topic.key1";
//    private static final String R_KEY_2 = "topic.#";
//
//    /**
//     * 队列  交换机模式一 直连模式direct
//     *
//     * @return {@link Queue}
//     */
//    @Bean
//    public Queue queue() {
//        //true 表示持久化
//        return new Queue(QUEUE, true);
//    }
//
//    @Bean
//    public Queue topqueue1() {
//        //true 表示持久化
//        return new Queue(TOPICQUEUE1, true);
//    }
//
//    @Bean
//    public Queue topqueue2() {
//        //true 表示持久化
//        return new Queue(TOPICQUEUE2, true);
//    }
//
//    /**
//     * 声明交换机
//     *
//     * @return {@link Queue}
//     */
//    @Bean
//    public TopicExchange topExchange() {
//        return new TopicExchange(EXCHANGE);
//    }
//
//    /**
//     * topqueue1必应
//     *
//     * @return {@link Binding}
//     */
//    @Bean
//    public Binding topqueue1Bing() {
//        return BindingBuilder.bind(topqueue1()).to(topExchange()).with("topic.key1");
//    }
//
//    /**
//     * topqueue2必应
//     * # 号类似通配符 ，比如在配置路由的时候key1
//     *
//     * @return {@link Binding}
//     */
//    @Bean
//    public Binding topqueue2Bing() {
//        return BindingBuilder.bind(topqueue2()).to(topExchange()).with("topic.#");
//    }
//
//
//}