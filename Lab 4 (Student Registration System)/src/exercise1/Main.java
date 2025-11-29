package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application {
	//Brandon Argenal Almanza, 301467830

    //UI Controls/ Variables
    private TextField name, address, city, province, postal, phone, email;
    private CheckBox studentCouncilCheck, volunteerWorkCheck;
    private RadioButton csRadio, businessRadio;
    private ToggleGroup majorToggleGroup;
    private ComboBox<String> coursesCombo;
    private ListView<String> selectedCoursesList;
    private Button displayButton;
    private TextArea displayArea;

    // Using simple arrays for our course data.
    private final String[] csCourses = {"Java", "Python", "C#", "SQL"};
    private final String[] businessCourses = {"Marketing", "Accounting", "Finance", "Management"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        //Methods to create the main form and one to display the info
        root.setCenter(createMainForm());
        root.setBottom(createBottomPanel());
        setupEventHandlers();

        //Initializing "Computer Science" to be selected by default
        csRadio.setSelected(true);
        //Add all CS courses to fill in the ComboBox
        coursesCombo.getItems().addAll(csCourses);

        //Creating the Scene and displaying the Stage
        Scene scene = new Scene(root, 750, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Node createMainForm() {
    	//Creating gridPane object
        GridPane grid = new GridPane();
        //Setting Horizontal spacing between columns
        grid.setHgap(10);
        
        //Setting Vertical spacing between columns
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 0, 10, 0));

        //Labels for form
        grid.add(new Label("Name:"), 0, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(new Label("Province:"), 0, 2);
        grid.add(new Label("City:"), 0, 3);
        grid.add(new Label("Postal Code:"), 0, 4);
        grid.add(new Label("Phone Number:"), 0, 5);
        grid.add(new Label("Email:"), 0, 6);

        //Initializing TextFields
        name = new TextField();
        address = new TextField();
        province = new TextField();
        city = new TextField();
        postal = new TextField();
        phone = new TextField();
        email = new TextField();

        //Adding them to the grid
        grid.add(name, 1, 0);
        grid.add(address, 1, 1);
        grid.add(province, 1, 2);
        grid.add(city, 1, 3);
        grid.add(postal, 1, 4);
        grid.add(phone, 1, 5);
        grid.add(email, 1, 6);

        //Initializing CheckBoxes
        studentCouncilCheck = new CheckBox("Student Council");
        volunteerWorkCheck = new CheckBox("Volunteer Work");
        
        //Aligning them to top of the grid
        GridPane.setValignment(studentCouncilCheck, VPos.TOP);
        GridPane.setValignment(volunteerWorkCheck, VPos.TOP);

        grid.add(studentCouncilCheck, 2, 1);
        grid.add(volunteerWorkCheck, 2, 4);

        //Right Panel for Major and Courses
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(0, 0, 0, 20));

        // A Horizontal Box for the radio buttons with 10px spacing
        HBox majorBox = new HBox(10);
        majorToggleGroup = new ToggleGroup();
        //initializing csRadio button to "Computer Science"
        csRadio = new RadioButton("Computer Science");
        csRadio.setToggleGroup(majorToggleGroup);
        //Initializing businessRadio to "Business"
        businessRadio = new RadioButton("Business");
        businessRadio.setToggleGroup(majorToggleGroup);
        //Adding all radio buttons to majorBox
        majorBox.getChildren().addAll(csRadio, businessRadio);

        //Creating ComboBox object
        coursesCombo = new ComboBox<>();
        //initializing promptText to "Select a course"
        coursesCombo.setPromptText("Select a course");

        //Creating listView object
        //Setting height to 150
        selectedCoursesList = new ListView<>();
        selectedCoursesList.setPrefHeight(150);

        // Add all parts to the right-side VBox
        rightPanel.getChildren().addAll(majorBox, coursesCombo, selectedCoursesList);
        
        //Add VBox to the grid.
        //VBox will span 1 column and 7 rows
        grid.add(rightPanel, 3, 0, 1, 7);

        return grid;
    }

    private Node createBottomPanel() {
        //Creating VBox object with 10px padding
        VBox bottomBox = new VBox(10);
        //Adding top padding to VBox
        bottomBox.setPadding(new Insets(10, 0, 0, 0));

        //Creating a Display button with max width
        displayButton = new Button("Display");
        displayButton.setMaxWidth(Double.MAX_VALUE);

        //Creating textArea to display form info
        displayArea = new TextArea();
        //Setting displayArea to false so users can't edit
        displayArea.setEditable(false);
        //Setting height of 150px to displayArea
        displayArea.setPrefHeight(150);

        //Add all elements to VBox (bottomBox)
        bottomBox.getChildren().addAll(displayButton, displayArea);
        return bottomBox;
    }

    private void setupEventHandlers() {
        // This code runs whenever the selection (csRadio or businessRadio) changes.
        majorToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                //Clearing out old items
                coursesCombo.getItems().clear();
                selectedCoursesList.getItems().clear();

                if (newToggle == csRadio) {
                    //Adds all CS courses
                    coursesCombo.getItems().addAll(csCourses);
                } else if (newToggle == businessRadio) {
                    //Adds all Business courses
                    coursesCombo.getItems().addAll(businessCourses);
                }
            }
        });

        //Code runs when the user selects an item from the dropdown menu.
        coursesCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the course the user selected
                String selectedCourse = coursesCombo.getValue();

                //If a course is selected and is not already in the list
                if (selectedCourse != null && !selectedCoursesList.getItems().contains(selectedCourse)) {
                    //Add course to the list
                    selectedCoursesList.getItems().add(selectedCourse);
                }
            }
        });

        //Code runs when the user clicks on "Display" button.
        displayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Calling displayStudentInfo() Method
                displayStudentInfo();
            }
        });
    }
    
    private void displayStudentInfo() {
    	//Initializing output to empty string
        String output = "";

        // Get info from text fields
        output += name.getText() + ", ";
        output += address.getText() + ", ";
        output += city.getText() + ", ";
        output += province.getText() + ", ";
        output += postal.getText() + ", ";
        output += phone.getText() + ", ";
        output += email.getText();
        
        //Adds two new lines
        output += "\n\n";

        //Display info from list course
        output += "Courses:\n";
        
        // Loops through all items in the selectedCoursesList
        for (String course : selectedCoursesList.getItems()) {
        	//Adds each course on a new line
            output += course + "\n";
        }

        //Set the text of the display area
        displayArea.setText(output);
    }

    public static void main(String[] args) {
        launch(args);
    }
}