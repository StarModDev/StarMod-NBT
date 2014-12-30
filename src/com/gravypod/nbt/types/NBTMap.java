package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NBTMap implements NBT<Map<String, Object>> {

	private Map<String, Object> value;

	public NBTMap(Map<String, Object> value) {
		this.value = value;
	}

	public NBTMap(NBTInputStream in) throws IOException {
		this.value = new HashMap<>();
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			byte type = in.readByte();
			String name = in.readUTF();
			TagType tagType = TagType.valueOf(type);
			NBT tag = in.readTag(tagType);
			addTag(name, tag.getValue());
		}
	}

	public void addTag(String name, Object tag) {
		value.put(name, tag);
	}

	public Object getTag(String name) {
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
	public Map<String, Object> getValue() {
		return value;
	}

	@Override
	public void setValue(Map<String, Object> value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.size());
		for (Map.Entry<String, Object> tag : value.entrySet()) {
			NBT nbt = TagType.getTag(tag.getValue());
			out.writeByte(nbt.getId());
			out.writeUTF(tag.getKey());
			out.writeTag(nbt, false);
		}
	}

}