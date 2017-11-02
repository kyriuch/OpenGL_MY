package renderers;

import android.content.res.Resources;

import com.example.tomek.opengl.R;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import models.RawModel;
import shaders.BasicShader;

/**
 * Created by Tomek on 28.10.2017.
 */

public class FirstTaskRenderer extends GlobalRenderer {

    RawModel rawModel;
    BasicShader basicShader;

    public FirstTaskRenderer(Resources resources) {
        super(resources);


    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        try {
            basicShader = new BasicShader(resources.openRawResource(R.raw.basicvertexshader), resources.openRawResource(R.raw.basicfragmentshader));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rawModel = loader.loadToVAO(new float[]{
                        -0.25f, 0.25f, 0f,
                        -0.25f, -0.25f, 0f,
                        0.25f, -0.25f, 0f,
                        0.25f, 0.25f, 0f
                },
                new short[]{
                        0, 1, 2,
                        0, 2, 3
                });
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        super.onSurfaceChanged(gl10, i, i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        basicShader.start();
        rawModel.draw();
    }
}
