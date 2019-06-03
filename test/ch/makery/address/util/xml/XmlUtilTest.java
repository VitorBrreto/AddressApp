/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util.xml;

import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.util.DateUtil;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paulo Vitor
 */
public class XmlUtilTest {
    
    private static XmlUtil INSTANCE;
    
    public XmlUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws UnboundableClassException {
        INSTANCE = XmlUtil.getInstance(PersonListWrapper.class);
    }

    // Tests of getInstance method, of class XmlUtil.
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhentNullIsPassed() throws UnboundableClassException{
        System.out.println("getInstance");
        Class classsToBeBounded = null;
        XmlUtil result = XmlUtil.getInstance(classsToBeBounded);
    }
    
    @Test(expected = UnboundableClassException.class)
    public void shouldThrowExceptionWhentNonFormattedClassIsPassed() throws UnboundableClassException{
        System.out.println("getInstance");
        Class classToBeBounded = WrongAnnotationSystem.class;
        XmlUtil result = XmlUtil.getInstance(classToBeBounded);
        result.unmarshall(null);
    }
    
    @Test
    public void shoudReturnANotNullInstanceOfXmlUtilWhenPersonListWrapperIsPassed() throws Exception {
        System.out.println("getInstance");
        Class contextClassType = PersonListWrapper.class;
        XmlUtil result = XmlUtil.getInstance(contextClassType);
        assertNotNull(result);
    }
    
    @Test
    public void shoudReturnANotNullInstanceOfXmlUtilWhenDateUtilIsPassed() throws Exception {
        System.out.println("getInstance");
        Class contextClassType = DateUtil.class;
        XmlUtil result = XmlUtil.getInstance(contextClassType);
        assertNotNull(result);
    }
    

//    /**
//     * Test of marshall method, of class XmlUtil.
//     */
//    @Test
//    public void shoudThrowException() {
//        System.out.println("marshall");
//        Object wrapper = null;
//        File outputFile = null;
//        XmlUtil instance = null;
//        instance.marshall(wrapper, outputFile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    @Test
//    public void testMarshall() {
//        System.out.println("marshall");
//        Object wrapper = null;
//        File outputFile = null;
//        XmlUtil instance = null;
//        instance.marshall(wrapper, outputFile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of unmarshall method, of class XmlUtil.
//     */
//    @Test
//    public void testUnmarshall() {
//        System.out.println("unmarshall");
//        File inputFile = null;
//        XmlUtil instance = null;
//        Object expResult = null;
//        Object result = instance.unmarshall(inputFile);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
