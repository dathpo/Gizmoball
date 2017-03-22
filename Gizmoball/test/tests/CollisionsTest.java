package tests;

import org.junit.Test;
import java.awt.Color;

import model.Model;
import view.PlayGUI;
import model.Collisions;

public class CollisionsTest {
	
	Model model = new Model();
	Collisions col = new Collisions(0, null);
	
	@Test
	public void BallSpeedSmallerThanFriction() throws InterruptedException {
		model.addBall("Ball", 2, 2, 100, 100, Color.blue);
		model.addTriangleB("triangle", 2, 4, Color.blue);
		model.setFriction(102, 102);
		new PlayGUI(null, model);
		Thread.sleep(7000);
		
}
	@Test
	public void BallSpeedGreaterThanFriction() throws InterruptedException
	{
		model.addBall("Ball", 2, 2, 100, 100, Color.blue);
		model.addTriangleB("triangle", 2, 8, Color.blue);
		model.setFriction(1, 1);
		new PlayGUI(null, model);
		Thread.sleep(10000);
		
	}
	@Test
	public void BallWithNoFriction() throws InterruptedException
	{
		model.addBall("Ball", 2, 2, 100, 100, Color.blue);
		model.addTriangleB("triangle", 2, 8, Color.blue);
		model.setFriction(0, 0);
		new PlayGUI(null, model);
		Thread.sleep(7000);
		
	}
	@Test
	public void BallWithAbsorberNoFriction() throws InterruptedException
	{
		
		model.addBall("Ball", 2, 2, 100, 100, Color.blue);
		model.addAbsorber("Absorber", 2,4,8, 10, Color.BLUE);
		model.setFriction(0, 0);
		new PlayGUI(null, model);
		Thread.sleep(7000);
	}
	@Test
	public void BallBouncingBetweenGizmos() throws InterruptedException
	{
		model.addBall("Ball", 4, 8, 100, 100, Color.blue);
		model.addSquareB("square1",4 ,14 , Color.blue);
		model.addSquareB("square2", 4, 7, Color.green);
	new PlayGUI(null, model);
		Thread.sleep(7000);
	}
	@Test
	public void BallBouncingOnGizmo() throws InterruptedException
	{
		model.addBall("Ball", 4, 8, 100, 100, Color.blue);
		model.addSquareB("square1",4 ,14 , Color.blue);
		new PlayGUI(null, model);
			Thread.sleep(7000);
	}
}
