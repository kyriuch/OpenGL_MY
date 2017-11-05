package shaders;

import java.io.IOException;
import java.io.InputStream;

public class TextureShader extends ShaderProgram {
    public TextureShader(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        super(vertexStream, fragmentStream);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "inPosition");
        super.bindAttribute(1, "inColor");
        super.bindAttribute(2, "inTexPosition");
    }
}
