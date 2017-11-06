package renderers;

import android.opengl.GLES30;

import com.example.tomek.opengl.R;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import models.TexturedModel;
import shaders.TextureShader;

public class ThirdTaskRenderer extends GlobalRenderer {

    private TexturedModel quad;
    private TextureShader textureShader;

    public ThirdTaskRenderer() {
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        try {
            textureShader = new TextureShader(resources.openRawResource(R.raw.texturedvertexshader), resources.openRawResource(R.raw.texturedfragmentshader));
        } catch (IOException e) {
            e.printStackTrace();
        }

        quad = loader.loadToVAO(new float[]{
                        -0.25f, 0.25f, 0f,
                        -0.25f, -0.25f, 0f,
                        0.25f, -0.25f, 0f,
                        0.25f, 0.25f, 0f
                },
                new float[]{
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f
                },
                new float[] {
                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f
                },
                new short[]{
                        0, 1, 2,
                        2, 3, 0
                }, loader.loadTexture(resources, R.drawable.picture));

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        super.onSurfaceChanged(gl10, i, i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        textureShader.start();
        quad.drawElements();
        textureShader.stop();
    }
}
