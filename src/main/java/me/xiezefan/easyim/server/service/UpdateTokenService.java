package me.xiezefan.easyim.server.service;

import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

/**
 * 七牛云 Token 生成
 * Created by xiezefan on 15/5/16.
 */
@Component
public class UpdateTokenService {
    private static final String AK = "xKzCfC_k6hj-7QN_wBwKiSigawkqutKmh280cH8-";
    private static final String SK = "wDiJw747BkWM_TuJtlr_1IQbf8RAeVYHsxr0rPGp";
    private static final int DEFAULT_EXPIRES = 60 * 60 * 24 * 15;
    private static final String BUCKET = "easyim";
    private Auth auth;

    public UpdateTokenService() {
        this.auth = Auth.create(AK, SK);;
    }

    public String createToken() {
        return auth.uploadToken(BUCKET, null, DEFAULT_EXPIRES, null);
    }

    public static void main(String[] args) {
        Auth auth2 = Auth.create(AK, SK);
        String url = auth2.privateDownloadUrl("http://7xj4ew.com1.z0.glb.clouddn.com/46.pic_hd.jpg");
        System.out.println(url);
    }
}
