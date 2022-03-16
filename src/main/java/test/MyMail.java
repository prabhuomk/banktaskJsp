package test;

	import java.util.Date;
	import java.util.Properties;
	import javax.mail.Session;
	import javax.mail.Message;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
	import javax.mail.Authenticator;
	import javax.mail.PasswordAuthentication;



	public class MyMail {

		
	public static void startOperation(String toEmail,int id,String genPassword)
	{
		    System.out.println("TLSEmail Start");
			Properties properies = new Properties();
			properies.put("mail.smtp.host", "smtp.gmail.com");
			properies.put("mail.smtp.port", "465");
			properies.put("mail.smtp.auth", "true");
			properies.put("mail.smtp.starttls.enable", "false");
			properies.put("mail.smtp.ssl.protocols", "TLSv1.3");
			properies.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properies.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
			
			
			
	                //create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("prabhaias007@gmail.com","@Prabhu007");
				}
			};
			Session session = Session.getInstance(properies, auth);
			session.setDebug(true);
			
		sendEmail(session, toEmail,"Confidential credentials", "Hi,We welcome to our family.Your UserId= "+id+" and Password= "+genPassword+" you can change your password once you login. with regards,PK's BANK");
		}
		public static void sendEmail(Session session, String toEmail, String subject, String body){
			try
		    {
		      MimeMessage msg = new MimeMessage(session);
		      //set message headers
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");

		      msg.setFrom(new InternetAddress("no_reply@example.com", "PK's Bank"));

		      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		      msg.setSubject(subject, "UTF-8");

		      msg.setText(body, "UTF-8");

		      msg.setSentDate(new Date());

		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      System.out.println("Message is ready");
		      
	    	  Transport.send(msg);  
	    	  
	    	 

		      System.out.println("EMail Sent Successfully!!");
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
		}
	}
	 
	 
	

