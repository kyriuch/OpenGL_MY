package renderers;

import android.opengl.GLES30;
import android.opengl.Matrix;

import com.example.tomek.opengl.R;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import models.RawModel;
import shaders.MvpShader;

public class FourthTaskRenderer extends GlobalRenderer {

    private RawModel cube;
    private MvpShader mvpShader;

    private float[] projectionMatrix;
    private float[] viewMatrix;

    public FourthTaskRenderer() {
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        GLES30.glEnable(GLES30.GL_CULL_FACE);

        projectionMatrix = new float[16];
        viewMatrix = new float[16];

        try {
            mvpShader = new MvpShader(resources.openRawResource(R.raw.mvpvertexshader), resources.openRawResource(R.raw.basicfragmentshader));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cube = loader.loadToVAO(new float[]{
                        -0.25f, 0.25f, 0f,
                        -0.25f, -0.25f, 0f,
                        0.25f, -0.25f, 0f,
                        0.25f, 0.25f, 0f,
                        -0.25f, 0.25f, -0.5f,
                        -0.25f, -0.25f, -0.5f,
                        0.25f, -0.25f, -0.5f,
                        0.25f, 0.25f, -0.5f
                },
                new float[]{
                        1f, 0f, 0f,
                        0f, 1f, 0f,
                        0f, 0f, 1f,
                        0f, 1f, 0f,
                        1f, 0f, 0f,
                        0f, 1f, 0f,
                        0f, 0f, 1f,
                        0f, 1f, 0f
                },
                new short[]{
                        0, 1, 2,
                        2, 3, 0,
                        3, 2, 7,
                        7, 2, 6,
                        0, 1, 4,
                        4, 1, 5,
                        4, 0, 3,
                        3, 7, 4,
                        5, 1, 2,
                        2, 6, 5,
                        4, 5, 6,
                        6, 7, 4
                });

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
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
        mvpShader.start();
        Matrix.setLookAtM(viewMatrix, 0, 2, 2, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        mvpShader.loadMvpMatrix(projectionMatrix, viewMatrix);
        cube.draw();
        mvpShader.stop();
    }
}
