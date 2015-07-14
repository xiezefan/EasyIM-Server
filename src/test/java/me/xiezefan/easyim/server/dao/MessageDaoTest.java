package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xiezefan-pc on 2015/4/11 0011.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class MessageDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private MessageDao messageDao;

    @Test
    public void saveAndFindByIdTest() {
        // save
        Message msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setFromId(UUID.randomUUID().toString());
        msg.setToId(UUID.randomUUID().toString());
        msg.setType(UUID.randomUUID().toString());
        msg.setContentStr(UUID.randomUUID().toString());
        msg.setCreateTime(new Date());
        messageDao.save(msg);

        // findById
        Message msg2 = messageDao.findById(msg.getId());
        Assert.assertTrue(msg2.getId().equals(msg.getId()));
    }

}
