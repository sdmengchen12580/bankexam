package com.udit.bankexam.ui.home.fragment.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {
  private static final int COUNT = 25;
  
  public static final List<DummyItem> ITEMS = new ArrayList();
  
  public static final Map<String, DummyItem> ITEM_MAP = new HashMap();
  
  static  {
    for (byte b = 1; b <= 25; b++)
      addItem(createDummyItem(b)); 
  }
  
  private static void addItem(DummyItem paramDummyItem) {
    ITEMS.add(paramDummyItem);
    ITEM_MAP.put(paramDummyItem.id, paramDummyItem);
  }
  
  private static DummyItem createDummyItem(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Item ");
    stringBuilder.append(paramInt);
    return new DummyItem(String.valueOf(paramInt), stringBuilder.toString(), makeDetails(paramInt));
  }
  
  private static String makeDetails(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Details about Item: ");
    stringBuilder.append(paramInt);
    for (byte b = 0; b < paramInt; b++)
      stringBuilder.append("\nMore details information here."); 
    return stringBuilder.toString();
  }
  
  public static class DummyItem {
    public final String content;
    
    public final String details;
    
    public final String id;
    
    public DummyItem(String param1String1, String param1String2, String param1String3) {
      this.id = param1String1;
      this.content = param1String2;
      this.details = param1String3;
    }
    
    public String toString() { return this.content; }
  }
}


