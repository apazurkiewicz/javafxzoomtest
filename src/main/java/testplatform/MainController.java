package testplatform;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainController extends AnchorPane {

    private final Stage stage;
    private DoubleProperty translateX = new SimpleDoubleProperty(0);
    private DoubleProperty translateY = new SimpleDoubleProperty(0);
    private DoubleProperty scale = new SimpleDoubleProperty(1);


    private static final double SCALE_SCROLL_FACTOR = 1.111111;

    private double mouseDragStartX;
    private double mouseDragStartY;

    public MainController(Stage stage) {

        this.stage = stage;
        this.getStylesheets().add(Main.class.getResource("css/style.css").toString());
        Circle circle = new Circle(100);
        circle.setCenterX(500);
        circle.setCenterY(500);
        circle.getStyleClass().add("my-circle");
        Rotate rotateX = new Rotate(45,Rotate.X_AXIS);
        Rotate rotateY = new Rotate(45,Rotate.Y_AXIS);
        //circle.getTransforms().addAll(rotateX,rotateY);

        circle.translateXProperty().bind(translateX);
        circle.translateYProperty().bind(translateY);
        circle.scaleXProperty().bind(scale);
        circle.scaleYProperty().bind(scale);
        circle.scaleZProperty().bind(scale);

        getChildren().add(circle);



        // on mouse scroll zoom in or out
        setOnScroll(scrollEvent -> {
            double sf;
            double scrollStartX = scrollEvent.getX() - translateX.get();
            double scrollStartY = scrollEvent.getY() - translateY.get();

            if (scrollEvent.getDeltaY()>0) {
                sf = SCALE_SCROLL_FACTOR;
            } else {
                sf = Math.pow(SCALE_SCROLL_FACTOR,-1);
            }
            scale.set(scale.get() * sf);
            translateX.set(scrollEvent.getX() - scrollStartX * sf);
            translateY.set(scrollEvent.getY() - scrollStartY * sf);
        });

        // Save mouse position on mouse drag for drag distance calculation
        setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                this.mouseDragStartX = mouseEvent.getX() - translateX.get();
                this.mouseDragStartY = mouseEvent.getY() - translateY.get();
            }
        });

        // when mouse dragged is finished, set translate property
        setOnMouseDragged(mouseEvent -> {
            double dx = mouseEvent.getX() - mouseDragStartX;
            double dy = mouseEvent.getY() - mouseDragStartY;
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                     //dragging
                translateX.set(dx);
                translateY.set(dy);
            }
        });

    }
}
