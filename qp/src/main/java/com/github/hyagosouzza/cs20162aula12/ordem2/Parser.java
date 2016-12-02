/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.hyagosouzza.cs20162aula12.ordem2;

import com.github.kyriosdata.parser.Expressao;

import com.github.kyriosdata.parser.Lexer;

import com.github.kyriosdata.parser.Token;

import java.util.List;

/**
 * Classe que possui o método Parser.
 * @version 1.0
 */
public class Parser {

    /**
    * Método para recer uma expressão e analisá-la.
    * @throws Exception - Expressão inválida
    * @link https://github.com/kyriosdata/parser
    * @param expressao final String - Expressão recebida pelo main
    * @return parser - Expressão
    */
    public static Expressao exprPara(final String expressao) throws Exception {

        List<Token> tokens = new Lexer(expressao).tokenize();
        com.github.kyriosdata.parser.Parser parser =
                new com.github.kyriosdata.parser.Parser(tokens);
        return parser.expressao();

    }
}
