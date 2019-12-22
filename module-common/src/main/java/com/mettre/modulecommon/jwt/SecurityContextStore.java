package com.mettre.modulecommon.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * @author prince.cao
 * @date 2017/2/22.13:46
 * @description:
 */
public class SecurityContextStore {

    private static final Logger log = LoggerFactory.getLogger(SecurityContextStore.class);

    private static final ThreadLocal<AccessToken> contextHolder = new ThreadLocal();

    public static void clearContext() {
        contextHolder.remove();
    }

    public static AccessToken getContext() {
        AccessToken ctx = (AccessToken) contextHolder.get();
        log.info("get security context {}", ctx);
        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    public static void setContext(AccessToken context) {
        log.info("set security context {}", context);
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder.set(context);
    }

    public static AccessToken createEmptyContext() {
        return new AccessToken();
    }
}
