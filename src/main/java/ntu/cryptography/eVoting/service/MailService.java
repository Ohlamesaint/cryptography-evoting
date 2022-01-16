package ntu.cryptography.eVoting.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import ntu.cryptography.eVoting.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    public String sendMail(String electionName, String electionId, String token, String studentId) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        try {
            studentId = studentId.toLowerCase();
            helper.setTo(studentId + "@ntu.edu.tw");
            helper.setText("<html><p>同學您好，請點選下方連結以進行投票</p>" +
                    "<a href=https://r10942055.wixsite.com/my-site/student-voting?electionId="+electionId+"&token="+token+">投票連結</html>", true);
            helper.setSubject("台灣大學線上投票系統 -- " + electionName);

            System.out.println(studentId + "@ntu.edu.tw");
            System.out.println("<html><p>同學您好，請點選下方連結以進行投票</p>" +
                    "<a href=https://r10942055.wixsite.com/my-site/student-voting?electionId="+electionId+"&token="+token+">投票連結</html>");
            System.out.println(electionName);

        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }

//    @Value("${aws.ses.accessKey}")
//    private String accessKey;
//    @Value("${aws.ses.secretKey}")
//    private String secretKey;
//    @Value("${aws.ses.region}")
//    private String region;
//
//    public String from = "mkop9456@gmail.com";
//    public String[] to = {"selab305.2021@gmail.com"};
//    private String templateName = "MyTemplate";
//    private String templateData = "{\"name\":\"Shang\", \"favoriteAnimal\":\"cat\"}";
//
//    public String sendEmail(TokenDto token, String electionId){
//        try {
//            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
//                    .standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
//
//            Destination destination = new Destination();
//            List<String> toAddress = new ArrayList<>();
//            String[] Emails = to;
//
//            toAddress.addAll(Arrays.asList(Emails));
//            destination.setToAddresses(toAddress);
//            SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
//            templatedEmailRequest.withDestination(destination);
//            templatedEmailRequest.withTemplate(templateName);
//            templatedEmailRequest.withTemplateData(templateData);
//            templatedEmailRequest.withSource(from);
//            client.sendTemplatedEmail(templatedEmailRequest);
//            System.out.println("in sendEmail");
//            return "email send";
//        } catch (Exception ex) {
//            return ("The email was not sent. Error message: "
//                    + ex.getMessage());
//        }
//    }



}
