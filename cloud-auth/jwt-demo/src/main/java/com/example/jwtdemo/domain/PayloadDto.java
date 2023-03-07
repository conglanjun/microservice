package com.example.jwtdemo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class PayloadDto {
    private String sub;
    private Long iat;
    private Long exp;
    private String jti;
    private String username;
    private List<String> authorities;
}
