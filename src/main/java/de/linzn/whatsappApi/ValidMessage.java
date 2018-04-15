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

public class ValidMessage {
    public String sender;
    public String data;

    public ValidMessage(String sender, String data) {
        this.sender = sender;
        this.data = data;
    }
}
