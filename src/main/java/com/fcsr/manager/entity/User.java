package com.fcsr.manager.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@Setter
@Getter
@ToString
public class User implements Serializable{

    private static final long serialVersionUID = -6473786963724031116L;

    private long id;
    private Date createTime;
    private Boolean isDeleted;
    private String name;
    private String password;
    private String username;
}
