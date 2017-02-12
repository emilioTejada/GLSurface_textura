package emiliotejada.glsurface_textura;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glvista;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.rl);
        glvista = new MyGLSurface_07(this);
        rl.addView(glvista);
    }

    public void mueve1(View v) {
        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(1);
    }

    public void mueve2(View v) {
        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(2);
    }

    public void mueve3(View v) {
        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(3);
    }
    public void mueve4(View v) {
        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(4);
    }

    public void mueve5(View v) {
        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(5);
    }
    public void mueve6(View v) {

        MyGLSurface_07 mueve = new MyGLSurface_07(this);
        mueve.Mueve(6);
    }
    @Override
    protected void onPause() {
        super.onPause();
        glvista.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        glvista.onResume();
    }

}