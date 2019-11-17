package io.github.ssy.user.agent;

public enum NetType {
  UNKNOWN("UNKNOWN"),

  WIFI("WIFI"),

  THREE_G("3G"),

  FOUR_G("4G"),

  FIVE_G("5G");

  private String netType;

  NetType(String netType) {
    this.netType = netType;
  }


  public static NetType parseUserAgentLowercaseString(String userAgentString) {
    if (userAgentString == null) {
      return NetType.UNKNOWN;
    }

    return WIFI;
  }
}
