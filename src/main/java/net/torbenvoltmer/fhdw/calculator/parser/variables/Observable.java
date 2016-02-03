package net.torbenvoltmer.fhdw.calculator.parser.variables;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by torben on 20.01.16.
 */
public abstract class Observable {

    private List<VariableObserver> observers;

    public Observable(){
        observers = new ArrayList<VariableObserver>();
    }
    public void registerObserver(VariableObserver observer) throws VariableCycleException{
        if(!this.getAllObservers().contains(observer))
            observers.add(observer);
        else{
            throw new VariableCycleException("Cycle not allowed");
        }

    }

    public void removeObserver(VariableObserver observer){
        observers.remove(observer);
    }

    public List<VariableObserver> getObservers(){

        return observers;
    }

    public void notifyObservers() throws DivisionByZeroException, VariableNotDefinedException {
        for(VariableObserver observer : observers){
            observer.update();
        }
    }

    public abstract List<VariableObserver> getAllObservers();
}
