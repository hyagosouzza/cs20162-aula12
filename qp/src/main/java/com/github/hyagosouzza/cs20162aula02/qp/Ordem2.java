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
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.regex.Pattern;

public class Ordem2 {

    public static Expressao exprPara(final String expressao) {
        List<Token> tokens = new Lexer(expressao).tokenize();
        Parser parser = new Parser(tokens);
        return parser.expressao();
    }

    public static int executarTestes(List<String> testes) {
        int contadorDeFalhas = 0;
        for (String test : testes) {
            String lista[] = test.split(Pattern.quote(";"));
            if ("".equals(lista[1])) {
                float result = (exprPara(lista[0]).valor());
                float resposta = Float.parseFloat(lista[2]);
                if (result != resposta) {
                    contadorDeFalhas = contadorDeFalhas + 1;
                }
            } else {
                String segundaLista[] = lista[1].split(Pattern.quote(","));
                Map<String, Float> letras = new HashMap<>();
                int contador = 0;
                for (String string : segundaLista) {
                    String terceiraLista[] =
                            segundaLista[contador].split(Pattern.quote("="));
                    String letraEmChar = terceiraLista[0];
                    letraEmChar = letraEmChar.replaceAll(" ", "");
                    float valorDaVariável =
                            Float.parseFloat(terceiraLista[1]);
                    letras.put(letraEmChar, valorDaVariável);
                    contador = contador + 1;
                }

                List<Token> tokens = new Lexer(lista[0]).tokenize();
                Parser parser = new Parser(tokens);
                float resultado = parser.expressao().valor(letras);
                float resposta = Float.parseFloat(lista[2]);
                if (resultado != resposta) {
                    contadorDeFalhas = contadorDeFalhas + 1;
                }
            }
        }

        return contadorDeFalhas;
    }

    public static List<String> lerTodasLinhasDoTexto(String localDoArquivoDeTexto)
            throws IOException {
        try {
            Path testes = Paths.get(localDoArquivoDeTexto);
            List<String> linhas = Files.readAllLines(testes);
            return linhas;
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public static int contadorDeLinhasDeTestes(String localDoArquivoDeTestes)
            throws Exception {
        try {
            LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(localDoArquivoDeTestes)));
            String nextLine = null;
            while ((nextLine = lineCounter.readLine()) != null) {
                if (nextLine == null) {
                    break;
                }
            }
            return lineCounter.getLineNumber();
        } catch (Exception ex) {
            return 1;
        }
    }

    public static int tempoTestes(String localArquivo)
            throws Exception, IOException {
        return executarTestes(lerTodasLinhasDoTexto(localArquivo));
    }

    public static void main(String[] args) throws Exception {

        JSONObject jsonObject = new JSONObject();
        FileWriter writeFile;

        long inicio = System.currentTimeMillis();
        int falhas;
        falhas = tempoTestes("testes.txt");
        long fim = System.currentTimeMillis();
        double resultado = (float) (fim - inicio);
        double mediaTempo = (float) (resultado
                / contadorDeLinhasDeTestes("testes.txt"));
        String resultadoString = Double.toString(resultado);
        String mediaTempoString = Double.toString(mediaTempo);
        jsonObject.put("Tempo gasto para execução dos testes ",
                resultadoString + " mili segundos.");
        jsonObject.put("Tempo médio de cada teste é de ",
                mediaTempoString + " mili segundos.");

        try {
            writeFile = new FileWriter("relatorio.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonObject.put("Falhas", falhas);
        System.out.println(jsonObject);
    }
}
