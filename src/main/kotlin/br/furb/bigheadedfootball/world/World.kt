package br.furb.bigheadedfootball.world

import br.furb.bigheadedfootball.common.gl
import br.furb.bigheadedfootball.common.initializeProvider
import br.furb.bigheadedfootball.world.components.Color
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLEventListener


class World : GLEventListener {

    private val backgroundColor: Color = Color.WHITE

    override fun init(glAutoDrawable: GLAutoDrawable?) {
        initializeProvider(glAutoDrawable)
        gl {
            glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)
        }


    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        gl {
            glMatrixMode(GL.GL_PROJECTION)
            glLoadIdentity()
            glViewport(0, 0, width, height)
        }

    }

    override fun display(p0: GLAutoDrawable?) {
        gl {
            glClear(GL.GL_COLOR_BUFFER_BIT)
            glMatrixMode(GL.GL_MODELVIEW)
            glLoadIdentity()

            draw()

            glFlush()
        }
    }

    override fun displayChanged(p0: GLAutoDrawable?, p1: Boolean, p2: Boolean) {

    }

    private fun draw() {

    }

}