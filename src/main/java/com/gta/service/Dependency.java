package com.gta.service;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Qualifier
public @interface Dependency {

}
