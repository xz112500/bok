package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformat {
   public String dateformat(Date date){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return format.format(date);
   }
}
