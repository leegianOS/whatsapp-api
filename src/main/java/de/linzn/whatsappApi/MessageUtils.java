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

public class MessageUtils {

    public static ValidMessage processInput(String rawData) {
        System.out.println(rawData);
        ValidMessage validMessage = null;
        String number = rawData.split("@")[0].replace("[", "");
        String data = null;
        if (rawData.split(" {2}").length == 2) {
            data = rawData.split(" {2}")[1];
        }
        if (data != null && !data.isEmpty() && !number.isEmpty()) {
            validMessage = new ValidMessage(number, data);
        }
        return validMessage;
    }
}
