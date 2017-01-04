
import javafx.scene.control.Button;


/**
 *
 * @author Jayvee L. Villa
 */
class WordSound extends Button {
	private final Button a;
	private final Button e;
	private final Button i;
	private final Button o;
	private final Button u;
	
	public WordSound(String a, String e, String i, String o, String u) {
		this.a = new Button(a);
		this.e = new Button(e);
		this.i = new Button(i);
		this.o = new Button(o);
		this.u = new Button(u);
	}

	public Button getA() {
		return a;
	}

	public Button getE() {
		return e;
	}

	public Button getI() {
		return i;
	}

	public Button getO() {
		return o;
	}

	public Button getU() {
		return u;
	}
	
}
