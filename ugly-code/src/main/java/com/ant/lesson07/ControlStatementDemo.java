package com.ant.lesson07;

/**
 * <p>
 * 滥用控制语句示例 1
 * </p>
 *
 * @author Ant
 * @since 2021/1/14 5:56 下午
 */
public class ControlStatementDemo {

    private static final Integer START_CHARGING_SEQUENCE = 10;
    private static final Integer FURTHER_CHARGING_SEQUENCE = 10;

    private Integer sequenceNumber;

    public double getEpubPrice(final boolean highQuality, final int chapterSequence) {
        double price = 0;
        if (highQuality && chapterSequence > START_CHARGING_SEQUENCE) {
            price = 4.99;
        } else if (sequenceNumber > START_CHARGING_SEQUENCE
                && sequenceNumber <= FURTHER_CHARGING_SEQUENCE) {
            price = 1.99;
        } else if (sequenceNumber > FURTHER_CHARGING_SEQUENCE) {
            price = 2.99;
        } else {
            price = 0.99;
        }

        return price;
    }

    /**
     * <p>
     * 卫语句的解决方案
     * </p>
     * 
      * @param highQuality
     * @param chapterSequence
     * @return double
     */
    public double getEpubPrice2(final boolean highQuality, final int chapterSequence) {
        if (highQuality && chapterSequence > START_CHARGING_SEQUENCE) {
            return 4.99;
        }

        if (sequenceNumber > START_CHARGING_SEQUENCE
                && sequenceNumber <= FURTHER_CHARGING_SEQUENCE) {
            return 1.99;
        }

        if (sequenceNumber > FURTHER_CHARGING_SEQUENCE) {
            return 2.99;
        }

        return 0.99;
    }


}
