package ch.g_7.gridMapBuilder.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import ch.g_7.gridEngine.core.FieldGrid;
import ch.g_7.gridEngine.core.FieldStack;
import ch.g_7.gridEngine.field.ColoredField;
import ch.g_7.gridEngine.field.Field;
import ch.g_7.gridEngine.field.building.DefaultFieldFactory;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldCreationRegister;
import ch.g_7.gridEngine.field.building.FieldFactory;
import ch.g_7.gridEngine.helper.Lambda;
import ch.g_7.gridEngine.stream.MapReader;
import ch.g_7.gridEngine.stream.MetaInfo;
import ch.g_7.gridMapBuilder.builder.FieldCodeList;
import ch.g_7.gridMapBuilder.builder.Placer;
import ch.g_7.gridMapBuilder.builder.PlacersKeyListner;
import ch.g_7.gridMapBuilder.field.FieldTranslator;
import ch.g_7.gridMapBuilder.field.Utility;
import ch.g_7.gridMapBuilder.field.WrappedFieldFactory;

public class GridMapBuilder {

	public static void main(String[] args) throws IOException {

		FieldTranslator translator = new FieldTranslator() {
			@Override
			public Color getColorFor(FieldCode code) {
				switch (code.getFieldType()) {
				case "ROCK":
					return Color.GRAY;
				case "GRASS":
					return Color.GREEN;
				default:
					return Color.black;
				}
			}
		};
		FieldCreationRegister.setDefaultFactory(new WrappedFieldFactory(translator));
		
		FieldCodeList fields = new FieldCodeList();
		fields.addField(new FieldCode("ROCK",""));
		fields.addField(new FieldCode("GRASS",""));
		
		
		String mapPath = "map1";
		MapReader<MetaInfo> mapReader = new MapReader<MetaInfo>(new MetaInfo());
		mapReader.read(new File(mapPath));
		FieldGrid grid = mapReader.getGrid();
		grid.setSize(new Dimension(500, 500));
		
		
//		int height = 25;
//		int width = 25;
//		FieldGrid grid = new FieldGrid(new Dimension(height,width), new Dimension(500, 500));
//		
//		grid.forEachStack(new Lambda<Void, FieldStack>() {
//			@Override
//			public Void apply(FieldStack stack) {
//				
//				if (stack.getPosition().x == 0 || stack.getPosition().x == 24 || stack.getPosition().y == 0 || stack.getPosition().y == 24) {
//					stack.addField(Utility.createColored(Color.GRAY, new FieldCode("ROCK","")));
//				}else {
//					stack.addField(Utility.createColored(Color.GREEN, new FieldCode("GRASS","")));
//				}
//				return null;
//			}
//		});
		
		
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
