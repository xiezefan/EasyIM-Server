package me.xiezefan.easyim.server.resource;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.resource.form.MessageSendForm;
import me.xiezefan.easyim.server.resource.form.MessageStatusUpdateForm;
import me.xiezefan.easyim.server.resource.vo.MessageVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.MessageService;
import me.xiezefan.easyim.server.util.JsonUtil;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Message Dispatch Resource
 * Created by xiezefan-pc on 2015/4/7 0007.
 */
@Path("/messages")
public class MessageResource {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MessageResource.class);

    @Autowired
    private MessageService messageService;

    @POST
    @Path("/send")
    @Consumes("application/json")
    @Produces("application/json")
    public Response send(MessageSendForm dataForm, @Context HttpHeaders headers) {
        try {
            MessageVo msg = messageService.send(headers.getHeaderString("User"), dataForm);
            return new ResponseBuilder(msg).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (ServiceException e) {
            if (e.isLogException()) {
                LOG.error("Business Error", e);
            }
            return e.getResponseBuilder().build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }


    @GET
    @Path("/offline")
    @Produces("application/json")
    public Response offline(@Context HttpHeaders headers) {
        try {
            List<MessageVo> result = messageService.getOfflineMessage(headers.getHeaderString("User"));
            return new ResponseBuilder(result).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }


    @PUT
    @Path("/status")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStatusBatch(MessageStatusUpdateForm[] dataForms, @Context HttpHeaders headers) {
        try {
            messageService.updateStatus(headers.getHeaderString("User"), dataForms);
            return ResponseBuilder.SUCCESS.build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }

    @PUT
    @Path("/status/{mid}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStatus(MessageStatusUpdateForm dataForm, @PathParam("mid")String mid, @Context HttpHeaders headers) {
        try {
            messageService.updateStatus(headers.getHeaderString("User"), mid, dataForm);
            return ResponseBuilder.SUCCESS.build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }






}

