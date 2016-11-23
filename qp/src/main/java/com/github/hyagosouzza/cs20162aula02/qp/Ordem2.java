package com.github.hyagosouzza.cs20162aula02.qp;

import com.github.kyriosdata.parser.Expressao;

import com.github.kyriosdata.parser.Lexer;

import com.github.kyriosdata.parser.Parser;

import com.github.kyriosdata.parser.Token;

import com.google.gson.Gson;

import org.json.simple.JSONObject;

import java.io.*;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import java.util.regex.Pattern;

public class Ordem2 {
    
    public static Expressao exprPara(final String expressao) {
        List<Token> tokens = new Lexer(expressao).tokenize();
        Parser parser = new Parser(tokens);
        return parser.expressao();
    }
    
    public static List<String> executarTestes(List<String> testes){
        List<String> falhas = new ArrayList();
        for(String test : testes){
            String lista[] = test.split(Pattern.quote(";"));
            if(" ".equals(lista[1])){
                String result = Float.toString(exprPara(lista[0]).valor());
                if(!result.equals(lista[2])){
                    falhas.add(test);
                }
            }
        }
        
        return falhas;
    }
    
    public static List<String>
        lerTodasLinhasDoTexto(String localDoArquivoDeTexto)
            throws IOException{
        Path testes = Paths.get(localDoArquivoDeTexto);
        List<String> linhas = Files.readAllLines(testes);
        return linhas;
    }
    
    public static int contadorDeLinhasDeTestes(String localDoArquivoDeTestes) 
            throws Exception {
        try {
	LineNumberReader lineCounter = new LineNumberReader
        (new InputStreamReader(new FileInputStream(localDoArquivoDeTestes)));
	String nextLine = null;
            while ((nextLine = lineCounter.readLine()) != null) {
		if (nextLine == null)
                    break;
            }
            return lineCounter.getLineNumber();
	} catch (Exception ex) {
            return 1;
	}
    }
    
    public static List<String> tempoTestes(String localArquivo)
            throws Exception, IOException {
        return executarTestes(lerTodasLinhasDoTexto(localArquivo));
    }
    
    public static void main(String[] args) throws Exception {
        
        JSONObject jsonObject = new JSONObject();
        FileWriter writeFile;
        
        long inicio = System.currentTimeMillis();
        List<String> falhas = new ArrayList();
        falhas = tempoTestes("testes.txt");
        long fim = System.currentTimeMillis();
        long resultado = fim - inicio;
        long mediaTempo = resultado / contadorDeLinhasDeTestes("testes.txt");
        String resultadoString = Long.toString(resultado);
        String mediaTempoString = Long.toString(mediaTempo);
        jsonObject.put("Tempo gasto para execução dos testes ",
                    resultadoString + " mili segundos.");
        jsonObject.put("Tempo médio de cada teste é de ",
                mediaTempoString + " mili segundos.");
        
        try{
            writeFile = new FileWriter("relatorio.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
	} catch(IOException e){
            e.printStackTrace();
	}
        
        for(String t : falhas){
            System.out.println(t);
        }
        System.out.println(jsonObject);
    }
}
