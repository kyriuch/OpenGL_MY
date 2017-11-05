package models;

import android.opengl.GLES30;

public class TexturedModel {
    private int vaoId;
    private int vertexCount;
    private int textureId;

    public TexturedModel(int vaoId, int vertexCount, int textureId) {
        this.vaoId = vaoId;
        this.vertexCount = vertexCount;
        this.textureId = textureId;
    }

    public void draw() {
        GLES30.glBindVertexArray(vaoId);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);
        GLES30.glEnableVertexAttribArray(2);
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureId);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, vertexCount, GLES30.GL_UNSIGNED_SHORT, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
        GLES30.glDisableVertexAttribArray(2);
        GLES30.glBindVertexArray(0);
    }

}
