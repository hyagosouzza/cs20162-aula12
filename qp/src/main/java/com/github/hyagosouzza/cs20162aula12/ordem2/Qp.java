/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

/**Classe com os métodos necessários para verificar a qualidade do parser.
 * @version 1.0
 */
public final class Qp {

    /**
     * Construtor privado da classe para satisfazer o CheckStyle.
     */
    private Qp() {
    }

    /**
     * Método que recebe a escolha do usuário em relação ao tipo de arquivo do
     * relatório e o local do arquivo de testes, gerando o relatório.
     * @param escolha String - Escolha do usuário sobre o tipo do arquivo
     * relatório
     * @param local String - Local do arquivo de testes
     * @throws Exception - O endereço do arquivo de testes é inválido
     */
    public static void menu(final String escolha, final String local)
            throws Exception {

        ExtraiInfo objetoExtr = new ExtraiInfo(local);

        if ("-h".equals(escolha)) {
            Html objetoH = new Html(objetoExtr.lerTodasLinhasDoTexto(), local);
            objetoH.relatorioHtml();
        } else {
            Json objetoJ = new Json(local, objetoExtr.lerTodasLinhasDoTexto());
            objetoJ.relatorioJson();
        }
    }

    /**
     * Método para executar as informações recebidas pelo usuário e passadas
     * pelo main.
     * @param args String - Informações recebidas pelo usuário e passadas pelo
     * main
     * @param local String - Local do arquivo de testes
     * @return int - Valor 0 se tudo ocorreu como planejado, senão, 1.
     */
    public static int pseudoMain(final String args, final String local) {

        try {
            menu(args, local);
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

        if (args.length == 1) {
            System.exit(pseudoMain("-j", args[0]));
        } else {
            System.exit(pseudoMain(args[1], args[0]));
        }
    }
}
