package io.github.wesleyosantos91.utils;

import de.huxhorn.sulky.ulid.ULID;

import java.time.Instant;

public class ULIDUtils {

    public static String createUTCMinus3ULID() {
        long utcMinus3Timestamp = Instant.now().minusSeconds(3 * 60 * 60).toEpochMilli();
        return new ULID().nextULID(utcMinus3Timestamp);
    }
}
