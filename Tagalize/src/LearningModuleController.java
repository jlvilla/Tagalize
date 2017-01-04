
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jayvee L. Villa
 */
class LearningModuleController implements Initializable {

	/*
	 * Attributes
	 */

	@FXML
	private Hyperlink close, help;
	private List<Text> lessonDesc;
	private Main main;
	private File[] files;
	private String whatFile;
	private LearningModule learningModule;
	private Stage stage;
	private Parent root;

	//Text to speech
	private VoiceManager voiceManager;
	private Voice helloVoice;


	/*
	 * Operations
	 */

	public void initialize(URL arg0, ResourceBundle arg1) {
		whatFile = "";
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public void setLearningModule(LearningModule learningModule) {
		this.learningModule = learningModule;
		voiceManager = this.learningModule.getVoiceManager();
		helloVoice = this.learningModule.getHelloVoice();
	}
	public void setHelloVoice(Voice helloVoice) {
		this.helloVoice = helloVoice;
	}
	public void setVoiceManager(VoiceManager voiceManager) {
		this.voiceManager = voiceManager;
	}
	public void setClose(Hyperlink close) {
		this.close = close;
	}
	public File[] getFilesFor(String no,String what) {
		whatFile = what;
		String[] fileName = null;
		if (whatFile.equals("lessons")) {
			String[] filename = {
					("lesson"+no+"Dialog.txt").toString(),
					("lesson"+no+"Mini Dialogs 1.txt").toString(),
					("lesson"+no+"Mini Dialogs 2.txt").toString(),
					("lesson"+no+"Mini Dialogs 3.txt").toString()
			};
			fileName = filename;
		} else if (whatFile.equals("listen")) {
			String[] filename = {
					("lesson"+no+"Dialog Listen.txt").toString(),
					("lesson"+no+"Mini Dialogs 1 Listen.txt").toString(),
					("lesson"+no+"Mini Dialogs 2 Listen.txt").toString(),
					("lesson"+no+"Mini Dialogs 3 Listen.txt").toString()
			};
			fileName = filename;
		} else if (whatFile.equals("response")) {
			String[] filename = {
					("lesson"+no+"response1").toString(),
					("lesson"+no+"response2").toString(),
					("lesson"+no+"response3").toString(),
					("lesson"+no+"response4").toString()
			};
			fileName = filename;
		} else if (whatFile.equals("exercise")) {
			String[] filename = {
					("lesson"+no+"Grammar Exercise").toString(),
					("lesson"+no+"Vocabulary Exercise 1").toString(),
					("lesson"+no+"Writing Exercise").toString()
			};
			fileName = filename;
		} else if (whatFile.equals("Pronunciation")) {
			String[] filename = {
					("list.txt").toString(),
					("lesson"+no+"Sentence Drills").toString(),
					("lesson"+no+"Word Drills").toString()
			};
			fileName = filename;
		}
		files = new File[fileName.length];
		try {
			for (int i = 0; i < fileName.length; files[i] = new File(getClass().getResource(fileName[i]).toURI()), i++);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR, "Fatal Error", ButtonType.OK);
			a.close();
			System.exit(0);
		}
		return files;
	}
	public Image getPlayIcon() {
		return new Image(getClass().getResourceAsStream("play.png"));  
	}
	public ImageView getGraphicNode(String icon) {
		return new ImageView(new Image(getClass().getResourceAsStream(icon+".png")));
	}
	public Voice getHelloVoice() {
		return helloVoice;
	}
	public VoiceManager getVoiceManager() {
		return voiceManager;
	}
	public Hyperlink getClose() {
		return close;
	}

	/*
	 * FXML Functions
	 */

	@FXML
	public void handleStartLesson(ActionEvent e) throws IOException {
		Button button = (Button) e.getSource();
		stage  = new Stage(StageStyle.TRANSPARENT);
		root = FXMLLoader.load(getClass().getResource("Lesson "+Pattern.compile("[a-zA-Z]").matcher(button.getId()).replaceAll("")+".fxml"));
		stage.setScene(new Scene(root, 880,680, Color.TRANSPARENT));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(button.getScene().getWindow());
		stage.centerOnScreen();
		stage.showAndWait();
	}
	@FXML
	public void closeStage() {
		stage = (Stage) getClose().getScene().getWindow();
		stage.close();
	}
	@FXML
	public void showHelp() {
		System.out.println("help");
	}
	@FXML
	public void returnToHome() {
		main.returnToHome();
	}
}