package com.flender.weaving;

/**
 * Created by Joel on 02/07/2015.
 */

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Joel on 30/06/2015.
 */

@Aspect
public class FlenderAspect {

    private static final String POINTCUT_METHOD = "execution(@com.flender.weaving.annotations.InternetRequired * *(..))";

    @Pointcut(value = POINTCUT_METHOD)
    public void internetAnnotatedMethod() {
    }



    @Around("internetAnnotatedMethod()")
    public void checkInternetConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.v("Aspect", "advice being triggered");
        if (Flender.isConnected()) {
            joinPoint.proceed();
        } else {
            Flender.Toast("Internet not available");
        }
    }
}
