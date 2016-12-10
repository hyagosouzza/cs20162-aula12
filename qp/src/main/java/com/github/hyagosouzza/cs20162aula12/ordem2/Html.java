/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import java.io.FileWriter;

import java.io.PrintWriter;

import java.util.ArrayList;

/**
 * Classe com os atributos/métodos necessários para criar um arquivo.html com
 * o relatório dos testes.
 * @version 1.0
 */
public class Html {

    /**
     * ArrayList com os testes em String.
     */
    private ArrayList<String> arrayLista = new ArrayList();
    /**
     * String para guardar o local do arquivo de texto que contém os testes.
     */
    private String localArquiv;
    /**
     * Objeto FileWriter usado para criar o arquivo.html.
     */
    private FileWriter relatorio;
    /**
     * Objeto PrintWriter usado para escrever/gravar no arquivo.json.
     */
    private PrintWriter gravar;
    /**
     * Conteúdo em String que será gravado no arquivo.html.
     */
    private String conteudo;
    /**
     * Restante do conteúdo em String que será gravado no arquivo.html.
     */
    private String conteudoRestante;

    /**
     * Construtor para que alguns atributos da classe sejam instanciados.
     * @param lista ArrayList - Testes em string
     * @param local String - Local do arquivo de testes
     */
    public Html(final ArrayList<String> lista, final String local) {
        this.arrayLista = lista;
        this.localArquiv = local;
    }

    /**
     * Método para gerar o relatório dos testes em formato html.
     * @throws Exception - O ArrayList com os testes fornecidos é inválido.
     */
    public final void relatorioHtml() throws Exception {

        relatorio = new FileWriter("relatorio.html");
        gravar = new PrintWriter(relatorio);
        GerenciadorDeTestes ger = new GerenciadorDeTestes(arrayLista);

        ArrayList<String> listaExpre = ger.pegaExpre();
        ArrayList<String> listaValor = ger.pegaValor();
        ArrayList<String> listaLetra = ger.pegaLetra();
        ArrayList<String> r = ger.pegaResul();
        ArrayList<String> fail = ger.executarTestes();

        CalculosParaRelat cal = new CalculosParaRelat(localArquiv, arrayLista);
        int numLinhasTestes = listaExpre.size();
        String tempoTestes = cal.tempoTestes();
        int falhas = fail.size();
        int corret = numLinhasTestes - falhas;
        String memoriaJvm = cal.calculaMemoriaJvm();
        String tempoMedio = cal.tempoMedioTestes();

        conteudo = "<html>\n"
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
                + "<td>" + tempoTestes + "ns</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<td><b>Tempo medio</b></td>\n"
                + "<td>" + tempoMedio
                + "ns</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<td><b>Total de testes</b></td>\n"
                + "<td>" + numLinhasTestes
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

        conteudoRestante = "";
        for (String expre : listaExpre) {
            conteudoRestante = conteudoRestante
                    + "<tr>\n"
                    + "<td>" + expre + "</td>\n"
                    + "<td>" + r.get(listaExpre.indexOf(expre)) + "</td>\n"
                    + "<td>"
                    + ger.verObtido(arrayLista.get(listaExpre.indexOf(expre)))
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
            conteudoRestante
                    += "<tr>\n"
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
    }
}
