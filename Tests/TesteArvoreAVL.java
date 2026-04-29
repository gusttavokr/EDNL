package Tests;

import Classes.ArvoreAVL;
import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

import java.util.Scanner;
// import Classes.Node;

public class TesteArvoreAVL {
    ArvoreAVL arvore = new ArvoreAVL(getClass());

    public TesteArvoreAVL() {
        arvore.main(null);
    }

    // Erros encontrados:
    // Remoção:
    // 1. Remoção filho direito da raiz
    // 2. Em uma sequência: 10, 15, 20, 25, e remover o 15, o 25 some (Erro de ponteiro)
}
    