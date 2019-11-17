package io.github.ssy.user.agent.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class UserAgentUtil {


  public Map<String, String> preParse(String userAgentString) {

    Map<String, String> agentMap = new HashMap<String, String>();
    //判空逻辑
    if (StringUtils.isBlank(userAgentString)) {
      return agentMap;
    }

    char[] userAgentChars = userAgentString.toCharArray();
    //

    StringBuffer keyStr = new StringBuffer();

    StringBuffer valueStr = new StringBuffer();

    //

    //0,1 /的左边或者是右边
    int flag = 0;

    //（）外面，或者是里面
    int flag1 = 0;

    for (int i = 0; i < userAgentChars.length; i++) {
      //
      char goalChar = userAgentChars[i];
      if (goalChar == '(') {
        flag1 = 1;
        continue;
      }
      if (goalChar == ')') {
        flag1 = 0;
        flag = 0;
        String keyString = keyStr.toString();
        String valueString = valueStr.toString();
        if (StringUtils.isEmpty(valueStr)) {
          agentMap.put(keyString, keyString);
        } else {
          agentMap.put(keyString, valueString);
        }
        keyStr = new StringBuffer();
        valueStr = new StringBuffer();
        continue;
      }
      if (goalChar == ' ' && (flag1 == 0)) {
        flag = 0;
        String keyString = keyStr.toString();
        String valueString = valueStr.toString();
        if (StringUtils.isEmpty(valueStr)) {
          agentMap.put(keyString, keyString);
        } else {
          agentMap.put(keyString, valueString);
        }
        keyStr = new StringBuffer();
        valueStr = new StringBuffer();
        continue;
      }

      if (goalChar == '/') {
        flag = 1;
        continue;
      }

      if (goalChar == ';') {
        flag = 0;
        String keyString = keyStr.toString();
        String valueString = valueStr.toString();
        if (StringUtils.isEmpty(valueStr)) {
          agentMap.put(keyString, keyString);
        } else {
          agentMap.put(keyString, valueString);
        }
        keyStr = new StringBuffer();
        valueStr = new StringBuffer();
        continue;
      }

      if (flag == 0) {
        keyStr.append(goalChar);
      }

      if (flag == 1) {
        valueStr.append(goalChar);
      }
    }
    return agentMap;
  }


  public static void main(String args[]) {
    UserAgentUtil userAgentUtil = new UserAgentUtil();

    Map<String, String> map = userAgentUtil.preParse(
      "Mozilla/5.0 (Linux; Android 9; vivo NEX S Build/PKQ1.181016.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 MQQBrowser/6.2 TBS/045008 Mobile Safari/537.36 MMWEBID/6142 MicroMessenger/7.0.8.1540(0x27000834) Process/tools NetType/WIFI Language/zh_CN ABI/arm64");
    System.out.println(map);

  }


}
