package com.ant.lesson15;


/**
 * <p>
 * 范型擦除
 * </P>
 *
 * @author Ant
 * @since 2023/01/10 21:30
 **/
public class GenericTest<T extends Number> {
    /**
     * T foo(T);
     * descriptor: (Ljava/lang/Number;)Ljava/lang/Number;
     *
     * 通过字节码可以看到 foo 方法的方法描述符所接收参数的类型以及返回类型都为 Number；
     * 所以对于限定了继承类的泛型参数，经过类型擦除后，所有的泛型参数都将变成所限定的继承类。
     * 也就是说，Java 编译器将选取该泛型所能指代的所有类中层次最高的那个，作为替换泛型的类。
     */
    T foo(T t) {
        return t;
    }
}

/**
 * 字节码：
 * Classfile /Users/ant/work/projects/java/geektime/jvm/src/main/java/com/ant/lesson15/GenericTest.class
 *   Last modified 2023年1月10日; size 382 bytes
 *   SHA-256 checksum c8bc68875a07b7db3d1be8f09e71b0292b4caf8d8e4835c70274bb3f05505f5c
 *   Compiled from "GenericTest.java"
 * public class com.ant.lesson15.GenericTest<T extends java.lang.Number> extends java.lang.Object
 *   minor version: 0
 *   major version: 57
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #7                          // com/ant/lesson15/GenericTest
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 2
 * Constant pool:
 *    #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
 *    #2 = Class              #4             // java/lang/Object
 *    #3 = NameAndType        #5:#6          // "<init>":()V
 *    #4 = Utf8               java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Class              #8             // com/ant/lesson15/GenericTest
 *    #8 = Utf8               com/ant/lesson15/GenericTest
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               foo
 *   #12 = Utf8               (Ljava/lang/Number;)Ljava/lang/Number;
 *   #13 = Utf8               Signature
 *   #14 = Utf8               (TT;)TT;
 *   #15 = Utf8               <T:Ljava/lang/Number;>Ljava/lang/Object;
 *   #16 = Utf8               SourceFile
 *   #17 = Utf8               GenericTest.java
 * {
 *   public com.ant.lesson15.GenericTest();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 12: 0
 *
 *   T foo(T);
 *     descriptor: (Ljava/lang/Number;)Ljava/lang/Number;
 *     flags: (0x0000)
 *     Code:
 *       stack=1, locals=2, args_size=2
 *          0: aload_1
 *          1: areturn
 *       LineNumberTable:
 *         line 14: 0
 *     Signature: #14                          // (TT;)TT;
 * }
 * Signature: #15                          // <T:Ljava/lang/Number;>Ljava/lang/Object;
 * SourceFile: "GenericTest.java"
 */