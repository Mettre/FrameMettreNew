package com.mettre.modulecommon.jwt;

import com.mettre.modulecommon.constant.CommonConstant;
import com.mettre.modulecommon.pojo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {


    public static AccessToken saveAccessToken(User user, Long tokenExpireTime, Integer saveLoginTime) {

        Long tokenExpiration = System.currentTimeMillis() + tokenExpireTime * 60 * 1000;
//        SecretKey key = JwtUtils.generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //登陆成功生成token
        String jwt = Jwts.builder()
                //主题 放入用户名
                .setSubject(user.getUserId())
                //失效时间
                .setExpiration(new Date(tokenExpiration))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, CommonConstant.JWT_TOKEN)
                .compact();

        return new AccessToken(jwt, "basic", tokenExpireTime, user.getUserId());
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = CommonConstant.JWT_TOKEN;//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码
        System.out.println(encodedKey);//[B@152f6e2
        System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return key;
    }

}
