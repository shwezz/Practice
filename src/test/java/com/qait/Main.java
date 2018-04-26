package com.qait;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        String xmlFileName = "/testxml/Core_Regression.xml";

        try {
            InputStream in = Object.class.getResourceAsStream(xmlFileName);
            List<XmlSuite> suite = (List<XmlSuite>) (new Parser(in).parse());
            in.close();

            TestNG testng = new TestNG();
            testng.setXmlSuites(suite);
            testng.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
