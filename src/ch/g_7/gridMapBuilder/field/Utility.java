package ch.g_7.gridMapBuilder.field;

import java.awt.Color;

import ch.g_7.gridEngine.field.ColoredField;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;

public class Utility {

	public static Field<?> createColored(Color color, FieldCode code){
		return new ColoredField(color) {
			@Override
			public FieldCode getCode() {
				return code;
			}
		};
	}
}
