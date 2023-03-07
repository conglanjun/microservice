package com.example.jwtdemo.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.example.jwtdemo.controller.api.CommonResult;
import com.example.jwtdemo.domain.PayloadDto;
import com.example.jwtdemo.service.JwtTokenService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Tag(name = "JwtTokenController", description = "JWT令牌管理")
@Controller
@RequestMapping("/token")
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Operation(summary = "使用对称加密（HMAC）算法生成token")
    @ApiResponse(responseCode = "2xx",description = "")
    @RequestMapping(value = "/hmac/generate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult generateTokenByHMAC() {
        PayloadDto payloadDto = jwtTokenService.getDefaultPayloadDto();
        try {
            String token = jwtTokenService.generateTokenByHMAC(JSONUtil.toJsonStr(payloadDto), SecureUtil.md5("test"));
            return CommonResult.success(token);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @Operation(summary = "使用对称加密（HMAC）算法验证token")
    @ApiResponse(responseCode = "2xx",description = "")
    @RequestMapping(value = "/hmac/verify", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult verifyTokenByHMAC(String token) {
        try {
            PayloadDto payloadDto = jwtTokenService.verifyTokenByHMAC(token, SecureUtil.md5("test"));
            return CommonResult.success(payloadDto);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @Operation(summary = "获取非对称加密（RSA）算法公钥")
    @RequestMapping(value = "/rsa/publicKey", method = RequestMethod.GET)
    @ResponseBody
    public Object getRSAPublicKey() {
        RSAKey key = jwtTokenService.getDefaultRSAKey();
        return new JWKSet(key).toJSONObject();
    }

    @Operation(summary = "使用非对称加密（RSA）算法生成token")
    @RequestMapping(value = "/rsa/generate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult generateTokenByRSA() {
        PayloadDto payloadDto = jwtTokenService.getDefaultPayloadDto();
        try {
            String token = jwtTokenService.generateTokenByRSA(JSONUtil.toJsonStr(payloadDto), jwtTokenService.getDefaultRSAKey());
            return CommonResult.success(token);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @Operation(summary = "使用非对称加密（RSA）算法验证token")
    @RequestMapping(value = "/rsa/verify", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult verifyTokenByRSA(String token) {
        try {
            PayloadDto payloadDto = jwtTokenService.verifyTokenRSA(token, jwtTokenService.getDefaultRSAKey());
            return CommonResult.success(payloadDto);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
