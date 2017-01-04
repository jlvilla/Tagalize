
import com.sun.speech.freetts.VoiceManager;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Tagalog;
import org.languagetool.rules.RuleMatch;


/**
 *
 * @author Hp
 */
class Lesson1Controller extends LearningModuleController implements Initializable {

	/*
	 * Attributes 
	 */

	// Tab *********************************************************************************
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab dialogVariationTab, grammarExerciseTab, miniDialog1Tab, miniDialog2Tab, miniDialog3Tab, pronunciationDrillsTab, selfAssessmentListTab, vocabularyExerciseTab, writingExerciseTab;
	//********************************************************

	// Text Area ***************************************************************************
	@FXML
	private TextArea dialogTextArea, miniDialog1TextArea, miniDialog2TextArea, miniDialog3TextArea;
	private List<TextArea> textAreaList;
	//********************************************************

	// Toggle Button ***********************************************************************
	@FXML
	private ToggleButton listenButton0, listenButton1, listenButton2, listenButton3;
	private List<ToggleButton> listenButton;
	//********************************************************

	// Mini Dialog 2 Practice **************************************************************
	@FXML
	private ToggleButton checkAnswer;
	@FXML
	private Text blank1, blank2, blank3, blank4, blank5;
	@FXML
	private Text choice1, choice2, choice3, choice4, choice5, choice6;
	@FXML
	private Hyperlink remove1, remove2, remove3, remove4, remove5;
	private List<String> miniDialog2DefaultBlank;
	private List<Text> miniDialog2BlankList, miniDialog2ChoiceList;
	private List<Hyperlink> miniDialog2RemoveList;
	//********************************************************

	// Mini Dialog 3 Practice **************************************************************
	@FXML
	private ToggleButton checkAnswerMD3;
	@FXML
	private Text blankMD3_1, blankMD3_2, blankMD3_3, blankMD3_4, blankMD3_5, blankMD3_6, blankMD3_7, choiceMD3_1, choiceMD3_2, choiceMD3_3, choiceMD3_4, choiceMD3_5, choiceMD3_6, choiceMD3_7, choiceMD3_8;
	@FXML
	private Hyperlink removeMD3_1, removeMD3_2, removeMD3_3, removeMD3_4, removeMD3_5, removeMD3_6, removeMD3_7;
	private List<String> miniDialog3DefaultBlank;
	private List<Text> miniDialog3BlankList, miniDialog3ChoiceList;
	private List<Hyperlink> miniDialog3RemoveList;
	//********************************************************

	// Chatbox - Dialog Variation **********************************************************
	@FXML
	private ListView<String> chatBoxLV;	
	@FXML
	private TextField chatBoxTF;
	@FXML
	private Button sendButton;
	@FXML
	private CubicCurve firstGoal, secondGoal, thirdGoal;
	//********************************************************

	// Grammar Exercise ********************************************************************
	@FXML
	private TextField grammarExerciseTF1, grammarExerciseTF2, grammarExerciseTF3, grammarExerciseTF4, grammarExerciseTF5, grammarExerciseTF6, grammarExerciseTF7, grammarExerciseTF8, grammarExerciseTF9, grammarExerciseTF10;
	@FXML
	private Button grammarSubmitButton;
	private List<TextField> grammarExerciseTF;
	private List<String> grammarExerciseAnswer;
	//********************************************************

	// Vocabulary Exercise *****************************************************************
	@FXML
	private Text vocabularyExercise1Choice1, vocabularyExercise1Choice2, vocabularyExercise1Choice3, vocabularyExercise1Choice4, vocabularyExercise1Choice5, vocabularyExercise1Choice6, vocabularyExercise1Choice7, vocabularyExercise1Choice8, vocabularyExercise1Choice9, vocabularyExercise1Choice10;
	@FXML
	private Text vocabularyExercise1Blank1, vocabularyExercise1Blank2, vocabularyExercise1Blank3, vocabularyExercise1Blank4, vocabularyExercise1Blank5, vocabularyExercise1Blank6, vocabularyExercise1Blank7, vocabularyExercise1Blank8, vocabularyExercise1Blank9, vocabularyExercise1Blank10;
	@FXML
	private Hyperlink vocabularyExercise1Remove1, vocabularyExercise1Remove2, vocabularyExercise1Remove3, vocabularyExercise1Remove4, vocabularyExercise1Remove5, vocabularyExercise1Remove6, vocabularyExercise1Remove7, vocabularyExercise1Remove8, vocabularyExercise1Remove9, vocabularyExercise1Remove10;
	@FXML
	private ToggleButton vocabularyExercise2Choice1, vocabularyExercise2Choice2, vocabularyExercise2Choice3, vocabularyExercise2Choice4, vocabularyExercise2Choice5, vocabularyExercise2Choice6, vocabularyExercise2Choice7, vocabularyExercise2Choice8, vocabularyExercise2Choice9, vocabularyExercise2Choice10, vocabularyExercise2Choice11, vocabularyExercise2Choice12, vocabularyExercise2Choice13, vocabularyExercise2Choice14, vocabularyExercise2Choice15, vocabularyExercise2Choice16, vocabularyExercise2Choice17, vocabularyExercise2Choice18, vocabularyExercise2Choice19, vocabularyExercise2Choice20, vocabularyExercise2Choice21, vocabularyExercise2Choice22, vocabularyExercise2Choice23, vocabularyExercise2Choice24, vocabularyExercise2Choice25, vocabularyExercise2Choice26, vocabularyExercise2Choice27, vocabularyExercise2Choice28, vocabularyExercise2Choice29, vocabularyExercise2Choice30, vocabularyExercise2Choice31, vocabularyExercise2Choice32, vocabularyExercise2Choice33, vocabularyExercise2Choice34, vocabularyExercise2Choice35, vocabularyExercise2Choice36, vocabularyExercise2Choice37, vocabularyExercise2Choice38, vocabularyExercise2Choice39, vocabularyExercise2Choice40, vocabularyExercise2Choice41, vocabularyExercise2Choice42, vocabularyExercise2Choice43, vocabularyExercise2Choice44, vocabularyExercise2Choice45, vocabularyExercise2Choice46;
	@FXML
	private Button vocabularyExercise1Submit, vocabularyExercise2Submit;
	private List<ToggleGroup> vocabularyExercise2ToggleGroup;
	private List<ToggleButton> vocabularyExercise2Choice, vocabularyExercise2CorrectAnswer;
	private List<Text> vocabularyExercise1Choice, vocabularyExercise1Blank;
	private List<String> vocabularyExercise1DefaultBlank, vocabularyExercise1CorrectAnswer;
	private List<Hyperlink> vocabularyExercise1Remove;
	//********************************************************

	// Writing Exercise & LanguageTool Components ******************************************
	@FXML
	private TextField writingExerciseTF1, writingExerciseTF2, writingExerciseTF3, writingExerciseTF4, writingExerciseTF5;
	@FXML
	private Button writingExerciseSubmit;
	private List<TextField> writingExerciseTF;
	private JLanguageTool langTool;
	private List<RuleMatch> matchesLT;
	private List<String> writingExerciseCorrectAnswer;
	//********************************************************

