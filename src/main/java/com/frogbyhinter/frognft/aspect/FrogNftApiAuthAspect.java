package com.frogbyhinter.frognft.aspect;

import com.frogbyhinter.frognft.exception.FrogNftApiAuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class FrogNftApiAuthAspect {

    @Around("@annotation(com.frogbyhinter.frognft.aspect.annotation.FrogNftApiAuth)")
    public Object frogNftApiAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String headerKey = request.getHeader("X-HEADER-NFT-KEY");

        if (!ObjectUtils.isEmpty(headerKey)) {
            if (!headerKey.equals("**********"))
                throw new FrogNftApiAuthException("unauthorized X-HEADER-NFT-KEY is invalid");
        } else {
            throw new FrogNftApiAuthException("unauthorized X-HEADER-NFT-KEY is empty");
        }

        try {
            return joinPoint.proceed();
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

}
