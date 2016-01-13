package net.torbenvoltmer.fhdw.calculator.gui.windows.mainwindowstates;

import net.torbenvoltmer.fhdw.calculator.gui.UseCaseController;

/**
 * Created by torben on 12.01.16.
 */
public interface MainWindowState {

    Boolean isCompileButtonEnabled();
    Boolean isEvaluateButtonEnabled();

    void accept(UseCaseController controller);

    String getMessage();

    String getResultText();


}
