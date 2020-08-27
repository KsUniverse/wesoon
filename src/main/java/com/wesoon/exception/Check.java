package com.wesoon.exception;

import java.util.Collection;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */

public final class Check {

    private Check() {
    }

    public static ObjectCheck that(Object o) {
        return new ObjectCheck(o);
    }

    public static CollectionCheck that(Collection o) {return new CollectionCheck(o); }
}

