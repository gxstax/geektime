package com.ant.lesson15;


import java.util.ArrayList;

/**
 * <p>
 * Java 编译
 * </P>
 *
 * @author Ant
 * @since 2023/01/10 21:06
 **/
public class JavaCompile {

    public int foo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int result = list.get(0);
        return result;
    }

}
/** 字节码
 * Classfile /Users/ant/work/projects/java/geektime/jvm/src/main/java/com/ant/lesson15/JavaCompile.class
 *   Last modified 2023年1月10日; size 505 bytes
 *   SHA-256 checksum aac64525213f01e8c95367dd707c54d9c3b6f758be9958f099f062d4cc0a16b4
 *   Compiled from "JavaCompile.java"
 * public class com.ant.lesson15.JavaCompile
 *   minor version: 0
 *   major version: 57
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #28                         // com/ant/lesson15/JavaCompile
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
 *    #2 = Class              #4             // java/lang/Object
 *    #3 = NameAndType        #5:#6          // "<init>":()V
 *    #4 = Utf8               java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Class              #8             // java/util/ArrayList
 *    #8 = Utf8               java/util/ArrayList
 *    #9 = Methodref          #7.#3          // java/util/ArrayList."<init>":()V
 *   #10 = Methodref          #11.#12        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *   #11 = Class              #13            // java/lang/Integer
 *   #12 = NameAndType        #14:#15        // valueOf:(I)Ljava/lang/Integer;
 *   #13 = Utf8               java/lang/Integer
 *   #14 = Utf8               valueOf
 *   #15 = Utf8               (I)Ljava/lang/Integer;
 *   #16 = Methodref          #7.#17         // java/util/ArrayList.add:(Ljava/lang/Object;)Z
 *   #17 = NameAndType        #18:#19        // add:(Ljava/lang/Object;)Z
 *   #18 = Utf8               add
 *   #19 = Utf8               (Ljava/lang/Object;)Z
 *   #20 = Methodref          #7.#21         // java/util/ArrayList.get:(I)Ljava/lang/Object;
 *   #21 = NameAndType        #22:#23        // get:(I)Ljava/lang/Object;
 *   #22 = Utf8               get
 *   #23 = Utf8               (I)Ljava/lang/Object;
 *   #24 = Methodref          #11.#25        // java/lang/Integer.intValue:()I
 *   #25 = NameAndType        #26:#27        // intValue:()I
 *   #26 = Utf8               intValue
 *   #27 = Utf8               ()I
 *   #28 = Class              #29            // com/ant/lesson15/JavaCompile
 *   #29 = Utf8               com/ant/lesson15/JavaCompile
 *   #30 = Utf8               Code
 *   #31 = Utf8               LineNumberTable
 *   #32 = Utf8               foo
 *   #33 = Utf8               SourceFile
 *   #34 = Utf8               JavaCompile.java
 * {
 *   public com.ant.lesson15.JavaCompile();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 14: 0
 *
 *   public int foo();
 *     descriptor: ()I
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: new           #7                  // class java/util/ArrayList
 *          3: dup
 *          4: invokespecial #9                  // Method java/util/ArrayList."<init>":()V
 *          7: astore_1
 *          8: aload_1
 *          9: iconst_0
 *         10: invokestatic  #10                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         13: invokevirtual #16                 // Method java/util/ArrayList.add:(Ljava/lang/Object;)Z
 *         16: pop
 *         17: aload_1
 *         18: iconst_0
 *         19: invokevirtual #20                 // Method java/util/ArrayList.get:(I)Ljava/lang/Object;
 *         22: checkcast     #11                 // class java/lang/Integer
 *         25: invokevirtual #24                 // Method java/lang/Integer.intValue:()I
 *         28: istore_2
 *         29: iload_2
 *         30: ireturn
 *       LineNumberTable:
 *         line 17: 0
 *         line 18: 8
 *         line 19: 17
 *         line 20: 29
 * }
 * SourceFile: "JavaCompile.java"
 */