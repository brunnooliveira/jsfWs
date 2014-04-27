package br.com.bgdo.jsfws.saaj;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class TesteSaaj {

	public static void main(String[] args) throws Exception {
		testeSaaj();
	}

	public static void testeSaaj() throws SOAPException, MalformedURLException {
		MessageFactory mf = MessageFactory
				.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);

		SOAPMessage message = mf.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();

		// SOAPHeader header2 = message.getSOAPHeader();
		// SOAPBody body2 = message.getSOAPBody();

		// Exluir header
		// header.detachNode();

		QName bodyName = new QName("http://wombat.ztrade.com",
				"GetLastTradePrice", "m");
		SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

		QName name = new QName("symbol");
		SOAPElement symbol = bodyElement.addChildElement(name);
		symbol.addTextNode("SUNW");

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection connection = soapConnectionFactory.createConnection();

		URL endpoint = new URL("http://wombat.ztrade.com/quotes");

		SOAPMessage response = connection.call(message, endpoint);

		System.out.println(response.toString());

	}

}
