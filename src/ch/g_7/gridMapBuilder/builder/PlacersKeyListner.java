package ch.g_7.gridMapBuilder.builder;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import ch.g_7.gridEngine.core.FieldGrid;
import ch.g_7.gridEngine.stream.MapWriter;
import ch.g_7.gridEngine.test.BasicMover;

public class PlacersKeyListner extends BasicMover<Placer>{

	public PlacersKeyListner(Placer field) {
		super(field);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			field.place();
			break;
		case KeyEvent.VK_LEFT:
			field.last();
			break;
		case KeyEvent.VK_RIGHT:
			field.next();
			break;
		case KeyEvent.VK_5:
			FieldGrid grid = field.getFieldStack().getGrid();
			grid.getStack(field.getPosition()).removeField(field);
			String name = JOptionPane.showInputDialog("Map name");
			try {
				new MapWriter(new File(name + ".xml")).write(grid);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case KeyEvent.VK_I:
			
		}
	}
	
}
