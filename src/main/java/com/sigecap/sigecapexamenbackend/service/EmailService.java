package com.sigecap.sigecapexamenbackend.service;

public interface EmailService {

    public void sendMail(String to, String subject, String text);

    public void sendMailHtml(String to, String subject, String text);

    public void sendMailNuevoParticipanteConAdjuntos(String to, String subject, String text);

}