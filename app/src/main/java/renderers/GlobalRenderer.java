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
        this.loader = new Loader();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        GLES30.glViewport(0, 0, i, i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    }
}
