package br.furb.bigheadedfootball.common

import br.furb.bigheadedfootball.world.components.*
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import com.sun.opengl.util.GLUT
import javax.media.opengl.DebugGL
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.glu.GLU


object GLProvider {
    lateinit var gl: GL
    lateinit var glu: GLU
    lateinit var glut: GLUT
    lateinit var glDrawable: GLAutoDrawable
}

fun initializeProvider(glAutoDrawable: GLAutoDrawable?) {
    GLProvider.glDrawable = glAutoDrawable!!
    GLProvider.gl = glAutoDrawable.gl
    GLProvider.glu = GLU()
    GLProvider.glut = GLUT()
    GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
}

fun glDrawable(block: GLAutoDrawable.() -> Unit) = GLProvider.glDrawable.block()

fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

fun glu(block: GLU.() -> Unit) = GLProvider.glu.block()

fun glut(block: GLUT.() -> Unit) = GLProvider.glut.block()

fun GraphicalObject.useTransformation(block: GraphicalObject.() -> Unit) {
    useTransformation(transformation) {
        block()
    }
}

fun GraphicalObject.useTransformation(auxTransformation: Transformation, block: GraphicalObject.() -> Unit) {
    matrix {
        gl {
            glMultMatrixd(auxTransformation.GetDate(), 0)
            block()
        }
    }
}

fun matrix(block: () -> Unit) {
    gl {
        glPushMatrix()
        block()
        glPopMatrix()
    }

}


fun drawGl(primitive: Int, block: () -> Unit) {
    run {
        gl { glBegin(primitive) }
        block()
        gl { glEnd() }
    }
}

fun glColor(color: Color) =
        gl {
            glColor3f(color.red, color.green, color.blue)
        }

fun glPoint(point: Point) = gl { glVertex3d(point.x, point.y, point.z) }

fun useLight(function: () -> Unit) {
    gl {
        glEnable(GL.GL_LIGHTING)
        function()
        glDisable(GL.GL_LIGHTING)
    }
}

fun GraphicalObject.useTexture(texture: Texture, block: GraphicalObject.() -> Unit) {
    gl {
        // Primeiro habilita uso de textura
        glEnable(GL.GL_TEXTURE_2D)
        // Especifica qual e a textura corrente pelo identificador
        //glBindTexture(GL.GL_TEXTURE_2D, TexturesContext.idTexture[texture.index])
        texture.load()
        block()
        //	Desabilita uso de textura
        glDisable(GL.GL_TEXTURE_2D)
    }
}
