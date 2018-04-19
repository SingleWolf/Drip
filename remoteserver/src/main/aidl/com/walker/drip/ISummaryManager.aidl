// ISummaryManager.aidl
package com.walker.drip;

import com.walker.drip.bean.Summary;
import com.walker.drip.IOnErrorListener;

interface ISummaryManager {
   List<Summary> listAllSummary();
   List<Summary> listSummary(int type);

   void setOnErrorListener(IOnErrorListener listener);
   void removeOnErrorListener(IOnErrorListener listener);
   }
