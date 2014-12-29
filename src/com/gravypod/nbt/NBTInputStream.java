package com.gravypod.nbt;

import com.gravypod.nbt.types.TagType;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

public class NBTInputStream extends DataInputStream {

	public NBTInputStream(InputStream in) {
		super(in);
	}

	public NBT readTag() throws IOException {
		byte type = this.readByte();
		TagType tagType = TagType.valueOf(type);
		if (tagType == null) throw new IOException("Invalid NBT Tag: For type " + type);
		return this.readTag(tagType);
	}

	public NBT readTag(TagType type) throws IOException {
		Constructor<? extends NBT> constructor = null;
		try {
			constructor = type.getType().getConstructor(NBTInputStream.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			assert constructor != null;
			return constructor.newInstance(this);
		} catch (Exception e) {
			throw new IOException("Invalid NBT Type: " + type.getType().getSimpleName());
		}
	}

}
