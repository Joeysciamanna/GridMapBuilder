package ch.g_7.gridMapBuilder.field;

import javax.swing.JComponent;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;

public class WrappedField extends Field<JComponent>{

	private Field<?> field;
	
	public WrappedField(Field<?> field) {
		this.field = field;
	}
	
	@Override
	public JComponent getPanel() {
		return field.getPanel();
	}

	@Override
	public FieldCode getCode() {
		return field.getCode();
	}

	public void setField(Field<?> field) {
		this.field = field;
	}
	
	
}
