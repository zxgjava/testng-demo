package cn.agilean;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTool {
     
    public Object[][] providerMethod(Method method){
    	System.out.println("method:"+method);
        Object[][] result = null;
        if(method.getName().equals("testmethod1")){
            result = new Object[][]{new Object[]{1}};
        }else if(method.getName().equals("testmethod2")){
            result = new Object[][]{new Object[]{2}};
        }else{
            result = new Object[][]{new Object[]{3}};
        }
        return result;
    }
}