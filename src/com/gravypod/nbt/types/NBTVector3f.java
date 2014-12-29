package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import javax.vecmath.Vector3f;
import java.io.IOException;

public class NBTVector3f implements NBT<Vector3f> {

	private Vector3f value;

	public NBTVector3f(Vector3f value) {
		this.value = value;
	}

	public NBTVector3f(NBTInputStream in) throws IOException {
		float x = in.readFloat();
		float y = in.readFloat();
		float z = in.readFloat();
		this.value = new Vector3f(x, y, z);
	}

	@Override
	public byte getId() {
		return TagType.VECTOR3F.getId();
	}

	@Override
	public Vector3f getValue() {
		return value;
	}

	@Override
	public void setValue(Vector3f value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeFloat(value.x);
		out.writeFloat(value.y);
		out.writeFloat(value.z);
	}

}