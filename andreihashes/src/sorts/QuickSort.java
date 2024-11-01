package sorts;

public class QuickSort {
    private long trocas; // inicia variavel trocas
    private long iteracoes; // inicia variavel iteracoes
    private long tempoFinal; // inicia variavel tempofinal

    public void iniciar(int[] array) {
        resetar();
        long tempoInicial = System.nanoTime(); // inicia a contagem
        quickSort(array, 0, array.length - 1); // primeira iteração do sort
        tempoFinal = System.nanoTime() - tempoInicial; // calcula o tempo total
    }

    private void quickSort(int[] x, int inferior, int superior) {
        iteracoes++;

        if (inferior >= superior) {
            return;
        }

        int pivo = particiona(x, inferior, superior);
        quickSort(x, inferior, pivo - 1);
        quickSort(x, pivo + 1, superior);
    }

    private int particiona(int[] x, int inferior, int superior) {
        int pivo = x[inferior]; // utilizando o primeiro elemento como pivô
                                // tambem pode ser usado o ultimo invertendo a lógica de trocas no final
        int i = inferior;
        int j = superior;

        while (true) {
            iteracoes++;

            // Encontra elemento maior que o pivô
            while (x[i] <= pivo && i < superior) {
                iteracoes++;
                i++; // aumenta até achar algum menor ou os indices se cruzarem

            }

            // Encontra elemento menor que o pivô
            while (x[j] > pivo) {
                iteracoes++;
                j--; // diminue até achar algum maior
            }

            // Se os índices se cruzaram, retorna o ponto de partição
            // o que necessáriamente significa que todos os menores estão abaixo do superior e os maiores acima
            if (i >= j) {
                // Troca o pivo com o superior
                x[inferior] = x[j];
                x[j] = pivo;
                trocas++;
                return j; // retorna o indice em que o pivo foi colocado, para dividir a array
            }

            // Troca os elementos nas posições i e j
            int temp = x[i];
            x[i] = x[j];
            x[j] = temp;
            trocas++;
        }
    }

    // Getters
    public long getTrocas() {
        return trocas;
    }
    public long getIteracoes() {
        return iteracoes;
    }
    public long getTempoFinal() {
        return tempoFinal;
    }

    //resetar os contadores
    public void resetar() {
        trocas = 0;
        iteracoes = 0;
        tempoFinal = 0;
    }
}
