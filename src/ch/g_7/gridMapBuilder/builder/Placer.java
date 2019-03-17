package ch.g_7.gridMapBuilder.builder;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import ch.g_7.gridEngine.base.Movable;
import ch.g_7.gridEngine.core.FieldStack;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;

public class Placer extends Field<JComponent> implements Movable{

	private FieldCodeList fieldList;
	private JComponent panel;
	
	public Placer(FieldCodeList fieldList) {
		panel = fieldList.getActualWrappedField().getPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		this.fieldList = fieldList;
	}
	
	public void place() {
		stack.addField(fieldList.getActualWrappedField());
	}
	
	public void next() {
		fieldList.next();
		updatePlacer();
	}
	
	public void last() {
		fieldList.last();
		updatePlacer();
	}
	
	private void updatePlacer() {
		stack.removeField(this);
		panel = fieldList.getActualWrappedField().getPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		stack.addField(this);
		stack.updatePanel();
	}


	@Override
	public boolean moveUp(int d) {
		boolean b = move(new Dimension(0, -d));
		return b;
	}
	
	@Override
	public boolean moveDown(int d) {
		boolean b = move(new Dimension(0, d));
		return b;
	}
	
	@Override
	public boolean moveLeft(int d) {
		boolean b = move(new Dimension(-d, 0));
		return b;
	}
	
	@Override
	public boolean moveRight(int d) {
		boolean b = move(new Dimension(d, 0));
		return b;
	}

	@Override
	public FieldCode getCode() {
		return new FieldCode("CODER: ");
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}
	
	public FieldStack getFieldStack() {
		return stack;
	}
}
