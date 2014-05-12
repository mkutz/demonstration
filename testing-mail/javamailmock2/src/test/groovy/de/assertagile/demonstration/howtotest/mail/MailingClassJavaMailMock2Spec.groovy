package de.assertagile.demonstration.howtotest.mail

import de.saly.javamail.mock2.MockMailbox
import spock.lang.Specification

import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MailingClassJavaMailMock2Spec extends Specification {

    MailingClass mailingClass = new MailingClass("smtpHost", 4711, "imapHost", 4712, "user", "password")

    def setup() {
        MockMailbox.resetAll()
    }

    def "sending mails should work"() {
        given:
        String to = "receiver@testmailingclass.net"
        String from = "test@mailingclass.net"
        String subject = "Test"
        String content = "This content should be received after the call of sendMail."

        when:
        mailingClass.sendMail(to, from, subject, content)

        then:
        MockMailbox.get(to).inbox.getByMsgNum(1).subject == subject

        MockMailbox.get(to).inbox.messageCount == 1
        Message message = MockMailbox.get(to).inbox.messages[0]
        message.from == [new InternetAddress(from)]
        message.allRecipients.contains(new InternetAddress(to))
        message.subject == subject
        message.content == "${content}"
    }

    def "receiving mails should work"() {
        given:
        String to = "receiver@testmailingclass.com"
        String from = "sender@testmailingclass.net"
        String subject = "Sending test"
        String content = "This content should be received by the user."
        deliverMessage(to, from, subject, content)

        when:
        Message[] messages = mailingClass.receiveMail(to, "")

        then:
        messages.size() == 1
        Message message = messages[0]
        message.from == [new InternetAddress(from)]
        message.allRecipients == [new InternetAddress(to)]
        message.subject == subject
        message.content == content
    }

    private MimeMessage deliverMessage(String to, String from, String subject, String text) {
        MimeMessage message = new MimeMessage((Session) null)
        message.setFrom(new InternetAddress(from))
        message.setRecipients(Message.RecipientType.TO, to)
        message.setSubject(subject)
        message.setText(text)
        MockMailbox.get(to).inbox.add(message)
        return message
    }
}
