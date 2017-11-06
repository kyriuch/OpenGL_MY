package shaders;

import java.io.IOException;
import java.io.InputStream;

public class MvpShader extends ShaderProgram {

    private int transformationMatrixLocation;

    public MvpShader(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        super(vertexStream, fragmentStream);
    }

    @Override
    protected void bindAttributes() {

    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrixLocation = super.getUniformLocation("uMVPMatrix");
    }

    public void loadMvpMatrix(float[] projectionMatrix, float[] viewMatrix) {
        super.loadMatrix(transformationMatrixLocation, projectionMatrix, viewMatrix);
    }
}
