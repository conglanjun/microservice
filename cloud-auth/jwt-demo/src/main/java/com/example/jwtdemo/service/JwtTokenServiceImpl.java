package com.example.jwtdemo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.example.jwtdemo.domain.PayloadDto;
import com.example.jwtdemo.exception.JwtExpiredException;
import com.example.jwtdemo.exception.JwtInvalidException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    @Override
    public String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        Payload payload = new Payload(payloadStr);
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        MACSigner jwsSigner = new MACSigner(secret);
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    @Override
    public PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        MACVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new JwtInvalidException("token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new JwtExpiredException("token已过期！");
        }
        return payloadDto;
    }

    @Override
    public String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
        Payload payload = new Payload(payloadStr);
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        RSASSASigner jwsSigner = new RSASSASigner(rsaKey, true);
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    @Override
    public PayloadDto verifyTokenRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        RSASSAVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new JwtInvalidException("token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new JwtExpiredException("token已过期！");
        }
        return payloadDto;
    }

    @Override
    public PayloadDto getDefaultPayloadDto() {
        Date date = new Date();
        DateTime exp = DateUtil.offsetSecond(date, 60 * 60);
        return PayloadDto.builder()
                .sub("macro")
                .iat(date.getTime())
                .exp(exp.getTime())
                .jti(UUID.randomUUID().toString())
                .username("macro")
                .authorities(CollUtil.toList("ADMIN"))
                .build();
    }

    @Override
    public RSAKey getDefaultRSAKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }
}
