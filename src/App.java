import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application{
    String user = "JavaFX2";
    String pw = "password";
    String checkUser,checkPw;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox vBox = new VBox();
        // Group group = new Group();
        
        // vBox.setPadding(new Insets(20,20,20,30));
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();

        // Adding Nodes to gridpane layout
        

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(4);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);
        VBox.setVgrow(gridPane, Priority.ALWAYS );
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 1, 2);
        gridPane.add(lblMessage, 1, 3);

        gridPane.setId("root");
        btnLogin.setId("btnLogin");

        btnLogin.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent event) 
            {
                checkUser = txtUserName.getText().toString();
                checkPw = pf.getText().toString();
                if(checkUser.equals(user)&&checkPw.equals(pw)){
                // VBox vb = new VBox();
                MenuBar menuBar = new MenuBar();

                Menu file = new Menu("File");
                Menu edit = new Menu("Edit");
                Menu format = new Menu("Format");
                Menu view = new Menu("View");
                Menu help = new Menu("Help");

                MenuItem New = new MenuItem("New");
                MenuItem Open = new MenuItem("Open");
                MenuItem Save = new MenuItem("Save");
                MenuItem Sava_as = new MenuItem("Save as");

                
                file.getItems().addAll(New,Open,Save,Sava_as);
                menuBar.getMenus().addAll(file,edit,format,view,help);
  
                TextArea textArea = new TextArea();
                // textArea.setMaxWidth(1000);
                // textArea.setMaxHeight(500);


                Save.setOnAction(ActionEvent->{
                    FileChooser fileChooser = new FileChooser();
                    // FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files","*.txt" );
                    File saveFile = fileChooser.showSaveDialog(null);
                    try {
                        FileWriter fw = new FileWriter(saveFile);
                        fw.write(textArea.getText());
                        fw.close();
                    } catch (IOException e) {
                    
                        e.printStackTrace();
                    }
    
                });

                Open.setOnAction(ActionEvent ->{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file (*.txt)", "*.txt"));
                    File selectedFile = fileChooser.showOpenDialog(null);
                    if(selectedFile!=null){
                        BufferedReader br = null;
                        try{
                            String currentLine;
                            br = new BufferedReader(new FileReader(selectedFile));
                            while((currentLine=br.readLine())!=null){

                                textArea.appendText(currentLine+"\n");

                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                
                });


                GridPane gp = new GridPane();
                gp.add(menuBar,0,0);
                gp.add(textArea, 0, 1);
                AnchorPane pane = new AnchorPane();
                pane.minHeight(500);
                pane.minWidth(600);

                



                pane.getChildren().addAll(gp);
                // Group gr = new Group();
                // gr.getChildren().add(gp);




                Scene scene2 = new Scene(pane);

                primaryStage.setScene(scene2);
                primaryStage.setTitle("Notepad");
 
 
                    lblMessage.setText("Congra");
                    lblMessage.setTextFill(Color.GREEN);

                }
                else
                {
                
                    lblMessage.setText("Incorrect");
                    lblMessage.setTextFill(Color.RED);
                }
                txtUserName.setText("");
                pf.setText("");
                
            }
            
        });
        vBox.getChildren().add(gridPane);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        
        primaryStage.setScene(scene);
        // primaryStage.setHeight(500);
        // primaryStage.setWidth(600);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}