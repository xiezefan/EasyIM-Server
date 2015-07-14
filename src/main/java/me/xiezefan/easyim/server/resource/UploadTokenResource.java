package me.xiezefan.easyim.server.resource;

import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.UpdateTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取七牛云Upload Token的Resource
 * Created by xiezefan on 15/5/16.
 */
@Path("/tokens")
public class UploadTokenResource {
    private static final Logger LOG = LoggerFactory.getLogger(UploadTokenResource.class);

    @Autowired
    public UpdateTokenService updateTokenService;

    @GET
    @Path("/generate")
    @Produces("application/json")
    public Response getToken() {
        try {
            String token = updateTokenService.createToken();
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return new ResponseBuilder(result).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }

    }

}
