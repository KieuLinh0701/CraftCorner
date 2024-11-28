package vn.iotstar.dao.implement;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.iotstar.entity.User;
import vn.iotstar.utils.Constant;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;

public class SendMail {
	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public Properties configEmail(Properties pr) {
//		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
//		pr.setProperty("mail.smtp.port", "587");
//		pr.setProperty("mail.smtp.auth", "true");
//		pr.setProperty("mail.smtp.starttls.enable", "true");
//		pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		pr.put("mail.smtp.socketFactory.port", "587");
		return pr;
	}

	public boolean SendEmail(User user) {
		boolean test = false;

		String toEmail = user.getEmail();
		String fromEmail = Constant.FROM_EMAIL;
		String password = Constant.PASSWORD_EMAIL;
		
		Properties pr = new Properties();
		pr.put("mail.smtp.host", "smtp.gmail.com");
		pr.put("mail.smtp.port", "587");
		pr.put("mail.smtp.auth", "true");
		pr.put("mail.smtp.starttls.enable","true");

		try {
//			Properties pr = configEmail(new Properties());

			Authenticator auth = new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(pr, auth);
			MimeMessage mess = new MimeMessage(session);
			mess.addHeader("Content-Type", "text/plain; charset=UTF-8");
			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			mess.setSubject("Confirm Code");

			mess.setText("Your code is: " + user.getCode());

			Transport.send(mess);

			test = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}
}