	// Pronunciation Drills & Exercise and Sphinx-4 Components ******************************************
	@FXML
	private Button pronunciatonExerciseButton1, pronunciatonExerciseButton2, pronunciatonExerciseButton3, pronunciatonExerciseButton4, pronunciatonExerciseButton5, pronunciatonExerciseButton6, pronunciatonExerciseButton7, pronunciatonExerciseButton8, pronunciatonExerciseButton9, pronunciatonExerciseButton10, pronunciatonExerciseButton11, pronunciatonExerciseButton12, pronunciatonExerciseButton13, pronunciatonExerciseButton14, pronunciatonExerciseButton15, pronunciatonExerciseButton16, pronunciatonExerciseButton17, pronunciatonExerciseButton18, pronunciatonExerciseButton19, pronunciatonExerciseButton20, pronunciatonExerciseButton21, pronunciatonExerciseButton22, pronunciatonExerciseButton23, pronunciatonExerciseButton24, pronunciatonExerciseButton25, pronunciatonExerciseButton26, pronunciatonExerciseButton27, pronunciatonExerciseButton28, startPronunciationDrill;
	@FXML
	private Hyperlink pronunciatonExerciseHyperlink1, pronunciatonExerciseHyperlink2, pronunciatonExerciseHyperlink3, pronunciatonExerciseHyperlink4, pronunciatonExerciseHyperlink5, pronunciatonExerciseHyperlink6, pronunciatonExerciseHyperlink7;
	@FXML
	private TableView<WordSound> pronunciationTable;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn _a, _e, _i, _o, _u;
	private List<Button> pronunciatonExerciseButton;
	private List<Hyperlink> pronunciatonExerciseHyperlink;
	private List<String> sentenceDrill;
	private ObservableList<String> wordSound;

	private ConfigurationManager configManager;
	private Recognizer recognizer;
	private Microphone microphone;
	private Result result;

	private Alert drill;
	private List<String> wordDrill;
	private Boolean speechLoop;
	private String headerText, contentText;	
	//********************************************************

	// Self-Assessment List ****************************************************************
	@FXML
	private Pagination paginationGraph;
	@SuppressWarnings("rawtypes")
	private BarChart barChart;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	//********************************************************

	// Exercises Scores and Cues ***********************************************************
	private Integer grammarScore, vocabulary1Score, vocabulary2Score, writingScore, pronunciationScore;
	private Boolean dialogVariationFinished, grammarFinished, vocabulary1Finished, vocabulary2Finished, writingFinished, pronunciationFinished;
	//********************************************************

	// For Initializing Generic Content of Text Fields *************************************
	private Tooltip textAreTooltip;
	private File[] lesson1, lesson1listen, response, exercisesAnswer, pronunciationDrillWords;
	private Integer wordCount;
	private BufferedReader file;
	private List<List<String>> chop,responseList;
	private List<String> nativeVoice;
	private ChangeListener<String> selectedTextHandler;
	private Thread thread, threadListen;
	private AudioClip barNote;
	//********************************************************

	// Correct Answers and Score alert - Practice / Exercises (*****************************
	private final Alert scoreAlert = new Alert(AlertType.INFORMATION);
	private final String[] correctAnswers = {"Kumusta ka Jayvee", "Revie", "Magandang gabi din, John", "ka", "Ayos lang"};
	private final String[] correctAnswersMD3 = {"John", "stasyon ng pulis", "ospital", "lagnat", "party", "istadyum", "parke"};
	private final ObservableList<String> listItems = FXCollections.observableArrayList("Jinky: Magandang umaga."); // show in screen
	private final ObservableList<String> feed = FXCollections.observableArrayList("Kumusta ka?",":)", "Nasa bahay ako.", "Nasa trabaho sya."); // jinky response
	private final ObservableList<WordSound> soundDrill = FXCollections.observableArrayList(new WordSound("magandang", "kapeterya", "Binibining", "po'","mabuti"), new WordSound("umaga", "Reyes", "Ginoong", "hapon","umaga"), new WordSound("tanghali","berde","Ginang","Santos","kumusta"));
	//********************************************************

	/*
	 * Private Operations 
	 */

