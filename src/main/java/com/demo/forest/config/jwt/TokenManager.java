package com.demo.forest.config.jwt;//package cn.config.jwt;
//
//import cn.zhkz.system.domain.UserInfo;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
////JWT令牌管理器
//public class TokenManager {
//
//    //服务端密匙，注意保护好不能外泄
//    private static final String SERVICE_KEY = "ADMIN-HASH386-LCK";
//
//    /**
//     * 生成TOKEN
//     * @param userInfo 用户信息
//     * @return (String)生成的TOKEN令牌
//     */
//    public static String generatorToken(UserInfo userInfo) {
//        //签发时间
//        long timeMillis = System.currentTimeMillis();
//        Date signDate = new Date(timeMillis);
//        Date outDate = new Date(timeMillis + ((1000 * 60) * 30));
//        //Header
//        Map<String, Object> header = new HashMap<>();
//        header.put("type", "JWT");
//        header.put("alg", "HS256");
//        String token = JWT.create()
//                //token头信息
//                .withHeader(header)
//                //payload(载荷)
//                .withClaim("userName", userInfo.getUserName())
//                .withClaim("userId", userInfo.getUserId().toString())
//                .withClaim("userRealName", userInfo.getUserRealName())
//                //token签发时间
//                .withIssuedAt(signDate)
//                //token过期时间
//                .withExpiresAt(outDate)
//                .sign(Algorithm.HMAC256(SERVICE_KEY));
//        return token;
//    }
//
//    //验证token
//    public static Map<String, Claim> verifyToken(String token) throws Exception {
//        JWTVerifier build = JWT.require(Algorithm.HMAC256(SERVICE_KEY)).build();
//        DecodedJWT verify = build.verify(token);
//        return verify.getClaims();
//    }
//    //测试
//    public static void main(String[] args) throws Exception {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(new BigInteger("1"));
//        userInfo.setUserName("LCK");
//        userInfo.setUserPassword("lck");
//        String token = generatorToken(userInfo);
//        System.out.println("token = " + token);
//        Map<String, Claim> claimMap = null;
//        claimMap = verifyToken(token);
//        System.out.println("claimMap = " + claimMap);
//        Claim userName = claimMap.get("userName");
//        System.out.println("userName = " + userName.asString());
//    }
//}
