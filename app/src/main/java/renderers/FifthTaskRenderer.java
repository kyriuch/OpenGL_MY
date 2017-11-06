package renderers;

import android.opengl.GLES30;
import android.opengl.Matrix;

import com.example.tomek.opengl.R;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import models.TexturedModel;
import shaders.TextureMvpShader;

public class FifthTaskRenderer extends GlobalRenderer {

    private TexturedModel cube;
    private TextureMvpShader textureMvpShader;

    private float[] projectionMatrix;
    private float[] viewMatrix;
    private float[] transformationMatrix;

    public FifthTaskRenderer() {
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        projectionMatrix = new float[16];
        viewMatrix = new float[16];
        transformationMatrix = new float[16];

        try {
            textureMvpShader = new TextureMvpShader(resources.openRawResource(R.raw.texturedmvpvertexshader), resources.openRawResource(R.raw.texturedfragmentshader));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cube = loader.loadToVAO(new float[]{
                        -0.25f, 0.25f, 0f,// przod
                -0.25f, -0.25f, 0f,
                0.25f, -0.25f, 0f,
                0.25f, -0.25f, 0f,
                0.25f, 0.25f, 0f,
                -0.25f, 0.25f, 0f,
                0.25f, 0.25f, 0f, // prawo
                0.25f, -0.25f, 0f,
                0.25f, -0.25f, -0.5f,
                0.25f, -0.25f, -0.5f,
                0.25f, 0.25f, -0.5f,
                0.25f, 0.25f, 0f,
                -0.25f, 0.25f, -0.5f, // tyl
                -0.25f, -0.25f, -0.5f,
                0.25f, -0.25f, -0.5f,
                0.25f, -0.25f, -0.5f,
                0.25f, 0.25f, -0.5f,
                -0.25f, 0.25f, -0.5f,
                -0.25f, -0.25f, -0.5f, // lewo
                -0.25f, -0.25f, 0f,
                -0.25f, 0.25f, 0f,
                -0.25f, 0.25f, 0f,
                -0.25f, 0.25f, -0.5f,
                        -0.25f, -0.25f, -0.5f,
                -0.25f, 0.25f, -0.5f, // gora
                -0.25f, 0.25f, 0f,
                0.25f, 0.25f, 0f,
                0.25f, 0.25f, 0f,
                0.25f, 0.25f, -0.5f,
                -0.25f, 0.25f, -0.5f,
                -0.25f, -0.25f, -0.5f, // dol
                -0.25f, -0.25f, 0f,
                0.25f, -0.25f, 0f,
                0.25f, -0.25f, -0.5f,
                -0.25f, -0.25f, -0.5f,
                0.25f, -0.25f, 0f
                },
                new float[]{
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f,
                        1f, 1f, 1f
                },
                new float[]{
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0,
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0,
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0,
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0,
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0,
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 1,
                        0, 1,
                        0, 0
                }, loader.loadTexture(resources, R.drawable.picture));

        Matrix.setLookAtM(viewMatrix, 0, -2, 2, 5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.setIdentityM(transformationMatrix, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        super.onSurfaceChanged(gl10, i, i1);

        float ratio = (float) i / i1;

        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        textureMvpShader.start();
        //Matrix.rotateM(transformationMatrix, 0, 0.5f, 0.25f, 0f, 0f);
        textureMvpShader.loadMvpMatrix(projectionMatrix, viewMatrix);
        textureMvpShader.loadTransformationMatrix(transformationMatrix);
        cube.drawTriangles();
        textureMvpShader.stop();
    }
}
