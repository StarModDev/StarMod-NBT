package com.gravypod.nbt;

import com.gravypod.nbt.types.TagType;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class NBTFile {

	private File file;
	private boolean newFile;

	public NBTFile(File file) {
		File parentDir = file.getParentFile();
		if (!parentDir.exists()) parentDir.mkdir();
		if (!file.exists()) try {
			file.createNewFile();
			newFile = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		else newFile = false;
		this.file = file;
	}

	public NBTFile(String path) {
		this(new File(path));
	}

	public boolean isNewFile() {
		return newFile;
	}

	public Object[] read() throws IOException {
		NBTInputStream in = new NBTInputStream(new BufferedInputStream(new FileInputStream(file)));
		int size = in.readInt();
		Object[] items = new Object[size];
		for (int i = 0; i < size; i++) {
			items[i] = in.readTag().getValue();
		}
		in.close();
		return items;
	}

	public void write(Object[] items) throws IOException {
		NBTOutputStream out = new NBTOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		out.writeInt(items.length);
		for (Object item : items) {
			out.writeTag(TagType.getTag(item));
		}
		out.close();
	}

}
