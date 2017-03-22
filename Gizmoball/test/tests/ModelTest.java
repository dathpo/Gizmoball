package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.junit.Test;
import view.PlayGUI;
import model.Model;


import org.junit.Before;
 
public class ModelTest {
	

	Model model = new Model();
		

	@Before
	
	public void init() {
		
	}
	
	@Test
	
	public void addBall() throws InterruptedException {
		System.out.println("Is the Ball there?");
		assertNull(model.getBall());
		model.addBall("B0", 0, 0, 0, 0, Color.BLUE);
		assertNotNull(model.getBall());
	}
	
	@Test
		public void addSquareBumper() {
			System.out.println("\n Is the Square there?");
			if(model.canPlaceGizmo(0, 0) ){
			System.out.println("There are no Bumpers at the moment..." );
			model.addSquareB("S0", 0, 0, Color.BLUE);
			}
			else
				fail();
			
			assertNotNull(model.getBumpers());
				
		}
	
	

	@Test
	public void addTriangleBumper() {
		System.out.println("\n Is the Triangle there?");		
		model.addTriangleB("T0", 0, 0, Color.BLUE);
		assertNotNull(model.getBumpers());
			
	}
	@Test
	public void addAbsorber() {
		System.out.println("\n Is the Absorber there?");
		model.addAbsorber("A01", 0, 0, 0, 0, Color.BLUE);
		assertNotNull(model.getAbsorbers());
	}
		@Test
		
		public void overlappingAbsorber()
		{
			System.out.println("\n Overlapping absorber check");
			model.addAbsorber("A01", 0, 0, 0, 0, Color.BLUE);
			if((model.canPlaceAbsorber(0, 0, 0, 0)) == true ) {
				System.out.println("The Field is already occupied by absorber");
				}
				else
				fail();
			}

		@Test
		
		public void overlappingBumpers()
		{
			System.out.println("\n Overlapping Bumper check");
			model.addSquareB("S01", 0, 0, Color.blue);
			if((model.canPlaceGizmo(0, 0) )== false ) {
				System.out.println("The Field is already occupied by bumper");
				}
				else
				fail();
		}
		@Test 
		//addd more stuff HERE
		 public void keyConnect()
		 {
			System.out.println("\n Key connect check: ");
			
		 }
			
		@Test
		 
		public void LeftFlipper()
		{
			System.out.println("\n Left Flipper placing");
			model.addLFlipper("LF01", 0, 0, Color.blue);
			assertNotNull(model.getFlippers());
			
		}
		
		@Test
		
		public void RightFlipper()
		{
			System.out.println("\nRight flipper placing");
			model.addRFlipper("RF01", 0, 0, Color.blue);
			assertNotNull(model.getFlippers());
			
		}
		@Test
		
			public void DeleteBumper()
		{
			System.out.println("\n Deleting functions: ");
			model.addSquareB("S02", 0 , 0, Color.blue);
			assertNotNull(model.getBumpers());
			model.setDeleteMode(true);
			model.deleteGizmo("S02");
			model.clearArrays();
			if (model.getBumpers().isEmpty()){
				System.out.println("The Bumper was deleted!");
			}
			else 
				fail();
			
		}
		@Test 
		
		public void LoadFile() throws FileNotFoundException, IOException
		{ 
			System.out.println("\n");
			model.loadNewModel("Tunnel.txt");
			assertNotNull(model.getBall());
		}
		
			
			
		
		@Test
		public void GizmoConnection()
		{
			System.out.println("Gizmo Connection Test");
			model.addCircleB("C01", 3, 3, Color.BLUE);
			model.addCircleB("C02", 3, 4, Color.black);
			
			
		}
		@Test
		public void RotateGizmo() throws InterruptedException
		{	
			model.addTriangleB("T03", 2, 2, Color.blue);
			new PlayGUI(null, model);
			Thread.sleep(2500);
			model.rotateGizmo("T03");
			new PlayGUI(null, model);
			Thread.sleep(2500);
			model.getBumpers();
			
			
		}
		@Test
		public void setGravity()
		{
			model.setGravity(5);
			assertNotNull(model.getGravity());
		}
		
		
		
		
	}

	
	
	
