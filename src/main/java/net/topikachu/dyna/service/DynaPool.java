package net.topikachu.dyna.service;

import javassist.*;
import org.eclipse.persistence.dynamic.DynamicClassLoader;
import org.eclipse.persistence.dynamic.DynamicClassWriter;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.internal.dynamic.DynamicEntityImpl;
import org.eclipse.persistence.internal.dynamic.DynamicPropertiesManager;
import org.eclipse.persistence.jpa.dynamic.JPADynamicTypeBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 禕 on 2017/1/21.
 */
@Component
public class DynaPool extends DynamicClassWriter {

    private Map<String, DynamicType> dynamicTypeMap = new HashMap();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public DynamicType[] getDynamicTypes() {
        readWriteLock.readLock().lock();
        try {
            return dynamicTypeMap.values().toArray(new DynamicType[]{});
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


    public void gen1() throws NotFoundException, CannotCompileException {


        ClassPool pool = ClassPool.getDefault();
        CtClass dpmClass = pool.get(DynamicPropertiesManager.class.getName());


        String classname = "net.topikachu.dyna.entity.dyna.Customer";
        CtClass cc = pool.makeClass(classname);
        cc.setSuperclass(pool.get(DynamicEntityImpl.class.getName()));

// public static DynamicPropertiesManager DPM = new
        // DynamicPropertiesManager();
        CtField defField = new CtField(dpmClass, "DPM", cc);
        defField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
        cc.addField(defField, CtField.Initializer.byNew(dpmClass));

        //CtNewConstructor.make(new CtClass[]{},new CtClass[]{},cc);

//        public DynamicPropertiesManager fetchPropertiesManager() { return DPM; } }
        CtMethod fetchPropertiesManagerMethod = CtNewMethod.make(dpmClass, "fetchPropertiesManager", new CtClass[]{}, new CtClass[]{}, "{ return DPM; } ", cc);
        cc.addMethod(fetchPropertiesManagerMethod);


        CtMethod prop = CtNewMethod.make(pool.get(Object.class.getName()), "getCustField1", new CtClass[]{}, new CtClass[]{}, "{ return get(\"" + "custField1" + "\"); } ", cc);
        cc.addMethod(prop);
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(DynaPool.class.getClassLoader());
        //dynamicClassLoader.addClass("net.topikachu.dyna.entity.dyna.Customer",metaDrivenClassWriter);
        Class<?> dynamicEntityClass = dynamicClassLoader.createDynamicClass(classname, this);
        JPADynamicTypeBuilder newType = new JPADynamicTypeBuilder(dynamicEntityClass, null, "CUSTOMER");
        newType.setPrimaryKeyFields("ID");
        newType.addDirectMapping("id", Integer.class, "ID");
        newType.addDirectMapping("firstName", String.class, "FIRSTNAME");
        newType.addDirectMapping("lastName", String.class, "LASTNAME");
        newType.addDirectMapping("custField1", String.class, "str1");
        cc.detach();
        Map<String, DynamicType> localDynamicTypeMap = new HashMap();
        localDynamicTypeMap.put(classname, newType.getType());
        readWriteLock.writeLock().lock();
        try {
            dynamicTypeMap = localDynamicTypeMap;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public void gen2() throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass dpmClass = pool.get(DynamicPropertiesManager.class.getName());


        String classname = "net.topikachu.dyna.entity.dyna.Customer";
        CtClass cc = pool.makeClass(classname);

        cc.setSuperclass(pool.get(DynamicEntityImpl.class.getName()));

// public static DynamicPropertiesManager DPM = new
        // DynamicPropertiesManager();
        CtField defField = new CtField(dpmClass, "DPM", cc);
        defField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
        cc.addField(defField, CtField.Initializer.byNew(dpmClass));

        //CtNewConstructor.make(new CtClass[]{},new CtClass[]{},cc);

//        public DynamicPropertiesManager fetchPropertiesManager() { return DPM; } }
        CtMethod fetchPropertiesManagerMethod = CtNewMethod.make(dpmClass, "fetchPropertiesManager", new CtClass[]{}, new CtClass[]{}, "{ return DPM; } ", cc);
        cc.addMethod(fetchPropertiesManagerMethod);


        CtMethod prop = CtNewMethod.make(pool.get(Object.class.getName()), "getCustField1", new CtClass[]{}, new CtClass[]{}, "{ return get(\"" + "custField1" + "\"); } ", cc);
        cc.addMethod(prop);
        CtMethod prop2 = CtNewMethod.make(pool.get(Object.class.getName()), "getCustField2", new CtClass[]{}, new CtClass[]{}, "{ return get(\"" + "custField2" + "\"); } ", cc);
        cc.addMethod(prop2);
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(DynaPool.class.getClassLoader());
        //dynamicClassLoader.addClass("net.topikachu.dyna.entity.dyna.Customer",metaDrivenClassWriter);
        Class<?> dynamicEntityClass = dynamicClassLoader.createDynamicClass(classname, this);
        JPADynamicTypeBuilder newType = new JPADynamicTypeBuilder(dynamicEntityClass, null, "CUSTOMER");
        newType.setPrimaryKeyFields("ID");
        newType.addDirectMapping("id", Integer.class, "ID");
        newType.addDirectMapping("firstName", String.class, "FIRSTNAME");
        newType.addDirectMapping("lastName", String.class, "LASTNAME");
        newType.addDirectMapping("custField1", String.class, "str1");
        newType.addDirectMapping("custField2", String.class, "str2");
        dynamicTypeMap.put(classname, newType.getType());
        cc.detach();
    }

    @Override
    public byte[] writeClass(DynamicClassLoader loader, String className) throws ClassNotFoundException {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(className);
            return cc.toBytecode();
        } catch (NotFoundException e) {
            throw new ClassNotFoundException(className, e);
        } catch (CannotCompileException e) {
            throw new ClassNotFoundException(className, e);
        } catch (IOException e) {
            throw new ClassNotFoundException(className, e);
        }
    }


}
