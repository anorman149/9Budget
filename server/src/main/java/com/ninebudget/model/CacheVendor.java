package com.ninebudget.model;

public interface CacheVendor extends Vendor{
    String get(String key);
    void set(String key, String value);
}
