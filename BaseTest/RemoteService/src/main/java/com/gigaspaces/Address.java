package com.gigaspaces;

import com.gigaspaces.document.DocumentProperties;

import java.io.Serializable;

/**
 * Created by Aharon on 02-Feb-15.
 */
public class Address extends DocumentProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    public String street;
    public String city;
    public String state;
    public String zipcode;
}

