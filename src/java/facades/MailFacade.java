package facades;

import entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailFacade {

    public static void send(String de, String para, List<Usuario> paraVarios, String asunto, String mensaje) throws UnsupportedEncodingException {

        final String user = "digitalsoftmarket@gmail.com";
        final String pass = "DigitalSoft12345";

        if (de.equals("")) {
            de = "MARKETING TEN BOSS";
        }

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); //El servidor SMTP de Google
        props.setProperty("mail.smtp.starttls.enable", "true");//Para conectar de manera segura al servidor SMTP, mensaje cifrado
        props.setProperty("mail.smtp.port", "587");//El puerto SMTP seguro de Google
        props.setProperty("mail.smtp.starttls.required", "true");//Para conectar de manera segura al servidor SMTP, mensaje cifrado
        props.setProperty("mail.smtp.auth", "true"); //Usar autenticación mediante usuario y clave
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");//Seguridad en la capa de transporte

        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            //Para adjuntos
            /* BodyPart adjunto = new MimeBodyPart();
adjunto.setDataHandler(new DataHandler(new FileDataSource("f:/cartagena.txt")));
adjunto.setFileName("cartagena.txt");*/
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
//multiparte.addBodyPart(adjunto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, de));

            if (paraVarios == null) {
                InternetAddress destinatario = new InternetAddress(para);
                message.setRecipient(Message.RecipientType.TO, destinatario);
            } else {
                InternetAddress[] destinatarios = new InternetAddress[paraVarios.size()];
                int i = 0;
                for (Usuario usuario : paraVarios) {
                    destinatarios[i] = new InternetAddress(usuario.getCorreoElectronico());
                    i++;
                }
                message.setRecipients(Message.RecipientType.TO, destinatarios);
            }
            
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}