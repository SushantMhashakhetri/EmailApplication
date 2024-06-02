package com.email.sushant.Services;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    public void send(String to,String subject,String msg);
    public void send(String[] to,String subject,String msg);
    public void sendWithHtml(String to,String subject,String html);
    public void sendWithFile(String to, String subject, String msg, File file);
    public void sendWithFileWithStream(String to, String subject, String msg, InputStream is);

}
