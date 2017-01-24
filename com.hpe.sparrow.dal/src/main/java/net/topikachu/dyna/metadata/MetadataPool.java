package net.topikachu.dyna.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.emfjson.jackson.module.EMFModule;

import java.io.IOException;

/**
 * Created by gongy on 2017/1/22.
 */
public class MetadataPool {
    public void gen1() throws IOException {
        EcorePackage theCorePackage = EcorePackage.eINSTANCE;

        EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
        EClass customerClass = ecoreFactory.createEClass();
        customerClass.setName("customer");

        EPackage bookStoreEPackage = ecoreFactory.createEPackage();
        bookStoreEPackage.setName("BookStorePackage");
        bookStoreEPackage.setNsPrefix("dynaApp");
        bookStoreEPackage.setNsURI("http:///com.hpe.saw.dyna.entity.dyna");
        EAttribute bookStoreOwner = ecoreFactory.createEAttribute();
        bookStoreEPackage.getEClassifiers().add(customerClass);


        EAttribute bookName = ecoreFactory.createEAttribute();
        bookName.setName("firstName");
        bookName.setEType(theCorePackage.getEString());
        //EAttribute firstName = ecoreFactory.createEAttribute();
        customerClass.getEStructuralFeatures().add(bookName);


        ResourceSet metaResourceSet = new ResourceSetImpl();

/*
* Register XML Factory implementation to handle .ecore files
*/
        metaResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
                "ecore", new XMLResourceFactoryImpl());

/*
* Create empty resource with the given URI
*/
        Resource metaResource = metaResourceSet.createResource(URI.createURI("./bookStore.ecore"));

/*
* Add bookStoreEPackage to contents list of the resource
*/
        metaResource.getContents().add(bookStoreEPackage);

        try {
     /*
     * Save the resource
     */
            metaResource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        EMFModule module = new EMFModule();
// ...
// configure the module and the mapper here...
        mapper.registerModule(module);


// obtain the mapper later like this
//        ObjectMapper mapper = factory.getMapper();
        String jsonString = mapper.writeValueAsString(bookStoreEPackage);
        System.out.println(jsonString);


        EPackage obj = mapper.reader()
                //.withAttribute(EMFContext.Attributes.RESOURCE_SET, resourceSet)
                //.withAttribute(EMFContext.Attributes.RESOURCE_URI, "src/main/resources/data.json")
                .forType(EPackage.class)
                .readValue(jsonString);
    }
}
