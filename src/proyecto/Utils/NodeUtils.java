package proyecto.Utils;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Neoterux
 */
public class NodeUtils {

    /**
     *
     * @param textFields
     * @return
     */
    public static boolean isNullOrEmpty(TextField ...textFields){
        boolean bl = true;
        try{
        for (TextField textField : textFields) {
            bl = bl && (textField.getText().trim().isEmpty() || textField.getText().trim().equals(""));
        }
        }catch(Exception e){}
        return bl;
    }

    /**
     *
     * @param textFields
     * @return
     */
    public static boolean notIsNullOrEmpty(TextField ...textFields){

        return !isNullOrEmpty(textFields);
    }
    
    

    /**
     *
     * @param target
     * @param Items
     */
    public static void addItemsToComboBox(ComboBox target, Object...Items){
        target.getItems().addAll(Arrays.asList(Items));
        
    }

    /**
     *
     * @param maxLength
     * @param textFields
     */
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
    
    /**
     *
     * @param textfield
     * @return
     */
    public static int parseInt(TextField textfield){
        try{
            return Integer.parseInt(textfield.getText().trim());
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo obtener datos del campo de texto, por favor revise el formato");
            a.show();
        }
        return 0;
    }
    
    /**
     *
     * @param textfields
     */
    public static void clearAll(TextField ... textfields){
        List<TextField> lst = Arrays.asList(textfields);
        lst.forEach(it->{
            it.setText("");
        });
        
    }
    
    /**
     *
     * @param root
     */
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
    
    /**
     *
     * @param table
     * @param data
     */
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
    
    
    /**
     * Añade un filtro al textfield para escribir caractered numericos
     * @param textfields textfields a los que se les colocara el filtro
     */
    public static void NumberValidation(TextField ...textfields){
        Arrays.asList(textfields).forEach(field->{
            field.getProperties().put("vktype", "numeric");
            field.setTextFormatter(new TextFormatter<>(c->{
                if (c.isContentChange()) {
                if (c.getControlNewText().length() == 0) {
                    return c;
                }
                try {
                    Integer.parseInt(c.getControlNewText());
                    return c;
                } catch (NumberFormatException e) {
                }
                return null;

                }
                return c;
            }));
        });
    }
}
