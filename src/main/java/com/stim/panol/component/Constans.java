package com.stim.panol.component;

public class Constans {

    public static final String LOGIN_URL = "/login"; // url para la autenticaichon
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization"; // el header para leer el token
    public static final String TOKEN_BEARER_PREFIX = "Bearer "; // el prefijo necesario para leer el token

    // JWT
    public static final String SUPER_SECRET_KEY = "rHdJa7ilw9j2TMU2x0HbnFCfXqQEmy2L1thlqp0XyYD2vd1BHLt8xV6sYJbuU90"; // clave unica
    public static final long TOKEN_EXPIRATION_TIME = 86_400_000; // tiempo de expiracion del token = 1 dias
}
