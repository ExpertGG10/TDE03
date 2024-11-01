import sorts.TimSort;
import sorts.QuickSort;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int[] seeds = {123, 132, 213, 231, 312};
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        GerarArray gerador = new GerarArray();
        QuickSort sort1 = new QuickSort();
        TimSort sort2 = new TimSort();

        try {
            FileWriter writer = new FileWriter("resultados.csv");
            writer.write("Algoritmo;Tamanho;Iteracoes;Trocas;TempoFinal\n");

            for (int tamanho : tamanhos) {

                long iteracoes = 0;
                long trocas = 0;
                long tempo = 0;
                for (int seed : seeds) {
                    int[] array1 = gerador.gerarArray(tamanho, seed);
                    sort1.iniciar(array1);
                    iteracoes += sort1.getIteracoes();
                    trocas += sort1.getTrocas();
                    tempo += sort1.getTempoFinal();
                }
                writer.write("QuickSort; "+tamanho+"; "+(iteracoes / seeds.length) + "; " + (trocas / seeds.length) + "; " + (tempo / seeds.length) + "\n");

                for (int seed : seeds) {
                    int[] array2 = gerador.gerarArray(tamanho, seed);
                    sort2.iniciar(array2);
                    iteracoes += sort1.getIteracoes();
                    trocas += sort1.getTrocas();
                    tempo += sort1.getTempoFinal();
                }
                writer.write("TimSort; "+tamanho+"; "+(iteracoes / seeds.length) + "; " + (trocas / seeds.length) + "; " + (tempo / seeds.length) + "\n");
            }

            writer.close();
            System.out.println("Resultados foram escritos no arquivo 'resultados.csv'");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}