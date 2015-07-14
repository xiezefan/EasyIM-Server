package me.xiezefan.easyim.server.model;

/**
 * Created by xiezefan-pc on 2015/4/1 0001.
 */
public class Friendship {
    private String id;
    private String userId;
    private User friend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
