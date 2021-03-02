package com.o2o.entity;

import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalAuth {
    private Long id;
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
    private PersonInfo personInfo;



}
