package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {


        public static void main(String[] args)
        {
            launch(args);
        }
        @Override
       /* public void start(Stage theStage)
        {
            theStage.setTitle("Hello, World!");
            theStage.show();
        }*/

    public void start(Stage theStage)
    {
        theStage.setTitle( "Click the Target!" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 500, 500 );

        root.getChildren().add( canvas );

        Circle targetData = new Circle(100,100,32);
        final int[] points = {0};

        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        if ( targetData.contains( e.getX(), e.getY() ) )
                        {
                            double x = 50 + 400 * Math.random();
                            double y = 50 + 400 * Math.random();
                            targetData.setCenterX(y);
                            targetData.setCenterY(y);
                            points[0]++;
                        }
                        else
                            points[0] = 0;
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Image bullseye = new Image( "bullseye.png" );

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
                gc.fillRect(0,0, 512,512);

                gc.drawImage( bullseye,
                        targetData.getCenterX() - targetData.getRadius(),
                        targetData.getCenterY() - targetData.getRadius() );

                gc.setFill( Color.BLUE );

                String pointsText = "Points: " + points[0];
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();

        theStage.show();
    }
    }


