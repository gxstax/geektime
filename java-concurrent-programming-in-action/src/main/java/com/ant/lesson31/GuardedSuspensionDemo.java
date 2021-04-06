package com.ant.lesson31;

/**
 * <p>
 * "保护性地暂停"-示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/26 1:32 下午
 */
public class GuardedSuspensionDemo {
    /**
     * <p>
     * 发送消息
     * </p>
     * 
      * @param 
     * @return void
     */
    void send(Message message) {
        // .....
    }

    /**
     * <p>
     * MQ消息返回后会调用该方法
     * 该方法的执行线程不同于发送消息的线程
     * </p>
     * 
      * @param message
     * @return void
     */
    void onMessage(Message message) {
        // 唤醒等待的线程
        GuardedObject.fireEvent(message.getId(), message);
    }

    /**
     * <p>
     * 处理浏览器发来的请求
     * </p>
     * 
      * @param 
     * @return {@link Object}
     */
    Object handleWebReq() {
        // 创建一条消息
        Message msg = new Message("1", "{...}");

        // 发送消息
        send(msg);

        GuardedObject<Message> go = GuardedObject.create(msg.getId());

        // 等待 MQ 消息
        Message message = go.get(t->t != null);

        return message;
    }
    
}
