package com.ant;


/**
 * <p>
 * 商户
 * </P>
 *
 * @author Ant
 * @since 2022/10/26 3:43 下午
 **/
public class Merchant {

    /**
     * <p>
     * 折后价格
     * </p>
     *
     * @param price     原价
     * @param customer  客户
     * @return double
     */
    public double actionPrice(double price, Customer customer) {
        return price * 0.8d;
    }
}
