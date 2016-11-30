/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import com.github.kyriosdata.parser.Expressao;

import com.github.kyriosdata.parser.Lexer;

import com.github.kyriosdata.parser.Parser;

import com.github.kyriosdata.parser.Token;

import org.json.simple.JSONObject;

import java.io.*;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.regex.Pattern;

/**Classe com os métodos necessários para verificar a qualidade do parser.
 * @version 1.0
 */
public class Qp {

    /**
     * Objeto JSON usado para criar o arquivo.json.
     */
    private static JSONObject jsonObject = new JSONObject();
    /**
     * Objeto FileWriter usado para escrever/gravar no arquivo.json.
     */
    private static FileWriter writeFile;

    /**
    * Método para recer objetos (JSON e FileWriter) e strings para que estas
    * strings sejam gravadas/escritas em um arquivo.json.
    * @param jsonObject - Objeto Json
    * @param writeFile - Objeto para escrever no arquivo
    * @param frase String - O que será escrito no arquivo
    * @param frase2 String - O que será escrito no arquivo
    * @throws IOException - Erro ao tentar gravar no arquivo.
    */
    public static void insereJson(final JSONObject jsonObject,
            FileWriter writeFile, final String frase, final String frase2)
            throws IOException {

        jsonObject.put(frase, frase2);

        try {

            writeFile = new FileWriter("relatorio.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();

        } catch (IOException ex) {
            throw new IOException("Erro ao tentar gravar no arquivo.");
        }
    }

    /**
    * Método para recer o local do arquivo de testes e retornar a memória JVM
    * utilizada pelo parser.
    * @param localArquivoTestes String - local do arquivo de testes
    * @throws Exception - Endereço do arquivo de testes é inválido
    * @return String - Memória JVM utilizada pelo parser
    */
    public static String calculaMemoriaJvm(final String localArquivoTestes)
            throws Exception {

        Runtime runtime = Runtime.getRuntime();

        long memoriaMaxAntes = runtime.maxMemory();
        long memoriaLivreAntes = runtime.freeMemory();

        long memoriaAntes = memoriaMaxAntes - memoriaLivreAntes;
        try {

        executarTestes(lerTodasLinhasDoTexto(localArquivoTestes));

        } catch (Exception ex) {
            throw new Exception("Endereço do arquivo de testes é inválido");
        }

        long memoriaMaxDps = runtime.maxMemory();
        long memoriaLivreDps = runtime.freeMemory();

        long memoriaDps = memoriaMaxDps - memoriaLivreDps;
        long memoriaFinal = memoriaDps - memoriaAntes;

        String resultado = Long.toString(memoriaFinal);

        return resultado;
    }

    /**
    * Método para recer uma expressão e analisá-la.
    * @throws Exception - Expressão inválida
    * @link https://github.com/kyriosdata/parser
    * @param expressao final String - Expressão recebida pelo main
    * @return parser - Expressão
    */
    public static Expressao exprPara(final String expressao) throws Exception {

        try {

            List<Token> tokens = new Lexer(expressao).tokenize();
            Parser parser = new Parser(tokens);
            return parser.expressao();

        } catch (Exception ex) {
            throw new Exception("Expressão inválida.");
        }
    }

    /**
     * Método para separar as variáveis dos testes recebidos e retorná-las.
     * @param lista - Lista com os testes em String
     * @return List - Lista com as variáveis dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public static ArrayList<String> pegaVari(final List<String> lista)
            throws Exception {

        ArrayList<String> listaVari = new ArrayList();
        try {
            for (String string : lista) {

                String[] listaE = string.split(Pattern.quote(";"));
                listaVari.add(listaE[1]);
            }
            return listaVari;
        } catch (Exception ex) {
            throw new Exception("A Lista recebida é inválida.");
        }
    }

    /**
     * Método para separar as expressões dos testes recebidos e retorná-las.
     * @param lista - Lista com os testes em String
     * @return List - Lista com as expressões dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public static ArrayList<String> pegaExpre(final List<String> lista)
            throws Exception {

        ArrayList<String> listaExpre = new ArrayList();
        try {
            for (String string : lista) {

                String listaE[] = string.split(Pattern.quote(";"));
                listaExpre.add(listaE[0]);

            }
            return listaExpre;
        } catch (Exception ex) {
            throw new Exception("A Lista recebida é inválida.");
        }
    }

    /**
     * Método para separar os resultados dos testes recebidos e retorná-los.
     * @param lista - Lista com os testes em String
     * @return List - Lista com os resultados dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public static ArrayList<String> pegaResul(final List<String> lista)
            throws Exception {

        ArrayList<String> listaR = new ArrayList();
        try {
            for (String string : lista) {

                String listaE[] = string.split(Pattern.quote(";"));
                listaR.add(listaE[2]);

            }
            return listaR;
        } catch (Exception ex) {
            throw new Exception("A Lista recebida é inválida.");
        }
    }

    /**
     * Método para separar as letras das variáveis recebidas e retorná-las.
     * @param lista - Lista com as variáveis dos testes em String
     * @return List - Lista com as letras das variáveis dos testes em String
     * @throws Exception - A Lista recebida é inválida
     */
    public static ArrayList<String> pegaLetra(final ArrayList<String> lista)
            throws Exception {

        ArrayList<String> listaL = new ArrayList();

            try {
                for (String string : lista) {

                    if (!("".equals(string))) {

                        String listaVirgula[] =
                                string.split(Pattern.quote(","));
                        if (listaVirgula.length > 1) {

                            String concat = "";
                            for (String s : listaVirgula) {
                                String listaLetra[] =
                                        s.split(Pattern.quote("="));
                                concat = concat + listaLetra[0] + " ";
                            }
                            listaL.add(concat);
                        } else {
                            for (String s : listaVirgula) {
                                String listaLetra[] =
                                        s.split(Pattern.quote("="));
                                listaL.add(listaLetra[0]);
                            }
                        }
                    } else {
                        listaL.add("Nao ha variaveis");
                    }
                }
                return listaL;
            } catch (Exception ex) {
                throw new Exception("A Lista recebida é inválida.");
            }
    }

    /**
     * Método para separar os valores das variáveis recebidas e retorná-los.
     * @param lista - Lista com as variáveis dos testes em String
     * @return List - Lista com os valores das variáveis dos testes em String
     * @throws Exception - A Lista recebida é inválida.
     */
    public static ArrayList<String> pegaValor(final ArrayList<String> lista)
            throws Exception {

        ArrayList<String> listaV = new ArrayList();

            try {
                for (String string : lista) {

                    if (!("".equals(string))) {

                        String listaVirgula[] =
                                string.split(Pattern.quote(","));
                        if (listaVirgula.length > 1) {
                            String concat = "";
                            for (String s : listaVirgula) {
                                String listaLetra[] =
                                        s.split(Pattern.quote("="));
                                concat = concat + listaLetra[1] + " ";
                            }
                            listaV.add(concat);
                        } else {
                            for (String s : listaVirgula) {
                                String listaLetra[] =
                                        s.split(Pattern.quote("="));
                            listaV.add(listaLetra[1]);
                            }
                        }
                    } else {
                        listaV.add("Nao ha valores");
                    }
                }
                return listaV;
            } catch (Exception ex) {
                throw new Exception("A Lista recebida é inválida.");
        }
    }

    /**
    * Método para recer Lista com String de teste e executar os testes.
    * @param testes Lista com os testes
    * @throws Exception  - A Lista recebida é inválida.
    * @return List - Testes que falharam
    */
    public static ArrayList<String> executarTestes(final List<String> testes)
            throws Exception {

        ArrayList<String> falhas = new ArrayList();

        try {
            for (String test : testes) {

                String listaT[] = test.split(Pattern.quote(";"));

                if ("".equals(listaT[1])) {

                    float resultadoExpre = (exprPara(listaT[0]).valor());
                    float respostaEsperada = Float.parseFloat(listaT[2]);

                    if (resultadoExpre != respostaEsperada) {
                        falhas.add(test);
                    }

                } else {

                    String segundaLista[] =
                            listaT[1].split(Pattern.quote(","));

                    Map<String, Float> variaveis = new HashMap<>();

                    for (String string : segundaLista) {

                        String terceiraLista[] =
                                string.split(Pattern.quote("="));
                        String letraEmChar = terceiraLista[0];
                        letraEmChar = letraEmChar.replaceAll(" ", "");
                        float valorDaLetra =
                                Float.parseFloat(terceiraLista[1]);
                        variaveis.put(letraEmChar, valorDaLetra);

                    }

                    List<Token> tokens = new Lexer(listaT[0]).tokenize();
                    Parser parser = new Parser(tokens);

                    float resultadoExpre = parser.expressao().valor(variaveis);
                    float respostaEsperada = Float.parseFloat(listaT[2]);

                    if (resultadoExpre != respostaEsperada) {
                        falhas.add(test);
                    }
                }
            }
            return falhas;
        } catch (Exception ex) {
            throw new Exception("A Lista recebida é inválida.");
        }
    }

    /**
     * Método para ver qual o valor obtido de um teste/expressão recebido(a).
     * @param testes String - Teste/expressão
     * @return float - Resultado da expressão/teste
     * @throws Exception
     */
    public static float verObtido(final String testes) throws Exception {

        try {
            String listaT[] = testes.split(Pattern.quote(";"));

            if ("".equals(listaT[1])) {

                float resultadoExpre = (exprPara(listaT[0]).valor());

                return resultadoExpre;
            } else {

                String segundaLista[] = listaT[1].split(Pattern.quote(","));

                Map<String, Float> variaveis = new HashMap<>();

                for (String string : segundaLista) {

                    String terceiraLista[] =
                            string.split(Pattern.quote("="));
                    String letraEmChar = terceiraLista[0];
                    letraEmChar = letraEmChar.replaceAll(" ", "");
                    float valorDaLetra =
                            Float.parseFloat(terceiraLista[1]);
                    variaveis.put(letraEmChar, valorDaLetra);

                }

                List<Token> tokens = new Lexer(listaT[0]).tokenize();
                Parser parser = new Parser(tokens);

                float resultadoExpre = parser.expressao().valor(variaveis);

                return resultadoExpre;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
    * Método para recer o local do arquivo de testes e ler cada linha.
    * @param localArquivoDeTexto String
    * @throws java.io.IOException
    * @return List - Lista de Strings com os testes
    */
    public static List<String>
        lerTodasLinhasDoTexto(final String localArquivoDeTexto)
                throws IOException {

        try {

            Path testes = Paths.get(localArquivoDeTexto);
            List<String> linhas = Files.readAllLines(testes);

            return linhas;

        } catch(IOException ex){
            throw new IOException(ex.getMessage());
        }
    }

    /**
    * Método para recer o local do arquivo de testes e contar a quantidade de
    * linhas que ele possui.
    * @param localDoArquivoDeTestes String
    * @throws Exception
    * @return int - Quantidade de linhas do arquivo
    */
    public static int contadorDeLinhasDeTestes (final String
            localDoArquivoDeTestes) throws Exception {

        try {

            LineNumberReader lineCounter =
                    new LineNumberReader(new InputStreamReader(new
                        FileInputStream(localDoArquivoDeTestes)));
            String nextLine = null;

            while ((nextLine = lineCounter.readLine()) != null) {

            }

            return lineCounter.getLineNumber();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
    * Método para recer o local do arquivo de testes e retornar a quantidade de
    * testes que falharam
    * @param localArquivo String
    * @throws Exception
    * @throws java.io.IOException
    * @return int  - Quantidades de testes que falharam
    */
    public static int quantFalhas(final String localArquivo)
            throws Exception, IOException {

        try {

            int falhas;
            falhas =
                    executarTestes(lerTodasLinhasDoTexto(localArquivo)).size();

            return falhas;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
    * Método para recer o local do arquivo de testes e retornar o tempo gasto
    * para a execução desses testes.
    * @param localArquivoDeTestes String
    * @throws Exception
    * @return String  - Tempo para a execução dos testes
    */
    public static String tempoTestes(final String localArquivoDeTestes)
            throws Exception {

        try {

            long inicio = System.nanoTime();
            executarTestes(lerTodasLinhasDoTexto(localArquivoDeTestes));
            long fim = System.nanoTime();

            long tempo = (fim - inicio);
            String tempoString = Long.toString(tempo);

            return tempoString;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
    * Método para recer o local do arquivo de testes e retornar o tempo médio
    * gasto para a execução de cada teste.
    * @param localArquivoDeTestes String
    * @param tempoDosTestes String
    * @throws Exception
    * @return String  - Tempo media para a execução de cada teste
    */
    public static String tempoMedioTestes(final String localArquivoDeTestes,
            final String tempoDosTestes) throws Exception {

        try {

            long tempoTestesL
                    = Long.parseLong(tempoDosTestes);
            float mediaTempo = ((float) tempoTestesL
                    / (float) contadorDeLinhasDeTestes(localArquivoDeTestes));

            String mediaTempoString = Float.toString(mediaTempo);

            return mediaTempoString;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Método que recebe a escolha do usuário em relação ao tipo de arquivo do
     * relatório e preenche-o com informações recebidas sobre os testes.
     * @param escolha - String Escolha do usuário sobre o tipo do arquivo
     * relatório
     * @param listaValor - Lista com os valores das variáveis dos testes
     * @param listaLetra - Lista com as variáveis dos testes
     * @param listaExpre - Lista das expressões dos testes
     * @param r - Lista dos resultados dos testes
     * @param fail - Lista dos testes que falharam
     * @param memoriaJvm - Memória JVM utilizada pelo parser
     * @param testes - Lista com os testes
     * @throws IOException
     * @throws Exception
     */
    public static void menu(final String escolha,
            final ArrayList<String> listaValor,
            final ArrayList<String> listaLetra,
            final ArrayList<String> listaExpre,
            final ArrayList<String> r, final ArrayList<String> fail,
            final String memoriaJvm,
            final List<String> testes)
            throws IOException, Exception {

        if ("h".equals(escolha)) {
            try {

                FileWriter relatorio = new FileWriter("relatorio.html");
                PrintWriter gravar = new PrintWriter(relatorio);

                String tempoT = tempoTestes("testes.txt");
                int falhas = quantFalhas("testes.txt");
                int corret = contadorDeLinhasDeTestes("testes.txt") - falhas;

                String conteudo = "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Relatorio de Testes</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Resultado geral</h1>\n"
                    + "<table>\n"
                    + "<tr>\n"
                    + "<td><b>Memoria da JVM utilizada pelo parser</b></td>\n"
                    + "<td>" + memoriaJvm + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td><b>Tempo total</b></td>\n"
                    + "<td>" + tempoT + "ns</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td><b>Tempo medio</b></td>\n"
                    + "<td>" + tempoMedioTestes("testes.txt", tempoT)
                        + "ns</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td><b>Total de testes</b></td>\n"
                    + "<td>" + contadorDeLinhasDeTestes("testes.txt")
                        + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td><b>Corretos</b></td>\n"
                    + "<td>" + corret + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td><b>Falhas</b></td>\n"
                    + "<td>" + falhas + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "\n"
                    + "<h1>Relatorio detalhado dos testes</h1>\n"
                    + "<table>\n"
                    + "<tr>\n"
                    + "<th>Expressao</th>\n"
                    + "<th>Esperado</th>\n"
                    + "<th>Obtido</th>\n"
                    + "<th>Variaveis</th>\n"
                    + "</tr>\n";

                String conteudoRestante = "";
                for (String expre : listaExpre) {
                    conteudoRestante = conteudoRestante
                        + "<tr>\n"
                        + "<td>" + expre +"</td>\n"
                        + "<td>" + r.get(listaExpre.indexOf(expre)) + "</td>\n"
                        + "<td>"
                            + verObtido(testes.get(listaExpre.indexOf(expre)))
                            + "</td>\n"
                        + "<td>" + listaLetra.get(listaExpre.indexOf(expre))
                            + " = " + "</td>"
                        + "<td>" + listaValor.get(listaExpre.indexOf(expre))
                        + "   </td>\n"
                        + "</tr>\n";
                }

                conteudoRestante = conteudoRestante
                    + "<tr>\n"
                    + "<th>Testes que falharam</th>\n"
                    + "</tr>\n";
                    for (String falha : fail) {
                        conteudoRestante +=
                                "<tr>\n"
                                + "<td>" + falha + "</td>\n"
                                + "</tr>\n";
                    }
                conteudoRestante = conteudoRestante
                    + "</table>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>\n";

                gravar.print(conteudo + conteudoRestante);
                relatorio.close();
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }

        } else {
            try {

                String tempoT = tempoTestes("testes.txt");
                insereJson(jsonObject, writeFile, "Tempo para a execução de "
                    + "todos os testes é de "
                    + tempoT + " ns.", "Tempo médio para a"
                            + " execução de cada teste "
                            + tempoMedioTestes("testes.txt", tempoT) + " ns.");

                String falhas = Integer.toString(quantFalhas("testes.txt"));
                insereJson(jsonObject, writeFile, "Falhas", falhas);

                int corretos = contadorDeLinhasDeTestes("testes.txt")
                    - quantFalhas("testes.txt");
                String corret = Integer.toString(corretos);
                insereJson(jsonObject, writeFile, "Corretos", corret);

                for (String s : listaExpre) {
                    insereJson(jsonObject, writeFile, "Expressão ("
                            + listaExpre.indexOf(s) + ") ", s);
                    insereJson(jsonObject, writeFile, "Variáveis ("
                            + listaExpre.indexOf(s) + ") ",
                            listaLetra.get(listaExpre.indexOf(s))
                            + " = " + listaValor.get(listaExpre.indexOf(s)));
                    insereJson(jsonObject, writeFile, "Resultado ("
                           + listaExpre.indexOf(s) + ") ",
                            r.get(listaExpre.indexOf(s)));
                }

                for (String falhou : fail) {
                    insereJson(jsonObject, writeFile, "Falha número "
                            + fail.indexOf(falhou), " " + falhou);
                }

                insereJson(jsonObject, writeFile, "Memória utilizada pelo"
                        + " parser ", memoriaJvm);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    /**
     * Método para executar as informações recebidas pelo usuário e passadas
     * pelo main.
     * @param args String - Informações recebidas pelo usuário e passadas pelo
     * main
     * @return int - Valor 0 se tudo ocorreu como planejado, senão, 1.
     */
    public static int pseudoMain(final String args) {

        String separarArgs[] = args.split(Pattern.quote("-"));
        String endeArquivo = separarArgs[0];
        String resposta = separarArgs[1];

        try {
            menu(resposta,
                pegaValor(pegaVari(lerTodasLinhasDoTexto(endeArquivo))),
                pegaLetra(pegaVari(lerTodasLinhasDoTexto(endeArquivo))),
                pegaExpre(lerTodasLinhasDoTexto(endeArquivo)),
                pegaResul(lerTodasLinhasDoTexto(endeArquivo)),
                executarTestes(lerTodasLinhasDoTexto(endeArquivo)),
                calculaMemoriaJvm(endeArquivo),
                lerTodasLinhasDoTexto(endeArquivo));
            return 0;
        } catch (Exception ex) {
            return 1;
        }
    }

    /**
     * Método main para receber informações do usuário.
     * @param args String - Informações dadas pelo usuário
     */
    public static void main(final String[] args) {

        System.exit(pseudoMain("testes.txt -h"));
    }
}
