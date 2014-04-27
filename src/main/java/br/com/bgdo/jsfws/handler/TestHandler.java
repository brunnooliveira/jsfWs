package br.com.bgdo.jsfws.handler;

import java.io.IOException;
import java.util.Set;
import java.util.Collections;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * SOAPHandler to log the SOAP Envelope to cosole
 * 
 * @author Brunno Gonçalves
 *
 */
 public class TestHandler implements SOAPHandler<SOAPMessageContext>
{
  public Set<QName> getHeaders()
  {
    return Collections.emptySet();
  }

  public boolean handleMessage(SOAPMessageContext messageContext)
  {
     Boolean outboundProperty = (Boolean)
         messageContext.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
     
     //getting a parameter from the caller
     System.out.println(messageContext.get("TESTE_PARAM"));

     if (outboundProperty.booleanValue()) {
         System.out.println("\nOutbound message:");
     } else {
         System.out.println("\nInbound message:");
     }

     System.out.println("** Response: "+messageContext.getMessage().toString());
     try {
		messageContext.getMessage().writeTo(System.out);
	} catch (SOAPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    return true;
  }

  public boolean handleFault(SOAPMessageContext messageContext)
  {
    return true;
  }
   public void close(MessageContext messageContext)
  {
  }
}
