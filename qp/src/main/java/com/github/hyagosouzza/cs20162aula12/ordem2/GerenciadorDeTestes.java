/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import static com.github.hyagosouzza.cs20162aula12.ordem2.Parser.exprPara;

import com.github.kyriosdata.parser.Lexer;

import com.github.kyriosdata.parser.Token;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.regex.Pattern;

/**
 * Classe com atributo/métodos necessários para gerenciar os testes, recolhendo
 * informações dos mesmos.
 * @version 1.0
 */
public class GerenciadorDeTestes {

    /**
     * ArrayList com os testes em String.
     */
    private ArrayList<String> testes = new ArrayList();

    /**
     * Constutor para instanciar um atributo da classe.
     * @param arrayTestes ArrayList - Testes em String
     */
    public GerenciadorDeTestes(final ArrayList<String> arrayTestes) {
        this.testes = arrayTestes;
    }
    /**
     * Método para separar as variáveis dos testes recebidos e retorná-las.
     * @return ArrayList - Lista com as variáveis dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public final ArrayList<String> pegaVari() throws Exception {

        ArrayList<String> listaVari = new ArrayList();

        for (String string : testes) {
            String[] listaE = string.split(Pattern.quote(";"));
            listaVari.add(listaE[1]);
        }

        return listaVari;
    }

    /**
     * Método para separar as expressões dos testes recebidos e retorná-las.
     * @return ArrayList - Lista com as expressões dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public final ArrayList<String> pegaExpre() throws Exception {

        ArrayList<String> listaExpre = new ArrayList();

        for (String string : testes) {

            String[] listaE = string.split(Pattern.quote(";"));
            listaExpre.add(listaE[0]);

        }
            return listaExpre;
    }

    /**
     * Método para separar os resultados dos testes recebidos e retorná-los.
     * @return ArrayList - Lista com os resultados dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public final ArrayList<String> pegaResul() throws Exception {

        ArrayList<String> listaR = new ArrayList();

        for (String string : testes) {

            String[] listaE = string.split(Pattern.quote(";"));
            listaR.add(listaE[2]);

        }
        return listaR;
    }

    /**
     * Método para separar as letras das variáveis recebidas e retorná-las.
     * @return ArrayList - Lista com as letras das variáveis dos testes em
     * String
     * @throws Exception - A Lista recebida é inválida
     */
    public final ArrayList<String> pegaLetra() throws Exception {

        ArrayList<String> listaL = new ArrayList();
        ArrayList<String> listaVari = pegaVari();

        for (String string : listaVari) {

            if (!("".equals(string))) {

                String[] listaVirgula
                        = string.split(Pattern.quote(","));
                if (listaVirgula.length > 1) {

                    String concat = "";
                    for (String s : listaVirgula) {
                        String[] listaLetra
                                = s.split(Pattern.quote("="));
                        concat = concat + listaLetra[0] + " ";
                    }
                    listaL.add(concat);
                } else {
                    for (String s : listaVirgula) {
                        String[] listaLetra
                                = s.split(Pattern.quote("="));
                        listaL.add(listaLetra[0]);
                    }
                }
            } else {
                listaL.add("Nao ha variaveis");
            }
        }
        return listaL;
    }

    /**
     * Método para separar os valores das variáveis recebidas e retorná-los.
     * @return ArrayList - Lista com os valores das variáveis dos testes em
     * String
     * @throws Exception - A Lista recebida é inválida.
     */
    public final ArrayList<String> pegaValor() throws Exception {

        ArrayList<String> listaV = new ArrayList();
        ArrayList<String> listaVari = pegaVari();

        for (String string : listaVari) {

            if (!("".equals(string))) {

                String[] listaVirgula
                        = string.split(Pattern.quote(","));
                if (listaVirgula.length > 1) {
                    String concat = "";
                    for (String s : listaVirgula) {
                        String[] listaLetra
                                = s.split(Pattern.quote("="));
                        concat = concat + listaLetra[1] + " ";
                    }
                    listaV.add(concat);
                } else {
                    for (String s : listaVirgula) {
                        String[] listaLetra
                                = s.split(Pattern.quote("="));
                        listaV.add(listaLetra[1]);
                    }
                }
            } else {
                listaV.add("Nao ha valores");
            }
        }
        return listaV;
    }

    /**
    * Método para recer Lista com String de teste e executar os testes.
    * @throws Exception  - A Lista recebida é inválida.
    * @return ArrayList - Testes que falharam
    */
    public final ArrayList<String> executarTestes() throws Exception {

        ArrayList<String> falhas = new ArrayList();

        for (String test : testes) {

            String[] listaT = test.split(Pattern.quote(";"));

            if ("".equals(listaT[1])) {

                float resultadoExpre = (exprPara(listaT[0]).valor());
                float respostaEsperada = Float.parseFloat(listaT[2]);

                if (resultadoExpre != respostaEsperada) {
                    falhas.add(test);
                }

            } else {

                String[] segundaLista
                        = listaT[1].split(Pattern.quote(","));

                Map<String, Float> variaveis = new HashMap<>();

                for (String string : segundaLista) {

                    String[] terceiraLista
                            = string.split(Pattern.quote("="));
                    String letraEmChar = terceiraLista[0];
                    letraEmChar = letraEmChar.replaceAll(" ", "");
                    float valorDaLetra
                            = Float.parseFloat(terceiraLista[1]);
                    variaveis.put(letraEmChar, valorDaLetra);

                }

                List<Token> tokens = new Lexer(listaT[0]).tokenize();
                com.github.kyriosdata.parser.Parser parser =
                        new com.github.kyriosdata.parser.Parser(tokens);

                float resultadoExpre = parser.expressao().valor(variaveis);
                float respostaEsperada = Float.parseFloat(listaT[2]);

                if (resultadoExpre != respostaEsperada) {
                    falhas.add(test);
                }
            }
        }
        return falhas;
    }

    /**
     * Método para ver qual o valor obtido de um teste/expressão recebido(a).
     * @param umTeste String - Teste/expressão
     * @return float - Resultado da expressão/teste
     * @throws Exception - A String com o teste fornecido é inválida.
     */
    public final float verObtido(final String umTeste) throws Exception {

        String[] listaT = umTeste.split(Pattern.quote(";"));
        if ("".equals(listaT[1])) {

            float resultadoExpre = (exprPara(listaT[0]).valor());

            return resultadoExpre;
        } else {

            String[] segundaLista = listaT[1].split(Pattern.quote(","));

            Map<String, Float> variaveis = new HashMap<>();

            for (String string : segundaLista) {

                String[] terceiraLista
                        = string.split(Pattern.quote("="));
                String letraEmChar = terceiraLista[0];
                letraEmChar = letraEmChar.replaceAll(" ", "");
                float valorDaLetra
                        = Float.parseFloat(terceiraLista[1]);
                variaveis.put(letraEmChar, valorDaLetra);

            }

            List<Token> tokens = new Lexer(listaT[0]).tokenize();
            com.github.kyriosdata.parser.Parser parser =
                    new com.github.kyriosdata.parser.Parser(tokens);

            float resultadoExpre = parser.expressao().valor(variaveis);

            return resultadoExpre;
        }
    }
}
