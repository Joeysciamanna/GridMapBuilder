package ch.g_7.gridMapBuilder.field;

import java.awt.Color;

import ch.g_7.gridEngine.field.ColoredField;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldFactory;

public class WrappedFieldFactory implements FieldFactory{

	private FieldFactory fieldFactory;
	
	public WrappedFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}
	
	public WrappedFieldFactory(FieldTranslator translator) {
		this.fieldFactory = new FieldFactory() {
			@Override
			public Field<?> getField(FieldCode code) {
				return Utility.createColored(translator.getColorFor(code), code);
			}
		};
	}
	
	@Override
	public Field<?> getField(FieldCode code) {
		return new WrappedField(fieldFactory.getField(code));
	}

}
