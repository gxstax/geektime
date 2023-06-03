package com.ant.dynamic_proxy;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/3/20 8:51 上午
 */
public class CollertorController implements ICollertorController {

    @Override
    public void collector() {
        System.out.println("草泥马啊");
    }
}
