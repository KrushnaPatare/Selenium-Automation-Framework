package com.swagLabs.utilities;

import java.util.HashMap;

import org.apache.logging.log4j.ThreadContext;



public class TestContext 
{
    private static ThreadLocal<HashMap<Object, Object>> threadLocalData = ThreadLocal.withInitial(HashMap::new);

    public static String extentReportPath = "F:\\WorkSpace\\SwagLabs-Ecommerce\\ExtReport\\ExtentReports"
            + SeleniumUtils.getFormattedDateTime("dd-MM-yyyy hh-mm-ss a") + ".html";

    // Static method to get thread-local data
    public static HashMap<Object, Object> getData() 
    {
        return threadLocalData.get();
    }

    // Static method to set data in thread-local context
    public static void setData(Object key, Object value) 
    {
        threadLocalData.get().put(key, value);
    }

    // Static method to clear thread-local data after test execution
    public static void clearData() 
    {
        threadLocalData.get().clear();
    }
    
    
    
    
    
    public static void setThreadContext() 
    {
        // Set the thread ID to the log context
        ThreadContext.put("threadId", String.valueOf(Thread.currentThread().getId()));
    }

    public static void clearThreadContext() 
    {
        // Remove the thread-specific information from the context
        ThreadContext.clearAll();
    }
    
    
    
}





































