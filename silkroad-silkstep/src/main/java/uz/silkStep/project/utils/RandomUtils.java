package uz.silkStep.project.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtils {

    private static final int DEF_COUNT = 20;

    public static final int SMS_KEY_LEN = 6;

    private static final int PIN_CODE = 6;

    private static final int SMS_PASSWORD_LEN = 10;

    private static final Long RESET = -1L;
    public static final long KANBAN_ORDER_GAP = 65535L;


    private RandomUtils() {
    }

    /**
     * Generate a password.
     *
     * @return the generated password.
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key.
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Generate an activation key for sms activation.
     *
     * @return the generated activation key.
     */
    public static String generateSmsActivationKey() {
        return RandomStringUtils.randomNumeric(SMS_KEY_LEN);
    }

    /**
     * Generate a reset key.
     *
     * @return the generated reset key.
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    public static String generatePasswordSms() {
        return RandomStringUtils.randomAlphanumeric(SMS_PASSWORD_LEN);
    }

    public static String generateResetKeySms() {
        return RandomStringUtils.randomNumeric(SMS_KEY_LEN).toUpperCase();
    }

    public static String generatePinCode() {
        return RandomStringUtils.randomNumeric(PIN_CODE);
    }

    public static Long calculateOrder(Long prevOrder, Long currentOrder, Long nextOrder) {
        long result = RESET;
        prevOrder = prevOrder != null ? prevOrder : 0;

        if (currentOrder != null) {
            if (nextOrder != null) {
                long difference = nextOrder - prevOrder;
                if (difference < 2) {
                    return RESET;
                }
                result = prevOrder + (difference / 2);
            } else {
                try {
                    result = Math.addExact(prevOrder, KANBAN_ORDER_GAP + 1);
                } catch (ArithmeticException e) {
                    result = RESET;
                }
            }
        }
        return result;
    }
}

// This utility class provides methods for generating random passwords, activation keys, SMS activation keys, reset keys, and pin codes using the Apache Commons Lang library. 
//It also includes a method to calculate the order of items in a Kanban board based on their previous and next order values.