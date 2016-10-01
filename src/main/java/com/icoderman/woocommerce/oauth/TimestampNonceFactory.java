package com.icoderman.woocommerce.oauth;

import java.util.Objects;

/**
 * Timestamp and Nonce generator.
 * This factory generates a new timestamp but also a Nonce value that should unique for all requests with that timestamp.
 */
public interface TimestampNonceFactory {

    TimestampNonce getTimestampNonce();

    public static class TimestampNonce {

        private final String timestamp;
        private final String nonce;

        public TimestampNonce(String timestamp, String nonce) {

            Objects.requireNonNull(timestamp, "Timestamp cannot be null");
            Objects.requireNonNull(nonce, "Nonce cannot be null");

            this.timestamp = timestamp;
            this.nonce = nonce;
        }

        /**
         * Retrieves a GMT timestamp (in seconds)
         * @return timestamp
         */
        public String getTimestampInSeconds() {
            return timestamp;
        }

        /**
         * Returns a nonce (unique value for each request)
         * @return nonce
         */
        public String getNonce() {
            return nonce;
        }
    }
}