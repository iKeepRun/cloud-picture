package com.zack.cloudpicturebackend.common;


import lombok.Data;

@Data
public class PageRequest {

    private Integer current;
    private Integer pageSize;
    private String orderBy;
    private String orderType;

}
