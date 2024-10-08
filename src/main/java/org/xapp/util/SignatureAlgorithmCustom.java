package org.xapp.util;

import org.springframework.stereotype.Component;

public class SignatureAlgorithmCustom {

    private final String name;
    private final String description;
    private final String type;
    private final String jcaName;
    private final boolean isHmac;
    private final int digestLength;
    private final int keySize;
    private final String oid;

    // Define the available algorithms as static final instances
    public static final SignatureAlgorithmCustom NONE = new SignatureAlgorithmCustom("none", "No digital signature or MAC performed", "None", null, false, 0, 0, null);
    public static final SignatureAlgorithmCustom HS256 = new SignatureAlgorithmCustom("HS256", "HMAC using SHA-256", "HMAC", "HmacSHA256", true, 256, 256, "1.2.840.113549.2.9");
    public static final SignatureAlgorithmCustom HS384 = new SignatureAlgorithmCustom("HS384", "HMAC using SHA-384", "HMAC", "HmacSHA384", true, 384, 384, "1.2.840.113549.2.10");
    public static final SignatureAlgorithmCustom HS512 = new SignatureAlgorithmCustom("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", true, 512, 512, "1.2.840.113549.2.11");
    public static final SignatureAlgorithmCustom RS256 = new SignatureAlgorithmCustom("RS256", "RSASSA-PKCS-v1_5 using SHA-256", "RSA", "SHA256withRSA", true, 256, 2048, null);
    public static final SignatureAlgorithmCustom RS384 = new SignatureAlgorithmCustom("RS384", "RSASSA-PKCS-v1_5 using SHA-384", "RSA", "SHA384withRSA", true, 384, 2048, null);
    public static final SignatureAlgorithmCustom RS512 = new SignatureAlgorithmCustom("RS512", "RSASSA-PKCS-v1_5 using SHA-512", "RSA", "SHA512withRSA", true, 512, 2048, null);
    public static final SignatureAlgorithmCustom ES256 = new SignatureAlgorithmCustom("ES256", "ECDSA using P-256 and SHA-256", "ECDSA", "SHA256withECDSA", true, 256, 256, null);
    public static final SignatureAlgorithmCustom ES384 = new SignatureAlgorithmCustom("ES384", "ECDSA using P-384 and SHA-384", "ECDSA", "SHA384withECDSA", true, 384, 384, null);
    public static final SignatureAlgorithmCustom ES512 = new SignatureAlgorithmCustom("ES512", "ECDSA using P-521 and SHA-512", "ECDSA", "SHA512withECDSA", true, 512, 521, null);
    public static final SignatureAlgorithmCustom PS256 = new SignatureAlgorithmCustom("PS256", "RSASSA-PSS using SHA-256 and MGF1 with SHA-256", "RSA", "RSASSA-PSS", false, 256, 2048, null);
    public static final SignatureAlgorithmCustom PS384 = new SignatureAlgorithmCustom("PS384", "RSASSA-PSS using SHA-384 and MGF1 with SHA-384", "RSA", "RSASSA-PSS", false, 384, 2048, null);
    public static final SignatureAlgorithmCustom PS512 = new SignatureAlgorithmCustom("PS512", "RSASSA-PSS using SHA-512 and MGF1 with SHA-512", "RSA", "RSASSA-PSS", false, 512, 2048, null);

    // Private constructor
    private SignatureAlgorithmCustom(String name, String description, String type, String jcaName, boolean isHmac, int digestLength, int keySize, String oid) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.jcaName = jcaName;
        this.isHmac = isHmac;
        this.digestLength = digestLength;
        this.keySize = keySize;
        this.oid = oid;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getJcaName() {
        return jcaName;
    }

    public boolean isHmac() {
        return isHmac;
    }

    public int getDigestLength() {
        return digestLength;
    }

    public int getKeySize() {
        return keySize;
    }

    public String getOid() {
        return oid;
    }
}
