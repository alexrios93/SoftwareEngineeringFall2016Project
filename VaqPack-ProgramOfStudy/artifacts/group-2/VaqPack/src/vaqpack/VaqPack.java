/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpack;

import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VaqPack extends Application
{
	ArrayList <CheckBox> checkBoxes= new ArrayList();
	TextField tf1;
	Label lb1;

	@Override
	public void start(Stage primaryStage)
	{
		BorderPane root = new BorderPane();
		root.setLeft(new leftPane());
		root.setCenter(new rightPane());
		root.setBottom(new bottomPane());
		root.setTop(new topPane());
		
		Scene scene = new Scene(root, 800, 480);
		
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public class topPane extends Text
	{
		topPane()
		{
			setWrappingWidth(800);
			setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
			setFill(Color.BLACK);

			setText("ABET Outcomes XML Creator");
			setTextAlignment(TextAlignment.CENTER);
		}
	}
	
	public class leftPane extends VBox
	{
		
	}
	
	public class rightPane extends VBox
	{
		rightPane()
		{
			Text title = new Text("ABET Outcomes");
			title.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, FontPosture.REGULAR, 20));
			checkBoxes.add(new CheckBox("(a) An ability to apply knowledge of computing and mathematics appropriate to the program's student outcomes and to the discipline."));
			checkBoxes.add(new CheckBox("(b) An ability to analyze a problem, and identify and define the computing requirements appropriate to its solution."));
			checkBoxes.add(new CheckBox("(c) An ability to design, implement, and evaluate a computer-based system, process, component, or program to meet desired needs."));
			checkBoxes.add(new CheckBox("(d) An ability to function effectively on teams to accomplish a common goal."));
			checkBoxes.add(new CheckBox("(e) An understanding of professional, ethical, legal, security and social issues and responsibilities."));
			checkBoxes.add(new CheckBox("(f) An ability to communicate effectively with a range of audiences."));
			checkBoxes.add(new CheckBox("(g) An ability to analyze the local and global impact of computing on individuals, organizations, and society."));
			checkBoxes.add(new CheckBox("(h) Recognition of the need for and an ability to engage in continuing professional development."));
			checkBoxes.add(new CheckBox("(i) An ability to use current techniques, skills, and tools necessary for computing practice."));
			checkBoxes.add(new CheckBox("(j) An ability to apply mathematical foundations, algorithmic principles, and computer science theory in the modeling and design of computer-based systems in a way that demonstrates comprehension of the tradeoffs involved in design choices."));
			checkBoxes.add(new CheckBox("(k) An ability to apply design and development principles in the construction of software systems of varying complexity."));
						
			getChildren().add(title);
			getChildren().addAll(checkBoxes);
			
			for(CheckBox x:checkBoxes)
			{
				x.setWrapText(true);
			}
		}
	}
	
	public class bottomPane extends HBox
	{
		bottomPane()
		{
			lb1 = new Label("File Name: ");
			tf1 = new TextField();
			
			Button btn1 = new Button("Create XML");
			btn1.setText("Create XML");
			btn1.setOnAction((ActionEvent e) ->
			{
				String name = tf1.getText();
				XMLGen abet = new XMLGen(checkBoxes,name);
			});
			this.setAlignment(Pos.CENTER);
			this.setPadding(new Insets(10, 10, 20, 10));
			this.getChildren().addAll(lb1,tf1,btn1);
		}
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
