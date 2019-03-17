package ch.g_7.gridMapBuilder.field;

import java.awt.Color;

import ch.g_7.gridEngine.field.building.FieldCode;

public interface FieldTranslator {

	public Color getColorFor(FieldCode code);
}
