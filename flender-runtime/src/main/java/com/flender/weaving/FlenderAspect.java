package com.flender.weaving;

/**
 * Created by Joel on 02/07/2015.
 */

import android.util.Log;

import com.flender.weaving.annotations.InternetRequired;
import com.flender.weaving.annotations.MobileRequired;
import com.flender.weaving.annotations.WiFiRequired;
import com.flender.weaving.exception.UnsupportedModeException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Joel on 30/06/2015.
 */

@Aspect
class FlenderAspect {

    private static final String INTERNET = "execution(@com.flender.weaving.annotations.InternetRequired * *(..))";
    private static final String WIFI = "execution(@com.flender.weaving.annotations.WiFiRequired * *(..))";
    private static final String MOBILE = "execution(@com.flender.weaving.annotations.MobileRequired * *(..))";

    @Pointcut(value = INTERNET)
    public void internetAnnotatedMethod() {
    }

    @Pointcut(value = WIFI)
    public void wifiAnnotatedMethod() {
    }

    @Pointcut(value = MOBILE)
    public void mobileAnnotatedMethod() {
    }


    @Around("internetAnnotatedMethod()")
    public void checkInternetConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.v("Aspect", "advice is being triggered");

        if (Flender.isConnected()) {
            joinPoint.proceed();
        } else {
            String mode = getInternetAnnotationParameter(joinPoint);

            if (mode == "silent") {
                Flender.Toast("Silent works available");

            } else if (mode == "alert") {
                if (Flender.getInternetUnavailable() != null) {
                    Flender.getInternetUnavailable().flenderEvent();
                } else {
                    Flender.Toast("Internet not available");

                }
            } else {
                throw new UnsupportedModeException("Unsupported mode,leave parameter empty or set to Silent.");
            }
        }
    }

    @Around("wiFiAnnotatedMethod()")
    public void checkWiFiConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {

        if (Flender.isConnectedWifi()) {
            joinPoint.proceed();
        } else {
            String mode = getWiFiAnnotationParameter(joinPoint);

            if (mode == "silent") {
                Flender.Toast("Silent works available");

            } else if (mode == "alert") {
                if (Flender.getWiFiUnavailable() != null) {
                    Flender.getWiFiUnavailable().flenderEvent();
                } else {
                    Flender.Toast("WiFi not available");

                }
            } else {
                throw new UnsupportedModeException("Unsupported mode,leave parameter empty or set to Silent.");
            }
        }
    }

    @Around("mobileAnnotatedMethod()")
    public void checkMobileConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {

        if (Flender.isConnectedMobile()) {
            joinPoint.proceed();
        } else {
            String mode = getMobileAnnotationParameter(joinPoint);

            if (mode == "silent") {
                Flender.Toast("Silent works available");

            } else if (mode == "alert") {
                if (Flender.getMobileUnavailable() != null) {
                    Flender.getMobileUnavailable().flenderEvent();
                } else {
                    Flender.Toast("Mobile network not available");

                }
            } else {
                throw new UnsupportedModeException("Unsupported mode,leave parameter empty or set to Silent.");
            }
        }
    }

    static String getInternetAnnotationParameter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(InternetRequired.class).value().toLowerCase();

        return value;
    }

    static String getWiFiAnnotationParameter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(WiFiRequired.class).value().toLowerCase();

        return value;
    }

    static String getMobileAnnotationParameter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(MobileRequired.class).value().toLowerCase();

        return value;
    }
}
