
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jayvee L. Villa
 */
class HomePageController extends AnchorPane implements Initializable {

	@FXML
	private Button startButton;

	@FXML
	private Button aboutButton;

	private Main main;
	private VoiceManager voiceManager;
	private Voice helloVoice;
	private Thread thread;

	public void initialize(URL arg0, ResourceBundle arg1) {
		voiceManager = VoiceManager.getInstance();
		helloVoice = voiceManager.getVoice("kevin16");
		System.out.println(voiceManager.getVoiceInfo());
		System.out.println(helloVoice);
		thread = new Thread() {
			public void run() {
				helloVoice.speak("Welcome to Tag-aa-lize!");
				helloVoice.speak("Tagalize is a tutoring system, that designed to give practice, in idiomatic Taa-ga-log conversation. by"
						+ "focusing exercises on simple language functions. The lessons are developed to enhace the interactional nature"
						+ "of communication.");
				helloVoice.speak(". . .Thank you for using Tagalize.");
			};
		};
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void introduction() {
		System.out.println("Intro");
		helloVoice.allocate();
		helloVoice.setDurationStretch(1.2f);
		thread.start();
	}

	@FXML
	@SuppressWarnings("deprecation")
	public void handleButton(ActionEvent action)
	{
		helloVoice.deallocate();
		thread.stop();
		if (action.getSource() == startButton)
			loadLearningModule();
		else if (action.getSource() == aboutButton)
			showAbout();
	}

	private void showAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.getDialogPane().setStyle("-fx-font-size: 20px;");
		alert.setTitle("About");
		alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("smallLogo.png"))));
		alert.setHeaderText("Tagalize: A Filipino Language Learning Tool");
		alert.setContentText("Version: 1.0 Full\n"
				+ "Developed by Jayvee L. Villa and Jinky D. Bernardo\n"
				+ "Copyright 2016");
		alert.initOwner(main.getStage());
		alert.showAndWait();
	}

	private void loadLearningModule() {
		main.goToLearningModule();
	}
}