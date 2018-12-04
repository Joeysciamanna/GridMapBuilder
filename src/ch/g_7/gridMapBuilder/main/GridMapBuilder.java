package ch.g_7.gridMapBuilder.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import ch.g_7.gridEngine.core.FieldGrid;
import ch.g_7.gridEngine.field.building.DefaultFieldFactory;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldFactory;
import ch.g_7.gridEngine.field.building.FieldFactoryProducer;
import ch.g_7.gridEngine.stream.MapReader;
import ch.g_7.gridMapBuilder.builder.FieldCodeList;
import ch.g_7.gridMapBuilder.builder.Placer;
import ch.g_7.gridMapBuilder.builder.PlacersKeyListner;
import ch.g_7.gridMapBuilder.field.WrappedFieldFactory;

public class GridMapBuilder {

	public static void main(String[] args) {

		FieldFactory defaultFactory = new DefaultFieldFactory();
		FieldFactoryProducer.setDefaultFactory(new WrappedFieldFactory(defaultFactory));
		
		FieldCodeList fields = new FieldCodeList();
		fields.addField(new FieldCode("COLORED_FIELD",String.valueOf(Color.GREEN.getRGB())));
		fields.addField(new FieldCode("COLORED_FIELD",String.valueOf(Color.BLUE.getRGB())));
		
		
		String mapPath = "map";
		FieldGrid grid = new MapReader(new File(mapPath+".xml")).read().getGrid();

//		int fieldHeight = 16;
//		int fieldWidth = 16;
//		int height = 12;
//		int width = 12;
//		FieldGrid grid = new FieldGrid(new Dimension(fieldWidth,fieldHeight), new Dimension(width, height));
		
		
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
