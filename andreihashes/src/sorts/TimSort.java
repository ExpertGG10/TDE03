package sorts;

public class TimSort {
    private static final int MIN_MERGE = 32;
    private long trocas; // inicia variavel trocas
    private long iteracoes; // inicia variavel iteracoes
    private long tempoFinal; // inicia variavel tempofinal

    public void iniciar(int[] array) {
        resetar();
        long tempoInicial = System.nanoTime(); // inicia a contagem
        timSort(array); // primeira iteração do sort
        tempoFinal = System.nanoTime() - tempoInicial; // calcula o tempo total
    }

    private void timSort(int[] array) {
        int n = array.length;
        int minRun = minRunLength(n);

        // Dividir o array em sub-arrays ordenados (runs)
        for (int start = 0; start < n; start += minRun) {
            int end = Math.min(start + minRun - 1, n - 1);
            insertionSort(array, start, end);
        }

        // Mesclar os runs ordenados
        for (int size = minRun; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }
    }

    private int minRunLength(int n) {
        int r = 0;
        while (n >= 32) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                trocas++;
                iteracoes++;
            }

            array[j + 1] = key;
            iteracoes++;
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        System.arraycopy(array, left, leftArray, 0, mid - left + 1);
        System.arraycopy(array, mid + 1, rightArray, 0, right - mid);

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            iteracoes++;
        }

        while (i < leftArray.length) {
            array[k] = leftArray[i];
            i++;
            k++;
            iteracoes++;
        }

        while (j < rightArray.length) {
            array[k] = rightArray[j];
            j++;
            k++;
            iteracoes++;
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