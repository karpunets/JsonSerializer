package com.karpunets.jsonSerializer.stream;

import com.karpunets.jsonSerializer.JsonSerializer;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class IndentedJsonWriter extends JsonWriter {

    private int indentSize = 4;
    private int currentLevel;
    private boolean needSeparator;
    private static final char INDENT = ' ';

    public IndentedJsonWriter(Writer out) {
        super(out);
    }

    public void setIndentSize(int indentSize) {
        if (indentSize > 0) {
            this.indentSize = indentSize;
        }
    }

    @Override
    public void writeObjectBegin() throws IOException {
        write(null);
        currentLevel++;
        out.append('{');
    }

    @Override
    public void writeObjectEnd() throws IOException {
        currentLevel--;
        writeLevel();
        super.writeObjectEnd();
    }

    @Override
    public void writeArrayBegin() throws IOException {
        needSeparator = true;
        currentLevel++;
        out.append('[');
    }

    @Override
    public void writeArrayEnd() throws IOException {
        currentLevel--;
        writeLevel();
        super.writeArrayEnd();
    }

    @Override
    public void writePropertySeparator() throws IOException {
        out.append(INDENT);
        super.writePropertySeparator();
        out.append(INDENT);
        needSeparator = false;
    }

    @Override
    void write(Object obj) throws IOException {
        if (needSeparator) {
            separator();
            writeLevel();
        } else {
            needSeparator = true;
        }
        if (obj != null) {
            out.append(obj.toString());
        }
    }

    private void writeLevel() throws IOException {
        out.append("\r\n");
        for (int i = 0; i < currentLevel * indentSize; i++) {
            out.append(INDENT);
        }
    }

}
