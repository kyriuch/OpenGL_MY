package shaders;

import android.opengl.GLES30;
import android.opengl.Matrix;
import android.renderscript.Matrix4f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import maths.Vector3;

public abstract class ShaderProgram {

    private int programId;

    ShaderProgram(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        int vertexId = loadShader(vertexStream, GLES30.GL_VERTEX_SHADER);
        int fragmentId = loadShader(fragmentStream, GLES30.GL_FRAGMENT_SHADER);
        programId = GLES30.glCreateProgram();

        GLES30.glAttachShader(programId, vertexId);
        GLES30.glAttachShader(programId, fragmentId);

        bindAttributes();

        GLES30.glLinkProgram(programId);

        getAllUniformLocations();
    }

    public void start() {
        GLES30.glUseProgram(programId);
    }

    public void stop() {
        GLES30.glUseProgram(0);
    }

    protected abstract void bindAttributes();

    void bindAttribute(int attribute, String variableName)
    {
        GLES30.glBindAttribLocation(programId, attribute, variableName);
    }

    private static int loadShader(InputStream stream, int type) throws IOException {
        StringBuilder shaderSource = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line;

        while((line = reader.readLine()) != null) {
            shaderSource.append(line).append("\n");
        }

        reader.close();

        int shaderId = GLES30.glCreateShader(type);

        GLES30.glShaderSource(shaderId, shaderSource.toString());
        GLES30.glCompileShader(shaderId);

        return shaderId;
    }

    protected void loadFloat(int location, float value) {
        GLES30.glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3 value) {
        GLES30.glUniform3f(location, value.getX(), value.getY(), value.getZ());
    }

    protected void loadBoolean(int location, boolean value) {
        float toLoad = value ? 1 : 0;

        GLES30.glUniform1f(location, toLoad);
    }

    void loadTransformationMatrix(int location, float[] matrix) {
        GLES30.glUniformMatrix4fv(location, 1, false, matrix, 0);
    }

    void loadMvpmatrix(int location, float[] projectionMatrix, float[] viewMatrix) {
        float[] MVPMatrix = new float[16];

        Matrix.multiplyMM(MVPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        GLES30.glUniformMatrix4fv(location, 1, false, MVPMatrix, 0);
    }

    protected abstract void getAllUniformLocations();

    int getUniformLocation(String uniformName) {
        return GLES30.glGetUniformLocation(programId, uniformName);
    }
}
