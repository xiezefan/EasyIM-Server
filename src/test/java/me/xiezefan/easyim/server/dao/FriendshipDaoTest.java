package me.xiezefan.easyim.server.dao;

import com.google.gson.JsonNull;
import me.xiezefan.easyim.server.common.Contact;
import me.xiezefan.easyim.server.model.Friendship;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
 * Friendship Dao Test
 * Created by xiezefan-pc on 2015/4/1 0001.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class FriendshipDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    public FriendshipDao friendshipDao;
    @Autowired
    public UserDao userDao;

    @Before
    public void before() {
        User user0 = new User();
        user0.setId(Contact.USER_ID);
        user0.setUsername(UUID.randomUUID().toString());
        user0.setPassword(UUID.randomUUID().toString());
        user0.setNickname(UUID.randomUUID().toString());
        user0.setDeviceId(UUID.randomUUID().toString());
        user0.setAvatar(UUID.randomUUID().toString());
        userDao.insert(user0);

        User user1 = new User();
        user1.setId(Contact.USER_ID2);
        user1.setUsername(UUID.randomUUID().toString());
        user1.setPassword(UUID.randomUUID().toString());
        user1.setNickname(UUID.randomUUID().toString());
        user1.setDeviceId(UUID.randomUUID().toString());
        user1.setAvatar(UUID.randomUUID().toString());
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(Contact.USER_ID3);
        user2.setUsername(UUID.randomUUID().toString());
        user2.setPassword(UUID.randomUUID().toString());
        user2.setNickname(UUID.randomUUID().toString());
        user2.setAvatar(UUID.randomUUID().toString());
        userDao.insert(user2);
    }

    @Test
    public void insertAndFindByIdTest() {
        User friend = userDao.findById(Contact.USER_ID2);

        Friendship friendship = new Friendship();
        friendship.setId(UUID.randomUUID().toString());
        friendship.setUserId(Contact.USER_ID);
        friendship.setFriend(friend);
        friendshipDao.insert(friendship);

        Friendship friendship2 = friendshipDao.findById(friendship.getId());
        Assert.assertTrue(friendship.getUserId().equals(friendship2.getUserId()));
    }

    @Test
    public void findByUserIdTest() {
        User friend1 = userDao.findById(Contact.USER_ID2);
        User friend2 = userDao.findById(Contact.USER_ID3);

        Friendship friendship1 = new Friendship();
        friendship1.setId(UUID.randomUUID().toString());
        friendship1.setUserId(Contact.USER_ID);
        friendship1.setFriend(friend1);
        friendshipDao.insert(friendship1);

        Friendship friendship2 = new Friendship();
        friendship2.setId(UUID.randomUUID().toString());
        friendship2.setUserId(Contact.USER_ID);
        friendship2.setFriend(friend2);
        friendshipDao.insert(friendship2);

        List<Friendship> list = friendshipDao.findByUserId(Contact.USER_ID);
        Assert.assertTrue(list.size() == 2);


    }

    @Test
    public void validateExistTest() {
        User friend1 = userDao.findById(Contact.USER_ID2);

        Friendship friendship1 = new Friendship();
        friendship1.setId(UUID.randomUUID().toString());
        friendship1.setUserId(Contact.USER_ID);
        friendship1.setFriend(friend1);
        friendshipDao.insert(friendship1);

        Long count = friendshipDao.validateExist(Contact.USER_ID, Contact.USER_ID2);
        Assert.assertTrue(count == 1);

        count = friendshipDao.validateExist(Contact.USER_ID, Contact.USER_ID3);
        Assert.assertTrue(count == 0);
    }

    @Test
    public void deleteBothTest() {
        User friend1 = userDao.findById(Contact.USER_ID2);

        Friendship friendship1 = new Friendship();
        friendship1.setId(UUID.randomUUID().toString());
        friendship1.setUserId(Contact.USER_ID);
        friendship1.setFriend(friend1);
        friendshipDao.insert(friendship1);

        friendshipDao.deleteBoth(Contact.USER_ID, Contact.USER_ID2);
        Long count = friendshipDao.validateExist(Contact.USER_ID, Contact.USER_ID2);
        Assert.assertTrue(count == 0);
    }

}
