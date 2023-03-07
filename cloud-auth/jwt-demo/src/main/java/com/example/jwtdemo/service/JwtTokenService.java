package com.example.jwtdemo.service;

import com.example.jwtdemo.domain.PayloadDto;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

import java.text.ParseException;

public interface JwtTokenService {

    String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException;

    PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException;

    String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException;

    PayloadDto verifyTokenRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException;

    PayloadDto getDefaultPayloadDto();

    RSAKey getDefaultRSAKey();
}
