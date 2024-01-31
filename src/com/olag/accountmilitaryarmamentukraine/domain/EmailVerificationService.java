package com.olag.accountmilitaryarmamentukraine.domain;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;


public class EmailVerificationService {
	private static final int VERIFICATION_CODE_EXPIRATION_MINUTES = 1;
	private static LocalDateTime codeCreationTime;
	private EmailVerificationService() {
	}
	private static void sendVerificationCodeEmail(String email, String verificationCode) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
		properties.put("mail.smtp.port", "2525");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("c86a83a1142fb0", "88f332ea988901");
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("from@example.com"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			message.setSubject("Код підтвердження: ");

			message.setText("Ваш код підтвердження: " + verificationCode);

			Transport.send(message);

			System.out.println("Повідомлення успішно відправлено!");

		} catch (MessagingException e) {
			throw new RuntimeException(
			    "Помилка при відправці електронного листа з кодом: " + e.getMessage());
		}
	}
	static String generateAndSendVerificationCode(String email) {
		String verificationCode = String.valueOf((int) (Math.random() * 900000 + 100000));

		sendVerificationCodeEmail(email, verificationCode);

		codeCreationTime = LocalDateTime.now();

		return verificationCode;
	}

	// Метод для перевірки введеного коду
	public static Boolean verifyCode(String inputCode, String generatedCode) {
		LocalDateTime currentTime = LocalDateTime.now();
		long minutesElapsed = ChronoUnit.MINUTES.between(codeCreationTime, currentTime);

		if (minutesElapsed > VERIFICATION_CODE_EXPIRATION_MINUTES) {
			return false;
		}

		if (!inputCode.equals(generatedCode)) {
			return false;
		}
		codeCreationTime = null;
		return true;
	}
}
