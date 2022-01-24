package com.aif.jvm.agent;

import java.lang.instrument.Instrumentation;

public class JavaAgentPre {
    public static void premain(String args, Instrumentation inst){
        System.out.println("premain start");
        //System.out.println(args);
        inst.addTransformer(new JavaAgentTransformer());
    }
}
