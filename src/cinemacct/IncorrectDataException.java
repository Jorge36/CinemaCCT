/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

/**
 * Description: Exception class to catch error when we read from the files
 * @author Jorge
 */
public class IncorrectDataException extends Exception {
    
    private int line;
    private String pathFile;
    private String message;

    public IncorrectDataException(int line, String pathFile, String msg) {
        this.line = line;
        this.pathFile = pathFile;
        this.message = msg;
    }
    
    
    /**
     * Creates a new instance of <code>IncorrectDataException</code> without
     * detail message.
     */
    public IncorrectDataException() {
    }

    /**
     * Constructs an instance of <code>IncorrectDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectDataException(String msg) {
        super(msg);
    }
    
    
 // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return "File: " + this.pathFile + " - Line: " + this.line + " - Message: " + this.message;
    }
    
}
