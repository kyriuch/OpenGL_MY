package models;

import android.opengl.GLES30;

public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public void draw() {
        GLES30.glBindVertexArray(vaoID);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, vertexCount, GLES30.GL_UNSIGNED_SHORT, 0);
        GLES30.glBindVertexArray(0);
    }

}
