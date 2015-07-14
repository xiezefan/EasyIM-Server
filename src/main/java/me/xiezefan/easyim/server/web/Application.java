package me.xiezefan.easyim.server.web;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by xiezefan-pc on 2015/4/5 0005.
 */
@ApplicationPath("/")
public class Application extends ResourceConfig {

    public Application() {
        packages("me.xiezefan.easyim.server.resource;me.xiezefan.easyim.server.filter;");
    }

}
