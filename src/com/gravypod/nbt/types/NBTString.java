package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTString extends NBT<String> {

    public NBTString() {
        this("");
    }

    public NBTString(RandomAccessFile channel) throws IOException {
        super(STRING, channel);
    }

    public NBTString(String value) {
        super(STRING);
        setValue(value);
    }

    @Override
    protected void loadPayload(RandomAccessFile channel) throws IOException {
        setValue(channel.readUTF());
    }

    @Override
    protected void savePayload(RandomAccessFile channel) throws IOException {
        channel.writeUTF(getValue());
    }
}
