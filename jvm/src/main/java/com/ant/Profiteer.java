package com.ant;


import java.util.Random;

/**
 * <p>
 * 奸商
 * </P>
 *
 * @author Ant
 * @since 2022/10/26 3:46 下午
 **/
public class Profiteer extends Merchant {

    private int contantNum = 3;

    @Override
    public double actionPrice(double price, Customer customer) {
        if (customer.isVIP()) {                             // invokeinterface
            return price * priceDiscrimination();           // invokestatic
        } else {
            return super.actionPrice(price, customer);    // invokespecial
        }
    }

    public static double priceDiscrimination() {
        // 咱们的杀熟算法太粗暴了，应该将客户城市作为随机数生成器的种子。
        return new Random()                                 // invokespecial
                .nextDouble()                               // invokevirtual
                + 0.8d;
    }
}
