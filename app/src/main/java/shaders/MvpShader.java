package shaders;

import java.io.IOException;
import java.io.InputStream;

public class MvpShader extends ShaderProgram {

    private int mvpMatrixLocation;
    private int transformationMatrixLocation;

    public MvpShader(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        super(vertexStream, fragmentStream);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "inPosition");
        super.bindAttribute(1, "inColor");
    }

    @Override
    protected void getAllUniformLocations() {
        mvpMatrixLocation = super.getUniformLocation("uMVPMatrix");
        transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrix(float[] matrix) {
        super.loadTransformationMatrix(transformationMatrixLocation, matrix);
    }

    public void loadMvpMatrix(float[] projectionMatrix, float[] viewMatrix) {
        super.loadMvpmatrix(mvpMatrixLocation, projectionMatrix, viewMatrix);
    }
}
