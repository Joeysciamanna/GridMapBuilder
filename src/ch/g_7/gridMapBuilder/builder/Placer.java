package ch.g_7.gridMapBuilder.builder;

import java.awt.Color;
import java.awt.Dimension;

import javax.activation.UnsupportedDataTypeException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import ch.g_7.gridEngine.base.Movable;
import ch.g_7.gridEngine.core.FieldStack;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridMapBuilder.field.WrappedField;

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
		update();
	}
	
	public void last() {
		fieldList.last();
		update();
	}
	
	private void update() {
		stack.removeField(this);
		panel = fieldList.getActualWrappedField().getPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		stack.addField(this);
		stack.getGrid().update();
	}

	@Override
	public boolean moveUp(int d) {
		return move(new Dimension(0, -d));
	}
	
	@Override
	public boolean moveDown(int d) {
		return move(new Dimension(0, d));
	}
	
	@Override
	public boolean moveLeft(int d) {
		return move(new Dimension(-d, 0));
	}
	
	@Override
	public boolean moveRight(int d) {
		return move(new Dimension(d, 0));
	}

	@Override
	public FieldCode getCode() {
		return new FieldCode("CODER");
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}
	
	public FieldStack getFieldStack() {
		return stack;
	}

}
