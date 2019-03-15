package com.java1234.entity;

import lombok.Data;

@Data
public class RequestIp {
    private String ip;
    private long createTime;
    private Integer reCount;
}
