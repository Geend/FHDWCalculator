package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;

/**
 * Created by torben on 12.01.16.
 */
public class ExpressionEnteredState implements MainWindowState {
    @Override
    public Boolean isCompileButtonEnabled() {
        return true;
    }

    @Override
    public Boolean isEvaluateButtonEnabled() {
        return false;
    }

    @Override
    public void accept(UseCaseController controller) {
        controller.handel(this);
    }

    @Override
    public String getMessage() {
        return "";
    }

    @Override
    public String getResultText() {
        return "";
    }
}
