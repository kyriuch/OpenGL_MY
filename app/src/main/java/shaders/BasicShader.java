package shaders;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tomek on 29.10.2017.
 */

public class BasicShader extends ShaderProgram {
    public BasicShader(InputStream vertexStream, InputStream fragmentStream) throws IOException {
        super(vertexStream, fragmentStream);
    }

/*    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }*/
}
