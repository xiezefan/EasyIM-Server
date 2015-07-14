package me.xiezefan.easyim.server.resource;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.LoginForm;
import me.xiezefan.easyim.server.resource.form.RegisterFrom;
import me.xiezefan.easyim.server.resource.form.UserUpdateForm;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * User Resource
 * Created by ZeFanXie on 15-3-26.
 */
@Path("/users")
public class UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    public UserService userService;

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginForm dataForm) {
        try {
            User user = userService.login(dataForm);
            return new ResponseBuilder(new UserVo(user)).build();
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

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(RegisterFrom dataForm) {
        try {
            userService.register(dataForm);
            return ResponseBuilder.SUCCESS.build();
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
    @Path("/")
    @Produces("application/json")
    public Response list(@QueryParam("start")int start, @QueryParam("row")int row, @Context HttpHeaders headers) {
        try {
            List<UserVo> list = userService.list(headers.getHeaderString("User"), start, row);
            return new ResponseBuilder(list).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("q")String queryText, @Context HttpHeaders headers) {
        try {
            List<UserVo> list = userService.search(headers.getHeaderString("User"), queryText);
            return new ResponseBuilder(list).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }


    @GET
    @Path("/{user_id}")
    @Produces("application/json")
    public Response getUserInfo(@PathParam("user_id")String userId, @Context HttpHeaders headers) {
        try {
            UserVo user = userService.getUserInfo(userId);
            return new ResponseBuilder(user).build();
        } catch (ServiceException e) {
            if (e.isLogException()) {
                LOG.error("Business Error", e);
            }
            return e.getResponseBuilder().build();
        }  catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }

    @PUT
    @Path("/self")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(UserUpdateForm dataForm, @Context HttpHeaders headers) {
        try {
            userService.updateUser(headers.getHeaderString("User"), dataForm);
            return ResponseBuilder.SUCCESS.build();
        } catch (ServiceException e) {
            if (e.isLogException()) {
                LOG.error("Business Error", e);
            }
            return e.getResponseBuilder().build();
        }  catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }



}
