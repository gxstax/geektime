package com.ant.lesson31;

/**
 * <p>
 * Guarded Suspension (监视挂起)模式示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/26 1:28 下午
 */
public class Message {
    String id;
    String content;

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}



