package finki.mk.uiktBackend.config;

public class JwtAuthConstants {

    public static final String SECRET = "secretCode";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public final static String CLAIM_AUTHORITY = "authority";

    public static final String LOGIN_URL = "/rest/user/login";

}
