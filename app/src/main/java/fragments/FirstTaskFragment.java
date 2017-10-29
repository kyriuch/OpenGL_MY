package fragments;


import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import renderers.FirstTaskRenderer;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstTaskFragment extends Fragment {


    public FirstTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("hehe");
        GLSurfaceView glSurfaceView = new GLSurfaceView(getContext());
        glSurfaceView.setEGLContextClientVersion(3);

        glSurfaceView.setRenderer(new FirstTaskRenderer(getResources()));

        return glSurfaceView;
    }

}