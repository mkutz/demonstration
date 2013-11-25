package de.assertagile.demonstration.howtotest.groovy

class MyJavaClassTest extends GroovyTestCase {

    MyJavaClass myJavaClass = new MyJavaClass()

    void testGetInboxXml() {
        def inbox = new XmlSlurper().parseText(myJavaClass.getInboxXml())

        assert inbox.message.size() == 2
        assert inbox.message[0].from == "me@somewhere.com"
        assert inbox.message[0].recipients.recipient[0].@type == "to"
        assert inbox.message[0].recipients.recipient[1].@type == "cc"
        assert inbox.message.content.text().each {it.contains("test message")}
    }
}
