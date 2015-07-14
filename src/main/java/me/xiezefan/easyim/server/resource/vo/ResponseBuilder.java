package me.xiezefan.easyim.server.resource.vo;


import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.OutputKeys;

/**
 * Success Vo
 * Created by xiezefan-pc on 2015/3/29 0029.
 */
@XmlRootElement
public class ResponseBuilder {
    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_INTERNAL_SERVER = 500;

    // error response
    public static final ResponseBuilder ERROR_BAD_SERVER = new ResponseBuilder(STATUS_INTERNAL_SERVER, 1000, "Bad Server");
    public static final ResponseBuilder ERROR_AUTHORIZATION_FAIL = new ResponseBuilder(STATUS_UNAUTHORIZED, 1001, "Authorization Fail");
    public static final ResponseBuilder ERROR_FORBIDDEN_FAIL = new ResponseBuilder(STATUS_FORBIDDEN, 1002, "Forbidden");
    public static final ResponseBuilder ERROR_INVALID_PARAMETER = new ResponseBuilder(STATUS_BAD_REQUEST, 1003, "Invalid Parameter");
    public static final ResponseBuilder ERROR_USER_EXIST = new ResponseBuilder(STATUS_BAD_REQUEST, 1004, "User Exist");
    public static final ResponseBuilder ERROR_USER_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1005, "No Such User");
    public static final ResponseBuilder ERROR_MESSAGE_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1006, "No Such Message");



    // success response
    public static final ResponseBuilder SUCCESS = new ResponseBuilder(STATUS_OK, 3000, "Success");


    private int status;
    private Object target;

    private int code;
    private String message;

    public ResponseBuilder() {
    }

    public ResponseBuilder(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.target = this;
    }

    public ResponseBuilder(Object target) {
        this.status = STATUS_OK;
        this.target = target;
    }



    public Response build() {
        return Response.status(status).entity(target).build();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
