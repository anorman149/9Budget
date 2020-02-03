package com.ninebudget.model;

import java.util.List;

@Vendor
public interface InstitutionVendor {
    List<InstitutionAccount> getAccounts() throws InstitutionException;

}
