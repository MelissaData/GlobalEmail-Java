package com.melissadata.globalemail.model;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class GlobalEmailTransaction {
    private final String endpoint;
    private GlobalEmailOptions options;
    private String identNumber;
    private String email;
    private String format;
    
    public GlobalEmailTransaction() {
        endpoint = "https://globalemail.melissadata.net/v4/WEB/GlobalEmail/doGlobalEmail?";
        options = new GlobalEmailOptions();
        identNumber = "";
        email = "";
        format = "";
    }
    
    public String processTransaction(String request) {
        String response = "";
        try {
            URL url = new URL(request);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            response = format.equals("JSON")
                ? getPrettyJSON(responseBody)
                : getPrettyXML(responseBody);

        } catch (Exception e){
            System.out.println("Error sending request: \n" + e);
        }
        return response;
    }

    private String getPrettyJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject responseObject = gson.fromJson(json, JsonObject.class);
        return gson.toJson(responseObject);
    }

    private String getPrettyXML(String xml) {
        String prettyXML = "";
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            String indentSize = "{http://xml.apache.org/xslt}indent-amount";
            t.setOutputProperty(indentSize, "2");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            Writer writer = new StringWriter();
            t.transform(new StreamSource(new StringReader(xml)),
                        new StreamResult(writer));
            prettyXML = writer.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return prettyXML;
    }
    
    public String generateRequestString() {
        String request = "";
        request = endpoint;
        try {
            request += "&id=" + URLEncoder.encode(getIdentNumber(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception: " + e);
        }
        
        request += "&opt=" + options.generateOptionString();
        try {
            if (!getEmail().equals(""))
            request += "&email=" + URLEncoder.encode(getEmail(), "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception: " + e);
        }
        request += "&format=" + getFormat();
        
        return request;
    }
    
    public GlobalEmailOptions getOptions() {
        return options;
    }
    
    public void setOptions(GlobalEmailOptions options) {
        this.options = options;
    }
    
    public String getIdentNumber() {
        return identNumber;
    }
    
    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
}
