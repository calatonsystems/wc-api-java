package com.icoderman.woocommerce.oauth;

import java.util.Objects;

/**
 * HttpParameter class corresponding to key/value pairs in HTTP requests: query parameters and
 * www-form/urlencoded parameters.
 * This class is used to support the possibility of having multiple valued parameters.
 */
public class HttpParameter implements Comparable<HttpParameter> {

    private final String name;
    private final String value;

    public HttpParameter(String name, String value) {

        Objects.requireNonNull(name, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof HttpParameter)) {
            return false;
        }

        final HttpParameter otherParam = (HttpParameter) other;
        return otherParam.getName().equals(name) && otherParam.getValue().equals(value);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + value.hashCode();
    }

    @Override
    public int compareTo(HttpParameter parameter) {
        final int keyDiff = name.compareTo(parameter.getName());

        return keyDiff == 0 ? value.compareTo(parameter.getValue()) : keyDiff;
    }
}
