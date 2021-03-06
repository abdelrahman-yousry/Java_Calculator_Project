package BaseMode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import CalcApplication.Calc_GUI;
import static CalcApplication.Calc_GUI.opValidation;
import java.io.IOException;
import static java.lang.Math.E;
import static java.lang.Math.PI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author yomna
 */
public class BaseMode implements Initializable {
    public static Stage window;
    Alert alert;
    DialogPane dialogPane;
    static String oldInput = "|";
    static String oldRes = " ";
    static String text;
    @FXML
    public Label res;
    public TextField input;
    @FXML
    private Button b_equal;    
    @FXML
    private Button b_backspace;
    @FXML
    private Button b_period;
    public String selectedMode = "Basic";
    
    int pos;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private MenuItem basic;
    @FXML
    private MenuItem scientific;
    @FXML
    private ImageView normal;
    @FXML
    public MenuBar menuBar;
    @FXML
    public Menu portMenu;
    
    
        
    @FXML
    public void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        pos = input.getText().indexOf("|");
        String s = Calc_GUI.numberValidation(input.getText(), tmp.getText(), pos);
        input.insertText(pos, s);
    }
    @FXML
    public void write_key(KeyEvent event) {
        pos = input.getText().indexOf("|");
        String s;
        if(event.isShiftDown()){
            switch(event.getCode()){
                case EQUALS:
                    s = opValidation(input.getText(), "+", pos);
                    input.setText(s);
                    break;
                case DIGIT6:
                    s = opValidation(input.getText(), "^", pos);
                    input.setText(s);
                    break;
                case DIGIT8:
                    s = opValidation(input.getText(), "??", pos);
                    input.setText(s);
                    break;
                
                case DIGIT5:
                    s = opValidation(input.getText(), "%", pos);
                    input.setText(s);
                    break;
                case DIGIT9:
                    s = opValidation(input.getText(), "( )", pos);
                    input.setText(s);
                    break;
                case DIGIT0:
                    s = opValidation(input.getText(), "( )", pos);
                    input.setText(s);
                    break;
            }
        }
        else if(event.getCode().isDigitKey()){
            s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
            input.insertText(pos, s);
        }
        else if(event.isControlDown()){
//        else if(event.getCode().isArrowKey()){
            switch(event.getCode()){
                case LEFT:
                    if(pos>0){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos-1, "|");
                    }break;
                case RIGHT:
                    if(pos<input.getText().length()-1){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos+1, "|");
                    }break;
            }
        }
        else{
            switch(event.getCode()){
                case E:
                    s = opValidation(input.getText(), "e", pos);
                    input.setText(s);
                    break;
                case SLASH:
                    s = opValidation(input.getText(), "??", pos);
                    input.setText(s);
                    break;
                case DIVIDE:
                    s = opValidation(input.getText(), "??", pos);
                    input.setText(s);
                    break;
                case MINUS:
                    s = opValidation(input.getText(), "-", pos);
                    input.setText(s);
                    break;
                case SUBTRACT:
                    s = opValidation(input.getText(), "-", pos);
                    input.setText(s);
                    break;
                case ADD:
                    s = opValidation(input.getText(), "+", pos);
                    input.setText(s);
                    break;
                case MULTIPLY:
                    s = opValidation(input.getText(), "??", pos);
                    input.setText(s);
                    break;
                case EQUALS:
                    b_equal.fire();
                    break; 
                case ENTER:
                    b_equal.fire();
                    break; 
                case BACK_SPACE:
                    b_backspace.fire();
                    break;
                case PERIOD:
                    b_period.fire();
                    break;
                case DECIMAL:
                    b_period.fire();
                    break;
                case HOME:
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(0, "|");
                    break;
                case END:
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(input.getText().length(), "|");
                    break;
            }
        }
    }
    @FXML
    public void operation(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        String s;
        pos = input.getText().indexOf("|");
        if(op.equals("???")){
            op = "???( )";
        }
        s = opValidation(input.getText(),op , pos);
        input.setText(s);
    }
   
    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        input.clear();
        input.setText("|");
        res.setText("");
    }   
    @FXML
    private void back_space(ActionEvent event) {
        // deletes the char
        pos = input.getText().indexOf("|");
        if(pos != 0)
            input.deleteText(pos-1, pos);
    } 
    
    @FXML
    private void dot_op(ActionEvent event) {
        // sets dot only one time per number
        // parse on operations
        String str = input.getText();
        String[] arrOfStr = str.split("[\\??\\??\\+\\-\\^\\???\\(\\)\\%]+");
        pos = input.getText().indexOf("|");
        for(String a:arrOfStr){
            if(a.contains("|")){
                if(!a.contains(".")){
                    input.insertText(pos, ".");
                    // check if pos of | doesn't has a number before it --> pos-2 = 0
                    if(pos == 0 || !Character.isDigit(input.getText().charAt(pos-1)))
                        input.insertText(pos, "0");
                }
                break;
            }
        }
    }
    
    @FXML
    private void equal_op(ActionEvent event) {
        String expression = input.getText();

        expression = expression.replace("|", "");
        if(!expression.isEmpty()){
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            
            expression = expression.replace('??', '*');
            expression = expression.replace('??', '/');
            expression = expression.replace("??", Double.toString(PI));

            expression = expression.replace("e",  Double.toString(E));
            expression = expression.replace("???", "Math.sqrt");
            
            if(expression.contains("^")){
                // split string on all operators except ^
                String[] arrOfStr = expression.split("[\\??\\??\\+\\-\\???\\(\\)\\%]+");
                for(String a:arrOfStr){
                    // get the substring that contains ^
                    if(a.contains("^")){
                        // replace the power part(base^exponent) by "^"
                        expression = expression.replace(a, "^");
                        // split substring on ^ to get the argumnets
                        String [] arg = a.split("\\^");
                        expression = expression.replace("^", "Math.pow(" + arg[0] + "," + arg[1] + ")");
                    }
                }
            }
            
            try {
                Double result = (Double) scriptEngine.eval(expression);
                res.setText(Double.toString(result));
            } catch (ScriptException ex) {
                res.setText("Math error");
            }
            catch(ArithmeticException ex){
                res.setText("Math error");
            }
            catch(Exception ex){
                try{
                    Integer result = (Integer) scriptEngine.eval(expression);
                    res.setText(Integer.toString(result));
                }catch(ScriptException e) {
                    res.setText("Math error");
                }
                catch(ArithmeticException e){
                res.setText("Math error");
                }
                catch(Exception e){ }
            }
        }
    }
    @FXML
    private void invert_sign(ActionEvent event) {
        String str = input.getText();
        pos = input.getText().indexOf("|");
        //split
        String[] arrOfStr = str.split("[\\??\\??\\+\\-\\^\\???\\(\\)\\%]+");
        for(String a:arrOfStr){
            //cursor on which token
            if(a.contains("|")){
                int pos_relative = a.indexOf("|");
                // invert "(- number)"
                input.setText(input.getText().replace(a, ""));
                a = "(-" + a.replace("|", "") + ")";
                input.insertText(pos-pos_relative, a);
                input.insertText(pos+2, "|");
                break;
            }
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeFadeOut();
        input.setText(oldInput);
        input.setEditable(false);
        input.setFocusTraversable(false);
        res.setText(oldRes);
        MenuItem dummy = new MenuItem("Serial ports");
        dummy.setDisable(true);
        
        if(Calc_GUI.isFirst){
            portMenu.getItems().add(dummy);
            Calc_GUI.isFirst = false;
        }
        
        if(Calc_GUI.darkFlag){
            anchor.getChildren().add(Calc_GUI.baseModeController.menuBar);
            Calc_GUI.baseModeController.menuBar.toBack();
            Calc_GUI.baseModeController.menuBar.setPrefWidth(480);
            Calc_GUI.baseModeController.menuBar.getStylesheets().clear();
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyleDark.css").toString());            
        }
        else{
            menuBar.setPrefWidth(480);
            menuBar.getStylesheets().clear();
            menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyle.css").toString());            
        }

    }    

    @FXML
    public void modesHandle(ActionEvent event) throws IOException {
        selectedMode = ((MenuItem)event.getSource()).getText();

        Parent root = null;  
        Scene scene;
        
        switch(selectedMode)
        {
            case "Basic":
                if(!Calc_GUI.darkFlag)
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));
                else
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeDark.fxml"));                   
                break;
            case "Scientific":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));                   
                break;
            case "Conversion":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));                  
                break;     
            case "Geometry":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeDark.fxml"));                  
                break;  
            case "Base-N":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeDark.fxml"));                  
                break; 
        }

        scene = new Scene(root);
        window.setScene(scene);
        window.show();
        //return to default
        oldInput = "|";
        oldRes = " ";
        
    }

    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        if("normal".equals(((ImageView)event.getSource()).getId()))
        {
            oldInput = input.getText();
            oldRes = res.getText();
            Calc_GUI.darkFlag = true;
            root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeDark.fxml"));

        }
        else
        {
            oldInput = input.getText();   
            oldRes = res.getText();            
            Calc_GUI.darkFlag = false;
            root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }


    @FXML
    private void helpHandle(ActionEvent event) {
        
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Guide":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guide");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("\t\t----IMPORTANT SHORTCUTS----\t\t\n"
                        + "-----------------------------------------------------------\n"
                        + "1- Ctrl + ???  :  Move Cursor to Left\n"
                        + "2- Ctrl + ??? : Move Cursor to Right\n"
                        + "3- ??? ??? ??? ???  :  Moving on the GUI\n"
                        + "4- Alt   :  Go to MenuBar\n"
                        + "5- Tab  :  Move out from the Text Field\n"
                        + "-----------------------------------------------------------\n"
                        + "NOTE  :  You can use the Keys on your Keyboard to\n\t\t\t  type what you need"); 
                
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;
            case "About":
                Image logoITI = new Image(getClass().getResource("..//Style/ITI.png").toString());
                ImageView logo = new ImageView(logoITI);
                StackPane pane = new StackPane();
                pane.getChildren().add(logo);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText(null);
                alert.setContentText("\n\n\t\tCopyright ?? 2022 by Team 9\n\n Aya Adel - Youmna Al-Shaboury - Nehal Amgad\n     Abdelrahman Yousry - Mohammed Hosny\n\n\t\tintake42-Embedded System Track");  
                alert.setGraphic(pane);
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;       
        }
    }

    @FXML
    private void editHandle(ActionEvent event) {
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Copy":
               text = input.getSelectedText();
               text = text.replace("|", "");
                break;
            case "Cut":
                text = input.getSelectedText();
                input.deleteText(input.getSelection());
                break;
            case "Paste":
                input.insertText(input.getCaretPosition(),text);
                break;
            case "Delete":
                input.setText("|");
                break;
        }
    }
    
    void makeFadeOut()
    {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(gridPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

       
}
