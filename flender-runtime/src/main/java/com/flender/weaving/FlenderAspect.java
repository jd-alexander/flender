package com.flender.weaving;

/**
 * Created by Joel on 02/07/2015.
 */

import android.util.Log;

import com.flender.weaving.annotations.InternetRequired;

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
class FlenderAspect
{

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

        if (Flender.isConnected())
        {
            joinPoint.proceed();
        } else
        {
            String mode = getInternetAnnotationParameter(joinPoint);

            if(mode=="silent")
            {
                Flender.Toast("Silent works available");

            }
            else
            {
                if(Flender.getInternetUnavailable()!=null)
                {
                    Flender.getInternetUnavailable().flenderEvent();
                }
                else
                {
                    Flender.Toast("Internet not available");

                }
            }
        }
    }

    @Around("wiFiAnnotatedMethod()")
    public void checkWiFiConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.v("Aspect", "advice is being triggered");

        if (Flender.isConnectedWifi())
        {
            joinPoint.proceed();
        } else
        {
            String mode = getInternetAnnotationParameter(joinPoint);

            if(mode=="silent")
            {
                Flender.Toast("Silent works available");

            }
            else
            {
                if(Flender.getWiFiUnavailable()!=null)
                {
                    Flender.getWiFiUnavailable().flenderEvent();
                }
                else
                {
                    Flender.Toast("WiFi not available");

                }
            }
        }
    }

    @Around("mobileAnnotatedMethod()")
    public void checkMobileConnectivity(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.v("Aspect", "advice is being triggered");

        if (Flender.isConnectedMobile())
        {
            joinPoint.proceed();
        } else
        {
            String mode = getInternetAnnotationParameter(joinPoint);

            if(mode=="silent")
            {
                Flender.Toast("Silent works available");

            }
            else
            {
                if(Flender.getMobileUnavailable()!=null)
                {
                    Flender.getMobileUnavailable().flenderEvent();
                }
                else
                {
                    Flender.Toast("Mobile network not available");

                }
            }
        }
    }

    static String getInternetAnnotationParameter(JoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String value=signature.getMethod().getAnnotation(InternetRequired.class).value().toLowerCase();

        return value;
    }
}
