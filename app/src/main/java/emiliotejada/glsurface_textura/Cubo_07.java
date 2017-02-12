package emiliotejada.glsurface_textura;

/**
 * Created by Emilio on 11/02/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class Cubo_07 {
    private FloatBuffer texBuffer;
    private int[] textureIDs = new int[1];
    private Bitmap bitmap;

    private int[] imageFileIDs={
            R.drawable.textura4d,
        };

    //las coordenadas UV son 0.0 en la parte superior izquierda y 1,1 en la parte inferior derecha
    float[] texCoords = {
            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f
    };

    private FloatBuffer vertexBuffer;
    private int numFaces = 6;
    private float[][] colors = {
            {1.0f, 0.5f, 0.0f, 1.0f},
            {1.0f, 0.0f, 1.0f, 1.0f},
            {0.0f, 1.0f, 0.0f, 1.0f},
            {0.0f, 0.0f, 1.0f, 1.0f},
            {1.0f, 0.0f, 0.0f, 1.0f},
            {1.0f, 1.0f, 0.0f, 1.0f}
    };
    private float[] vertices = {
//FRONTAL
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
// DORSAL
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
// IZQUIERDA
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
// DERECHA
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f,
// SUPERIOR
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
// INFERIOR
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f
    };


    public Cubo_07(Context context) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        escalarCubo(4);
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);


        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4*numFaces);
        tbb.order(ByteOrder.nativeOrder());
        texBuffer = tbb.asFloatBuffer();
        for(int face=0;face<numFaces;face++){
            texBuffer.put(texCoords);
        }
        texBuffer.position(0);
        bitmap= BitmapFactory.decodeStream(context.getResources().openRawResource(imageFileIDs[0]));
    }


    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); //con GL10.GL_TEXTURE_COORD_ARRAY no me sale
//gl.glTexCoordPointer(); con esto no me sale
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer);

        for (int face = 0; face < numFaces; face++) {
          //  gl.glColor4f(colors[face][0], colors[face][1], colors[face][2],colors[face][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face * 4, 4);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }





    public void cargaTextura(GL10 gl) {
        gl.glGenTextures(1, textureIDs, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[0]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,bitmap,0);
        bitmap.recycle();
    }


    public void escalarCubo(int escala){
        for (int i=0;i<this.vertices.length;i++){
            this.vertices[i]= this.vertices[i]*escala;
        }
    }
}