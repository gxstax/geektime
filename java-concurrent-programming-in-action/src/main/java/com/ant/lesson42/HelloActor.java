package com.ant.lesson42;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * <p>
 * Actor模型
 * </p>
 *
 * @author Ant
 * @since 2021/4/13 9:47 上午
 */
public class HelloActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Hello :" + message);
    }

    public static void main(String[] args) {
        // 创建 Actor 系统
        ActorSystem system = ActorSystem.create("HelloSystem");

        // 创建 HelloActor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));

        // 发送消息给 HelloActor
        helloActor.tell("Actor", ActorRef.noSender());
    }
}
