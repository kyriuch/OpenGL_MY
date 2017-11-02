package utils;

import android.opengl.GLES30;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import models.RawModel;

/**
 * Created by Tomek on 28.10.2017.
 */

public class Loader {

    public RawModel loadToVAO(float positions[], short indices[]) {
        int vbos[] = createVBO(positions, indices);
        int vaoId = createVAO(vbos);
        return new RawModel(vaoId, indices.length);
    }

    private int createVAO(int[] vbos) {
        int[] vao = new int[1];

        GLES30.glGenVertexArrays(1, vao, 0);
        GLES30.glBindVertexArray(vao[0]);
        System.out.println("VAO " + vao[0]);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbos[0]);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vbos[1]);

        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, 0);

        GLES30.glBindVertexArray(0);

        return vao[0];
    }

    private int[] createVBO(float[] positions, short[] indices) {
        int[] vbos = new int[2];

        GLES30.glGenBuffers(2, vbos, 0);
        System.out.println("VBO0 " + vbos[0]);
        System.out.println("VBO1 " + vbos[1]);

        FloatBuffer floatBuffer = getFloatBufferFromData(positions);
        ShortBuffer shortBuffer = getShortBufferFromData(indices);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbos[0]);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, floatBuffer.capacity() * 4, floatBuffer, GLES30.GL_STATIC_DRAW);

        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vbos[1]);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, shortBuffer.capacity() * 2, shortBuffer, GLES30.GL_STATIC_DRAW);

        return vbos;
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
}
