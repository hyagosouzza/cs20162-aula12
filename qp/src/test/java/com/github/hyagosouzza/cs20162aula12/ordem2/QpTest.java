package com.github.hyagosouzza.cs20162aula12.ordem2;

import org.junit.Test;
import static org.junit.Assert.*;

public class QpTest {

    @Test
    public void testPseudoMain() {
        String args = "-h";
        String local = "testes.txt";
        int expResult = 0;
        int result = Qp.pseudoMain(args, local);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPseudoMain2() {
        String args = "-h";
        String local = null;
        int expResult = 1;
        int result = Qp.pseudoMain(args, local);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPseudoMain3() {
        String args = "-j";
        String local = "testes.txt";
        int expResult = 0;
        int result = Qp.pseudoMain(args, local);
        assertEquals(expResult, result);
    }
}
