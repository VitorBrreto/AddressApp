/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util.xml;

import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.util.DateUtil;
import java.io.File;
import javax.xml.bind.JAXBException;
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
    
    private XmlUtil<PersonListWrapper> xmlUtil;
    
    public XmlUtilTest() {
    }
    
    @Before
    public void setUpClass() throws UnboundableClassException {
        xmlUtil = new XmlUtil<>(PersonListWrapper.class);
    }

    // Tests of getInstance method, of class XmlUtil.
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhentNullIsPassed() throws UnboundableClassException{
        System.out.println("getInstance");
        Class classToBeBounded = null;
        XmlUtil result = new XmlUtil(classToBeBounded);
    }
    
    @Test
    public void shoudReturnANotNullInstanceOfXmlUtilWhenPersonListWrapperIsPassed() throws Exception {
        System.out.println("getInstance");
        Class contextClassType = PersonListWrapper.class;
        XmlUtil result = new XmlUtil(contextClassType);
        assertNotNull(result);
    }
    
    @Test
    public void shoudReturnANotNullInstanceOfXmlUtilWhenDateUtilIsPassed() throws Exception {
        System.out.println("getInstance");
        Class contextClassType = DateUtil.class;
        XmlUtil result = new XmlUtil(contextClassType);
        assertNotNull(result);
    }
    
    /**
     * Test of marshall method, of class XmlUtil.
     */
    @Test(expected = JAXBException.class)
    public void shoudThrowExceptionIfNullIsPassed() {
        System.out.println("marshall");
        Object wrapper = null;
        File outputFile = null;
        XmlUtil instance = null;
        instance.marshall(wrapper, outputFile);
    }
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
