package com.huawei.runtime;

import java.io.IOException;

public class ShutDown
{
    public static void main(String[] args)
    {
//        shutdown("shutdown -s -t 60");
        shutdown("services.msc");
    }
    
    /**
     * 
     * @param biosCmd(传入cmd命令)
     */
    private static void shutdown(String biosCmd)
    {
        
        try
        {
            Runtime.getRuntime().exec(biosCmd);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
