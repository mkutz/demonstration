package de.assertagile.demonstration.howtotest.groovy;

public class MyJavaClass {

    public String getInboxXml() {
        return "<inbox>\n" +
                "    <message>\n" +
                "        <from>me@somewhere.com</from>\n" +
                "        <recipients>\n" +
                "            <recipient type=\"to\">somebody@somewhereelse.org</recipient>\n" +
                "            <recipient type=\"cc\">somebodyelse@spmewhereelse.org</recipient>\n" +
                "        </recipients>\n" +
                "        <subject>This is a test message</subject>\n" +
                "        <content>This is a test message that is used to demonstrate the XML paring feature of Groovy.</content>\n" +
                "    </message>\n" +
                "    <message>\n" +
                "        <from>me@somewhere.com</from>\n" +
                "        <recipients>\n" +
                "            <recipient type=\"to\">whoever@someplace.co.uk</recipient>\n" +
                "        </recipients>\n" +
                "        <subject>Another test message</subject>\n" +
                "        <content>This is another test message that is used to demonstrate the XML paring feature of Groovy.</content>\n" +
                "    </message>\n" +
                "</inbox>";
    }
}
