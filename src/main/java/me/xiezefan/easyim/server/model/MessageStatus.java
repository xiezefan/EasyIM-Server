package me.xiezefan.easyim.server.model;

/**
 * Message Receiver Status
 * Created by xiezefan-pc on 2015/4/9 0009.
 */
public enum  MessageStatus {
    SEND, RECEIVER, READ;

    public static MessageStatus format(String name) {
        if ("SEND".equals(name)) {
            return SEND;
        } else if ("RECEIVER".equals(name)) {
            return RECEIVER;
        } else if ("READ".equals(name)) {
            return READ;
        } else {
            return null;
        }
    }
}
