/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * Classe com os atributos/métodos necessários para criar um arquivo.json com
 * o relatório dos testes.
 * @version 1.0
 */
public class Json {

    /**
     * Objeto JSON usado para criar o arquivo.json.
     */
    private JSONObject jsonObject = new JSONObject();
    /**
     * Objeto FileWriter usado para escrever/gravar no arquivo.json.
     */
    private FileWriter writeFile;
    /**
     * String para guardar o local do arquivo de texto que contém os testes.
     */
    private String localArquivo;
    /**
     * ArrayList com os testes em String.
     */
    private ArrayList<String> listaTestes = new ArrayList();

    /**
     * Construtor para que alguns atributos da classe sejam instanciados.
     * @param localArq String - Local do arquivo de testes
     * @param lista ArrayList - Testes em String
     */
    public Json(final String localArq, final ArrayList<String> lista) {
        this.localArquivo = localArq;
        this.listaTestes = lista;
    }

    /**
    * Método para recer objetos (JSON e FileWriter) e strings para que estas
    * strings sejam gravadas/escritas em um arquivo.json.
    * @param frase String - O que será escrito no arquivo
    * @param frase2 String - O que será escrito no arquivo
    * @throws IOException - Erro ao tentar gravar no arquivo.
    */
    public final void insereJson(final String frase, final String frase2)
            throws IOException {

        jsonObject.put(frase, frase2);

        writeFile = new FileWriter("relatorio.json");
        writeFile.write(jsonObject.toJSONString());
        writeFile.close();
    }

    /**
     * Método para gerar o relatório dos testes em formato html.
     * @throws Exception - O ArrayList com os testes fornecidos é inválido.
     */
    public final void relatorioJson() throws Exception {

        GerenciadorDeTestes gerencia = new GerenciadorDeTestes(listaTestes);

        ArrayList<String> listaExpre = gerencia.pegaExpre();
        ArrayList<String> listaValor = gerencia.pegaValor();
        ArrayList<String> listaLetra = gerencia.pegaLetra();
        ArrayList<String> r = gerencia.pegaResul();
        ArrayList<String> fail = gerencia.executarTestes();

        CalculosParaRelat calculos =
                new CalculosParaRelat(localArquivo, listaTestes);
        int numLinhasTestes = listaExpre.size();
        int corretos = numLinhasTestes - fail.size();
        String tempoT = calculos.tempoTestes();
        String falhas = Integer.toString(fail.size());
        String corret = Integer.toString(corretos);
        String memoriaJvm = calculos.calculaMemoriaJvm();
        String tempoMedio = calculos.tempoMedioTestes();

        insereJson("Tempo para a execução de "
                + "todos os testes é de "
                + tempoT + " ns.", "Tempo médio para a"
                + " execução de cada teste "
                + tempoMedio + " ns.");

        insereJson("Memória utilizada pelo parser ", memoriaJvm);

        insereJson("Falhas", falhas);

        insereJson("Corretos", corret);

        for (String s : listaExpre) {

            insereJson("Expressão ("
                    + listaExpre.indexOf(s) + ") ", s);

            insereJson("Variáveis ("
                    + listaExpre.indexOf(s) + ") ",
                    listaLetra.get(listaExpre.indexOf(s))
                    + " = " + listaValor.get(listaExpre.indexOf(s)));

            insereJson("Resultado ("
                    + listaExpre.indexOf(s) + ") ",
                    r.get(listaExpre.indexOf(s)));
        }

        for (String falhou : fail) {
            insereJson("Falha número " + fail.indexOf(falhou), " " + falhou);
        }
    }
}
