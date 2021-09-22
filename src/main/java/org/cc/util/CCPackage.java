package org.cc.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.google.common.reflect.ClassPath;
import org.cc.IAProxyClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CCPackage {

    public static void scanPackage(Map<String, Class<?>> c, String pkg) {
        try {
            ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            classpath.getTopLevelClassesRecursive(pkg).stream().map((classInfo) -> classInfo.load()).forEachOrdered((cls) -> {
                IAProxyClass a = (IAProxyClass) cls.getAnnotation(IAProxyClass.class);
                if (a != null) {
                    c.put(a.id(), cls);
                    log.info( a.id() + "-->"+cls.getName());
                }
            });
        } catch (IOException ex) {
            log.info("Scan Package fail : "+ex.getMessage());
        }
    }
    
    public static File[] getPackageContent(String packageName) throws IOException {
        ArrayList<File> list = new ArrayList<>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            File dir = new File(url.getFile());
            for (File f : dir.listFiles()) {
                list.add(f);
            }
        }
        return list.toArray(new File[]{});
    }

    public static void main(String[] args) throws IOException {
        Map<String,Class<?>> m = new HashMap<String,Class<?>>();
        CCPackage.scanPackage(m,"org.cc");
    
    }

}