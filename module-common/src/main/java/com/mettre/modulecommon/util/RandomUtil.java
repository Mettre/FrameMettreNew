package com.mettre.modulecommon.util;

import org.apache.commons.lang.RandomStringUtils;

public final class RandomUtil {
    private static final int DEF_COUNT = 20;
    private static final String PREFIX_ORDER = "O";
    private static final String PREFIX_AFTER_SALE_ORDER = "AO";
    private static final String PREFIX_ORDER_ITEM = "I";
    private static final String PREFIX_BILL = "B";
    private static final String PREFIX_REFUND = "R";
    private static final String PREFIX_FILE = "F";
    private static final String PREFIX_PROPERTY_BILL = "PB";

    private RandomUtil() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(20);
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(20);
    }

    public static String generateIdentifyingCode() {
        return RandomStringUtils.randomNumeric(5);
    }

    public static String generateOrderNumber() {
        return "O" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    public static String generateAfterSaleOrderNumber() {
        return "AO" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    public static String generateOrderItemNumber() {
        return "I" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    public static String generateBillNumber() {
        return "B" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 阅读id
     * @return
     */
    public static String ReadInformationNumber() {
        return "R" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 闹钟id
     * @return
     */
    public static String AlarmClockNumber() {
        return "C" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 评论id
     * @return
     */
    public static String ReplyInformationNumber() {
        return "RP" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(4);
    }
    public static String generateNickName() {
        return "u" + System.currentTimeMillis() + RandomStringUtils.randomNumeric(5);
    }


    public static String generateUserId() {
        return DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(5);
    }

    public static String generateFileKey() {
        return "F" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(10);
    }

    public static String generatePropertyBillBatchNumber() {
        return "PB" + DateUtil.getCurrentTimeStr() + RandomStringUtils.randomNumeric(2);
    }

    public static String generateSimplePassword() {
        return RandomStringUtils.randomNumeric(6);
    }

    public static String generateAppId() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public static String generateSecret() {
        return RandomStringUtils.randomAlphanumeric(15);
    }
}