package utils;

import android.opengl.GLES30;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import models.RawModel;

/**
 * Created by Tomek on 28.10.2017.
 */

public class Loader {

    public RawModel loadToVAO(float positions[], int indices[]) {
        int vaoId = createVAO();
        int vbos[] = createVBO();
        storeDataInVBO(vbos[0], positions);
        storeIndicesInVBO(vbos[1], indices);
        unbindVAO();
        return new RawModel(vaoId, positions.length / 3);
    }

    private int createVAO() {
        int[] vao = new int[1];

        GLES30.glGenVertexArrays(1, vao, 0);
        GLES30.glBindVertexArray(vao[0]);

        return vao[0];
    }

    private int[] createVBO() {
        int[] vbos = new int[2];

        GLES30.glGenBuffers(2, vbos, 0);

        return vbos;
    }

    private void storeDataInVBO(int vbo, float[] data) {
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbo);
        FloatBuffer dataBuffer = getFloatBufferFromData(data);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, dataBuffer.capacity(), dataBuffer, GLES30.GL_STATIC_DRAW);
        GLES30.glVertexAttribPointer(vbo, 3, GLES30.GL_FLOAT, false, 0, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    private void storeIndicesInVBO(int vbo, int[] data) {
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer dataBuffer = getIntBufferFromData(data);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, dataBuffer.capacity(), dataBuffer, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private void unbindVAO() {
        GLES30.glBindVertexArray(0);
    }

    private FloatBuffer getFloatBufferFromData(float[] data) {
        FloatBuffer floatBuffer = ByteBuffer.allocateDirect(data.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

        floatBuffer.put(data);
        floatBuffer.flip();

        return floatBuffer;
    }

    private IntBuffer getIntBufferFromData(int[] data) {
        IntBuffer intBuffer = ByteBuffer.allocateDirect(data.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();

        intBuffer.put(data);
        intBuffer.flip();

        return intBuffer;
    }
}
