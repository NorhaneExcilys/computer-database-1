package test.java.dao;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.excilys.computer.database.dao.DAOComputer;
import main.java.com.excilys.computer.database.modele.Computer;
import test.java.database.TestDatabaseActions;

public class DAOComputerTest {

	@Before
	public void setUp() throws Exception {
		TestDatabaseActions testDatabaseActions = TestDatabaseActions.getInstance();
		testDatabaseActions.initDatabaseComputer();
	}

	@After
	public void tearDown() throws Exception {
		TestDatabaseActions testDatabaseActions = TestDatabaseActions.getInstance();
		testDatabaseActions.dropComputerDatabase();
	}
	
	@Test
	public void testGetAllComputer() {
		List<Computer> computers = DAOComputer.getInstance().getAllComputer();
		assertEquals(4, computers.size());
		
		Computer computer0 = new Computer(0, "MacBook Pro 15.4 inch", null, null, 1);
		assertEquals(computer0, computers.get(0));
		
		Computer computer2 = new Computer(2, "Monster Black", null, null, 3);
		assertEquals(computer2, computers.get(2));
	}

	@Test
	public void testGetSomeComputers() {
		List<Computer> computers = DAOComputer.getInstance().getSomeComputers(0, 2);
		assertEquals(2, computers.size());
		
		Computer computer0 = new Computer(0, "MacBook Pro 15.4 inch", null, null, 1);
		assertEquals(computer0, computers.get(0));
		
		Computer computer1 = new Computer(1, "MacBook retina", null, null, 1);
		assertEquals(computer1, computers.get(1));
	}

	@Test
	public void testAddComputer() {
		TestDatabaseActions testDatabaseActions = TestDatabaseActions.getInstance();
		int nbrTuplesModifie = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		assertEquals(4, testDatabaseActions.getNombre());
		
		Computer computer4 = new Computer("OrdiTest", null, null, 1);
		nbrTuplesModifie = DAOComputer.getInstance().addComputer(computer4);
		assertEquals(5, testDatabaseActions.getNombre());
		assertEquals(1, nbrTuplesModifie);
		
		Computer computer5 = new Computer("Test avec date valide", LocalDate.parse("13/02/1997", formatter), LocalDate.parse("13/05/2050", formatter), 1);
		nbrTuplesModifie = DAOComputer.getInstance().addComputer(computer5);
		assertEquals(6, testDatabaseActions.getNombre());
		assertEquals(1, nbrTuplesModifie);
		
		Computer computer6 = new Computer("Test qui doit retourner Exception avec mysql!", LocalDate.parse("13/02/0001", formatter), LocalDate.parse("13/05/1975", formatter), 1);
		nbrTuplesModifie = DAOComputer.getInstance().addComputer(computer6);
		assertEquals(7, testDatabaseActions.getNombre());
		assertEquals(1, nbrTuplesModifie);
	}

	@Test
	public void testRmComputer() {
		TestDatabaseActions testDatabaseActions = TestDatabaseActions.getInstance();
		int nbrTuplesModifie = 0;
		
		assertEquals(4, testDatabaseActions.getNombre());
		
		Computer computer2 = new Computer(2, "Monster Black", null, null, 3);
		nbrTuplesModifie = DAOComputer.getInstance().rmComputer(computer2);
		assertEquals(3, testDatabaseActions.getNombre());
		assertEquals(1, nbrTuplesModifie);
		
		Computer computer5 = new Computer(5, "Yolo", null, null, 10);
		nbrTuplesModifie = DAOComputer.getInstance().rmComputer(computer5);
		assertEquals(3, testDatabaseActions.getNombre());
		assertEquals(0, nbrTuplesModifie);
	}

	@Test
	public void testDetailComputer() {
		Computer computer2 = new Computer(2, "Monster Black", null, null, 3);
		assertEquals(computer2, DAOComputer.getInstance().detailComputer(2));
		
		assertEquals(null, DAOComputer.getInstance().detailComputer(5));
	}

	@Test
	public void testUpdateComputer() {
		int nbrTuplesModifie = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Computer computer2 = new Computer(2, "Yolo", null, null, 10);
		nbrTuplesModifie = DAOComputer.getInstance().updateComputer(computer2);
		assertEquals(1, nbrTuplesModifie);
		
		Computer computer3 = new Computer(3, "Test avec date donnee!", LocalDate.parse("13/02/0001", formatter), LocalDate.parse("13/05/1975", formatter), 1);
		nbrTuplesModifie = DAOComputer.getInstance().updateComputer(computer3);
		assertEquals(1, nbrTuplesModifie);
		
		Computer computer5 = new Computer(6, "Test avec date valide", LocalDate.parse("13/02/1997", formatter), LocalDate.parse("13/05/2050", formatter), 1);
		nbrTuplesModifie = DAOComputer.getInstance().updateComputer(computer5);
		assertEquals(0, nbrTuplesModifie);
	}
}