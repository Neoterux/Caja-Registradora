/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.Custom;

/**
 *
 * @author Neoterux
 */
public class DbgMessage {
    
        
    private ExceptionType type;
    private String className;
    private String exceptionMessage;
    private CrudType crudType;
    private int rowsAffected;

    public DbgMessage(ExceptionType type, Object className, String exceptionMessage) {
        this.type = type;
        this.className = className.getClass().getName();
        this.exceptionMessage = exceptionMessage;
    }
    
    public DbgMessage(Object className, CrudType type, int rowsAffected){
        this.className = this.className.getClass().getName();
        this.crudType = type;
        this.rowsAffected = rowsAffected;
        
    }
    
    public void showExceptionDbg(){
        
        System.out.println("["+type.toString()+"]\n"
                + "[ClassName]: "+className+
                "[Error Message]: "+exceptionMessage);
       
    }
    
    public void showCrudDbg(){
        System.out.println("["+crudType.toString()+"]\n"
                + "[ClassName]: "+className+
                "[Rows Affected]: "+ rowsAffected);
    }
    
    
    
    
}
