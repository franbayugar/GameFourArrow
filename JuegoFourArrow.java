package practicoespecial;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JuegoFourArrow {

    final int MINFILACOL = 4;
    final int novalido = -1;
    final int numero_usuario = 1;
    final int minimo_ficha = 1;

    public void resolver() {
        //declaro valores iniciales
        int cantFilas = 0, cantCol = 0, modo_juego = 0, valor_jugador1 = 0, valor_jugador2 = 0, jugador = 1, fila_ficha = 0, comprobarGanador = 0, verificarTablero = 1;
        boolean finaldeljuego = false, columnavalida = false;
        //declaro dos char para identificar tipo de ficha
        char fichaRoja = 'R', fichaAmarilla = 'A';
        char[][] matriz;
        //se presenta el juego
        System.out.println("******BIENVENIDO AL JUEGO CUATRO EN LINEA******");
        System.out.println(" ");
        System.out.println("ES EL MOMENTO DE SELECCIONAR LAS DIMENSIONES DEL TABLERO!");
        //se pide cantidad de filas y columnas del tablero
        cantFilas = obtenerCantidadInicial("filas");
        cantCol = obtenerCantidadInicial("columnas");
        //se definen las dimensiones de la matriz
        matriz = new char[cantFilas][cantCol];
        //se carga la matriz de espacios vacíos
        cargarMatriz(matriz, cantFilas, cantCol);
        System.out.println(" ");
        System.out.println("******SELECCIONE UN MODO DE JUEGO******");
        //seleccionar Modo de juego
        modo_juego = obtenerModo();
        
        //comienza el juego
        System.out.println("******EL JUEGO HA COMENZADO******");
        if (modo_juego == 1) {
            System.out.println("******MODO DE JUEGO: JUGADOR CONTRA JUGADOR******");
        } else {
            System.out.println("******MODO DE JUEGO: JUGADOR CONTRA PC******");
        }
        System.out.println("******JUGADOR 1 JUEGA CON FICHAS ROJAS (R), JUGADOR 2 JUEGA CON FICHAS AMARILLAS (A)******");

        //se muestra la matriz al iniciar
        imprimirMatriz(matriz, cantFilas, cantCol);

        //ciclo principal del juego
        //mientras final del juego sea false se va a seguir ejecutando el programa
        while (!finaldeljuego) {
            //ciclo para corroborar que la columna que se va a ingresar no este llena
            while (!columnavalida) {
                System.out.println("******TURNO DE JUGADOR 1 (R)******");
                valor_jugador1 = obtenerNumeroColumna(jugador, cantCol);
                fila_ficha = insertar_ficha(matriz, valor_jugador1, cantFilas, fichaRoja);
                if (fila_ficha != novalido) {
                    columnavalida = true;
                } else {
                    System.out.println("La columna ingresada está completa. Intente otra vez");
                }
            }
            //en este punto debe comprobarse si el jugador gano o el juego termino
            comprobarGanador = verificarGanador(matriz, fila_ficha, valor_jugador1, cantFilas, cantCol, fichaRoja);
            verificarTablero = verificarTableroLleno(matriz, cantFilas, cantCol);

            //si el numero de fichas consecutivas es mayor o igual a 4 termina el juego
            if (comprobarGanador >= MINFILACOL) {
                finaldeljuego = true;
                System.out.println("Siiii, ganó el jugador 1!!!");
            }

            //en este punto se verifica que el tablero no este lleno y que algun jugador no haya ganado
            if (verificarTablero == 0 && (!finaldeljuego)) {
                finaldeljuego = true;
                System.out.println("TERMINÓ EL JUEGO, EMPATARON!");
            }

            //se muestra la matriz y se reinician valores
            imprimirMatriz(matriz, cantFilas, cantCol);
            columnavalida = false;
            fila_ficha = -1;

            //TURNO DEL SEGUNDO JUGADOR, MIENTRAS EL PRIMERO NO HAYA GANADO
            //si final de juego es false significa que el juego sigue y le toca al jugador 2 
            if (!finaldeljuego) {
                //ciclo para corroborar que la columna que se va a ingresar no este llena
                while (!columnavalida) {
                    System.out.println("******TURNO DE JUGADOR 2 (A)******");
                    valor_jugador2 = obtenerNumeroColumna(modo_juego, cantCol);
                    fila_ficha = insertar_ficha(matriz, valor_jugador2, cantFilas, fichaAmarilla);
                    if (fila_ficha != novalido) {
                        columnavalida = true;
                    } else {
                        System.out.println("La columna ingresada está completa. Intente otra vez");
                    }

                }
                //en este punto debe comprobarse si el jugador gano o el juego termino
                comprobarGanador = verificarGanador(matriz, fila_ficha, valor_jugador2, cantFilas, cantCol, fichaAmarilla);
                verificarTablero = verificarTableroLleno(matriz, cantFilas, cantCol);

                //si el numero de fichas consecutivas es mayor o igual a 4 termina el juego
                if (comprobarGanador >= MINFILACOL) {
                    finaldeljuego = true;
                    System.out.println("Siiii, ganó el jugador 2!!!");

                }
                //en este punto se verifica que el tablero no este lleno y que algun jugador no haya ganado
                if (verificarTablero == 0 && (!finaldeljuego)) {
                    finaldeljuego = true;
                    System.out.println("TERMINÓ EL JUEGO, EMPATARON!");
                }
                
                //se muestra la matriz y se reinician valores
                imprimirMatriz(matriz, cantFilas, cantCol);
                columnavalida = false;
                fila_ficha = -1;

            }
        }
    }
    //FIN DEL METODO RESOLVER

    
    //metodo para mostrar la matriz
    private void imprimirMatriz(char[][] matriz, int cantFilas, int cantCol) {
        for (int i = 0; i < cantFilas; i++) {
            for (int j = 0; j < cantCol; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println("");

        }
    }

    //cargar la matriz de espacios vacíos
    private void cargarMatriz(char[][] matriz, int cantFilas, int cantCol) {
        for (int i = 0; i < cantFilas; i++) {
            for (int j = 0; j < cantCol; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    //pedir al usuario cantidad de filas y columnas
    private int obtenerCantidadInicial(String dato) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int valor_ingresado = 0;
        boolean valorvalido = false;
        while (!valorvalido) {
            try {
                System.out.println("Por favor, ingrese el número de " + dato + ". (El número debe ser mayor o igual a " + MINFILACOL + ")");
                valor_ingresado = new Integer(in.readLine());
                if (valor_ingresado >= MINFILACOL) {
                    valorvalido = true;
                }
            } catch (Exception exc) {
                System.out.println("Error: " + exc);
            }
        }
        return valor_ingresado;
    }

    //pedirle al usuario un modo de juego
    private int obtenerModo() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int valor_ingresado = 0;
        boolean valorvalido = false;
        while (!valorvalido) {
            try {
                System.out.println("******PRESIONE 1 PARA JUGAR CONTRA OTRO JUGADOR O 2 PARA JUGAR CONTRA LA PC******");
                valor_ingresado = new Integer(in.readLine());
                if (valor_ingresado == 1 || valor_ingresado == 2) {
                    valorvalido = true;
                }
            } catch (Exception exc) {
                System.out.println("Error: " + exc);
            }
        }
        return valor_ingresado;
    }

    //se obtiene numero de columna
    private int obtenerNumeroColumna(int mododejuego, int maxcol) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int valor_ingresado = 0;
        boolean valorvalido = false;

        //en caso de que el valor de modo de juego sea igual al valor para que juegue el usuario, juega el usuario
        if (mododejuego == numero_usuario) {
            while (!valorvalido) {
                try {

                    System.out.println("Seleccione un número de columna donde quiere colocar su ficha (del 1 al " + maxcol + ")");
                    valor_ingresado = new Integer(in.readLine());
                    if (valor_ingresado <= maxcol && valor_ingresado > 0) {
                        valorvalido = true;
                    }

                } catch (Exception exc) {
                    System.out.println("Error: " + exc);
                }
            }
        }
        //de lo contrario, juega la PC y elige un numero aleatorio
        else {
            System.out.println("Por favor espere mientras juega la PC...");
            try {
                //de esta forma pasan 3 segundos hasta que juegue la PC, simulando que esta pensando
                Thread.sleep(3 * 1000);
                valor_ingresado = (int) (Math.random() * maxcol + 1);

            } catch (InterruptedException exc) {
                System.out.println("Error: " + exc);
            }
        }
        return valor_ingresado - 1;
    }

    //metodo para insertar la ficha en la matriz
    private int insertar_ficha(char[][] matriz, int columna, int maxfilas, char ficha) {
        boolean valorvalido = false;
        //se declara la varaible j y se le da el valor de maxfilas -1
        int j = maxfilas - 1;
        //a valor a retornar se le da el valor de novalido en caso de que la columna este llena
        int valor_retornar = novalido;
        //se recorre la columna cambiando el valor de la fila
        while (j >= 0 && (!valorvalido)) {
            //en caso de que el espacio este vacio se inserta la ficha que llega por parametro
            if (matriz[j][columna] == ' ') {
                matriz[j][columna] = ficha;
                //valor valido se convierte en true para salir del ciclo
                valorvalido = true;
                //a valor_retornar se le asigna el valor de la columna 
                valor_retornar = j;
            }
            j--;
        }
        //se retorna el valor de la columna para comprobar ganador o comprobar si la columna ingresada esta llena
        return valor_retornar;
    }

    // metodo para verificar al ganador
    private int verificarGanador(char[][] matriz, int num_fila, int columna, int maxfilas, int maxcol, char ficha) {
        int fichasiguales = minimo_ficha;

        /*LA FORMA ELEGIDA PARA BUSCAR, ES CREAR UNA VARIABLE FICHASIGUALES, 
        QUE COMIENZA CON EL VALOR DE 1, Y A MEDIDA QUE SE ENCUENTRAN FICHAS IGUALES EN LAS DISTINTAS DIRECCIONES
        AUMENTE SU VALOR*/
        
        //BUSQUEDA HORIZONTAL
        //buscar hacia la izquierda
        if (columna != 0) {
            fichasiguales += buscarIzquierda(matriz[num_fila], columna, ficha);
        }

        //buscar hacia la derecha
        if (columna != maxcol - 1) {
            fichasiguales += buscarDerecha(matriz[num_fila], columna, maxcol, ficha);
        }

        //en caso de que el numero de fichas iguales sea menor al objetivo para ganar se reinicia el valor
        //de la variable fichasiguales, dado que tiene que buscar en otra direccion y hay que contabilizar desde uno nuevamente
        if (fichasiguales < MINFILACOL) {
            fichasiguales = minimo_ficha;
        }

        //BUSQUEDA VERTICAL
        //buscar hacia abajo
        if (num_fila != maxfilas - 1 && fichasiguales<MINFILACOL) {
            fichasiguales += buscarAbajo(matriz, num_fila, columna, maxfilas, ficha);
        }

        //en caso de que el numero de fichas iguales sea menor al objetivo para ganar se reinicia el valor
        //de la variable fichasiguales, dado que tiene que buscar en otra direccion y hay que contabilizar desde uno nuevamente
        if (fichasiguales < MINFILACOL) {
            fichasiguales = minimo_ficha;
        }

        //BUSQUEDA DIAGONAL
        //buscar diagonal abajo derecha
        if (num_fila != maxfilas - 1 && columna != maxcol - 1 && fichasiguales<MINFILACOL) {
            fichasiguales += buscarDiagonalABD(matriz, num_fila, columna, maxfilas, maxcol, ficha);
        }

        //buscar diagonal arriba izquierda
        if (num_fila != 0 && columna != 0 && fichasiguales<MINFILACOL) {
            fichasiguales += buscarDiagonalARI(matriz, num_fila, columna, ficha);
        }

        //en caso de que el numero de fichas iguales sea menor al objetivo para ganar se reinicia el valor
        //de la variable fichasiguales, dado que tiene que buscar en otra direccion y hay que contabilizar desde uno nuevamente
        if (fichasiguales < MINFILACOL) {
            fichasiguales = minimo_ficha;
        }

        //buscar diagonal abajo izquierda
        if (num_fila != maxfilas - 1 && columna != 0 && fichasiguales<MINFILACOL) {
            fichasiguales += buscarDiagonalABI(matriz, num_fila, columna, maxfilas, ficha);
        }

        //buscar diagonal arriba derecha
        if (num_fila != 0 && columna != maxcol - 1 && fichasiguales<MINFILACOL) {
            fichasiguales += buscarDiagonalARD(matriz, num_fila, columna, maxcol, ficha);
        }

        //se retorna el valor de fichas iguales 
        return fichasiguales;
    }

    /*
     ********** METODOS DE BÚSQUEDA *************
     */
    //metodo para buscar hacia la izquierda
    private int buscarIzquierda(char[] array, int columna, char ficha) {
        int i = columna - 1;
        int equivalente = 0;
        while (i >= 0 && array[i] == ficha) {
            equivalente++;
            i--;
        }
        return equivalente;
    }

    //metodo para buscar hacia la derecha
    private int buscarDerecha(char[] array, int columna, int maxcol, char ficha) {
        int i = columna + 1;
        int equivalente = 0;
        while (i < maxcol && array[i] == ficha) {
            equivalente++;
            i++;
        }
        return equivalente;
    }

    //metodo para buscar hacia abajo
    private int buscarAbajo(char[][] matriz, int fila, int columna, int maxfilas, char ficha) {
        int equivalente = 0;
        int i = fila + 1;
        while (i < maxfilas && matriz[i][columna] == ficha) {
            equivalente++;
            i++;
        }
        return equivalente;
    }

    //metodo para buscar diagonal abajo derecha
    private int buscarDiagonalABD(char[][] matriz, int fila, int columna, int maxfilas, int maxcol, char ficha) {
        int equivalente = 0;
        int i = fila + 1;
        int j = columna + 1;
        while (i < maxfilas && j < maxcol && matriz[i][j] == ficha) {
            equivalente++;
            i++;
            j++;
        }

        return equivalente;
    }

    //metodo para buscar diagonal abajo izquierda
    private int buscarDiagonalABI(char[][] matriz, int fila, int columna, int maxfilas, char ficha) {
        int equivalente = 0;
        int i = fila + 1;
        int j = columna - 1;
        while (i < maxfilas && j >= 0 && matriz[i][j] == ficha) {
            equivalente++;
            i++;
            j--;
        }

        return equivalente;
    }

    //metodo para buscar diagonal arriba derecha
    private int buscarDiagonalARD(char[][] matriz, int fila, int columna, int maxcol, char ficha) {
        int equivalente = 0;
        int i = fila - 1;
        int j = columna + 1;
        while (i >= 0 && j < maxcol && matriz[i][j] == ficha) {
            equivalente++;
            i--;
            j++;
        }

        return equivalente;
    }

    //metodo para buscar diagonal arriba izquierda
    private int buscarDiagonalARI(char[][] matriz, int fila, int columna, char ficha) {
        int equivalente = 0;
        int i = fila - 1;
        int j = columna - 1;
        while (i >= 0 && j >= 0 && matriz[i][j] == ficha) {
            equivalente++;
            i--;
            j--;
        }

        return equivalente;
    }

    /**
     * *********** COMPROBAR QUE LA MATRIZ ESTE LLENA ******************
     */
    private int verificarTableroLleno(char[][] matriz, int cantFilas, int cantCol) {
        int contador = 0;
        for (int i = 0; i < cantFilas; i++) {
            for (int j = 0; j < cantCol; j++) {
                if (matriz[i][j] == ' ') {
                    contador++;
                    j = cantCol;
                    i = cantFilas;
                }
            }
        }
        return contador;
    }
}
