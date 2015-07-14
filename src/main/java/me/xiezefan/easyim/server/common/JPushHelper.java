package me.xiezefan.easyim.server.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import java.util.Map;

/**
 * Message Push Common Helper
 * Created by xiezefan-pc on 2015/4/7 0007.
 */
public class JPushHelper {

    private static JPushClient client = new JPushClient("4cf558267989ca0f526cb16d", "b732f0d3e5d71f6fd741f495");


    public static void sendMsg(String targetAlias, String targetId, me.xiezefan.easyim.server.model.Message msg) {

        Message.Builder msgBuilder = Message.newBuilder()
                .setMsgContent(msg.getId())
                .setTitle(targetId)
                .setContentType(msg.getType());

        Map<String, Object> extras = msg.getContent();
        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            if (value instanceof String) {
                msgBuilder.addExtra(key, (String) value);
            } else if (value instanceof Number) {
                msgBuilder.addExtra(key, (Number) value);
            } else if (value instanceof Boolean) {
                msgBuilder.addExtra(key, (Boolean) value);
            }
        }


        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(targetAlias))
                .setMessage(msgBuilder.build())
                .build();

        try {
            client.sendPush(payload);
        } catch (Exception e) {
            // TODO retry
            e.printStackTrace();
        }


    }




}
