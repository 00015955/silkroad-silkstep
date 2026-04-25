package uz.silkStep.project.service;

public interface MailSenderService {

    void sendOtp(String email, String otp);
}
/// The MailSenderService interface defines a contract for sending emails, specifically for sending one-time passwords (OTPs) to users.