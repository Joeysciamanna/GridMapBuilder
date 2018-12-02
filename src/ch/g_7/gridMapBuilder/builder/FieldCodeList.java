package ch.g_7.gridMapBuilder.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ch.g_7.gridEngine.field.building.FieldCode;
import ch.g_7.gridEngine.field.building.FieldFactoryProducer;
import ch.g_7.gridMapBuilder.field.WrappedField;

public class FieldCodeList{

	private List<FieldCode> fieldCodes;
	private int index=0;


	public FieldCodeList(FieldCode...fieldCodes) {
		this.fieldCodes = Arrays.asList(fieldCodes);
	}
	
	public FieldCodeList() {
		this.fieldCodes = new ArrayList<>();
	}
	
	public void addField(FieldCode field) {
		fieldCodes.add(field);
	}
	
	public void next() {
		if(index >= fieldCodes.size()-1) {
			 index=0;
		}else {
			index++;
		}
	}
	
	public void last() {
		if(index == 0) {
			 index = fieldCodes.size()-1;
		}else {
			index--;
		}
	}
	
	public FieldCode getActualFieldCode() {
		return fieldCodes.get(index);
	}
	
	public WrappedField getActualWrappedField() {
		return new WrappedField(FieldFactoryProducer.getDefaultFactory().getField(getActualFieldCode()));
	}

}
