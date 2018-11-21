package com.wxy;

import java.util.Iterator;

public class ObjectCheck {
    private Object o;

    public ObjectCheck(Object o) {
        this.o = o;
    }
    public void isNotNull(String code) {
        if(o == null)
            throw BusinessExceptionFactory.build(code);
    }
    public void isNotEmpty(String code) {
        isNotNull(code);
        if(o instanceof String) {
            if(((String) o).trim().length() == 0)
                throw BusinessExceptionFactory.build(code);
        } else if(o instanceof Iterator) {
            if(!((Iterator) o).hasNext())
                throw BusinessExceptionFactory.build(code);
        }
    }
}
