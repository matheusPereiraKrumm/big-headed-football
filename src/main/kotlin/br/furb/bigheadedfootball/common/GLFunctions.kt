package br.furb.bigheadedfootball.common

import br.furb.bigheadedfootball.aplication.componets.Color
import br.furb.bigheadedfootball.aplication.componets.Point
import com.sun.opengl.util.GLUT
import javax.media.opengl.GL
import javax.media.opengl.glu.GLU

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

fun light(block: () -> Unit) {
    run {
        gl { glEnable(GL.GL_LIGHTING) }
        block()
        gl { glDisable(GL.GL_LIGHTING) }
    }
}


fun matrix(block: () -> Unit) {
    run {
        gl { glPushMatrix() }
        block()
        gl { glPopMatrix() }
    }
}

/**
 * Atribui a cor de parametro
 */
fun glColor(color: Color) = gl { glColor3f(color.red, color.green, color.blue) }


/**
 * Desenha o ponto em x e y
 */
fun glPoint(point: Point) = gl { glVertex3d(point.x, point.y, point.z) }

/**
 * Preparação para desenhar Com a primitiva POINTS
 */
fun glQuads(block: () -> Unit) { run { drawGl(GL.GL_QUADS, block) } }

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
