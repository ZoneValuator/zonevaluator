package fr.esgi.zonevaluator.automate;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class GeneratePdfReceiver {

    @JmsListener(destination = "generatePdf", containerFactory = "myFactory")
    public void receiveMessage(String msg) {
        System.out.println("Received <" + msg + ">");
    }
}
