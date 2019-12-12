package com.sirra.demo.configuration;

import java.time.ZoneId;
import java.time.ZoneOffset;

public class AppConfig {
    public static int LOCAL_OFFSET = -5;
    public static ZoneId ZONE_ID = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(LOCAL_OFFSET));
}
