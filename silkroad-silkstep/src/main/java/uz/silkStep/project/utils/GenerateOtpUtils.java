package uz.silkStep.project.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class GenerateOtpUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }
}
