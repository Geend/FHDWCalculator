package net.torbenvoltmer.fhdw.calculator.parser.variables;

import java.util.*;

/**
 * Created by torben on 30.01.16.
 */
public class VariableStorage {

    private static VariableStorage instance;

    public  static VariableStorage getInstance(){
        if(instance == null)
            instance = new VariableStorage();

       return instance;
    }

    private Map<Character, Variable> variables;

    private VariableStorage(){
        variables = new HashMap<Character, Variable>();
    }

    public void addVariable(Variable var){
        this.variables.put(var.getName(), var);
    }

    public boolean contains(Character varName){
        return this.variables.containsKey(varName);
    }

    public boolean contains(Variable var){
        return this.variables.containsValue(var);
    }

    public Variable getVariable(Character varName){
        return this.variables.get(varName);
    }

    public Map<Character, Variable> getVariables() {
        return variables;
    }

    public void clear() {
        instance = new VariableStorage();
    }
}
