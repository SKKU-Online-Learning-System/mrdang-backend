package cs.skku.edu.mrdang.domain.common;

import org.springframework.restdocs.cookies.RequestCookiesSnippet;
import static org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName;
import static org.springframework.restdocs.cookies.CookieDocumentation.requestCookies;

public class CommonSnippet {
    public static RequestCookiesSnippet MasterCookie() {
        return requestCookies(
                cookieWithName("access-token").description("유저 인증에 필요한 access token (MASTER 권한 필요)"),
                cookieWithName("refresh-token").description("access token 재발급에 필요한 refresh token (MASTER 권한 필요)")
        );
    }

    public static RequestCookiesSnippet AdminCookie() {
        return requestCookies(
                cookieWithName("access-token").description("유저 인증에 필요한 access token (ADMIN 권한 필요)"),
                cookieWithName("refresh-token").description("access token 재발급에 필요한 refresh token (ADMIN 권한 필요)")
        );
    }

    public static RequestCookiesSnippet StudentCookie() {
        return requestCookies(
                cookieWithName("access-token").description("유저 인증에 필요한 access token (STUDENT 권한 필요)"),
                cookieWithName("refresh-token").description("access token 재발급에 필요한 refresh token (STUDENT 권한 필요)")
        );
    }
}