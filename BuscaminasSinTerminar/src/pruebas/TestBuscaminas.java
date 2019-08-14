package pruebas;
import modelo.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBuscaminas {
	private Buscaminas buscaminas;
	public void escenario1() {
		buscaminas = new Buscaminas(1);
	}
	public void escenario2() {
		buscaminas = new Buscaminas(2);
	}
	@Test
	void testInicializarBuscaminas() {
		escenario1();
		int numeroMinas = 0, numeroLibre = 0;
		Casilla[][] busca = buscaminas.darCasillas();
		for (int i = 0; i < busca.length; i++) {
			for (int j = 0; j < busca[i].length; j++) {
				if(busca[i][j].esMina()) numeroMinas++;
				if(!busca[i][j].esMina()) numeroLibre++;
			}
		}
		assertTrue(numeroMinas == 10);
		assertTrue(numeroLibre == 54);
	}
	
	@Test
	void testCantidadMinasAlrededor() {
		escenario2();
		Casilla[][] busca = buscaminas.darCasillas();
		int cantidadMinasContadas = 0;
		if(busca[0][1].esMina()) cantidadMinasContadas++;
		if(busca[1][0].esMina()) cantidadMinasContadas++;
		if(busca[1][1].esMina()) cantidadMinasContadas++;
		int valor = buscaminas.cantidadMinasAlrededor(0, 0);
		assertTrue(cantidadMinasContadas == valor);
	}
	@Test 
	void testDarPista() {
		escenario1();
		boolean thrown = false;
		
		try {
			while(!thrown) {
				buscaminas.darPista();
			}
		} catch (Exception e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
	}
	@Test
	void testAbriCasilla() {
		escenario1();
		buscaminas.abrirCasilla(0, 0);
		boolean revisar = buscaminas.abrirCasilla(0, 0);
		assertTrue(!revisar);
	}
	@Test
	void testAbrirCasilla1() {
		escenario1();
		boolean revisar = buscaminas.abrirCasilla(0, 0); 
		assertTrue(revisar);
		
	}
	@Test
	void testGanoTrue() {
		escenario1();
		Casilla[][] busca = buscaminas.darCasillas();
		
		for (int i = 0; i < busca.length; i++) {
			for (int j = 0; j < busca[i].length ; j++) {
				if(!busca[i][j].esMina()) {
					buscaminas.abrirCasilla(i, j);
				}	
			}
		}
		assertTrue(buscaminas.gano());
	}
	@Test
	void testGanoFalse() {
		escenario2();
		buscaminas.abrirCasilla(0,3);
		boolean flag = true;
		Casilla[][] busca = buscaminas.darCasillas();
		for (int i = 0; i < busca.length && flag; i++) {
			for (int j = 0; j < busca[i].length && flag; j++) {
				if(busca[i][j].esMina()) {
					buscaminas.abrirCasilla(i, j);
					flag = false;
				}
			}
		}
		assertTrue(!buscaminas.gano());
		
	}
	@Test
	void testDarPerdioFalse() {
		escenario1();
		assertTrue(!buscaminas.darPerdio());
	}
	@Test
	void testDarPerdioTrue() {
		escenario2();
		boolean flag = true;
		Casilla[][] busca = buscaminas.darCasillas();
		for (int i = 0; i < busca.length && flag; i++) {
			for (int j = 0; j < busca[i].length && flag; j++) {
				if(busca[i][j].esMina()) {
					buscaminas.abrirCasilla(i, j);
					flag = false;
				}
			}
		}
		assertTrue(buscaminas.darPerdio());
	}
}
