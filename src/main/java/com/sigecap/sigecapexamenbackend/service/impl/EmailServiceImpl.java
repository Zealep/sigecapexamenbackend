package com.sigecap.sigecapexamenbackend.service.impl;

import java.io.File;


import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sigecap.sigecapexamenbackend.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService{

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);


    @Autowired
    private JavaMailSender emailSender;


    private String rutaGuardaArchivo;

    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@jsconsulting.pe");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    @Override
    public void sendMailHtml(String to, String subject, String body) {

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply@jsconsulting.pe");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            emailSender.send(message);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void sendMailNuevoParticipanteConAdjuntos(String to, String subject, String body) {

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //helper.setFrom(new InternetAddress("no-reply@jsconsulting.pe"));
            helper.setFrom("no-reply@jsconsulting.pe");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            FileSystemResource fileDj = new FileSystemResource(new File(rutaGuardaArchivo + "/"  + "IND - Declaracion jurada covid - JS.pdf"));
            helper.addAttachment(fileDj.getFilename(), new ByteArrayResource(IOUtils.toByteArray(fileDj.getInputStream())), "application/pdf");

            FileSystemResource fileMapa = new FileSystemResource(new File(rutaGuardaArchivo + "/"  + "MAPA UBICACION - CEPTAR 2 - INDUCCION.jpeg"));
            helper.addAttachment(fileMapa.getFilename(), new ByteArrayResource(IOUtils.toByteArray(fileMapa.getInputStream())), "image/jpeg");

            emailSender.send(message);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }

    }

}