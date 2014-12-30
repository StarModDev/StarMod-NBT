package com.gravypod.nbt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NBTOutputStream extends DataOutputStream {

	public NBTOutputStream(OutputStream out) {
		super(out);
	}

	public void writeTag(NBT tag) throws IOException {
		writeTag(tag, true);
	}

	public void writeTag(NBT tag, boolean id) throws IOException {
		if (id) {
			this.writeByte(tag.getId());
		}
		tag.write(this);
	}

}
