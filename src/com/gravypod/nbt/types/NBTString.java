package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTString implements NBT<String> {

	private String value;

	public NBTString(String value) {
		this.value = value;
	}

	public NBTString(NBTInputStream in) throws IOException {
		this.value = in.readUTF();
	}

	@Override
	public byte getId() {
		return TagType.STRING.getId();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeUTF(value);
	}

}