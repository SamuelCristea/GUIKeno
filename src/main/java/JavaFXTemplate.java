import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class JavaFXTemplate extends Application {
	
	private MenuBar menu;
	private Button startButton;
	private GridPane kenoBoard;
	private int playSpots;
	private int numPlayTimes;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Keno!");
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(400);
		
		startButton = new Button("Start");
		
		startButton.setOnAction(new EventHandler<ActionEvent>( ) {
			public void handle(ActionEvent t) {
				getPlayNum(primaryStage);
			}
		});
			
		menu = new MenuBar();
		
		Menu mOne = new Menu("Menu");
		
		
		MenuItem exit = new MenuItem("Exit");
		MenuItem rules = new MenuItem("Rules");
		MenuItem odds = new MenuItem("Odds");
		exit.setOnAction(new EventDriver.Exit());
		
		rules.setOnAction(new EventDriver.DisplayRules());
		
		odds.setOnAction(new EventDriver.DisplayOdds());
		
		mOne.getItems().addAll(rules,odds,exit);
		
		menu.getMenus().add(mOne);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(startButton);
		bp.setTop(menu);
		Scene scene = new Scene(bp);
		scene.setFill(Color.WHEAT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void getPlayNum(Stage prevStage) {
		
		prevStage.setTitle("Configure Your Game!");
		prevStage.setMinHeight(600);
		prevStage.setMinWidth(600);
		
		menu = new MenuBar();
		
		Menu mOne = new Menu("Menu");
		
		
		MenuItem exit = new MenuItem("Exit");
		MenuItem rules = new MenuItem("Rules");
		MenuItem odds = new MenuItem("Odds");
		MenuItem newLook = new MenuItem("New Look");
		
		exit.setOnAction(new EventDriver.Exit());

		rules.setOnAction(new EventDriver.DisplayRules());

		odds.setOnAction(new EventDriver.DisplayOdds());

		
		newLook.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				newLook(prevStage);
			}
		});
		
		mOne.getItems().addAll(rules,odds,newLook,exit);
		
		menu.getMenus().add(mOne);
		
		kenoBoard = new GridPane();
		kenoBoard.setAlignment(Pos.CENTER);
		
		Button temp;
		
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 8; ++j) {
				temp = new Button(Integer.toString(1+i+(j*10)));
				temp.setDisable(true);
				kenoBoard.add(temp, i, j);
			}
		}
		
		BorderPane bp = new BorderPane();
		bp.setCenter(kenoBoard);
		bp.setTop(menu);
		Scene scene = new Scene(bp);
		scene.setFill(Color.WHEAT);
		prevStage.setScene(scene);
		prevStage.show();
		getGameInfo();
		beginGame(prevStage);			
		}
	
	public void beginGame(Stage board) {
		board.setTitle("Keno");
		
		int numSpotsLeftToChoose = this.playSpots;
		
		Label title = new Label("Click What Spots You Want to Play");
		
	}
	
	public void newLook(Stage board) {
		
	}
	
	public void getGameInfo() {
		Stage stage = new Stage();
		stage.setTitle("Enter Necessary Info");
		TextField entreLosOjos = new TextField();
		
		//the variable names above and below are a crappy reference to a movie called "Abre Los Ojos",
		//translated as "Open Your Eyes"
		Label losOjos = new Label("Enter the number of spots you want to play");
		
		entreLosOjos.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.ENTER)) {
					boolean isInt = isInteger(entreLosOjos.getText());
					if (isInt) {
						playSpots = Integer.parseInt(entreLosOjos.getText());
						System.out.println(playSpots);
					} else {
						errorBox(1);
						entreLosOjos.clear();
					}
					
					
					if (playSpots == 1 || playSpots == 4 || playSpots == 8 || playSpots == 10) {
						getGameInfoPt2();
						stage.close();
					} else {
						errorBox(2);
						entreLosOjos.clear();
					}
					
					// code to go over each elem in the girdpane, and we enable each button
					ObservableList<Node> childrens = kenoBoard.getChildren();
					
					for( Node node : childrens) {
						node.setDisable(false);
					}
				}
				
				
			}
			
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		grid.add(losOjos,0,1,1,1);
		grid.add(entreLosOjos,1,1);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(grid);
		Scene scene = new Scene(bp);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				closeConfirmationBox();
			}
		});
		stage.setScene(scene);
		stage.show();
	}
	
	public void getGameInfoPt2() {
		Stage stage = new Stage();
		stage.setTitle("Enter Necessary Info");
		TextField nuevoOjos = new TextField();
		Label dosOjos = new Label("Enter the number of times you want to play (Max 4 Min 1):");
		
		nuevoOjos.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.ENTER)) {
					boolean isInt = isInteger(nuevoOjos.getText());
					if (isInt) {
						numPlayTimes = Integer.parseInt(nuevoOjos.getText());
					} else {
						errorBox(1);
						nuevoOjos.clear();
					}
					
					if (numPlayTimes > 4 || numPlayTimes < 1) {
						errorBox(2);
						nuevoOjos.clear();
					} else {
						stage.close();
					}
					
					
				}
			}
		});
		
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		grid.add(dosOjos,0,1,1,1);
		grid.add(nuevoOjos,1,1);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(grid);
		Scene scene  = new Scene(bp);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent c) {
				closeConfirmationBox();
			}
		});
		
		stage.setScene(scene);
		stage.show();
	}
	
	//private helper function that lets us know if a string can be considered
	//an integer
	private static boolean isInteger(String s) {
		
		boolean valid = false;
		try {
			Integer.parseInt(s);
			valid = true;
		} catch (NumberFormatException ex) {
			
		}
		return valid;
	}
	
	//error box when the user has done goofed on an input
	//TODO: Ensure that all user inputs are correct through this
	private void errorBox(int errorType) {

		Stage errorStage = new Stage();
		errorStage.setTitle("Error");
		errorStage.setMinHeight(100);
		errorStage.setMinWidth(400);
		Text uDonGoofed = new Text();
		
		try {
	        File f = new File("peno.mp3");
	        Media hit = new Media(f.toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(hit);
	        mediaPlayer.play();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Exception: " + ex.getMessage());
	    }
		
		switch(errorType) {
		case (1) :
			uDonGoofed.setText("Invalid Entry. Try Again");
		case (2) :
			uDonGoofed.setText("Invalid Number. Try Again");
		}
		
		BorderPane bp = new BorderPane();
		bp.setCenter(uDonGoofed);
		Scene scene = new Scene(bp);
		errorStage.setScene(scene);
		errorStage.show();
	}
	
	//this pops open when the user clicks the big red X on the window
	//allows them to go back from closing the game if they do no want to
	private void closeConfirmationBox() {
		Stage confirmClose = new Stage();
		confirmClose.setTitle("Confirm");
		confirmClose.setMinHeight(200);
		confirmClose.setMinWidth(400);
		
		Text uSure = new Text("Are you sure? This will close the whole program");
		
		Button yes = new Button("Yes");
		Button no = new Button("No");
		
		yes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent y) {
				System.exit(0);
			}
		});
		
		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent n) {
				confirmClose.close();
			}
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		grid.add(uSure,0,1,2,1);
		grid.add(yes, 1, 0);
		grid.add(no, 1, 2);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(grid);
		Scene scene = new Scene(bp);
		confirmClose.setScene(scene);
		confirmClose.show();
		
	}
}
