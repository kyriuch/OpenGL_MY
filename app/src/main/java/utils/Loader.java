package utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import models.RawModel;
import models.TexturedModel;

public class Loader {

    public RawModel loadToVAO(float[] positions, float[] colors, short[] indices) {
        int vaoId = createVAO();
        createVBOs(positions, colors, indices);
        GLES30.glBindVertexArray(0);
        return new RawModel(vaoId, indices.length);
    }

    public RawModel loadToVAO(float[] positions, short[] indices) {
        float[] colors = new float[positions.length];

        for(int i = 0; i < colors.length; i++)
        {
            colors[i] = 0f;
        }

        return loadToVAO(positions, colors, indices);
    }

    public TexturedModel loadToVAO(float[] positions, float[] colors, float[] texturePositions, int textureId) {
        int vaoId = createVAO();
        createVBOs(positions, colors, texturePositions);
        GLES30.glBindVertexArray(0);

        return new TexturedModel(vaoId, positions.length / 3, textureId);
    }

    public TexturedModel loadToVAO(float[] positions, float[] colors, float[] texturePositions, short[] indices, int textureId) {
        int vaoId = createVAO();
        createVBOs(positions, colors, texturePositions, indices);
        GLES30.glBindVertexArray(0);
        return new TexturedModel(vaoId, indices.length, textureId);
    }

    private int createVAO() {
        int[] vao = new int[1];

        GLES30.glGenVertexArrays(1, vao, 0);
        GLES30.glBindVertexArray(vao[0]);

        return vao[0];
    }

    private void createVBOs(float[] positions, float[] colors, short[] indices) {
        int[] vbos = new int[3];

        GLES30.glGenBuffers(3, vbos, 0);

        FloatBuffer positionsBuffer = getFloatBufferFromData(positions);
        FloatBuffer colorsBuffer = getFloatBufferFromData(colors);
        ShortBuffer indicesBuffer = getShortBufferFromData(indices);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[0], 0, positionsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[1], 1, colorsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vbos[2]);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * 2, indicesBuffer, GLES30.GL_STATIC_DRAW);
    }

    private void createVBOs(float[] positions, float[] colors, float[] texturePositions, short[] indices) {
        int[] vbos = new int[4];

        GLES30.glGenBuffers(4, vbos, 0);

        FloatBuffer positionsBuffer = getFloatBufferFromData(positions);
        FloatBuffer colorsBuffer = getFloatBufferFromData(colors);
        FloatBuffer texturePositionsBuffer = getFloatBufferFromData(texturePositions);
        ShortBuffer indicesBuffer = getShortBufferFromData(indices);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[0], 0, positionsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[1], 1, colorsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[2], 2, texturePositionsBuffer, 4, 2, 4 * 2);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vbos[3]);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * 2, indicesBuffer, GLES30.GL_STATIC_DRAW);
    }

    private void createVBOs(float[] positions, float[] colors, float[] texturePositions) {
        int[] vbos = new int[3];

        GLES30.glGenBuffers(3, vbos, 0);

        FloatBuffer positionsBuffer = getFloatBufferFromData(positions);
        FloatBuffer colorsBuffer = getFloatBufferFromData(colors);
        FloatBuffer texturePositionsBuffer = getFloatBufferFromData(texturePositions);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[0], 0, positionsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[1], 1, colorsBuffer, 4, 3, 4 * 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);

        bindAndStoreDataInBuffer(GLES30.GL_ARRAY_BUFFER, vbos[2], 2, texturePositionsBuffer, 4, 2, 4 * 2);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    private void bindAndStoreDataInBuffer(int bufferType, int vbo, int attributePosition, Buffer dataBuffer, int typeSize, int dataSize, int stride) {
        GLES30.glBindBuffer(bufferType, vbo);
        GLES30.glBufferData(bufferType, dataBuffer.capacity() * typeSize, dataBuffer, GLES30.GL_STATIC_DRAW);
        GLES30.glVertexAttribPointer(attributePosition, dataSize, GLES30.GL_FLOAT, false, stride, 0);
    }

    private FloatBuffer getFloatBufferFromData(float[] data) {
        FloatBuffer floatBuffer = ByteBuffer.allocateDirect(data.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

        floatBuffer.put(data);
        floatBuffer.position(0);

        return floatBuffer;
    }

    private ShortBuffer getShortBufferFromData(short[] data) {
        ShortBuffer shortBuffer = ByteBuffer.allocateDirect(data.length * 2).order(ByteOrder.nativeOrder()).asShortBuffer();

        shortBuffer.put(data);
        shortBuffer.position(0);

        return shortBuffer;
    }

    public int loadTexture(Resources resources, int resourceId)
    {
        final int[] textureHandle = new int[1];

        GLES30.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            final Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId, options);

            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);

            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

            GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

            bitmap.recycle();
        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }

        return textureHandle[0];
    }
}
