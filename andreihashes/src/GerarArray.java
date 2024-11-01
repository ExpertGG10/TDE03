import java.util.Random;

public class GerarArray {

    public int[] gerarArray(int tamanho, int seed) {


        Random random = new Random(seed);


        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}