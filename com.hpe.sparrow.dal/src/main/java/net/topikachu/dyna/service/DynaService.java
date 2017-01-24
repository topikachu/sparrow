package net.topikachu.dyna.service;

import org.eclipse.persistence.dynamic.DynamicClassLoader;
import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.jpa.dynamic.JPADynamicHelper;
import org.eclipse.persistence.jpa.dynamic.JPADynamicTypeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Component
public class DynaService {
    @Autowired
    private EntityManager entityManager;
    private static final Logger log = LoggerFactory.getLogger(DynaService.class);
    @Autowired
    private Dyna2Pool dyna2Pool;
    @Transactional
    public void dyna() throws Exception {
        String classname = "net.topikachu.dyna.entity.dyna.Customer";


        JPADynamicHelper jpaDynamicHelper = new JPADynamicHelper(entityManager);
        jpaDynamicHelper.addTypes(false, false, dyna2Pool.getDynamicTypes());
        DynamicEntity newDynamicEntity = jpaDynamicHelper.newDynamicEntity(classname);
        newDynamicEntity.set("id", 100);
        newDynamicEntity.set("firstName", "firstNameDyna");
        newDynamicEntity.set("lastName", "lastNameDyna");
        newDynamicEntity.set("custField1", "custField1Val");
        entityManager.persist(newDynamicEntity);


    }

    @Transactional
    public void dyna2() throws Exception {
        String classname = "net.topikachu.dyna.entity.dyna.Customer";


        JPADynamicHelper jpaDynamicHelper = new JPADynamicHelper(entityManager);
        jpaDynamicHelper.addTypes(false, false, dyna2Pool.getDynamicTypes());
        DynamicEntity newDynamicEntity = jpaDynamicHelper.newDynamicEntity(classname);
        DynamicEntity entityCustomer = (DynamicEntity) entityManager.find(jpaDynamicHelper.getType(classname).getJavaClass(), 100);

        log.info(entityCustomer.get("custField1"));
        entityCustomer.set("custField2", "custField2Val");

        entityManager.persist(entityCustomer);


    }

}
