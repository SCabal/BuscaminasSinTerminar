/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solución laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package modelo;

import java.util.Random;


public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida();

	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {
		if(this.nivel == 1) {
			casillas = new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
		}
		if(this.nivel == 2) {
			casillas = new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
		}
		if(this.nivel == 3) {
			casillas = new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];
		}
		inicializarCasillasLibres();
		generarMinas();
		for(int i= 0; i < casillas.length; i++) {
			for(int j= 0; j < casillas[i].length;j++) {
				int x = cantidadMinasAlrededor(i, j);
				casillas[i][j].modificarValor(x);
			}
		}
	}


	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		if(nivel == 1) {
			for(int i = 0; i < FILAS_PRINCIPIANTE; i++){
	            for(int j = 0; j < COLUMNAS_PRINCIPIANTE; j++){
	                casillas[i][j]= new Casilla(50);
	                
	            }
	        }
		}
		if(nivel == 2) {
			for(int i = 0; i < FILAS_INTERMEDIO; i++){
	            for(int j = 0; j < COLUMNAS_INTERMEDIO; j++){
	          casillas[i][j]= new Casilla(50);
	            }
	        }
		}
		if(nivel == 3) {
			for(int i = 0; i < FILAS_EXPERTO; i++){
	            for(int j = 0; j < COLUMNAS_EXPERTO; j++){
	            	casillas[i][j]= new Casilla(50);
	            }
	        }
		}		
	}


	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j)  {
		int valorContadorSoldador = 0;
		if(i==0 && j==0) {
			if(casillas[i][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}	
		}
		else if(i==0 && j==casillas[i].length-1) {
			if(casillas[i][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}
		}
		else if(i==casillas.length-1 && j== 0) {
			if(casillas[i-1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j+1].esMina()) {
				valorContadorSoldador++;
			}
		}
		else if(i==casillas.length-1 && j==casillas[i].length-1) {
			if(casillas[i-1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j-1].esMina()) {
				valorContadorSoldador++;
			}
		}
		else if(i==0) {
			if(casillas[i][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j+1].esMina()) {
				valorContadorSoldador++;
			}	
		}
		else if(j==0) {
			if(casillas[i-1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j+1].esMina()) {
				valorContadorSoldador++;
			}	
		}
		else if(i==casillas.length-1) {
				if(casillas[i][j-1].esMina()) {
					valorContadorSoldador++;
				}
				if(casillas[i][j+1].esMina()) {
					valorContadorSoldador++;
				}
				if(casillas[i-1][j-1].esMina()) {
					valorContadorSoldador++;
				}
				if(casillas[i-1][j].esMina()) {
					valorContadorSoldador++;
				}
				if(casillas[i-1][j+1].esMina()) {
					valorContadorSoldador++;
				}
		}
		else if(j==casillas[i].length-1) {
			if(casillas[i-1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j-1].esMina()) {
				valorContadorSoldador++;
			}	
		}
		else {
			if(casillas[i-1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j-1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i+1][j+1].esMina()) {
				valorContadorSoldador++;
			}	
			if(casillas[i][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j+1].esMina()) {
				valorContadorSoldador++;
			}
			if(casillas[i-1][j].esMina()) {
				valorContadorSoldador++;
			}
		}
		
		return valorContadorSoldador;
	}

	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {
		Random random = new Random(); 
		int cantidadMinas = 0;
		if(nivel == 1) {
			while(cantidadMinas < CANTIDAD_MINAS_PRINCIPANTE) {
				int numFila = random.nextInt(FILAS_PRINCIPIANTE);
				int numColumna = random.nextInt(COLUMNAS_PRINCIPIANTE);
				if(!casillas[numFila][numColumna].esMina()) {
					casillas[numFila][numColumna].setTipo(Casilla.MINA);
					cantidadMinas++;
				}
			}
		}
		if(nivel == 2) {
			while(cantidadMinas < CANTIDAD_MINAS_INTERMEDIO) {
				int numFila = random.nextInt(FILAS_INTERMEDIO);
				int numColumna = random.nextInt(COLUMNAS_INTERMEDIO);
				if(!casillas[numFila][numColumna].esMina()) {
					casillas[numFila][numColumna].setTipo(Casilla.MINA);
					cantidadMinas++;
				}
			}
		}
		if(nivel == 3) {
			while(cantidadMinas < CANTIDAD_MINAS_EXPERTO) {
				int numFila = random.nextInt(FILAS_EXPERTO);
				int numColumna = random.nextInt(COLUMNAS_EXPERTO);
				if(!casillas[numFila][numColumna].esMina()) {
					casillas[numFila][numColumna].setTipo(Casilla.MINA);
					cantidadMinas++;
				}
			}
		}
		
	}


	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {
		String salida = "";
        for(int i = 0; i < casillas.length; i++){
            for(int j = 0; j < casillas[i].length; j++){
                salida += casillas[i][j].mostrarValorCasilla()+ " ";      
            }
            salida += "\n";
        }
		return salida;
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {
		for(int i = 0; i < casillas.length; i++) {
			for(int j = 0; j < casillas[i].length;j++) {
				abrirCasilla(i, j);
			}
		}
	}

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {
		if(casillas[i][j].esMina()) {
			perdio = true;
		}
		if(casillas[i][j].darSeleccionada()) {
			return false;
		}
		casillas[i][j].destapar();
		return true;
	}


	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		int contador = 0;
		for(int i = 0; i < casillas.length;i++) {
			for(int j = 0; j< casillas[i].length; j++) {
				if(casillas[i][j].darSeleccionada()) {
					contador++;					
				}
			}
		}
		
		if(nivel == 1) {
			if(contador == (FILAS_PRINCIPIANTE*COLUMNAS_PRINCIPIANTE)-CANTIDAD_MINAS_PRINCIPANTE) {
				return true;
			}
			
		}
		if(nivel == 2) {
			if(contador == (FILAS_INTERMEDIO*COLUMNAS_INTERMEDIO)-CANTIDAD_MINAS_INTERMEDIO) {
				return true;
			}
			
		}
		if(nivel == 3) {
			if(contador == (FILAS_EXPERTO*COLUMNAS_EXPERTO)-CANTIDAD_MINAS_EXPERTO) {
				return true;
			}
			
		}
		return false;
	}


	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 */
	public String darPista() throws ExceptionPistaNoEncontrada{
		for(int i = 0 ; i < casillas.length; i++) {
			for(int j = 0; j < casillas[i].length; j++) {
				if(casillas[i][j].darValor() != 0 && !casillas[i][j].esMina() && !casillas[i][j].darSeleccionada()){
					casillas[i][j].destapar();
					return "Se destapo la casilla en la fila " +i+ " y en la columna "+j+ ".";
				}
			}
		}
		throw new ExceptionPistaNoEncontrada();
	}
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}

}