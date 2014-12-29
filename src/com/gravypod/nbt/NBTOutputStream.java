package com.gravypod.nbt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NBTOutputStream extends DataOutputStream {

	public NBTOutputStream(OutputStream out) {
		super(out);
	}

	public void writeTag(NBT tag) throws IOException {
		this.writeByte(tag.getId());
		tag.write(this);
	}

}
