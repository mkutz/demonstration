package de.assertagile.demonstration.howtotest.mail

import org.jvnet.mock_javamail.Mailbox
import spock.lang.Specification

import javax.mail.Message
import javax.mail.internet.InternetAddress

class MailingClassMockJavaMailSpec extends Specification {

    String smtpHost = "smtp.somwehere.com"
    int smtpPort = 4712
    String imapHost = "imap.somwhere.com"
    int imapPort = 4711
    String user = "someUser"
    String password = "somePassword"

    MailingClass mailingClass = new MailingClass(smtpHost, smtpPort, imapHost, imapPort, user, password)

    def setup() {
        Mailbox.clearAll()
    }

    def "sending mails should work"() {
        given:
        String to = "somebody@somewhere.com"
        String from = "test@mailingclass.net"
        String subject = "Test"
        String content = "This content should be received after the call of sendMail."

        when:
        mailingClass.sendMail(to, from, subject, content)

        then:
        Mailbox.get(to).newMessageCount == 1
        Message message = Mailbox.get(to)[0]
        message.from == [new InternetAddress(from)]
        message.allRecipients == [new InternetAddress(to)]
        message.subject == subject
        message.content == content
    }
}
