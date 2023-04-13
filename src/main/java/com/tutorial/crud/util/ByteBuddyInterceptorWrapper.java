package com.tutorial.crud.util;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

import org.hibernate.type.CompositeType;
import java.io.Serializable;
import java.lang.reflect.Method;

@JsonSerialize(using = ByteBuddyInterceptorSerializer.class)
@JsonTypeName("org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor")
public class ByteBuddyInterceptorWrapper extends ByteBuddyInterceptor {


    public ByteBuddyInterceptorWrapper(
            String entityName,
            Class persistentClass,
            Class[] interfaces,
            Serializable id,
            Method getIdentifierMethod,
            Method setIdentifierMethod,
            CompositeType componentIdType,
            SharedSessionContractImplementor session,
            boolean overridesEquals
    ) {
        super(entityName, persistentClass, interfaces, id, getIdentifierMethod, setIdentifierMethod, componentIdType, session, overridesEquals);
    }


    private Object writeReplace() {
        return this;
    }
}