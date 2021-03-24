package com.ant.lesson28;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 合理库存示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/24 1:01 下午
 */
public class SafeWM {

    class WMRange{
        final int upper;
        final int lower;

        public WMRange(int upper,int lower){
            this.upper = upper;
            this.lower = lower;
        }
    }

    final AtomicReference<WMRange>
            rf = new AtomicReference<>(
            new WMRange(0,0)
    );
    // 设置库存上限
    void setUpper(int v){
        while(true){
            WMRange or = rf.get();
            // 检查参数合法性
            if(v < or.lower){
                throw new IllegalArgumentException();
            }
            WMRange nr = new
                    WMRange(v, or.lower);
            if(rf.compareAndSet(or, nr)){
                return;
            }
        }
    }

}
