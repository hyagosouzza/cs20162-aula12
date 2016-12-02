/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import java.util.ArrayList;

/**
 * Classe com os atributos/métodos necessários para realizar alguns calculos em
 * relação aos testes.
 * @version 1.0
 */
public class CalculosParaRelat {

    /**
     * String para guardar o local do arquivo de texto que possui os
     * testes.
     */
    private String localArquivo;
    /**
     * ArrayList para guardar todos os testes em String.
     */
    private ArrayList<String> testes =  new ArrayList();
    /**
     * Objeto GerenciadorDeTestes para que os métodos da classe possam utilizar
     * os métodos da classe GerenciadorDeTestes.
     */
    private GerenciadorDeTestes geren = new GerenciadorDeTestes(testes);

    /**
     * Construtor para que os atributos da classe sejam instanciados.
     * @param localArq String - Local do arquivo de testes
     * @param arrayTestes ArrayList - Testes em String
     */
    public CalculosParaRelat(final String localArq,
            final ArrayList<String> arrayTestes) {
        this.localArquivo = localArq;
        this.testes = arrayTestes;
    }

    /**
    * Método para recer o local do arquivo de testes e retornar a memória JVM
    * utilizada pelo parser.
    * @throws Exception - Endereço do arquivo de testes é inválido
    * @return String - Memória JVM utilizada pelo parser
    */
    public final String calculaMemoriaJvm() throws Exception {

        Runtime runtime = Runtime.getRuntime();

        long memoriaMaxAntes = runtime.maxMemory();
        long memoriaLivreAntes = runtime.freeMemory();

        long memoriaAntes = memoriaMaxAntes - memoriaLivreAntes;

        geren.executarTestes();

        long memoriaMaxDps = runtime.maxMemory();
        long memoriaLivreDps = runtime.freeMemory();

        long memoriaDps = memoriaMaxDps - memoriaLivreDps;
        long memoriaFinal = memoriaDps - memoriaAntes;

        String resultado = Long.toString(memoriaFinal);

        return resultado;
    }

    /**
    * Método para recer o local do arquivo de testes e retornar o tempo gasto
    * para a execução desses testes.
    * @throws Exception - Endereço do arquivo de testes é inválido
    * @return String  - Tempo para a execução dos testes
    */
    public final String tempoTestes() throws Exception {

        long inicio = System.nanoTime();
        geren.executarTestes();
        long fim = System.nanoTime();

        long tempo = (fim - inicio);
        String tempoString = Long.toString(tempo);

        return tempoString;
    }

    /**
    * Método para recer o local do arquivo de testes e retornar o tempo médio
    * gasto para a execução de cada teste.
    * @throws Exception - Endereço do arquivo de testes é inválido
    * @return String  - Tempo media para a execução de cada teste
    */
    public final String tempoMedioTestes() throws Exception {

        ExtraiInfo extrai = new ExtraiInfo(localArquivo);
        int numLinhasTestes = extrai.lerTodasLinhasDoTexto().size();

        String tempoDosTestes = tempoTestes();
        long tempoTestesL
                = Long.parseLong(tempoDosTestes);

        float mediaTempo = ((float) tempoTestesL
                / (float) numLinhasTestes);

        String mediaTempoString = Float.toString(mediaTempo);

        return mediaTempoString;
    }
}
