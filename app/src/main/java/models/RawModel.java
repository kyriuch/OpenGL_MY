package models;

import android.opengl.GLES30;

/**
 * Created by Tomek on 28.10.2017.
 */

public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void draw() {
        GLES30.glBindVertexArray(vaoID);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, vertexCount, GLES30.GL_UNSIGNED_INT, 0);
        GLES30.glDisableVertexAttribArray(0);
    }

}
