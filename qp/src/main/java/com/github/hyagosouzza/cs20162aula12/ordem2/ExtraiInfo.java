/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

/**
 * Classe com os atributos/métodos necessários para ler todas as linhas de um
 * arquivo de texto e retorná-las.
 * @version 1.0
 */
public class ExtraiInfo {

    /**
     * String para guardar o local do arquivo de texto que contém os testes.
     */
    private String localArquivo;

    /**
     * Construtor para que o atributo da classe seja instanciado.
     * @param localArq String - Local do arquivo de testes
     */
    public ExtraiInfo(final String localArq) {
        this.localArquivo = localArq;
    }

    /**
    * Método para recer o local do arquivo de testes e ler cada linha.
    * @throws IOException - Endereço do arquivo de testes é inválido
    * @return ArrayList - Lista de Strings com os testes
    */
    public final ArrayList<String> lerTodasLinhasDoTexto() throws IOException {

        ArrayList<String> linhas = new ArrayList();

        FileReader arq = new FileReader(localArquivo);
        BufferedReader lerArq = new BufferedReader(arq);

        String linha = lerArq.readLine();
        linhas.add(linha);

        while (linha != null) {
            linha = lerArq.readLine();
            linhas.add(linha);
        }
        arq.close();
        linhas.remove(linhas.size() - 1);

        return linhas;
    }
}
