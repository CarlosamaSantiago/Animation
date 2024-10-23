package icesi.com.animation;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private double posX;
    private double posY;
    private Rectangle rectangle1;
    private Rectangle rectangle2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphicsContext= canvas.getGraphicsContext2D();
        rectangle1 = new Rectangle(0, 50, 50,50);
        rectangle2 = new Rectangle(100, 50, 50,50);


        //Hilo
        new Thread(
                //Runable ---> lambda
                () -> {
                    while(true){
                        //Hilo de la libreria
                        Platform.runLater(
                                () -> {
                                    //Limpiar el canvas
                                    graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

                                    //Settear el color de un elemento
                                    graphicsContext.setFill(Color.BLUE);

                                    //Dibujar un elemento
                                    graphicsContext.fillRect(posX,rectangle1.getY() ,rectangle1.getWidth(), rectangle1.getHeight());
                                    //posicion en x, posicion en y, width, height

                                    graphicsContext.setFill(Color.RED);
                                    graphicsContext.fillRect(rectangle2.getX(), rectangle2.getY(), rectangle2.getWidth(), rectangle2.getHeight());

                                }
                        );

                        IDistance calc = (x1, x2, y1, y2) -> {
                            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                        };


                        double distance=calc.calculateDistance(rectangle1.getX(), rectangle2.getX(), rectangle1.getY(), rectangle2.getY());
                        System.out.println("Distance: "+calc.calculateDistance(rectangle1.getX(), rectangle2.getX(), rectangle1.getY(), rectangle2.getY()));

                        if(distance<20){

                            posY = rectangle1.getY()+5;
                            rectangle1.setY(posY);

                        }else{

                            posX= rectangle1.getX()+1;
                            rectangle1.setX(posX);

                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

    }
}