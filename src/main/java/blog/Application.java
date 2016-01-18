package blog;

import java.io.BufferedWriter;
import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class Application {
	
	ApplicationContext applicationContext = null;
	
	public void sendEmail() {
		Message<String> message = MessageBuilder.withPayload("Hello World!")
				.setHeader(MailHeaders.TO, "stepangharslyan@gmail.com")
				.setHeader(MailHeaders.SUBJECT, "Greeting")
				.build();
		applicationContext = new ClassPathXmlApplicationContext("/integration/mail.xml");
		MessageChannel outboundMailChannel = (MessageChannel) applicationContext.getBean("outboundMail");
		DirectChannel inboundMailChannel = (DirectChannel) applicationContext.getBean("emails");		
		outboundMailChannel.send(message);
		
		inboundMailChannel.subscribe(new MessageHandler() {
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println("######################## Message ##################################: " + message.getPayload().toString());
			}
		});
		
	}
	
	File JClass = new File("/home/stepan/JClass.java");
	public static BufferedWriter out = null;
	
    public static void main(String[] args) throws Exception {
      
    	ConfigurableApplicationContext ctx = new SpringApplication("/integration/file.xml").run(args);
        Application application = new Application();
        application.sendEmail();
        
        System.out.println("Hit Enter to terminate");
        System.in.read();
        ctx.close();
    	
    	
    	

    	
    	
    }

}
