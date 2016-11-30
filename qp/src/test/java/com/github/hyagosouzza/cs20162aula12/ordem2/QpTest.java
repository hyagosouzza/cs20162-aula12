package com.github.hyagosouzza.cs20162aula12.ordem2;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class QpTest {

    @Test(expected = java.lang.Exception.class)
    public void testCalculaMemoriaJava() throws Exception {
        String escolha = "erro";
        assertEquals(1,Qp.calculaMemoriaJvm(escolha));
    }
    
    @Test(expected = java.lang.AssertionError.class)
    public void testExprPara() throws AssertionError, Exception {
        String ex = "erro";
        assertEquals(1,Qp.exprPara(ex));
    }
    
    @Test(expected = java.lang.Exception.class)
    public void testTempoMedioTestes() throws Exception {
        String ex = "erro";
        assertEquals(1,Qp.tempoMedioTestes(ex, "1"));
    }
    
    @Test(expected = java.lang.Exception.class)
    public void testTempoTestes() throws Exception {
        String ex = "erro";
        assertEquals(1,Qp.tempoTestes(ex));
    }
    
    @Test
    public void testPseudoMain3() {
        String agrs = "testes.txt -j";
        int expResult = 0;
        int result = Qp.pseudoMain(agrs);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPseudoMain1() {
        String agrs = "testes.txt -h";
        int expResult = 0;
        int result = Qp.pseudoMain(agrs);
        assertEquals(expResult, result);
    }
    
    @Test(expected = java.lang.AssertionError.class)
    public void testPseudoMain2() throws AssertionError {
        String agrs = "erro-6";
        int expResult = 1;
        int result = Qp.pseudoMain(agrs);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaVari() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("b + c;b = 2;2");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("b = 2");
        ArrayList<String> result = Qp.pegaVari(lista);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testPegaVari2() throws Exception {
        List<String> lista = null;
        List<String> expResult = null;
        List<String> result = Qp.pegaVari(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaVari3() throws Exception {
        List<String> lista = new ArrayList();
        lista.add("1 + 1;;2");
        List<String> expResult = new ArrayList();
        expResult.add("");
        List<String> result = Qp.pegaVari(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaExpre() throws Exception {
        List<String> lista = new ArrayList();
        lista.add("b + c; c = 2, b = 2;4");
        List<String> expResult = new ArrayList();
        expResult.add("b + c");
        List<String> result = Qp.pegaExpre(lista);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testPegaExpre2() throws Exception {
        List<String> lista = null;
        List<String> expResult = null;
        List<String> result = Qp.pegaExpre(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaResul() throws Exception {
        List<String> lista = new ArrayList();
        lista.add("b + c; c = 2, b = 2;4");
        List<String> expResult = new ArrayList();
        expResult.add("4");
        List<String> result = Qp.pegaResul(lista);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testPegaResul2() throws Exception {
        List<String> lista = null;
        List<String> expResult = null;
        List<String> result = Qp.pegaResul(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaLetra() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("c=2,b=2");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("c b ");
        ArrayList<String> result = Qp.pegaLetra(lista);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testPegaLetra2() throws Exception {
        ArrayList<String> lista = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = Qp.pegaLetra(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaLetra3() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("a = 2");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("a ");
        ArrayList<String> result = Qp.pegaLetra(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaLetra4() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("Nao ha variaveis");
        ArrayList<String> result = Qp.pegaLetra(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaValor() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("c=2,b=2");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("2 2 ");
        ArrayList<String> result = Qp.pegaValor(lista);
        assertEquals(expResult, result);
    }

    @Test
    public void testPegaValor3() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("Nao ha valores");
        ArrayList<String> result = Qp.pegaValor(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPegaValor2() throws Exception {
        ArrayList<String> lista = new ArrayList();
        lista.add("c=2");
        ArrayList<String> expResult = new ArrayList();
        expResult.add("2");
        ArrayList<String> result = Qp.pegaValor(lista);
        assertEquals(expResult, result);
    }
    
    @Test(expected = java.lang.Exception.class)
    public void testPegaValor4() throws Exception {
        ArrayList<String> lista = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = Qp.pegaValor(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testExecutarTestes() throws Exception {
        List<String> lista = new ArrayList();
        lista.add("b + c; c = 2, b = 2;4");
        lista.add("b + c; c = 3, b = 3;7");
        lista.add("2 * 3;;5");
        List<String> expResult = new ArrayList();
        expResult.add("b + c; c = 3, b = 3;7");
        expResult.add("2 * 3;;5");
        List<String> result = Qp.executarTestes(lista);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testExecutarTestes2() throws Exception {
        List<String> lista = null;
        List<String> expResult = null;
        List<String> result = Qp.executarTestes(lista);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLerTodasLinhasDoTexto() throws Exception {
        String localArquivoDeTexto = "testes.txt";
        List<String> expResult = new ArrayList();
        expResult.add("(4 + x) * (9 - y);;36");
        expResult.add("3.14 * (z + 1);;3.14");
        expResult.add("1 + 2;;3");
        expResult.add("5 / (6 - 5);;5");
        expResult.add("2 * 3;;6");
        expResult.add("1 + a; a = 2;3");
        expResult.add("b + c; c = 2, b = 2;4");
        expResult.add("a / 1; a = 7;7 ");
        expResult.add("1 + 1;;3");
        List<String> result = Qp.lerTodasLinhasDoTexto(localArquivoDeTexto);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testLerTodasLinhasDoTexto2() throws Exception {
        String localArquivoDeTexto = "erro";
        List<String> expResult = null;
        List<String> result = Qp.lerTodasLinhasDoTexto(localArquivoDeTexto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testContadorDeLinhasDeTestes() throws Exception {
        String localDoArquivoDeTestes = "testes.txt";
        int expResult = 9;
        int result = Qp.contadorDeLinhasDeTestes(localDoArquivoDeTestes);
        assertEquals(expResult, result);
    }

    @Test(expected = java.lang.Exception.class)
    public void testContadorDeLinhasDeTestes2() throws Exception {
        String localDoArquivoDeTestes = "erro";
        int expResult = 0;
        int result = Qp.contadorDeLinhasDeTestes(localDoArquivoDeTestes);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testQuantFalhas() throws Exception {
        String localArquivo = "testes.txt";
        int expResult = 1;
        int result = Qp.quantFalhas(localArquivo);
        assertEquals(expResult, result);
    }
    
    @Test(expected = java.lang.Exception.class)
    public void testQuantFalhas2() throws Exception {
        String localArquivo = "erro";
        int expResult = 0;
        int result = Qp.quantFalhas(localArquivo);
        assertEquals(expResult, result);
    }
}
