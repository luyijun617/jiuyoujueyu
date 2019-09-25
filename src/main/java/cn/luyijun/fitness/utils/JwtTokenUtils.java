package cn.luyijun.fitness.utils;

import cn.luyijun.fitness.entity.response.RespResult;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class JwtTokenUtils {

    @Value("${jiuyoujueyu.jwt.cookieName}")
    private String cookieName;
    @Value("${jiuyoujueyu.jwt.subject}")
    private String subject;
    @Value("${jiuyoujueyu.jwt.issuer}")
    private String issuer;
    @Value("${jiuyoujueyu.jwt.logOutTime}")
    private int logOutTime;
    @Value("${jiuyoujueyu.jwt.secretKey}")
    private String secretKey;


    public String createJwtToken() {
        Claims claims = Jwts.claims();
        claims = claims.setIssuer(issuer).setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + logOutTime));
        claims.put("username", "wang");
        claims.put("userid", 12);
        JwtBuilder builder = Jwts.builder();
        String token = builder.setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        return token;
    }


    public boolean verifyJwtToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().requireIssuer(issuer).requireSubject(subject).setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Date expirationDate = claims.getExpiration();
        Date nowDate = new Date();
        if (nowDate.getTime() > expirationDate.getTime()) {
            throw new RuntimeException("登录超时");
        }
        JwtBuilder builder = Jwts.builder();
        String newToken = builder.setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        return token.equals(newToken);
    }

    public void setExpirationTime(String token, HttpServletResponse response) {
        Jws<Claims> claimsJws = Jwts.parser().requireIssuer(issuer).requireSubject(subject).setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        claims.setExpiration(new Date(System.currentTimeMillis() + logOutTime));
        String newToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        Cookie cookie = new Cookie("jwt", newToken);
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping("/token")
    @ResponseBody
    public RespResult<String> checkCookie(HttpServletRequest request, HttpServletResponse response) {
        RespResult<String> respResult = new RespResult<>();
        Cookie[] cookies = request.getCookies();
        boolean hasJwtToken = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    hasJwtToken = true;
                    try {
                        if (!verifyJwtToken(cookie.getValue())) {
                            respResult.setRespCode(9999);
                            respResult.setRespMsg("请重新登录");
                        }
                        setExpirationTime(cookie.getValue(), response);
                        break;
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        respResult.setRespCode(8888);
                        respResult.setRespMsg("登录超时");
                    } catch (Exception e) {
                        e.printStackTrace();
                        respResult.setRespCode(9999);
                        respResult.setRespMsg("请重新登录");
                    }
                }
            }
        }
        if(!hasJwtToken){
            String jwtToken = createJwtToken();
            Cookie cookie = new Cookie("jwt", jwtToken);
            cookie.setMaxAge(-1);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return respResult;
    }


}
