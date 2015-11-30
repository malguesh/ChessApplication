package ChessApplication;

//Connect 4 window that appears in each cell in the board

//imports required for this class
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

//class definition 
class Box extends Group {

	// constructor for the class Box
	public Box(Color color) {
		// make a new Rectangle and Translate, add the Translate to the Rectangle, add the Rectangle to the Group 
		square = new Rectangle();
		pos = new Translate();
		
		getChildren().addAll(square);
		square.getTransforms().add(pos);
		square.setFill(color);
	}

	// overridden version of the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the size of the square representing the box
		super.resize(width, height);
		System.out.println("lol");
		square.setWidth(width); square.setHeight(height);
	}

	// overridden version of the relocate method
//	@Override
//	public void relocate(double x, double y) {
//		// call the superclass method and update the relevant transform
//		super.relocate(x, y);
//		pos.setX(0);
//		pos.setY(0);		
//	}

	// private fields of the class
	private Rectangle square; 			// box for rendering this box
	private Translate pos; 		//translate to set the position of this box
}
