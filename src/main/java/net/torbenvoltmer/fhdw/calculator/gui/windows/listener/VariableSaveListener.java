package net.torbenvoltmer.fhdw.calculator.gui.windows.listener;

import net.torbenvoltmer.fhdw.calculator.gui.GuiTextConstants;
import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.ErrorState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.NewVariableEnteredState;
import net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates.NewVariableSavedState;
import net.torbenvoltmer.fhdw.calculator.parser.exception.DivisionByZeroException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.ParserSymbolHandleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableCycleException;
import net.torbenvoltmer.fhdw.calculator.parser.exception.VariableNotDefinedException;
import net.torbenvoltmer.fhdw.calculator.parser.variables.Variable;
import net.torbenvoltmer.fhdw.calculator.parser.variables.VariableStorage;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.ScannerException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by torben on 31.01.16.
 */
public class VariableSaveListener extends MainWindowListener implements ActionListener {

    private UseCaseController controller;
    public VariableSaveListener(UseCaseController controller) {
        this.controller = controller;
    }

    @Override
    public void handel(NewVariableEnteredState newVariableEnteredState) {
        Variable var = new Variable(controller.getView().getVariableName().charAt(0));
        try {
            var.setDefiningExpression(controller.getView().getVariableExpression());

            VariableStorage.getInstance().addVariable(var);

            controller.getView().setState(new NewVariableSavedState(newVariableEnteredState));

        }
        catch(ParserSymbolHandleException e) {
            controller.getView().setState(new ErrorState(newVariableEnteredState, GuiTextConstants.PARSING_ERROR_TEXT));
        } catch (VariableNotDefinedException e) {
            controller.getView().setState(new ErrorState(newVariableEnteredState, e.getMessage()));
        } catch (ScannerException e) {
            controller.getView().setState(new ErrorState(newVariableEnteredState, GuiTextConstants.SCANNING_ERROR_TEXT));
        } catch (DivisionByZeroException e) {
            controller.getView().setState(new ErrorState(newVariableEnteredState, GuiTextConstants.DIVISION_BY_ZERO_ERROR_TEXT));
        } catch (VariableCycleException e) {
            controller.getView().setState(new ErrorState(newVariableEnteredState, GuiTextConstants.VARIABLE_CYCLE_ERROR_TEXT));
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.getView().getState().accept(this);
    }
}
