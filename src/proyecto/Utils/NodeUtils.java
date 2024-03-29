package proyecto.Utils;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NodeUtils {

   
    public static boolean isNullOrEmpty(TextField ...textFields){
        boolean bl = true;
        try{
        for (TextField textField : textFields) {
            bl = bl && (textField.getText().trim().isEmpty() || textField.getText().trim().equals(""));
        }
        }catch(Exception e){}
        return bl;
    }

    public static boolean notIsNullOrEmpty(TextField ...textFields){

        return !isNullOrEmpty(textFields);
    }

    public static void addItemsToComboBox(ComboBox target, Object...Items){
        for (Object item: Items){
            target.getItems().add(item);
        }
        
    }

    public static void limitTextFieldLength(int maxLength, TextField...textFields){
        for(TextField textField:textFields){
            textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                if (newValue.intValue() > oldValue.intValue()){
                    if (textField.getText().length() > maxLength){
                        textField.setText(textField.getText().substring(0,maxLength));
                    }
                }
            });
        }
    }
    
    public static int parseInt(TextField textfield){
        try{
            return Integer.parseInt(textfield.getText().trim());
        }catch(NumberFormatException e){
            
        }
        return 0;
    }
    
    public static void clearAll(TextField ... textfields){
        List<TextField> lst = Arrays.asList(textfields);
        lst.forEach(it->{
            it.setText("");
        });
        
    }
    
    public static void clearAll(Parent root){
        
        root.getChildrenUnmodifiable().forEach(it->{
            if(it instanceof TextField){
                ((TextField) it).setText("");
            }
            
            if (it instanceof TableView)
                ((TableView) it).setItems(null);
            
            if(it instanceof Parent){
                clearAll((Parent) it);
            }
        });
        
    }
    
    public static void setTableHeightByRowCount(TableView table, ObservableList data) {
        int rowCount = data.size();
        TableHeaderRow headerRow = (TableHeaderRow) table.lookup("TableHeaderRow");
        double tableHeight = (rowCount * table.getFixedCellSize())
        // add the insets or we'll be short by a few pixels
        + table.getInsets().getTop() + table.getInsets().getBottom()
        // header row has its own (different) height
        + (headerRow == null ? 0 : headerRow.getHeight())
        ;

        table.setMinHeight(tableHeight);
        table.setMaxHeight(tableHeight);
        table.setPrefHeight(tableHeight);
    }
}
