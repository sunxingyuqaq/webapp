package com.fcsr.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Boolean isDeleted;
    private String name;
    private String password;
    private String username;
}
