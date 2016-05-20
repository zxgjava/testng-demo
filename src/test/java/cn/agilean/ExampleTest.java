package cn.agilean;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExampleTest {
	 
	@DataProvider
    public Object[][] providerMethod(Method method){
        return new DataProviderTool().providerMethod(method);
    }
     
    @Test(dataProvider="providerMethod", invocationCount=10, threadPoolSize=5)
    public void testmethod1(int param){
        System.out.println("method1 received:"+param);
    }
     
    @Test(dataProvider="providerMethod")
    public void testmethod2(int param){
        System.out.println("method2 received:"+param);
    }
     
    @Test(dataProvider="providerMethod")
    public void testmethod3(int param){
        System.out.println("method3 received:"+param);
    }
}
