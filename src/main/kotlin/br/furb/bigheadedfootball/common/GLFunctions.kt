package br.furb.bigheadedfootball.common

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

/**
 * Coloca como contexto o GL
 */
fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

/**
 * Coloca como contexto o GLU
 */
fun glu(block: GLU.() -> Unit) = GLProvider.glu.block()

/**
 * Coloca como contexto o GLUT
 */
fun glut(block: GLUT.() -> Unit) = GLProvider.glut.block()

/**
 * Faz o encapsulamento de desenho no GL
 */
fun drawGl(primitive: Int, block: () -> Unit) {
    run {
        gl { glBegin(primitive) }
        block()
        gl { glEnd() }
    }
}
