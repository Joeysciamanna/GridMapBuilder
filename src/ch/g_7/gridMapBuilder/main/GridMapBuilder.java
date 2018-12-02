package ch.g_7.gridMapBuilder.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ch.g_7.gridEngine.base.Lambda;
import ch.g_7.gridEngine.core.FieldGrid;
import ch.g_7.gridEngine.core.FieldStack;
import ch.g_7.gridEngine.field.ColoredField;
import ch.g_7.gridEngine.field.building.DefaultFieldFactory;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldFactoryProducer;
import ch.g_7.gridEngine.field.building.FieldType;
import ch.g_7.gridEngine.helper.BasicMover;
import ch.g_7.gridEngine.stream.MapReader;
import ch.g_7.gridMapBuilder.builder.FieldCodeList;
import ch.g_7.gridMapBuilder.builder.Placer;
import ch.g_7.gridMapBuilder.builder.PlacersKeyListner;
import ch.g_7.gridMapBuilder.field.WrappedField;
import ch.g_7.gridMapBuilder.field.WrappedFieldFactory;

public class GridMapBuilder {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		
		FieldFactoryProducer.setDefaultFactory(new WrappedFieldFactory(new DefaultFieldFactory()));
		
		String[] sizeList = JOptionPane.showInputDialog(window, "Höhe/Breite or L for loading a map").split("/");
		
		FieldGrid grid;
		if (sizeList[0].equals("L")) {
			String mapPath = JOptionPane.showInputDialog(window, "Map Path");
			grid = new MapReader(new File(mapPath)).read().getGrid();
		} else{
			String[] fieldSizeList = JOptionPane.showInputDialog(window, "Höhe/Breite of the Field").split("/");
			int fieldHeight = Integer.parseInt(fieldSizeList[0]);
			int fieldWidth = Integer.parseInt(fieldSizeList[1]);
			int height = Integer.parseInt(sizeList[0]);
			int width = Integer.parseInt(sizeList[1]);
			
			grid = new FieldGrid(new Dimension(fieldWidth,fieldHeight), new Dimension(width, height));
		}
		
		FieldCodeList fields = new FieldCodeList();
		fields.addField(new FieldCode(FieldType.COLORED_FIELD,String.valueOf(Color.GREEN.getRGB())));
		fields.addField(new FieldCode(FieldType.COLORED_FIELD,String.valueOf(Color.BLUE.getRGB())));
		
		Placer placer = new Placer(fields);
		grid.addField(placer, new Point(1, 1));
		
		window.addKeyListener(new PlacersKeyListner(placer));
		window.add(grid.getPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
}
