public class Tablero {
    private char[][] matriz;

    public Tablero(int w, int h){
        matriz = new char[w][h];
        iniciarMatriz();
    }
    private void iniciarMatriz(){
        int w = matriz.length;
        int h= matriz[0].length;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                matriz[i][j] = '•';
            }
        }

    }
    public void PoblacionInicial(String poblacionInicial){
        String[] filas = poblacionInicial.split("#");
        int w = matriz.length;
        int h = matriz[0].length;
        for (int i = 0; i < w && i < filas.length; i++) {
            String fila = filas[i];
            for (int j = 0; j < h && j < fila.length(); j++) {
                if (fila.charAt(j) == '0') {
                    matriz[i][j] = '•'; // Si el carácter es '0', se asigna '•'
                } else if (fila.charAt(j) == '1') {
                    matriz[i][j] = '○'; // Si el carácter es '1', se asigna '○'
                } else {
                    matriz[i][j] = fila.charAt(j); // Si no, se asigna el carácter original
                }
            }
        }

    }
    public void evolucionarAuto(int numGeneraciones, int velocidadReproduccion) {
        for (int gen = 0; gen < numGeneraciones; gen++) {
            System.out.println("Generación " + (gen + 1));
            imprimirTablero();
            System.out.println("----------------------------------");
            try {
                Thread.sleep(velocidadReproduccion); // velocidad de reproducción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            evolucionarGeneracion();
        }
    }
    public void evolucionarGeneracion() {
        char[][] nuevaGeneracion = new char[matriz.length][matriz[0].length];

        // Recorremos cada celda de la matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                // Obtenemos el número de vecinos vivos de la celda actual
                int vecinosVivos = contarVecinosVivos(i, j);

                // Aplicamos las reglas del Juego de la Vida
                if (matriz[i][j] == '○') { // Célula viva
                    if (vecinosVivos < 2 || vecinosVivos > 3) {
                        nuevaGeneracion[i][j] = '•'; // Muere por soledad o sobrepoblación
                    } else {
                        nuevaGeneracion[i][j] = '○'; // Sobrevive
                    }
                } else { // Célula muerta
                    if (vecinosVivos == 3) {
                        nuevaGeneracion[i][j] = '○'; // Nace una nueva célula
                    } else {
                        nuevaGeneracion[i][j] = '•'; // Sigue muerta
                    }
                }
            }
        }

        // Actualizamos la matriz con la nueva generación
        matriz = nuevaGeneracion;
    }
    private int contarVecinosVivos(int fila, int columna) {
        int count = 0;
        // Definimos las posiciones relativas de los vecinos
        int[][] vecinos = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        // Recorremos los vecinos
        for (int[] vecino : vecinos) {
            int vecinoFila = fila + vecino[0];
            int vecinoColumna = columna + vecino[1];
            // Verificamos que el vecino esté dentro de los límites de la matriz
            if (vecinoFila >= 0 && vecinoFila < matriz.length && vecinoColumna >= 0 && vecinoColumna < matriz[0].length) {
                // Incrementamos el contador si el vecino está vivo
                if (matriz[vecinoFila][vecinoColumna] == '○') {
                    count++;
                }
            }
        }
        return count;
    }
    public void imprimirTablero(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }

}
