import model.Model;
import org.junit.jupiter.api.Test;
import view.PlayGUI;

import java.awt.*;

public class CollisionsTest {

    Model model = new Model();

    @Test
    public void BallSpeedSmallerThanFriction() {
        model.addBall("Ball", 2, 2, 100, 100, Color.blue);
        model.addTriangleB("triangle", 2, 4, Color.blue);
        model.setFriction(102, 102);
        new PlayGUI(null, model);
    }

    @Test
    public void BallSpeedGreaterThanFriction() {
        model.addBall("Ball", 2, 2, 100, 100, Color.blue);
        model.addTriangleB("triangle", 2, 8, Color.blue);
        model.setFriction(1, 1);
        new PlayGUI(null, model);
    }

    @Test
    public void BallWithNoFriction() {
        model.addBall("Ball", 2, 2, 100, 100, Color.blue);
        model.addTriangleB("triangle", 2, 8, Color.blue);
        model.setFriction(0, 0);
        new PlayGUI(null, model);
    }

    @Test
    public void BallWithAbsorberNoFriction() {
        model.addBall("Ball", 2, 2, 100, 100, Color.blue);
        model.addAbsorber("Absorber", 2, 4, 8, 10, Color.BLUE);
        model.setFriction(0, 0);
        new PlayGUI(null, model);
    }

    @Test
    public void BallBouncingBetweenGizmos() {
        model.addBall("Ball", 4, 8, 100, 100, Color.blue);
        model.addSquareB("square1", 4, 14, Color.blue);
        model.addSquareB("square2", 4, 7, Color.green);
        new PlayGUI(null, model);
    }

    @Test
    public void BallBouncingOnGizmo() {
        model.addBall("Ball", 4, 8, 100, 100, Color.blue);
        model.addSquareB("square1", 4, 14, Color.blue);
        new PlayGUI(null, model);
    }
}
