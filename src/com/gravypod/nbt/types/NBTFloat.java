package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTFloat implements NBT<Float> {

	private Float value;

	public NBTFloat(Float value) {
		this.value = value;
	}

	public NBTFloat(NBTInputStream in) throws IOException {
		this.value = in.readFloat();
	}

	@Override
	public byte getId() {
		return TagType.FLOAT.getId();
	}

	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void setValue(Float value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeFloat(value);
	}

}