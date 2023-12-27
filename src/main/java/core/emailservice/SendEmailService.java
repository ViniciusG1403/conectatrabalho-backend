package core.emailservice;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import modules.users.structure.dtos.user.UserDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Properties;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
public abstract class SendEmailService {
    @ConfigProperty(name = "mail.credentials.password")
    private String passwordMail;

    public void sendMail(UserDTO dto, String assuntoEmail, MessageOperation tpMensagem) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vinigpo@gmail.com", passwordMail);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vinigpo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dto.getEmail()));
            message.setSubject(assuntoEmail);

            String htmlMessage = selectMessage(tpMensagem, dto);

            message.setContent(htmlMessage, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(
                "Ocorreu um erro ao enviar o email: " + e.getMessage());
        }
    }

    private String selectMessage(MessageOperation tpMensagem, UserDTO dto) {
        if (tpMensagem.equals(MessageOperation.ATIVACAO)) {
            return activationMessage(dto);
        }
        if (tpMensagem.equals(MessageOperation.TROCA_SENHA)) {
            return changePassword(dto);

        } else {
            return updateUser(dto);
        }
    }

    private String activationMessage(UserDTO dto) {
        return
            "<html><body style='display: flex; justify-content: center; align-items: center; height: 100vh;'>"
                + "<div style='text-align: center;'>"
                + "<div style='font-size: 24px; font-weight: bold; color: #4A90E2;'>Bem-vindo ao Conecta Trabalho</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Olá, " + dto.getName()
                + "! Pronto para explorar novas oportunidades de trabalho? Seu código de ativação é: "
                + dto.getCode()
                + "</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Estamos ansiosos para ajudá-lo a se conectar com empregadores ao redor do mundo!"
                + "</div>"
                + "</div>"
                + "</body></html>";
    }

    private String changePassword(UserDTO dto) {
        return
            "<html><body style='display: flex; justify-content: center; align-items: center; height: 100vh;'>"
                + "<div style='text-align: center;'>"
                + "<div style='font-size: 24px; font-weight: bold; color: #4A90E2;'>Alteração de Senha</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Recebemos um pedido para redefinir sua senha. Seu código de redefinição é: <strong>"
                + dto.getCode() + "</strong>"
                + "</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Se você não solicitou esta alteração, por favor, ignore este e-mail e entre em contato imediatamente com nosso suporte."
                + "</div>"
                + "</div>"
                + "</body></html>";
    }

    private String updateUser(UserDTO dto) {
        return
            "<html><body style='display: flex; justify-content: center; align-items: center; height: 100vh;'>"
                + "<div style='text-align: center;'>"
                + "<div style='font-size: 24px; font-weight: bold; color: #E53935;'>Desativação de Conta</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Olá, " + dto.getName()
                + ". Recebemos uma solicitação para atualizar alguns dados de sua conta."
                + "</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Seu código de atualização é: <strong>" + dto.getCode()
                + "</strong>. Por favor, use este código para confirmar a desativação."
                + "</div>"
                + "<div style='margin-top: 20px; font-size: 16px;'>"
                + "Se você não solicitou esta ação ou mudou de ideia, entre em contato conosco imediatamente."
                + "</div>"
                + "</div>"
                + "</body></html>";
    }

}
