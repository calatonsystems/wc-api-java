package com.icoderman.woocommerce.oauth;

/**
 * Enum with special symbols and their encoded representations
 */
public enum SpecialSymbol {

    AMP("&", "%26"),
    EQUAL("=", "%3D"),
    PLUS("+", "%2B"),
    STAR("*", "%2A"),
    TILDE("~", "%7E");

    private String plain;
    private String encoded;

    SpecialSymbol(String plain, String encoded) {
        this.plain = plain;
        this.encoded = encoded;
    }

    public String getPlain() {
        return plain;
    }

    public String getEncoded() {
        return encoded;
    }
}
