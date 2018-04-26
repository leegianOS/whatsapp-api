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

    public void sendMessage(ValidMessage validMessage) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            writer.write("/message send " + validMessage.phonenumber + " '" + validMessage.data + "'\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkReceive() {
        new Thread(() -> {
            BufferedReader reader;
            while (true) {
                try {
                    reader = new BufferedReader(new InputStreamReader(this.inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        ValidMessage validMessage = MessageUtils.processInput(line);
                        if (validMessage != null) {
                            System.out.println(validMessage.phonenumber + "#:#" + validMessage.data);
                        }
                    }
                } catch (IOException ignored) {
                }
            }
        }).start();
    }
}
