package com.example.basedatos_ejemplo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.TableViewMatchers;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class HelloAplicationTest {

    FXMLLoader fxmlLoader;
    Scene scene;


    @Start
    public void start(Stage stage) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1220, 740);
        stage.setTitle("Directorio");
        stage.setScene(scene);
        stage.show();
    }
    void validar (FxRobot robot) throws InterruptedException{
        robot.clickOn("#id");
        robot.write("1");
        robot.clickOn("#nombre");
        robot.write("Bulbasaur");
        robot.clickOn("#hp");
        robot.write("20");
        robot.clickOn("#ataque");
        robot.write("10");
        robot.clickOn("#defensa");
        robot.write("20");
        robot.clickOn("#ataquesp");
        robot.write("10");
        robot.clickOn("#defensasp");
        robot.write("20");
        robot.clickOn("#velocidad");
        robot.write("10");
        robot.clickOn("#dobletipo");
        robot.write("1");
        robot.clickOn("#einsertar");
        FxAssert.verifyThat(robot.window("Mensaje"), WindowMatchers.isShowing());
        robot.targetWindow("Mensaje");
        robot.sleep(1500);
        robot.type(KeyCode.ENTER);
        robot.sleep(1500);

        FxAssert.verifyThat("#TablaVista", TableViewMatchers.containsRow("Bulbasaur", 20, 10, 20, 10, 20 ,10, 1));
        robot.sleep(1500);

    }

}
