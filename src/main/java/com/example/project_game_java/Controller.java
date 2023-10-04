package com.example.project_game_java;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, player, enemy;

    @FXML
    private Label labelPause, labelLose;

    private final int BG_WIDTH = 713;
    private ParallelTransition parallelTransition;
    TranslateTransition enemyTransition;

    public static boolean right = false;
    public static boolean left = false;
    public static boolean jump = false;

    public static boolean isPause = false;
    private int playerSpeed = 3, jumpDownSpeed = 5;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (jump && player.getLayoutY() > 85f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
                else if(player.getLayoutY() <= 181f) {
                    jump = false;
                player.setLayoutY(player.getLayoutY() + jumpDownSpeed);
                }

            if (right && player.getLayoutX() < 580f)
                player.setLayoutX(player.getLayoutX() + playerSpeed);

            if (left && player.getLayoutX() > 40f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);

            if (isPause && !labelPause.isVisible()) {
                playerSpeed = 0;
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
                labelPause.setVisible(true);
            }
            else if (!isPause && labelPause.isVisible()) {
                playerSpeed = 3;
                jumpDownSpeed = 5;
                parallelTransition.play();
                enemyTransition.play();
                labelPause.setVisible(false);
            }
            if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                labelLose.setVisible(true);
                playerSpeed = 0;
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
            }
        }
    };


    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(-BG_WIDTH);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);


        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(-BG_WIDTH);
        bgTwoTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);

        enemyTransition = new TranslateTransition(Duration.millis(4000), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(-BG_WIDTH - 50);
        enemyTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();

        parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();

    }

}
