/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.exceptions;

/**
 *
 * @author helder
 */
public class ObjetoExistenteException extends Exception{

    public ObjetoExistenteException() {
    }
    
    public ObjetoExistenteException(String mensaje) {
        super(mensaje);
    }
}
