package cn.agilean;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class DataProviderFromCsv {  
    // 定义csv文件的分割符  
    public static char csvSeprator = ',';  
    // 定义@DataProvider的name  
    public static final String dataProviderName = "dataProviderName";  
  
    @DataProvider(name = dataProviderName)  
    public static Object[][] getCsvData(Method method, ITestContext context)  
            throws IOException {  
        // 获取当前文件编译后的绝对路径  
        DataProviderFromCsv dataProviderFromCsv = new DataProviderFromCsv();  
        String retPath = dataProviderFromCsv.getPath();  
  
        // 获取调用方法的方法名  
        String methodName = method.getName();  
  
        // 获取调用方法的类名  
        String className = null;  
        String[] packageName = method.getDeclaringClass().getName().toString()  
                .split("\\.");  
        className = packageName[packageName.length - 1];  
  
        // 指定当前类当前方法对应的csv文件  
        String csvPath = retPath + className + "." + methodName + ".csv";  
  
        // 从CSV文件中读取数据  
        CSVReader reader = new CSVReader(new FileReader(csvPath), csvSeprator);  
  
        // 不读第一行,第一行统一为参数的字段名字  
        reader.readNext();  
  
        // csv中每行的数据都是一个string数组  
        String[] csvRow = null;  
        ArrayList<Object[]> csvList = new ArrayList<Object[]>();  
        // 将读取的数据，按行存入到csvList中  
        while ((csvRow = reader.readNext()) != null) {  
            csvList.add(csvRow);  
        }  
  
        // 定义返回值  
        Object[][] results = new Object[csvList.size()][];  
        // 设置二维数组每行的值，每行是个object对象  
        for (int i = 0; i < csvList.size(); i++) {  
            results[i] = csvList.get(i);  
        }  
  
        return results;  
    }  
  
    public String getPath() {  
        String absolutePath = null;  
        try {  
            absolutePath = this.getClass().getResource("").getPath();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return absolutePath;  
    }  
}  