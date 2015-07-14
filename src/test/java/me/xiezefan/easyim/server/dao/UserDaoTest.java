package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.common.Contact;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.util.JsonUtil;
import me.xiezefan.easyim.server.util.StringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * User Dao Test
 * Created  by xiezefan-pc on 2015/3/30 0030.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    public UserDao userDao;

    @Before
    public void before() {
        User user = new User();
        user.setId(Contact.USER_ID);
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setNickname(UUID.randomUUID().toString());
        user.setDeviceId(UUID.randomUUID().toString());
        userDao.insert(user);
    }

    @Test
    public void insertAndFindByIdTest() {
        // insert
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setNickname(UUID.randomUUID().toString());
        user.setDeviceId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        userDao.insert(user);

        // find by id
        User user2 = userDao.findById(user.getId());
        Assert.assertTrue(user2.getUsername().equals(user.getUsername()));

    }

    @Test
    public void updateDeviceIdTest() {
        User user = userDao.findById(Contact.USER_ID);
        String newDeviceId = StringUtil.getRandomString(8);
        user.setDeviceId(newDeviceId);
        userDao.updateDeviceId(user);
        User user2 = userDao.findById(user.getId());
        Assert.assertTrue(user2.getDeviceId().equals(newDeviceId));
    }

    @Test
    public void findDeviceByUserIdTest() {
        String deviceId = userDao.findDeviceByUserId(Contact.USER_ID);
        Assert.assertTrue(!StringUtil.isEmpty(deviceId));
    }

    @Test
    public void listText() {
        List<User> result = userDao.list(0, 15);
        Assert.assertTrue(result.size() > 1);
    }

    @Test
    public void searchText() {
        User user = new User();
        user.setId(Contact.USER_ID2);
        user.setUsername("kdsjaskdhjaksjdh");
        user.setPassword(UUID.randomUUID().toString());
        user.setNickname("hjcvxkhjvsdfsdf");
        user.setDeviceId(UUID.randomUUID().toString());
        userDao.insert(user);

        List<User> result1 = userDao.search(Contact.USER_ID3, "%skdhja%");
        List<User> result2 = userDao.search(Contact.USER_ID3, "%hjcvxkhjvsdfsdf%");
        List<User> result3 = userDao.search(Contact.USER_ID3, "%not_exist%");

        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(result3.size() == 0);
    }

    @Test
    public void listExcludeUserIdText() {
        User user = new User();
        user.setId(Contact.USER_ID2);
        user.setUsername("kdsjaskdhjaksjdh");
        user.setPassword(UUID.randomUUID().toString());
        user.setNickname("hjcvxkhjvsdfsdf");
        user.setDeviceId(UUID.randomUUID().toString());
        userDao.insert(user);

        List<User> result1 = userDao.listExcludeUserId(Contact.USER_ID2, 0, 15);
        List<User> result2 = userDao.list(0, 15);
        Assert.assertTrue(result2.size() - result1.size() == 1);
    }

    @Test
    public void updateTest() {
        User user = userDao.findById(Contact.USER_ID);
        user.setNickname("Nickname");
        user.setLocation("Location");
        user.setSex("Sex");
        user.setDescription("Description");
        user.setAvatar("Avatar");
        userDao.update(user);

        User user2 = userDao.findById(Contact.USER_ID);
        Assert.assertEquals("Nickname", user2.getNickname());
        Assert.assertEquals("Location", user2.getLocation());
        Assert.assertEquals("Sex", user2.getSex());
        Assert.assertEquals("Description", user2.getDescription());
        Assert.assertEquals("Avatar", user2.getAvatar());
    }

}
