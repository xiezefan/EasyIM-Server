package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.common.Contact;
import me.xiezefan.easyim.server.model.Message;
import me.xiezefan.easyim.server.model.MessageStatus;
import me.xiezefan.easyim.server.model.OfflineMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xiezefan-pc on 2015/4/11 0011.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class OfflineMessageDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private OfflineMessageDao offlineMessageDao;
    @Resource
    private MessageDao messageDao;

    @Before
    public void before() {
        Message msg = new Message();
        msg.setId(Contact.MESSAGE_ID1);
        msg.setFromId(UUID.randomUUID().toString());
        msg.setToId(UUID.randomUUID().toString());
        msg.setType(UUID.randomUUID().toString());
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("key1", "value1");
        content.put("key2", "value2");
        msg.setContent(content);
        msg.setCreateTime(new Date());
        messageDao.save(msg);
    }

    @Test
    public void saveAndFindByIdTest() {
        Message msg = messageDao.findById(Contact.MESSAGE_ID1);

        // save
        OfflineMessage offlineMsg = new OfflineMessage();
        offlineMsg.setId(UUID.randomUUID().toString());
        offlineMsg.setUserId(UUID.randomUUID().toString());
        offlineMsg.setMessage(msg);
        offlineMsg.setStatus(MessageStatus.SEND);
        offlineMsg.setCreateTime(new Date());
        offlineMessageDao.save(offlineMsg);

        // findById
        OfflineMessage offlineMsg2 = offlineMessageDao.findById(offlineMsg.getId());
        Assert.assertTrue(offlineMsg2.getMessage().getId().equals(msg.getId()));

    }


    @Test
    public void updateStatusTest() {
        Message msg = messageDao.findById(Contact.MESSAGE_ID1);

        OfflineMessage offlineMsg = new OfflineMessage();
        offlineMsg.setId(UUID.randomUUID().toString());
        offlineMsg.setUserId(UUID.randomUUID().toString());
        offlineMsg.setMessage(msg);
        offlineMsg.setStatus(MessageStatus.SEND);
        offlineMsg.setCreateTime(new Date());
        offlineMessageDao.save(offlineMsg);

        offlineMsg.setStatus(MessageStatus.READ);
        offlineMessageDao.updateStatus(offlineMsg);

        OfflineMessage offlineMsg2 = offlineMessageDao.findById(offlineMsg.getId());
        Assert.assertTrue(offlineMsg2.getStatus() == MessageStatus.READ);


    }

    @Test
    public void findOfflineTest() {
        Message msg = messageDao.findById(Contact.MESSAGE_ID1);

        Message msg2 = new Message();
        msg2.setId(Contact.MESSAGE_ID2);
        msg2.setFromId(UUID.randomUUID().toString());
        msg2.setToId(UUID.randomUUID().toString());
        msg2.setType(UUID.randomUUID().toString());
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("key1", "value1");
        content.put("key2", "value2");
        msg2.setContent(content);
        msg2.setCreateTime(new Date());
        messageDao.save(msg2);

        Message msg3 = new Message();
        msg3.setId(Contact.MESSAGE_ID3);
        msg3.setFromId(UUID.randomUUID().toString());
        msg3.setToId(UUID.randomUUID().toString());
        msg3.setType(UUID.randomUUID().toString());
        msg3.setContent(content);
        msg3.setCreateTime(new Date());
        messageDao.save(msg3);

        OfflineMessage offlineMsg = new OfflineMessage();
        offlineMsg.setId(UUID.randomUUID().toString());
        offlineMsg.setUserId(Contact.USER_ID);
        offlineMsg.setMessage(msg);
        offlineMsg.setStatus(MessageStatus.SEND);
        offlineMsg.setCreateTime(new Date());
        offlineMessageDao.save(offlineMsg);

        OfflineMessage offlineMsg2 = new OfflineMessage();
        offlineMsg2.setId(UUID.randomUUID().toString());
        offlineMsg2.setUserId(Contact.USER_ID);
        offlineMsg2.setMessage(msg2);
        offlineMsg2.setStatus(MessageStatus.READ);
        offlineMsg2.setCreateTime(new Date());
        offlineMessageDao.save(offlineMsg2);

        OfflineMessage offlineMsg3 = new OfflineMessage();
        offlineMsg3.setId(UUID.randomUUID().toString());
        offlineMsg3.setUserId(Contact.USER_ID);
        offlineMsg3.setMessage(msg3);
        offlineMsg3.setStatus(MessageStatus.SEND);
        offlineMsg3.setCreateTime(new Date());
        offlineMessageDao.save(offlineMsg3);

        List<OfflineMessage> list = offlineMessageDao.findOffline(Contact.USER_ID);

        Assert.assertTrue(list.size() == 2);
    }


}
