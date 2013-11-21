package de.assertagile.demonstration.howtotest.mail

import com.icegreen.greenmail.user.GreenMailUser
import com.icegreen.greenmail.util.GreenMail
import com.icegreen.greenmail.util.ServerSetupTest
import spock.lang.Specification

import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailingClassGreenMailSpec extends Specification {

    GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP)
    GreenMailUser greenMailUser = greenMail.setUser(
            "someuser@somewhere.com", "someUser", "somePassword")

    MailingClass mailingClass = new MailingClass(
            "localhost", ServerSetupTest.SMTP.port,
            "localhost", ServerSetupTest.IMAP.port,
            greenMailUser.login, greenMailUser.password)

    def setup() {
        greenMail.start()
    }

    def cleanup() {
        greenMail.stop()
    }

    def "sending mails should work"() {
        given:
        String to = "receiver@testmailingclass.net"
        String from = greenMailUser.email
        String subject = "Sending test"
        String content = "This content should be sent by the user."

        when:
        mailingClass.sendMail(to, from, subject, content)

        then:
        greenMail.receivedMessages.size() == 1
        Message message = greenMail.receivedMessages[0]
        message.from == [new InternetAddress(from)]
        message.allRecipients.contains(new InternetAddress(to))
        message.subject == subject
        message.content == "${content}\r\n"
    }

    def "receiving mails should work"() {
        given:
        String from = "sender@testmailingclass.net"
        String subject = "Sending test"
        String content = "This content should be received by the user."
        deliverMessage(from, subject, content)

        when:
        Message[] messages = mailingClass.receiveMail(
                greenMailUser.login, greenMailUser.password)

        then:
        messages.size() == 1
        Message message = messages[0]
        message.from == [new InternetAddress(from)]
        message.allRecipients == [new InternetAddress(greenMailUser.email)]
        message.subject == subject
        message.content == content
    }

    private MimeMessage deliverMessage(String from, String subject, String text) {
        MimeMessage message = new MimeMessage((Session) null)
        message.setFrom(new InternetAddress(from))
        message.setRecipients(Message.RecipientType.TO, greenMailUser.email)
        message.setSubject(subject)
        message.setText(text)
        greenMailUser.deliver(message)
        return message
    }
}