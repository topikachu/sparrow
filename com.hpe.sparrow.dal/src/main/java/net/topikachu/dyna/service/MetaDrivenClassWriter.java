package net.topikachu.dyna.service;

import org.eclipse.persistence.dynamic.DynamicClassWriter;
import org.eclipse.persistence.internal.libraries.asm.ClassWriter;
import org.eclipse.persistence.internal.libraries.asm.MethodVisitor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import static org.eclipse.persistence.internal.libraries.asm.Opcodes.*;

/**
 * Created by gongy on 2017/1/22.
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MetaDrivenClassWriter extends DynamicClassWriter {

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
