package com.app;

import kong.unirest.Unirest;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Objects;

public class Parser {

    public String time;
    public String code;
    public String name;
    public String value;


    public Parser(String time, String code) throws ParserConfigurationException, IOException, SAXException {
        this.time = time;
        this.code = code;
    }

    public String getTime() {return time;}

    public void setValue(String value){
        this.value = value;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getBody() {
        return Unirest.get(getTime()).asString().getBody();
    }



    public String domParser() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(getBody())));
        document.getDocumentElement().normalize();
        System.out.println(time);


        NodeList nList = document.getElementsByTagName("CharCode");

        int count = -1;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);

            if (Objects.equals(node.getTextContent(), this.code)) {
                count = temp;
            }

        }
        if (count == -1) {
            return "Нет такой валюты";
        }

        NodeList nList1 = document.getElementsByTagName("Value");
        Node value = nList1.item(count);
        setValue(value.getTextContent());

        NodeList nList2 = document.getElementsByTagName("Name");
        Node name = nList2.item(count);
        setName(name.getTextContent());


        return "1 " + name.getTextContent() + " = " + value.getTextContent() + " Российских рубля";


    }
}



