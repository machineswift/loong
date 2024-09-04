package com.machine.agent.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class CookieTransformer implements ClassFileTransformer {

    private static final String COOKIE_CLASS_PATH = "jakarta/servlet/http/Cookie";
    private static final String COOKIE_CLASS_NAME = "jakarta.servlet.http.Cookie";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (!COOKIE_CLASS_PATH.equals(className)) {
            return classfileBuffer;
        }

        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass clazz = classPool.get(COOKIE_CLASS_NAME);

            // 获取 setPath 方法
            CtMethod method = clazz.getDeclaredMethod("setPath");

            // 创建一个新的 setPath 方法，将路径固定为 "/"
            String newSetPathBody = "public void setPath(String uri) { uri = \"/\"; setAttributeInternal(PATH, uri); }";
            CtMethod newSetPath = CtNewMethod.make(newSetPathBody, clazz);

            // 替换原有的 setPath 方法
            clazz.removeMethod(method);
            clazz.addMethod(newSetPath);
            return clazz.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}