	// Initialize Generic Functions ********************************************************
	private void initializeTextArea() {
		textAreaList = new ArrayList<TextArea>();
		textAreaList.addAll(FXCollections.observableArrayList(dialogTextArea, miniDialog1TextArea, miniDialog2TextArea, miniDialog3TextArea));
		for (int i = 0; i < textAreaList.size(); i++) {
			textAreaList.get(i).setTooltip(textAreTooltip);
			textAreaList.get(i).selectedTextProperty().addListener(selectedTextHandler);
		}
	}
	private void initializeTextAreaContent() {
		try {
			for (int i = 0; i < lesson1listen.length; i++) {
				//Initialize all Text area's content
				for(file = new BufferedReader(new FileReader(lesson1[i])); file.ready(); textAreaList.get(i).setText(textAreaList.get(i).getText() + file.readLine() + "\n"));

				//Initialize all listen button's content
				List<String> in = new ArrayList<String>(); // List of words to say divided to list of strings to have a pause
				for (file = new BufferedReader(new FileReader(lesson1listen[i])); file.ready(); in.add(file.readLine())); //Read file then Loop its content
				chop.add(in); // Add it to the list
			}

			// Response
			for (int i = 0; i < response.length; i++) {
				List<String> in = new ArrayList<String>(); // List of phrases to validate
				for (file = new BufferedReader(new FileReader(response[i])); file.ready(); in.add(file.readLine()));
				responseList.add(in);
			}

			// Tagalog native pronunciation
			for (file = new BufferedReader(new FileReader(new File(Lesson1Controller.class.getResource("/data/voice/list.txt").toURI()))); file.ready(); nativeVoice.add(file.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private void initializeListenButton() {
		listenButton = new ArrayList<ToggleButton>();
		listenButton.addAll(FXCollections.observableArrayList(listenButton0, listenButton1, listenButton2, listenButton3));
		for (int i = 0; i < listenButton.size(); i++)
			listenButton.get(i).setGraphic(new ImageView(getPlayIcon()));
	}
	private void setListenButtonSelected(Boolean b) {
		for (int i = 0; i < listenButton.size(); i++)
			listenButton.get(i).setSelected(b);
	}
	private void deselectTextAreas() {
		for (int i = 0; i < textAreaList.size(); i++)
			textAreaList.get(i).deselect();
	}
	//********************************************************

	// Check Answers of Blank Fields Function **********************************************
	private void checkAnswer(String md) {
		if (md.equals("MD2")) { // If Mini Dialog 2
			for (int i = 0; i < miniDialog2RemoveList.size(); i++) {
				if (checkAnswer.isSelected()) {
					for (int j = 0; j < miniDialog2BlankList.size(); j++) {
						if (miniDialog2BlankList.get(j).getText().equals(correctAnswers[j]))
							miniDialog2BlankList.get(j).setFill(Color.GREEN);
						else
							miniDialog2BlankList.get(j).setFill(Color.RED);
					}
					break;
				} else {
					for (int j = 0; j < miniDialog2BlankList.size(); j++)
						miniDialog2BlankList.get(j).setFill(Color.BLACK);
				}
			}	
		} else if (md.equals("MD3")) { // If Mini Dialog 3
			for (int i = 0; i < miniDialog3RemoveList.size(); i++) {
				if (checkAnswerMD3.isSelected()) {
					for (int j = 0; j < miniDialog3BlankList.size(); j++) {
						if (miniDialog3BlankList.get(j).getText().equals(correctAnswersMD3[j]))
							miniDialog3BlankList.get(j).setFill(Color.GREEN);
						else
							miniDialog3BlankList.get(j).setFill(Color.RED);
					}
				} else {
					for (int j = 0; j < miniDialog3BlankList.size(); j++)
						miniDialog3BlankList.get(j).setFill(Color.BLACK);
				}
			}	
		}
	}
	//********************************************************

	// Show Scores Right After An Exercise *************************************************
	private void showScoreAlert(String title, Integer score, String wrongAnswer) {
		scoreAlert.setTitle(title);

		if (title.equals("Pronunciation Drills")) {
			scoreAlert.setHeaderText( score == 5 ? "Perfect!" : score == 4 ? "Excellent!" :	score == 3 ? "Not Bad" : "You need more practice.");
			scoreAlert.setGraphic(score == 5 ? getGraphicNode("Perfect") : score == 4 ? getGraphicNode("Excellent") : score  == 3 ? getGraphicNode("Not Bad") : getGraphicNode("Practice"));
			scoreAlert.setContentText("You got "+score+" out of 5.\n" + wrongAnswer);
		} else {
			scoreAlert.setHeaderText( score == 10 ? "Perfect!" : score < 10 && score > 7 ? "Excellent!" :
				score < 8 && score > 4 ? "Not Bad" : "You need more practice.");
			scoreAlert.setGraphic(score == 10 ? getGraphicNode("Perfect") :	score < 10 && score > 7 ? getGraphicNode("Excellent") :
				score < 8 && score > 4 ? getGraphicNode("Not Bad") : getGraphicNode("Practice"));
			scoreAlert.setContentText("You got "+score+" out of 10.\n" + wrongAnswer);
		}
		scoreAlert.showAndWait();
	}
	//********************************************************

	// Initialize Mini Dialog 2 Functions **************************************************
	private void initializeMiniDialog2() {
		initializeMiniDialog2BlankList();
		initializeMiniDialog2ChoiceList();
		initializeMiniDialog2RemoveList();
	}
	private void initializeMiniDialog2RemoveList() {
		miniDialog2RemoveList = new ArrayList<Hyperlink>();
		miniDialog2RemoveList.addAll(FXCollections.observableArrayList(remove1, remove2, remove3, remove4, remove5));
	}
	private void initializeMiniDialog2BlankList() {
		miniDialog2BlankList = new ArrayList<Text>();
		miniDialog2BlankList.addAll(FXCollections.observableArrayList(blank1, blank2, blank3, blank4, blank5));
		miniDialog2DefaultBlank = new ArrayList<String>();
		miniDialog2DefaultBlank.addAll(FXCollections.observableArrayList(blank1.getText(), blank2.getText(), blank3.getText(), blank4.getText(), blank5.getText()));
	}
	private void initializeMiniDialog2ChoiceList() {
		miniDialog2ChoiceList = new ArrayList<Text>();
		miniDialog2ChoiceList.addAll(FXCollections.observableArrayList(choice1, choice2, choice3, choice4, choice5, choice6));
	}
	//********************************************************

	// Initialize Mini Dialog 3 Functions **************************************************
	private void initializeMiniDialog3() {
		initializeMiniDialog3BlankList();
		initializeMiniDialog3ChoiceList();
		initializeMiniDialog3RemoveList();
	}
	private void initializeMiniDialog3RemoveList() {
		miniDialog3RemoveList = new ArrayList<Hyperlink>();
		miniDialog3RemoveList.addAll(FXCollections.observableArrayList(removeMD3_1, removeMD3_2, removeMD3_3, removeMD3_4, removeMD3_5, removeMD3_6, removeMD3_7));
	}
	private void initializeMiniDialog3BlankList() {
		miniDialog3BlankList = new ArrayList<Text>();
		miniDialog3BlankList.addAll(FXCollections.observableArrayList(blankMD3_1, blankMD3_2, blankMD3_3, blankMD3_4, blankMD3_5, blankMD3_6, blankMD3_7));
		miniDialog3DefaultBlank = new ArrayList<String>();
		miniDialog3DefaultBlank.addAll(FXCollections.observableArrayList(blankMD3_1.getText(), blankMD3_2.getText(), blankMD3_3.getText(), blankMD3_4.getText(), blankMD3_5.getText(), blankMD3_6.getText(), blankMD3_7.getText()));
	}
	private void initializeMiniDialog3ChoiceList() {
		miniDialog3ChoiceList = new ArrayList<Text>();
		miniDialog3ChoiceList.addAll(FXCollections.observableArrayList(choiceMD3_1, choiceMD3_2, choiceMD3_3, choiceMD3_4, choiceMD3_5, choiceMD3_6, choiceMD3_7, choiceMD3_8));
	}
	//********************************************************

	// Initialize Dialog Variation Functions ***********************************************
	private void initializeChatBox() {
		sendButton.setDisable(true);
		chatBoxTF.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> arg0, String arg1, String newValue) {
				sendButton.setDisable(newValue.isEmpty());
			}
		});
		chatBoxLV.setItems(listItems);
	}
	private void getReply() {
		if (responseList.size() != 0)
			for (int i = 0; i <responseList.get(0).size(); i++) {
				if (chatBoxTF.getText().trim().equalsIgnoreCase(responseList.get(0).get(i))) {
					listItems.add("Jinky: " + feed.get(0));

					if (feed.get(0).equals(":)"))
						firstGoal.setVisible(true);
					else if (feed.get(0).equals("Nasa bahay ako."))
						secondGoal.setVisible(true);
					else if (feed.get(0).equals("Nasa trabaho sya."))
						thirdGoal.setVisible(true);

					responseList.remove(responseList.get(0));
					feed.remove(feed.get(0));

					if (feed.size() == 0)
						getReply();
					break;
				}
			}
		else {
			listItems.add("Congratulations. You may proceed to the next section.\nPlease click the bottom left button having the next button.\nThen choose grammar exercise.");
			sendButton.setDisable(true);
			chatBoxTF.setDisable(true);
			dialogVariationFinished = true;
			handleTabSelection();
		}
	}
	//********************************************************

	// Initialize Grammar Exercise Function ************************************************
	private void initializeGrammarExercise() {
		grammarExerciseTF = new ArrayList<TextField>();
		grammarExerciseAnswer = new ArrayList<String>();
		grammarExerciseTF.addAll(FXCollections.observableArrayList(grammarExerciseTF1, grammarExerciseTF2, grammarExerciseTF3, grammarExerciseTF4, grammarExerciseTF5, grammarExerciseTF6, grammarExerciseTF7, grammarExerciseTF8, grammarExerciseTF9, grammarExerciseTF10));
		try {
			for(file = new BufferedReader(new FileReader(exercisesAnswer[0])); file.ready(); grammarExerciseAnswer.add(file.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//********************************************************

	// Initialize Vocabulary Exercise Functions ********************************************
	private void initializeVocabularyExercise() {
		initializeVocabularyExercise1();
		initializeVocabularyExercise2();
	}
	private void initializeVocabularyExercise1() {
		initializeVocabularyExercise1Choice();
		initializeVocabularyExercise1Blank();
		initializeVocabularyExercise1Remove();
		vocabularyExercise1DefaultBlank = new ArrayList<String>();
		vocabularyExercise1DefaultBlank.addAll(FXCollections.observableArrayList(vocabularyExercise1Blank1.getText(), vocabularyExercise1Blank2.getText(), vocabularyExercise1Blank3.getText(), vocabularyExercise1Blank4.getText(), vocabularyExercise1Blank5.getText(), vocabularyExercise1Blank6.getText(), vocabularyExercise1Blank7.getText(), vocabularyExercise1Blank8.getText(), vocabularyExercise1Blank9.getText(), vocabularyExercise1Blank10.getText()));
		vocabularyExercise1CorrectAnswer = new ArrayList<String>();
		try {
			for(file = new BufferedReader(new FileReader(exercisesAnswer[1])); file.ready(); vocabularyExercise1CorrectAnswer.add(file.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initializeVocabularyExercise1Remove() {
		vocabularyExercise1Remove = new ArrayList<Hyperlink>();
		vocabularyExercise1Remove.addAll(FXCollections.observableArrayList(vocabularyExercise1Remove1, vocabularyExercise1Remove2, vocabularyExercise1Remove3, vocabularyExercise1Remove4, vocabularyExercise1Remove5, vocabularyExercise1Remove6, vocabularyExercise1Remove7, vocabularyExercise1Remove8, vocabularyExercise1Remove9, vocabularyExercise1Remove10));
	}
	private void initializeVocabularyExercise1Blank() {
		vocabularyExercise1Blank = new ArrayList<Text>();
		vocabularyExercise1Blank.addAll(FXCollections.observableArrayList(vocabularyExercise1Blank1, vocabularyExercise1Blank2, vocabularyExercise1Blank3, vocabularyExercise1Blank4, vocabularyExercise1Blank5, vocabularyExercise1Blank6, vocabularyExercise1Blank7, vocabularyExercise1Blank8, vocabularyExercise1Blank9, vocabularyExercise1Blank10));
	}
	private void initializeVocabularyExercise1Choice() {
		vocabularyExercise1Choice = new ArrayList<Text>();
		vocabularyExercise1Choice.addAll(FXCollections.observableArrayList(vocabularyExercise1Choice1, vocabularyExercise1Choice2, vocabularyExercise1Choice3, vocabularyExercise1Choice4, vocabularyExercise1Choice5, vocabularyExercise1Choice6, vocabularyExercise1Choice7, vocabularyExercise1Choice8, vocabularyExercise1Choice9, vocabularyExercise1Choice10));
	}
	private void initializeVocabularyExercise2() {
		vocabularyExercise2Choice = new ArrayList<ToggleButton>();
		vocabularyExercise2Choice.addAll(FXCollections.observableArrayList(vocabularyExercise2Choice1, vocabularyExercise2Choice2, vocabularyExercise2Choice3, vocabularyExercise2Choice4, vocabularyExercise2Choice5, vocabularyExercise2Choice6, vocabularyExercise2Choice7, vocabularyExercise2Choice8, vocabularyExercise2Choice9, vocabularyExercise2Choice10, vocabularyExercise2Choice11, vocabularyExercise2Choice12, vocabularyExercise2Choice13, vocabularyExercise2Choice14, vocabularyExercise2Choice15, vocabularyExercise2Choice16, vocabularyExercise2Choice17, vocabularyExercise2Choice18, vocabularyExercise2Choice19, vocabularyExercise2Choice20, vocabularyExercise2Choice21, vocabularyExercise2Choice22, vocabularyExercise2Choice23, vocabularyExercise2Choice24, vocabularyExercise2Choice25, vocabularyExercise2Choice26, vocabularyExercise2Choice27, vocabularyExercise2Choice28, vocabularyExercise2Choice29, vocabularyExercise2Choice30, vocabularyExercise2Choice31, vocabularyExercise2Choice32, vocabularyExercise2Choice33, vocabularyExercise2Choice34, vocabularyExercise2Choice35, vocabularyExercise2Choice36, vocabularyExercise2Choice37, vocabularyExercise2Choice38, vocabularyExercise2Choice39, vocabularyExercise2Choice40, vocabularyExercise2Choice41, vocabularyExercise2Choice42, vocabularyExercise2Choice43, vocabularyExercise2Choice44, vocabularyExercise2Choice45, vocabularyExercise2Choice46));
		vocabularyExercise2CorrectAnswer = new ArrayList<ToggleButton>();
		vocabularyExercise2CorrectAnswer.addAll(FXCollections.observableArrayList(vocabularyExercise2Choice1, vocabularyExercise2Choice8, vocabularyExercise2Choice14, vocabularyExercise2Choice17, vocabularyExercise2Choice22, vocabularyExercise2Choice26, vocabularyExercise2Choice32, vocabularyExercise2Choice37, vocabularyExercise2Choice39, vocabularyExercise2Choice45));
		vocabularyExercise2ToggleGroup = new ArrayList<ToggleGroup>();
		for (int i = 0; i < 10; i++) {
			ToggleGroup group = new ToggleGroup();
			if (i == 0)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice1, vocabularyExercise2Choice2, vocabularyExercise2Choice3, vocabularyExercise2Choice4, vocabularyExercise2Choice5));
			else if (i == 1)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice6, vocabularyExercise2Choice7, vocabularyExercise2Choice8, vocabularyExercise2Choice9, vocabularyExercise2Choice10));
			else if (i == 2)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice11, vocabularyExercise2Choice12, vocabularyExercise2Choice13, vocabularyExercise2Choice14, vocabularyExercise2Choice15));
			else if (i == 3)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice16, vocabularyExercise2Choice17, vocabularyExercise2Choice18, vocabularyExercise2Choice19, vocabularyExercise2Choice20));
			else if (i == 4)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice21, vocabularyExercise2Choice22, vocabularyExercise2Choice23, vocabularyExercise2Choice24, vocabularyExercise2Choice25));
			else if (i == 5)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice26, vocabularyExercise2Choice27, vocabularyExercise2Choice28, vocabularyExercise2Choice29, vocabularyExercise2Choice30));
			else if (i == 6)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice31, vocabularyExercise2Choice32, vocabularyExercise2Choice33, vocabularyExercise2Choice34, vocabularyExercise2Choice35));
			else if (i == 7)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice36, vocabularyExercise2Choice37, vocabularyExercise2Choice38));
			else if (i == 8)
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice39, vocabularyExercise2Choice40, vocabularyExercise2Choice41, vocabularyExercise2Choice42));
			else
				group.getToggles().addAll(FXCollections.observableArrayList(vocabularyExercise2Choice43, vocabularyExercise2Choice44, vocabularyExercise2Choice45, vocabularyExercise2Choice46));
			vocabularyExercise2ToggleGroup.add(group);
		}
	}
	//********************************************************

	// Initialize Writing Exercise Function ************************************************
	private void intializeWritingExercise() {
		langTool = new JLanguageTool(new Tagalog());
		writingExerciseTF = new ArrayList<TextField>();
		writingExerciseTF.addAll(FXCollections.observableArrayList(writingExerciseTF1, writingExerciseTF2, writingExerciseTF3, writingExerciseTF4, writingExerciseTF5));
		writingExerciseCorrectAnswer = new ArrayList<String>();
		try {
			for(file = new BufferedReader(new FileReader(exercisesAnswer[2])); file.ready(); writingExerciseCorrectAnswer.add(file.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//********************************************************

	// Initialize Pronunciation Exercise Function ******************************************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void intializePronunciationExercise() {
		wordSound = FXCollections.observableArrayList();
		wordDrill = new ArrayList<String>();
		sentenceDrill = new ArrayList<String>();
		pronunciatonExerciseButton = new ArrayList<Button>();
		pronunciatonExerciseHyperlink = new ArrayList<Hyperlink>();
		pronunciatonExerciseButton.addAll(FXCollections.observableArrayList(pronunciatonExerciseButton1, pronunciatonExerciseButton2, pronunciatonExerciseButton3, pronunciatonExerciseButton4, pronunciatonExerciseButton5, pronunciatonExerciseButton6, pronunciatonExerciseButton7, pronunciatonExerciseButton8, pronunciatonExerciseButton9, pronunciatonExerciseButton10, pronunciatonExerciseButton11, pronunciatonExerciseButton12, pronunciatonExerciseButton13, pronunciatonExerciseButton14, pronunciatonExerciseButton15, pronunciatonExerciseButton16, pronunciatonExerciseButton17, pronunciatonExerciseButton18, pronunciatonExerciseButton19, pronunciatonExerciseButton20, pronunciatonExerciseButton21, pronunciatonExerciseButton22, pronunciatonExerciseButton23, pronunciatonExerciseButton24, pronunciatonExerciseButton25, pronunciatonExerciseButton26, pronunciatonExerciseButton27, pronunciatonExerciseButton28));
		pronunciatonExerciseHyperlink.addAll(FXCollections.observableArrayList(pronunciatonExerciseHyperlink1, pronunciatonExerciseHyperlink2, pronunciatonExerciseHyperlink3, pronunciatonExerciseHyperlink4, pronunciatonExerciseHyperlink5, pronunciatonExerciseHyperlink6, pronunciatonExerciseHyperlink7));

		configManager = new ConfigurationManager(Lesson1Controller.class.getResource("/data/main.config.xml"));
		recognizer = (Recognizer) configManager.lookup("recognizer");
		microphone = (Microphone) configManager.lookup("microphone");

		try {
			for(file = new BufferedReader(new FileReader(pronunciationDrillWords[0])); file.ready(); wordSound.add(file.readLine()));
			for(file = new BufferedReader(new FileReader(pronunciationDrillWords[1])); file.ready(); sentenceDrill.add(file.readLine()));
			for(file = new BufferedReader(new FileReader(pronunciationDrillWords[2])); file.ready(); wordDrill.add(file.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		_a.setCellValueFactory(new PropertyValueFactory("a"));
		_e.setCellValueFactory(new PropertyValueFactory("e"));
		_i.setCellValueFactory(new PropertyValueFactory("i"));
		_o.setCellValueFactory(new PropertyValueFactory("o"));
		_u.setCellValueFactory(new PropertyValueFactory("u"));
		pronunciationTable.setItems(soundDrill);
		for (int j = 0; j < soundDrill.size(); j++) {
			soundDrill.get(j).getA().setOnAction(pronunciatonExerciseButton1.getOnAction());
			soundDrill.get(j).getE().setOnAction(pronunciatonExerciseButton1.getOnAction());
			soundDrill.get(j).getI().setOnAction(pronunciatonExerciseButton1.getOnAction());
			soundDrill.get(j).getO().setOnAction(pronunciatonExerciseButton1.getOnAction());
			soundDrill.get(j).getU().setOnAction(pronunciatonExerciseButton1.getOnAction());
		}
	}
	//********************************************************

	// Initialize Self Assessment List Function ******************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeSelfAssessmentList() {
		String[] items = {"Total Score"};
		xAxis = new CategoryAxis();
		xAxis.setCategories(FXCollections.<String>observableArrayList(items));
		yAxis = new NumberAxis("Total Items", 0, 20, 20);
		ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
				new BarChart.Series("Grammar Exercise", FXCollections.observableArrayList(new BarChart.Data(items[0], grammarScore))),
				new BarChart.Series("Vocabulary Exercise", FXCollections.observableArrayList(new BarChart.Data(items[0], vocabulary1Score+vocabulary2Score))),
				new BarChart.Series("Writing Exercise", FXCollections.observableArrayList(new BarChart.Data(items[0], writingScore))),
				new BarChart.Series("Pronunciation Exercise", FXCollections.observableArrayList(new BarChart.Data(items[0], pronunciationScore)))
				);
		barChart = new BarChart(xAxis, yAxis, barChartData, 20);
		paginationGraph.setPageFactory(new Callback<Integer, Node>() {
			public Node call(Integer pageIndex) {
				VBox box = new VBox();
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(barChart);
				return box;
			}
		});
		xAxis.autosize();
		xAxis.autoRangingProperty().setValue(true);
		yAxis.autosize();
		yAxis.autoRangingProperty().setValue(true);
	}
	//********************************************************

	// Check grammar function for Writing Exercise *****************************************
	private Integer checkGrammar(String userAnswer, int answer) throws IOException {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.getDialogPane().setStyle("-fx-font-size: 20;");
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Writing Exercise");
		dialog.setHeaderText("Your answer is \"" + userAnswer + "\"");
		matchesLT = langTool.check(userAnswer);
		String wrongAnswer = "The correct answer is " + writingExerciseCorrectAnswer.get(answer) + "\n";
		for (RuleMatch match : matchesLT) 
		{
			wrongAnswer += "\nGrammar Error: " + userAnswer.substring(match.getFromPos(), match.getToPos());
			wrongAnswer += ("\nGrammar Issue: "+ (match.getMessage().startsWith("Posibleng may nahanap na mali sa ispeling") ? "Possible spelling mistake found." 
					: match.getMessage().startsWith("Lagyan ng espasiyo pagkatapos ng kuwit") ? "Put a space after the comma." 
							: match.getMessage().startsWith("Hindi nagsisimula ang pangungusap na ito sa malaking letra") ? "This sentence does not start with an uppercase letter."
									: match.getMessage().startsWith("Posibleng typo: naulit mo ang whitespace") ? "Possible typo: you repeated a whitespace."
											: match.getMessage().startsWith("Dalawang magkasunod na tuldok") ? "Two consecutive dots."
													: match.getMessage().startsWith("Maglagay ng espasyo pagkatapos ng kuwit, pero hindi bago ang kuwit") ? "Put a space after the comma, but not before the comma."
															: match.getMessage().startsWith("Lagyan ng espasiyo pagkatapos ng kuwit") ? "Put a space after the comma."
																	: match.getMessage().startsWith("Huwag lagyan ng espasyo bago ang tuldok") ? "Don't put a space before the full stop."
																			: match.getMessage().substring(0, match.getMessage().indexOf("?")+1) + "\n" +
																			match.getMessage().substring(match.getMessage().indexOf("?")+1).trim()));
			wrongAnswer += "\nSuggested correction: ";
			if (match.getSuggestedReplacements().size() == 1)
				wrongAnswer += match.getSuggestedReplacements().get(0);
			else
				for (int i = 0; i < match.getSuggestedReplacements().size(); i++)
					wrongAnswer += (i < match.getSuggestedReplacements().size()-1 ? match.getSuggestedReplacements().get(i)+", " : "and " + match.getSuggestedReplacements().get(i));
			wrongAnswer += "\n\n";
		}
		ScrollPane scroll = new ScrollPane(new Text(wrongAnswer));
		scroll.setMinWidth(300);
		dialog.getDialogPane().setContent(scroll);
		if (userAnswer.isEmpty())
			return 0; // No answer
		else if (matchesLT.isEmpty() && writingExerciseCorrectAnswer.get(answer).equalsIgnoreCase(userAnswer))
			return 2; // Grammatically correct and correct translation + +
		else if (!matchesLT.isEmpty() && !writingExerciseCorrectAnswer.get(answer).equalsIgnoreCase(userAnswer)) {
			dialog.showAndWait();
			return 0; // Grammatically wrong and incorrect translation - -
		}
		else if (matchesLT.isEmpty())
			return 1; // Grammatically correct but incorrect translation + -
		else {
			dialog.showAndWait();
			return 1; // Either grammatically wrong but correct translation - +
		}
	}
	//********************************************************

	/*
	 *  Public Operations
	 */

	// Initialize controller components ****************************************************
	public void initialize(URL arg0, ResourceBundle arg1) {

		scoreAlert.getDialogPane().setStyle("-fx-font-size: 20;");
		scoreAlert.initStyle(StageStyle.UTILITY);
		chop = new ArrayList<List<String>>();
		wordCount = -1;

		textAreTooltip = new Tooltip("Highlight the Tagalog word to hear its pronunciation.\nAvoid to highlight multiple lines/words.");
		grammarScore = 0;
		vocabulary1Score = 0;
		vocabulary2Score = 0;
		pronunciationScore = 0;
		writingScore = 0;
		dialogVariationFinished = grammarFinished = vocabulary1Finished = vocabulary2Finished = writingFinished = pronunciationFinished = false;

		lesson1 = getFilesFor("1","lessons");
		lesson1listen = getFilesFor("1","listen");
		response = getFilesFor("1","response");
		exercisesAnswer = getFilesFor("1", "exercise");
		pronunciationDrillWords = getFilesFor("1", "Pronunciation");
		responseList = new ArrayList<List<String>>();
		nativeVoice = new ArrayList<String>();
		selectedTextHandler = new ChangeListener<String>() {
			@SuppressWarnings("deprecation")
			public void changed(ObservableValue<? extends String> arg0, String arg1, final String newVal) {
				if (threadListen != null)
					threadListen.stop();
				threadListen = new Thread() {
					public void run() {
						setListenButtonSelected(false);
						setHelloVoice(getVoiceManager().getVoice("kevin16"));
						Boolean nativeDetected = false;
						getHelloVoice().allocate();
						for (int i = 0; i < wordSound.size(); i++) {
							if (wordSound.get(i).equalsIgnoreCase(newVal.trim().replaceAll("/", "").replaceAll("'", ""))) {
								// speak native
								try {
									System.out.println("Try");
									barNote = new AudioClip(getClass().getResource("/data/voice/"+nativeVoice.get(i)+".wav").toExternalForm());
								} catch (Exception e) {
									System.out.println("Catch it!");
									barNote = new AudioClip(getClass().getResource("/data/voice/"+nativeVoice.get(i)+".mp3").toExternalForm());
								}
								nativeDetected = true;
								break;
							}
						}
						if (!nativeDetected)
							getHelloVoice().speak(newVal);
						else {
							barNote.play();
							while (barNote.isPlaying())
								this.interrupt();
							this.resume();
						}
					}
				};
				threadListen.start();
			}
		};

		setVoiceManager(VoiceManager.getInstance());
		setHelloVoice(getVoiceManager().getVoice("kevin16"));

		getHelloVoice().allocate();
		getClose().setTooltip(new Tooltip("Close"));
		getClose().setShape(new Circle(1));

		initializeTextArea();
		initializeTextAreaContent();
		initializeListenButton();
		initializeMiniDialog2();
		initializeMiniDialog3();
		initializeChatBox();
		initializeGrammarExercise();
		initializeVocabularyExercise();
		intializeWritingExercise();
		intializePronunciationExercise();
	}
	//********************************************************

	// Closing the lesson ******************************************************************
	@SuppressWarnings("deprecation")
	@Override
	public void closeStage() {
		try {
			deselectTextAreas();
			thread.stop();
			threadListen.stop();
			thread = null;
			threadListen = null;
			recognizer = null;
			configManager = null;
			microphone = null;
			result = null;
			setHelloVoice(null);
		} catch(Exception e) {
			super.closeStage();
		}
		super.closeStage();
	}
	//********************************************************

	/* 
	 * FXML Functions 
	 */

	// Dialog Variation - Send of Chat Box *************************************************
	@FXML
	public void handleSendButton() {
		listItems.add("Me: " + chatBoxTF.getText());
		getReply();
		chatBoxLV.scrollTo(chatBoxLV.getItems().size());
		chatBoxTF.setText("");
		chatBoxTF.requestFocus();
	}
	//**************************************************************************************

	// Handles Listen ToggleButtons ********************************************************
	@FXML
	public void handleListenButton(ActionEvent toggle) {
		deselectTextAreas();
		final ToggleButton toggleId = (ToggleButton) toggle.getSource();
		if (!toggleId.isSelected())
			getHelloVoice().deallocate();
		thread = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				setHelloVoice(getVoiceManager().getVoice("kevin16"));
				Integer idNo = Integer.parseInt(""+toggleId.getId().charAt(toggleId.getId().length()-1));
				while (toggleId.isSelected()) {
					try {
						Boolean nativeDetected = false;
						getHelloVoice().allocate();
						wordCount++;
						for (int i = 0; i < nativeVoice.size(); i++) {
							if (chop.get(idNo).get(wordCount).equals(nativeVoice.get(i))) {
								// speak native
								try {
									System.out.println("Try");
									barNote = new AudioClip(Lesson1Controller.class.getResource("/data/voice/"+nativeVoice.get(i)+".wav").toExternalForm());
								} catch (Exception e) {
									System.out.println("Catch it!");
									barNote = new AudioClip(Lesson1Controller.class.getResource("/data/voice/"+nativeVoice.get(i)+".mp3").toExternalForm());
								}
								nativeDetected = true;
								break;
							}
						}
						if (!nativeDetected)
							getHelloVoice().speak(chop.get(Integer.parseInt(""+toggleId.getId().charAt(toggleId.getId().length()-1))).get(wordCount));
						else {
							barNote.play();
							while (barNote.isPlaying())
								this.interrupt();
							this.resume();
						}
						System.out.println("Speak: "+ chop.get(idNo).get(wordCount));
					} catch (Exception e) {
						toggleId.setSelected(false);
						wordCount = -1;
						break;
					}
				}
			}
		};
		thread.start();
	}
	//********************************************************

	// Submit Button From Exercises ********************************************************
	@FXML
	public void submitGrammarExercise() {
		String wrongAnswer = "";
		grammarSubmitButton.setDisable(true);
		for (int i = 0; i < grammarExerciseTF.size(); i++) {
			grammarExerciseTF.get(i).setDisable(true);
			if (grammarExerciseTF.get(i).getText().trim().equals(grammarExerciseAnswer.get(i)))
				grammarScore++;
			else
				wrongAnswer += (i+1) + ". "+ "Correct answer: " + grammarExerciseAnswer.get(i) + "\n";
		}
		showScoreAlert("Grammar Exercise", grammarScore, wrongAnswer);
		grammarFinished = true;
		handleTabSelection();
	}
	@FXML
	public void submitVocabularyExercise(ActionEvent e) {
		Button button = (Button) e.getSource();
		int number = Integer.parseInt(Pattern.compile("[a-zA-Z]").matcher(button.getId()).replaceAll(""));
		if (number == 1) {
			String wrongAnswer = "";
			vocabularyExercise1Submit.setDisable(true);
			for (int j = 0; j < vocabularyExercise1Blank.size(); j++)
				if (vocabularyExercise1Blank.get(j).getText().equals(vocabularyExercise1CorrectAnswer.get(j)))
					vocabulary1Score++;
				else
					wrongAnswer += (j+1) + ". "+ "Correct answer: " + vocabularyExercise1CorrectAnswer.get(j) + "\n";
			for (int i = 0; i < vocabularyExercise1Choice.size(); i++)
				vocabularyExercise1Choice.get(i).setDisable(true);
			for (int i = 0; i < vocabularyExercise1Remove.size(); i++)
				vocabularyExercise1Remove.get(i).setVisible(false);
			vocabulary1Finished = true;
			showScoreAlert("Vocabulary Exercise", vocabulary1Score, wrongAnswer);
			handleTabSelection();
		} else if (number == 2) {
			String wrongAnswer = "";
			vocabularyExercise2Submit.setDisable(true);
			for (int i = 0; i < vocabularyExercise2CorrectAnswer.size(); i++)
				if (vocabularyExercise2CorrectAnswer.get(i).isSelected())
					vocabulary2Score++;
				else
					wrongAnswer += (i+1) + ". "+ "Correct answer: " + vocabularyExercise2CorrectAnswer.get(i).getText() + "\n";
			for (int i = 0; i < vocabularyExercise2Choice.size(); i++)
				vocabularyExercise2Choice.get(i).setDisable(true);
			vocabulary2Finished = true;
			showScoreAlert("Vocabulary Exercise", vocabulary2Score, wrongAnswer);
			handleTabSelection();
		}
	}
	@FXML
	public void submitWritingExercise() throws IOException {
		String userAnswer = "";
		writingExerciseSubmit.setDisable(true);
		for (int i = 0; i < writingExerciseTF.size(); i++) {
			writingExerciseTF.get(i).setDisable(true);
			userAnswer = writingExerciseTF.get(i).getText().trim();
			writingScore += checkGrammar(userAnswer, i);
			System.out.println(writingScore);
		}
		showScoreAlert("Writing Exercise", writingScore, "");
		writingFinished = true;
		handleTabSelection();
	}
	@FXML
	public void pronunciationDrills() {
		headerText = "Speak the Tagalog word for the following english words:";
		contentText = "1.beautiful\n2.Reyes\n3.Ginang\n4.Po'\n5.Kumusta\n\nSpeak now.\nI'm listening...";
		drill = new Alert(AlertType.NONE);
		drill.getDialogPane().setStyle("-fx-font-size: 20;");
		drill.initStyle(StageStyle.UTILITY);
		drill.setTitle("Pronunciation Exercise");
		drill.setHeaderText(headerText);
		drill.setContentText(contentText);

		recognizer.allocate();
		recognizer.getState();

		ButtonType end = new ButtonType("End Exercise");
		thread = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				super.run();
				try{
					Thread.sleep(100);
				}catch (Exception e){}
				if (!microphone.startRecording()) {
					getHelloVoice().speak("Cannot start microphone.");
					contentText = ("Cannot start microphone.");
					try{
						Thread.sleep(100);
					}catch (Exception e){}
				} 
				else {
					try{
						Thread.sleep(100);
					}catch (Exception e){}
					speechLoop = true;
					while (speechLoop) {
						result = recognizer.recognize();
						if (result != null) {
							String resultText = result.getBestFinalResultNoFiller();
							System.out.println("Result Pronunce: " + result.getBestPronunciationResult());
							if (resultText.equalsIgnoreCase(wordDrill.get(0))) {
								contentText = (resultText);
								getHelloVoice().speak("Nice. You said, ");
								getPronunciaton(resultText);
								pronunciationScore++;
								wordDrill.remove(0);
								speechLoop = false;
								try{
									headerText = (nextWord());
								}catch (Exception e){
									thread.stop();
									drill.close();
								}
							} 
							else {
								contentText = ("Try again.");
								try{
									Thread.sleep(100);
								}catch (Exception e){}
							}
						}
						else {
							contentText = ("I can't hear what you said.");
							try{
								Thread.sleep(100);
							}catch (Exception e){}
						}
					}
				}
			}
		};
		thread.start();

		drill.getButtonTypes().setAll(end);
		drill.showAndWait(); 
		try {
			recognizer.deallocate();
		} catch(Exception e) {System.out.println("deallocate");}
		startPronunciationDrill.setDisable(true);
		pronunciationFinished = true;
		drill.close();
		showScoreAlert("Pronunciation Drills", pronunciationScore, "");
		intializePronunciationExercise();
		handleTabSelection();
	}
	protected void getPronunciaton(String resultText) {
		if (barNote != null)
			barNote.stop();
		for (int i = 0; i < wordSound.size(); i++)
			if (wordSound.get(i).equalsIgnoreCase(resultText.replaceAll("/", "").replaceAll("'", ""))) {
				int index = nativeVoice.indexOf(wordSound.get(i));
				barNote = new AudioClip(Lesson1Controller.class.getResource("/data/voice/"+nativeVoice.get(index)+".wav").toExternalForm());
				barNote.setVolume(100f);
				barNote.play();
				while (barNote.isPlaying()) {
					if (!barNote.isPlaying()) {
						barNote = new AudioClip(Lesson1Controller.class.getResource("/data/voice/"+nativeVoice.get(index)+".mp3").toExternalForm());
						barNote.setVolume(100f);
						barNote.play();
						break;
					}
				}
				System.out.println(wordSound.get(i) + ": "+index);
				break;
			}
	}
	private String nextWord() {
		headerText = ("Speak the Tagalog word for "+ 
				(wordDrill.get(0).equals("Ginang") ? "Mrs." :
					wordDrill.get(0).equals("po") ? "the respect particle" :
						wordDrill.get(0).equals("kumusta") ? "how are you?" : "Reyes")
						+ ".");

		recognizer.getState();

		speechLoop = true;
		while (speechLoop) {
			result = recognizer.recognize();
			if (result != null) {
				String resultText = result.getBestFinalResultNoFiller();
				System.out.println("Result Pronunce: " + result.getBestPronunciationResult());
				if (resultText.equalsIgnoreCase(wordDrill.get(0))) {
					contentText = (resultText);
					getHelloVoice().speak("Nice. You said, ");
					getPronunciaton(resultText);
					pronunciationScore++;
					wordDrill.remove(0);
					speechLoop = false;
					headerText = nextWord();
				} 
				else {
					System.out.println("Try again.");
				}
			}
			else {
				System.out.println("I can't hear what you said.");
			}
		}
		return headerText;
	}
	@FXML
	public void pronunciationDrillSound(ActionEvent e) {
		Button source = (Button) e.getSource();
		getPronunciaton(source.getText());
	}
	@FXML
	public void sentenceDrillSound(ActionEvent e) {
		Hyperlink source = (Hyperlink) e.getSource();
		System.out.println(source.getText());
		int visitedFromLeft = -1, visitedFromRight = -1;
		for (int i = 0; i < pronunciatonExerciseHyperlink.size(); i++) {
			if (pronunciatonExerciseHyperlink.get(i).isVisited()) // Is the hyperlink visited?
				if (i < 4 ) // Is the visited hyperlink is greeting? Then assigned it as left visited
					visitedFromLeft = i;
				else // Is the visited hyperlink a person? Then assigned it as right visited
					visitedFromRight = i;
		}
		int indexOfSource = pronunciatonExerciseHyperlink.indexOf(source);
		if (indexOfSource < 4) {// is the source index a greeting? then put it in the left
			visitedFromLeft = indexOfSource;
			getHelloVoice().speak(sentenceDrill.get(indexOfSource) + sentenceDrill.get(visitedFromRight));
		} else {// put it in the right
			visitedFromRight = indexOfSource;
			getHelloVoice().speak(sentenceDrill.get(visitedFromLeft) + sentenceDrill.get(indexOfSource));
		}		
		for (int i = 0; i < pronunciatonExerciseHyperlink.size(); i++)
			if (i == visitedFromLeft || i == visitedFromRight) // Reset visited hyperlinks except the left and right
				pronunciatonExerciseHyperlink.get(i).setVisited(true);
			else
				pronunciatonExerciseHyperlink.get(i).setVisited(false);
	}
	//********************************************************

	// Check Answers in Practice ***********************************************************	
	@FXML
	public void checkAnswer(ActionEvent toggle) {
		ToggleButton toggleId = (ToggleButton) toggle.getSource();
		if (toggleId == checkAnswer) {
			if (toggleId.isSelected())
				checkAnswer.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			else
				checkAnswer.setContentDisplay(ContentDisplay.TEXT_ONLY);
			checkAnswer("MD2");
		} else if (toggleId == checkAnswerMD3) {
			if (toggleId.isSelected())
				checkAnswerMD3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			else
				checkAnswerMD3.setContentDisplay(ContentDisplay.TEXT_ONLY);
			checkAnswer("MD3");
		}
	}
	//********************************************************

	// Displays User Manual ****************************************************************
	@FXML
	public void showHelp() {
		System.out.println("help");
	}
	//********************************************************

	// Remove Choices From Blanks **********************************************************
	@FXML
	public void removeAnswer(ActionEvent link) {
		Hyperlink hyperlink = (Hyperlink) link.getSource();
		if (miniDialog2RemoveList.contains(hyperlink)) {
			int number = miniDialog2RemoveList.indexOf(hyperlink);
			for (int j = 0; j < miniDialog2ChoiceList.size(); j++)  {
				if (miniDialog2BlankList.get(number).getText().equals(miniDialog2ChoiceList.get(j).getText())) {
					miniDialog2ChoiceList.get(j).setVisible(true);
					break;
				}
			}
			miniDialog2BlankList.get(number).setText(miniDialog2DefaultBlank.get(number));
			miniDialog2RemoveList.get(number).setVisible(false);
			checkAnswer("MD2");
		} else if (miniDialog3RemoveList.contains(hyperlink)) {
			int number = miniDialog3RemoveList.indexOf(hyperlink);
			for (int j = 0; j < miniDialog3ChoiceList.size(); j++)  {
				if (miniDialog3BlankList.get(number).getText().equals(miniDialog3ChoiceList.get(j).getText())) {
					miniDialog3ChoiceList.get(j).setVisible(true);
					break;
				}
			}
			miniDialog3BlankList.get(number).setText(miniDialog3DefaultBlank.get(number));
			miniDialog3RemoveList.get(number).setVisible(false);
			checkAnswer("MD3");
		} else if (vocabularyExercise1Remove.contains(hyperlink)) {
			int number = vocabularyExercise1Remove.indexOf(hyperlink);
			for (int j = 0; j < vocabularyExercise1Choice.size(); j++)  {
				if (vocabularyExercise1Blank.get(number).getText().equals(vocabularyExercise1Choice.get(j).getText())) {
					vocabularyExercise1Choice.get(j).setVisible(true);
					break;
				}
			}
			vocabularyExercise1Blank.get(number).setText(vocabularyExercise1DefaultBlank.get(number));
			vocabularyExercise1Remove.get(number).setVisible(false);
		}
	}
	//********************************************************

	// Handles tab selection ***************************************************************
	@FXML
	public void handleTabSelection() {
		deselectTextAreas();
		setListenButtonSelected(false);

		if (miniDialog1Tab.isSelected())
			miniDialog2Tab.setDisable(false);
		else if (miniDialog2Tab.isSelected())
			miniDialog3Tab.setDisable(false);
		else if (miniDialog3Tab.isSelected())
			dialogVariationTab.setDisable(false);
		else if (dialogVariationTab.isSelected() && dialogVariationFinished)
			grammarExerciseTab.setDisable(false);
		else if (grammarExerciseTab.isSelected() && grammarFinished)
			vocabularyExerciseTab.setDisable(false);
		else if (vocabularyExerciseTab.isSelected() && vocabulary1Finished && vocabulary2Finished)
			writingExerciseTab.setDisable(false);
		else if (writingExerciseTab.isSelected() && writingFinished)
			pronunciationDrillsTab.setDisable(false);
		else if (pronunciationDrillsTab.isSelected() && pronunciationFinished) {
			selfAssessmentListTab.setDisable(false);
			initializeSelfAssessmentList();
		}
		tabPane.requestFocus();
	}
	//********************************************************

	// Handles Drag Events *****************************************************************
	@FXML
	public void choiceDragDetected(MouseEvent event)
	{
		Text sourceText = (Text) event.getSource();
		String text = sourceText.getText();
		if (text == null || text.trim().equals(""))
		{
			event.consume();
			return;
		}

		Dragboard dragboard = sourceText.startDragAndDrop(TransferMode.COPY_OR_MOVE);

		ClipboardContent content = new ClipboardContent();
		content.putString(text);
		dragboard.setContent(content);
		event.consume();
	}
	@FXML
	public void dragDone(DragEvent event)
	{
		Text sourceText = (Text) event.getSource();
		TransferMode modeUsed = event.getTransferMode();
		if (modeUsed == TransferMode.MOVE)
			sourceText.setVisible(false);
		event.consume();
	}
	@FXML
	public void dragOver(DragEvent event)
	{
		Dragboard dragboard = event.getDragboard();

		Text sourceText = (Text) event.getSource();

		sourceText.setStrikethrough(true);
		if (dragboard.hasString() &&  sourceText.getText().startsWith("_"))
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		event.consume();
	}
	@FXML
	public void dragExited(DragEvent event)
	{
		Text sourceText = (Text) event.getSource();
		sourceText.setStrikethrough(false);
	}
	@FXML
	public void dragDropped(DragEvent event)
	{
		Text sourceText = (Text) event.getSource();
		String md = "";
		for (int i = 0; i < miniDialog2BlankList.size(); i++)
			if (sourceText ==  miniDialog2BlankList.get(i)) {
				miniDialog2RemoveList.get(i).setVisible(true);
				md = "MD2";
			}
		for (int i = 0; i < miniDialog3BlankList.size(); i++)
			if (sourceText ==  miniDialog3BlankList.get(i)) {
				miniDialog3RemoveList.get(i).setVisible(true);
				md = "MD3";
			}
		for (int i = 0; i < vocabularyExercise1Blank.size(); i++)
			if (sourceText == vocabularyExercise1Blank.get(i))
				vocabularyExercise1Remove.get(i).setVisible(true);

		Dragboard dragboard = event.getDragboard();

		if (dragboard.hasString())
		{
			sourceText.setText(dragboard.getString());
			checkAnswer(md);
			event.setDropCompleted(true);
		}
		else
		{
			event.setDropCompleted(false);
		}
		event.consume();
	}
	//********************************************************
}
