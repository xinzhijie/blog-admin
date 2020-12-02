package cn.dblearn.blog.auth;

import cn.dblearn.blog.common.constants.ConstantSecurity;
import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.common.exception.enums.ErrorEnum;
import cn.dblearn.blog.common.util.AESUtil;
import cn.dblearn.blog.common.util.JwtTokenUtil;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生成token
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 14:41
 */
public class TokenGenerator {

    public static String generateValue(Integer userId) {
        return getValue(userId);
    }

    public static String getValue(Integer userId) {
        String iss = AESUtil.AESEncode(ConstantSecurity.AES_SECRET, String.format("id,%s",
                userId.toString()));

        Map<String, Object> claims = new HashMap<>(4);
        claims.put("iss", iss); //j wt的签发者 保存用户的帐号和密码以及id 使用AES对称加密
        claims.put("sub", String.valueOf(userId)); // JWT所面向的用户 用户的昵称
        claims.put("iat", new Date());
        claims.put("jti", UUID.randomUUID()); //jwt的唯一身份表示
        // 生成token
        String token = "Bearer " + JwtTokenUtil.generateToken(claims, ConstantSecurity.JWT_SECRET, ConstantSecurity.JWT_EXPIRATION);
        return token;
    }
}
