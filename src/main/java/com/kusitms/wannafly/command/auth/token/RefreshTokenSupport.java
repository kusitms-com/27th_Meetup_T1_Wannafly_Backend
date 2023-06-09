package com.kusitms.wannafly.command.auth.token;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenSupport {

    private static final String REFRESH_TOKEN = "refreshToken";

    public static ResponseCookie convertToCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite(SameSite.NONE.attributeValue())
                .build();
    }
}
