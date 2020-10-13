import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventDriver{
	
	public static class Exit implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent t) {
			System.exit(0);
		}
	}
	
	
	public static class DisplayRules implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent t) {
			Stage rulesStage = new Stage();
			rulesStage.setTitle("Rules of Keno");
			rulesStage.setMinHeight(300);
			rulesStage.setMinWidth(1000);
			
			Button returnButton = new Button("Return to Menu");
			
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					rulesStage.close();
				}
			});
			
			Text rulesText = new Text();
			
			rulesText.setText(" \n" + 
					"1. Decide how much to play per draw.\n" + 
					"2. Select how many consecutive draws to play. Pick up to 4.\n" + 
					"3. Select how many numbers to match, either 1, 4, 8, or 10. In Keno, these are called Spots.\n" +
					"   The number of Spots you choose and the amount you play per draw will determine the amount you could win.\n"+
					"   See the odds chart to look at the chances of winning.\n" + 
					"4. Pick as many numbers as you did Spots.\n" +
					"5. Play and see if you won :)"
					);
			rulesText.setFont(Font.font("Calibri",FontWeight.NORMAL,20));
			
			BorderPane bp = new BorderPane();
			bp.setCenter(rulesText);
			BorderPane.setAlignment(returnButton,Pos.BOTTOM_CENTER);
			bp.setBottom(returnButton);
			
			Scene scene = new Scene(bp);
			scene.setFill(Color.WHEAT);
			rulesStage.setScene(scene);
			rulesStage.show();
		}
	}
	
	public static class DisplayOdds implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent t) {
			Stage oddsStage = new Stage();
			oddsStage.setTitle("Rules of Keno");
			oddsStage.setMinHeight(210);
			oddsStage.setMinWidth(210);
			
			Button returnButton = new Button("Return to Menu");
			
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					oddsStage.close();
				}
			});
			
			Text oddsText = new Text();
			
			oddsText.setText(" \n" + 
					"Odds: \n" + 
					"   Choose 1: 1 in 4.00\n" + 
					"   Choose 4: 1 in 3.86\n" +
					"   Choose 8: 1 in 6.53\n" +
					"   Choose 10: 1 in 9.05"
					);
			oddsText.setFont(Font.font("Calibri",FontWeight.NORMAL,20));
			
			BorderPane bp = new BorderPane();
			bp.setCenter(oddsText);
			BorderPane.setAlignment(returnButton,Pos.BOTTOM_CENTER);
			bp.setBottom(returnButton);
			
			Scene scene = new Scene(bp);
			oddsStage.setScene(scene);
			oddsStage.show();
			
		}
		
	}


}
