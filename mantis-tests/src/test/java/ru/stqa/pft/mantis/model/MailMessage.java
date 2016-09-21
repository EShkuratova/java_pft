package ru.stqa.pft.mantis.model;

/**
 * Created by eshkuratova on 21.09.2016.
 */
public class MailMessage {
    public String to;
    public String text;
    public MailMessage(String to,String text){
        this.to = to;
        this.text = text;
    }
}
