package com.flender.weaving.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by Joel on 01/07/2015.
 */
@Target({METHOD})
@Retention(CLASS)
public @interface InternetRequired {

}
