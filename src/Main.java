public class Main{
    public static boolean validarDatos(int w, int h, int g, int s, String p) {
        boolean wValido = (w == 10 || w == 20 || w == 40 || w == 80);
        // Validar h
        boolean hValido = (h == 10 || h == 20 || h == 40);
        // Validar g
        boolean gValido = (g >= 0 || g == 0); // Agregamos g==0 como válido también
        // Validar s
        boolean sValido = (s >= 250 && s <= 1000);
        // Validar longitud de las secciones
        boolean longitudValida = true;
        if (sValido) {
            String[] secciones = p.split("#");
            for (String seccion : secciones) {
                if (seccion.length() > w) {
                    longitudValida = false;
                }
            }
        }
        if (wValido && hValido && gValido && sValido && longitudValida) {
            return true; // Si todas las condiciones son válidas, retorna true
        } else {
            // Imprimir mensaje específico para identificar qué variable booleana es falsa
            if (!wValido || !hValido) {
                System.out.println("El tamaño de la matriz es invalido.Modifique los tamaños...");
            }
            if (!gValido) {
                System.out.println("La cantidad de generaciones no es válido.");
            }
            if (!sValido) {
                System.out.println("La velocidad de reproduccion no es correcta...");
            }
            if (!longitudValida) {
                System.out.println("La longitud de las secciones no es válida para el valor del tablero.");
            }
            return false;
        }

    }
    public static void main(String[] args) {
        int w=0,h=0,g=0,s=0;
        String p="";
        int numArgs=args.length;

        w=Integer.parseInt(args[0]);
        h=Integer.parseInt(args[1]);
        g=Integer.parseInt(args[2]);
        s=Integer.parseInt(args[3]);
        p=args[4];
        if(numArgs==5){
            if(validarDatos(w,h,g,s,p)){
                System.out.println("La validacion es correcta...");
                Tablero tablero1 = new Tablero(w, h);

                System.out.println("width = ["+w+"]");
                System.out.println("height = ["+h+"]");
                System.out.println("generations= ["+g+"]");
                System.out.println("Speed = ["+s+"]");
                System.out.println("population= ["+p+"]");
                System.out.println("Tablero Inicial: ");
                tablero1.imprimirTablero();
                System.out.println("----------------------------------");
                tablero1.PoblacionInicial(p);
                tablero1.evolucionarAuto(g,s);

            }
            else{
                System.out.println("Validacion incorrecta, ingrese de nuevo los parametros ... ");
            }
        }
        else{
            System.out.println("No se proporcionaron suficientes argumentos...");
        }

    }
}