package ch.g_7.gridMapBuilder.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ch.g_7.gridEngine.core.FieldGrid;
import ch.g_7.gridEngine.core.FieldStack;
import ch.g_7.gridEngine.field.ColoredField;
import ch.g_7.gridEngine.field.building.DefaultFieldFactory;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldCreationRegister;
import ch.g_7.gridEngine.field.building.FieldFactory;
import ch.g_7.gridEngine.helper.Lambda;
import ch.g_7.gridMapBuilder.builder.FieldCodeList;
import ch.g_7.gridMapBuilder.builder.Placer;
import ch.g_7.gridMapBuilder.builder.PlacersKeyListner;
import ch.g_7.gridMapBuilder.field.WrappedFieldFactory;

public class GridMapBuilder {

	public static void main(String[] args) {

		FieldFactory defaultFactory = new DefaultFieldFactory();
		FieldCreationRegister.setDefaultFactory(new WrappedFieldFactory(defaultFactory));
		
		FieldCodeList fields = new FieldCodeList();
		fields.addField(new FieldCode("COLORED_FIELD",String.valueOf(Color.GRAY.getRGB())));
		fields.addField(new FieldCode("COLORED_FIELD",String.valueOf(Color.white.getRGB())));
		
		
//		String mapPath = "map";
//		FieldGrid grid = new MapReader(new File(mapPath+".xml")).read().getGrid();

		int fieldHeight = 16;
		int fieldWidth = 16;
		int height = 24;
		int width = 32;
		FieldGrid grid = new FieldGrid(new Dimension(fieldWidth,fieldHeight), new Dimension(width, height));
		
		grid.forEach(new Lambda<Void, FieldStack>() {
			@Override
			public Void apply(FieldStack stack) {
				stack.addField(new ColoredField(Color.GREEN) {
				@Override
				public FieldCode getCode() {
					return new FieldCode("GRASS", "");
				}});
				return null;
			}
		});
		
		Placer placer = new Placer(fields);
		grid.addField(placer, new Point(1, 1));
		
		
		JFrame window = new JFrame();
		window.addKeyListener(new PlacersKeyListner(placer));
		
		JPanel gridPanel = grid.getPanel();
		gridPanel.setLocation(0,0);
		window.add(gridPanel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
