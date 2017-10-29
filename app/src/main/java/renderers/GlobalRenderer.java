package renderers;

import android.content.res.Resources;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import utils.Loader;


public abstract class GlobalRenderer implements Renderer {

    Loader loader;
    Resources resources;

    public GlobalRenderer(Resources resources){
        this.resources = resources;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(0f, 0f, 0f, 1f);

        loader = new Loader();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        GLES30.glViewport(0, 0, i, i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES30.glClearColor(0f, 0f, 0f, 1f);
    }
}
