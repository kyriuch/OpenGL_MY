package fragments;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import renderers.FifthTaskRenderer;
import renderers.FirstTaskRenderer;
import renderers.FourthTaskRenderer;
import renderers.SecondTaskRenderer;
import renderers.SeventhTaskRenderer;
import renderers.SixthTaskRenderer;
import renderers.ThirdTaskRenderer;

public class MainFragment extends Fragment {

    static GLSurfaceView.Renderer renderers[];

    public static void createRenderersInstances() {
        GLSurfaceView.Renderer renderers[] = new GLSurfaceView.Renderer[7];

        renderers[0] = new FirstTaskRenderer();
        renderers[1] = new SecondTaskRenderer();
        renderers[2] = new ThirdTaskRenderer();
        renderers[3] = new FourthTaskRenderer();
        renderers[4] = new FifthTaskRenderer();
        renderers[5] = new SixthTaskRenderer();
        renderers[6] = new SeventhTaskRenderer();

        MainFragment.renderers = renderers;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GLSurfaceView glSurfaceView = new GLSurfaceView(getActivity());
        glSurfaceView.setEGLContextClientVersion(2);

        int renderer = getArguments().getInt("renderer");

        glSurfaceView.setRenderer(renderers[renderer]);

        return glSurfaceView;
    }

}