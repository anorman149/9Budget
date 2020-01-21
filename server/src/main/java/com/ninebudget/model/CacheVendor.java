package com.ninebudget.model;

@Vendor
public interface CacheVendor{
    String get(String key);
    void set(String key, String value);
}
