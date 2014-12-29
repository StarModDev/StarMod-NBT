package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NBTMap implements NBT<Map<String, NBT>> {

	private Map<String, NBT> value;

	public NBTMap(Map<String, NBT> value) {
		this.value = value;
	}

	public NBTMap(NBTInputStream in) throws IOException {
		this.value = new HashMap<>();
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			String name = in.readUTF();
			NBT tag = in.readTag();
			addTag(name, tag);
		}
	}

	public void addTag(String name, NBT tag) {
		value.put(name, tag);
	}

	public NBT getTag(String name) {
		return value.get(name);
	}

	public void removeTag(String name) {
		value.remove(name);
	}

	@Override
	public byte getId() {
		return TagType.MAP.getId();
	}

	@Override
	public Map<String, NBT> getValue() {
		return value;
	}

	@Override
	public void setValue(Map<String, NBT> value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.size());
		for (Map.Entry<String, NBT> tag : value.entrySet()) {
			out.writeUTF(tag.getKey());
			out.writeTag(tag.getValue());
		}
	}

}