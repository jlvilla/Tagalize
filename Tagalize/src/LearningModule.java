
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.image.Image;

/**
 *
 * @author Jayvee L. Villa
 */
class LearningModule {
        private Main main;
	private LearningModuleController controller;
	private VoiceManager voiceManager;
	private Voice helloVoice;
	private final Image playIcon = new Image(LearningModuleController.class.getResourceAsStream("play.png"));   
	//private final Image playIcon_small = new Image(Lesson1Controller.class.getResourceAsStream("/img/play_small.png"));

	public LearningModule() throws Exception {
		main = null;
		controller = null;
		voiceManager = VoiceManager.getInstance();
		helloVoice = voiceManager.getVoice("kevin16");
	}

	public Image getPlayicon() {
		return playIcon;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public VoiceManager getVoiceManager() {
		return voiceManager;
	}

	public Voice getHelloVoice() {
		return helloVoice;
	}

	public void initialize() throws Exception {
		controller = (LearningModuleController) main.changeSceneContent("LearningModule.fxml");
		controller.setMain(main);
		controller.setLearningModule(this);
	}
}	