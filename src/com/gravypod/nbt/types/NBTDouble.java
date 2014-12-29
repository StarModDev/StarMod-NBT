package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTDouble implements NBT<Double> {

	private Double value;

	public NBTDouble(Double value) {
		this.value = value;
	}

	public NBTDouble(NBTInputStream in) throws IOException {
		this.value = in.readDouble();
	}

	@Override
	public byte getId() {
		return TagType.DOUBLE.getId();
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeDouble(value);
	}

}