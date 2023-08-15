package model.interfaces;

import model.colorUtilities.ShapeColor;
import model.mouseUtilities.MouseMode;
import model.shadeUtilities.ShapeShadingType;
import model.shapeUtilities.ShapeType;
import view.interfaces.IDialogChoice;

public interface IDialogProvider {

    IDialogChoice<ShapeType> getChooseShapeDialog();

    IDialogChoice<ShapeColor> getChoosePrimaryColorDialog();

    IDialogChoice<ShapeColor> getChooseSecondaryColorDialog();

    IDialogChoice<ShapeShadingType> getChooseShadingTypeDialog();

    IDialogChoice<MouseMode> getChooseStartAndEndPointModeDialog();
}
