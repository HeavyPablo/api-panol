package com.stim.panol.component;

public class Constans {

    public static final String LOGIN_URL = "/login"; // url para la autenticaichon
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization"; // el header para leer el token
    public static final String TOKEN_BEARER_PREFIX = "Bearer "; // el prefijo necesario para leer el token

    // JWT
    public static final String ISSUER_INFO = "https://www.autentia.com/"; // nose pa qe pero dejemoslo ahi
    public static final String SUPER_SECRET_KEY = "1234"; // nose pa qe pero dejemoslo ahi x2
    public static final long TOKEN_EXPIRATION_TIME = 86_400_000; // tiempo de expiracion del token = 1 dias
}
