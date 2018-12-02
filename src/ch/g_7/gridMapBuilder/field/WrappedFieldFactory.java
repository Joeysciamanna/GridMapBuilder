package ch.g_7.gridMapBuilder.field;

import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldFactory;

public class WrappedFieldFactory implements FieldFactory{

	FieldFactory extendingFactory;
	
	public WrappedFieldFactory(FieldFactory extendingFactory) {
		this.extendingFactory = extendingFactory;
	}
	
	@Override
	public Field<?> getField(FieldCode code) {
		return new WrappedField(extendingFactory.getField(code));
	}

}
