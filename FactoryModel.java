import VendingMachine.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A class representing the vending machine factory model
 */
public class FactoryModel {
    private VendingMachine vm;

    //VM creation

    /**
     * Creates a vending machine for the factory
     */
    public void createVM() {
        this.vm = new VendingMachine();
    }

    //VM testing

    /**
     * Tests current vending machine in factory
     */
    public void testVM() {
        
    }
    
    public VendingMachine getRVM(){
        return this.vm;
    }
}