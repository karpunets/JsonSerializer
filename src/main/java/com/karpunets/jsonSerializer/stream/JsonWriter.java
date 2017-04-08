package com.karpunets.jsonSerializer.stream;


import java.io.IOException;
import java.io.Writer;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class JsonWriter {

    final Writer out;
    boolean needSeparator;

    public JsonWriter(Writer out) {
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }

    public void writeObjectBegin() throws IOException {
        write('{');
    }

    public void writeObjectEnd() throws IOException {
        out.append('}');
    }

    public void writeArrayBegin() throws IOException {
        write('[');
    }

    public void writeArrayEnd() throws IOException {
        out.append(']');
    }

    public void writeString(String s) throws IOException {
        write('"' + s + '"');
    }

    public void writeNumber(Number n) throws IOException {
        write(n);
    }

    public void writeBoolean(Boolean value) throws IOException {
        write(value);
    }

    public void writeNull() throws IOException {
        write("null");
    }

    public void writeSeparator() throws IOException {
        needSeparator = true;
    }

    public void writePropertySeparator() throws IOException {
        out.append(':');
    }

    public void flush() throws IOException {
        out.flush();
    }

    void write(Object obj) throws IOException {
        separator();
        out.append(obj.toString());
    }

    void separator() throws IOException {
        if (needSeparator) {
            out.append(',');
            needSeparator = false;
        }
    }

}
