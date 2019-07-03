package com.fcsr.manager.utils;

import java.util.UUID;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
public final class Uuid {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }
}
