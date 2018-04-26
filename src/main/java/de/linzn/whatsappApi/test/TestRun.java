/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.whatsappApi.test;


import de.linzn.whatsappApi.WhatsappClient;

public class TestRun {

    public static void main(String[] args) {
        WhatsappClient whatsappClient = new WhatsappClient(args[0], args[1]);
        whatsappClient.init();
    }

}
