package com.idltd.hydramob.utils.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenFromEugeneandCo";
    public static final long EXPIRATION_TIME = 999_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String HEADER_WORKPLACE = "WORKPLACE";
}
