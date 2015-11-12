package com.hy.oauth2.auth.handler;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.hy.oauth2.auth.httpclient.HttpResponseHandler;
import com.hy.oauth2.auth.httpclient.MkkHttpResponse;
import com.hy.oauth2.auth.json.JsonUtils;

public class DefaultResponseHandler implements HttpResponseHandler {
    private Map<String,Object> data = new HashMap<String,Object>();
    public void handleResponse(MkkHttpResponse response) {
    	String text = response.responseAsString();
        if (response.isResponse200()) {
        	data = JsonUtils.jsonToObj(text, Map.class);
        	data.put("originalText", text);
        } else {
        	parseErrorXML(response);
        }
    }
    
    public Map<String, Object> getData() {
		return data;
	}



	private void parseErrorXML(MkkHttpResponse response) {
        try {
            final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            final ErrorDefaultHandler dh = new ErrorDefaultHandler();
            saxParser.parse(response.httpResponse().getEntity().getContent(), dh);
            data.put("error", dh.error());
            data.put("desc", dh.errorDescription());
            data.put("content", response.responseAsString());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    /**
     * Parse Error XML handler
     */
    private class ErrorDefaultHandler extends DefaultHandler {
        private String qName;

        private String error;
        private String errorDescription;

        protected ErrorDefaultHandler() {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            this.qName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            final String text = new String(ch, start, length);

            if ("error_description".equalsIgnoreCase(qName)) {
                this.errorDescription = text;
            } else if ("error".equalsIgnoreCase(qName)) {
                this.error = text;
            }

        }

        public String error() {
            return error;
        }

        public String errorDescription() {
            return errorDescription;
        }
    }
}
