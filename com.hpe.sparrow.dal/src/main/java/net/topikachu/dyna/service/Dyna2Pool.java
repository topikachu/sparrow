package net.topikachu.dyna.service;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.eclipse.persistence.dynamic.DynamicClassLoader;
import org.eclipse.persistence.dynamic.DynamicClassWriter;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.internal.libraries.asm.ClassWriter;
import org.eclipse.persistence.internal.libraries.asm.MethodVisitor;
import org.eclipse.persistence.jpa.dynamic.JPADynamicTypeBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.eclipse.persistence.internal.libraries.asm.Opcodes.*;

/**
 * Created by gongy on 2017/1/22.
 */
@Component
public class Dyna2Pool extends DynamicClassWriter {
    private Map<String, DynamicType> dynamicTypeMap = new HashMap();

    //    String classname = "net.topikachu.dyna.entity.dyna.Customer";
//    String classNameInByteCode=classname.replace(".","/");
    public DynamicType[] getDynamicTypes() {

        return dynamicTypeMap.values().toArray(new DynamicType[]{});

    }

    public void gen1() throws NotFoundException, CannotCompileException {
        String classname = "net.topikachu.dyna.entity.dyna.Customer";
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(DynaPool.class.getClassLoader());
        //dynamicClassLoader.addClass("net.topikachu.dyna.entity.dyna.Customer",metaDrivenClassWriter);
        Class<?> dynamicEntityClass = dynamicClassLoader.createDynamicClass(classname, this);
        JPADynamicTypeBuilder newType = new JPADynamicTypeBuilder(dynamicEntityClass, null, "CUSTOMER");
        newType.setPrimaryKeyFields("ID");
        newType.addDirectMapping("id", Integer.class, "ID");
        newType.addDirectMapping("firstName", String.class, "FIRSTNAME");
        newType.addDirectMapping("lastName", String.class, "LASTNAME");
        newType.addDirectMapping("custField1", String.class, "str1");
        Map<String, DynamicType> localDynamicTypeMap = new HashMap();
        localDynamicTypeMap.put(classname, newType.getType());
        dynamicTypeMap = localDynamicTypeMap;
    }

    @Override
    protected void addMethods(ClassWriter cw, String parentClassType) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "getCusField1", "()Ljava/lang/String;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitLdcInsn("cusField1");
        mv.visitMethodInsn(INVOKEVIRTUAL, "net/topikachu/dyna/entity/dyna/Customer", "get", "(Ljava/lang/String;)Ljava/lang/Object;", false);
        mv.visitTypeInsn(CHECKCAST, "java/lang/String");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }
}
