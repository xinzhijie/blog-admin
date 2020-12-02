package cn.dblearn.blog.common.constants;

public class ConstantSecurity {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_KEY = "ESP-TOKEN";
    public static final String USER_ID = "USER_ID";
    public static final String JWT_SECRET = "ESP-TOKEN";
    public static final int JWT_EXPIRATION = 18000;
    public static final String AES_SECRET = "ESP-TOKEN";

    private ConstantSecurity() {
        throw new IllegalStateException("错误：不能被初始化");
    }
}
