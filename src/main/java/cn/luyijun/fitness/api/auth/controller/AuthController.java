package cn.luyijun.fitness.api.auth.controller;

import cn.luyijun.fitness.api.auth.service.AuthService;
import cn.luyijun.fitness.api.service.entity.AuthReq;
import cn.luyijun.fitness.api.service.entity.StaffInfo;
import cn.luyijun.fitness.api.service.entity.response.RespResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

//    jiuyoujueyu.jwt.cookieName=jyjyjwt
//    jiuyoujueyu.jwt.dataKey.staffId=staffId
//    jiuyoujueyu.jwt.dataKey.ownerId=ownerId
//    jiuyoujueyu.jwt.subject=jyjysubject
//    jiuyoujueyu.jwt.secretKey=dsfdsf_.,2sdfsgi341sa24&^
//    jiuyoujueyu.jwt.issuer=jyjyissuer
//    jiuyoujueyu.jwt.logOutTime=7200000


    @Value("${jiuyoujueyu.jwt.issuer}")
    private String jwtIssuer;
    @Value("${jiuyoujueyu.jwt.subject}")
    private String jwtSubject;
    @Value("${jiuyoujueyu.jwt.logOutTime}")
    private String jwtLogOutTime;
    @Value("${jiuyoujueyu.jwt.secretKey}")
    private String jwtSecretKey;
    @Value("${jiuyoujueyu.jwt.cookieName}")
    private String jwtCookieName;
    @Value("${jiuyoujueyu.jwt.dataKey.staffId}")
    private String jwtStaffId;
    @Value("${jiuyoujueyu.jwt.dataKey.ownerId}")
    private String jwtOwnerId;

    @RequestMapping(value = "token")
    public RespResult token(@RequestBody(required = false) AuthReq authReq, HttpServletRequest request, HttpServletResponse response) {
        RespResult<StaffInfo> restResult = new RespResult<>();
        try {
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setStaffName("admin");
            staffInfo.setStaffId(617);
            staffInfo.setOwnerId(618);
            // check params
//            if(authReq.getLoginType() == LoginType.PHONE.getType()){
//
//            }else{
//                String userName = authReq.getUserName();
//                String passWord = authReq.getPassWord();
//                if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(passWord)){
//                    //密码
//                }else {
//                    throw new RuntimeException();
//                }
//            }

            //last login time
//            new Thread(new UpdateLoginTime(this.authorizationService, new StaffInfo(staffInfo.getStaffId(), HttpTookit.getRealIpAddr(request)))).start();

            loginResult(restResult, staffInfo, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restResult;
    }

    //create JWT with userId
    private void loginResult(RespResult<StaffInfo> result, StaffInfo staffInfo, HttpServletResponse response) {
        Claims cs = Jwts.claims()
                .setIssuer(jwtIssuer)
                .setSubject(jwtSubject)
                .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(jwtLogOutTime)));

        cs.put(jwtStaffId, staffInfo.getStaffId());
        cs.put(jwtOwnerId, staffInfo.getOwnerId());

        String token = Jwts.builder()
                .setClaims(cs)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey.getBytes())
                .compact();



        Cookie cookie = new Cookie(jwtCookieName, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.valueOf(jwtLogOutTime) / 1000);
        response.addCookie(cookie);

        staffInfo.setStaffId(null);
        result.setData(staffInfo);
    }


    // LocalDateTime
    public static void main(String[] args) {
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1585370252045L), ZoneId.systemDefault());
        System.out.println(localDateTime);
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
        System.out.println(format);

        System.out.println(new Date(1585370252045L));

//        LocalDate now1 = LocalDate.now();
//        System.out.println(now1);
//        LocalTime now2 = LocalTime.now();
//        System.out.println(now2);

//        Jwts.builder().setClaims(cs).signWith(SignatureAlgorithm.HS512, jwtSecretKey.getBytes()).compact();
        String compact = Jwts.builder().setSubject("subject")
                .setIssuer("issuer")
                .setExpiration(new Date(1585370252045L))
                .signWith(SignatureAlgorithm.HS512, "jwtSecretKey".getBytes()).compact();
    }
}
