package com.ninebudget.cache;

import com.ninebudget.model.CacheVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

public enum Cache {
    INSTANCE{
        @Override
        public void setCacheVendor(CacheVendor cacheVendorInject) {
            cacheVendor = cacheVendorInject;
        }

        @Override
        public String get(String key) {
            return cacheVendor.get(key);
        }

        @Override
        public void set(String key, String value) {
            cacheVendor.set(key, value);
        }
    };

    /*
        Inject CacheVendor so we do use Dependency Injection
        and not hard code the Vendor
     */
    @Component
    public static class CacheServiceInjector {
        @Autowired
        private CacheVendor cacheVendor;

        @PostConstruct
        public void postConstruct() {
            for (Cache cache : EnumSet.allOf(Cache.class))
                cache.setCacheVendor(cacheVendor);
        }
    }

    private static CacheVendor cacheVendor;
    public abstract void setCacheVendor(CacheVendor cacheVendorInject);
    public abstract String get(String key);
    public abstract void set(String key, String value);
}
