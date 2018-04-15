/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.whatsappApi;

import java.io.*;

public class WhatsappClient {
    private String number;
    private String secret;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Process process;

    public WhatsappClient(String number, String secret) {
        this.number = number;
        this.secret = secret;
    }

    public void init() {
        Runtime rt = Runtime.getRuntime();
        try {
            this.process = rt.exec("yowsup-cli demos -l " + number + ":" + secret + " -y");
            this.outputStream = process.getOutputStream();
            this.inputStream = process.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkReceive();
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            writer.write("/login " + number + " " + secret + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String phoneNumber, String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            writer.write("/message send " + phoneNumber + " '" + text + "'\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkReceive() {
        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));
            String command;
            try {
                while ((command = reader.readLine()) != null) {
                    System.out.println(command);
                    ValidMessage validMessage = MessageUtils.processInput(command);
                    if (validMessage != null) {
                        System.out.println("FROM: " + validMessage.sender + " DATA: " + validMessage.data);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
