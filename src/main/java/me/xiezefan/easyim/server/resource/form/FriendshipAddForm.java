package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

/**
 * Friendship Add From
 * Created by xiezefan-pc on 2015/4/2 0002.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendshipAddForm {
    public String friend_id;

    public boolean validate() {
        return !StringUtil.isEmpty(friend_id);
    }
}
