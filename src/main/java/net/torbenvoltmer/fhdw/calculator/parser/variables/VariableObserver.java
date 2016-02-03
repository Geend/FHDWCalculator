package net.torbenvoltmer.fhdw.calculator.parser.variables;

import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;

import java.util.List;

/**
 * Created by torben on 20.01.16.
 */
public interface VariableObserver {
    void update() throws DivisionByZeroException, VariableNotDefinedException;


}
