package shaders;

import android.opengl.GLES30;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class ShaderProgram {

    private int programId;
    private int vertexId;
    private int fragmentId;

    public ShaderProgram(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        vertexId = loadShader(vertexStream, GLES30.GL_VERTEX_SHADER);
        fragmentId = loadShader(fragmentStream, GLES30.GL_FRAGMENT_SHADER);
        programId = GLES30.glCreateProgram();

        GLES30.glAttachShader(programId, vertexId);
        GLES30.glAttachShader(programId, fragmentId);
        bindAttributes();
        GLES30.glLinkProgram(programId);
        GLES30.glValidateProgram(programId);
    }

    public void start() {
        GLES30.glUseProgram(programId);
    }

    public void stop() {
        GLES30.glUseProgram(0);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName)
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

        String shaderInfoLog = GLES30.glGetShaderInfoLog(shaderId);
        Log.i("SHADER: ", shaderInfoLog);

        return shaderId;
    }

}
