package br.furb.bigheadedfootball.common


import com.sun.opengl.util.GLUT
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.glu.GLU

object GLProvider {
    lateinit var gl: GL
    lateinit var glu: GLU
    lateinit var glut: GLUT
    lateinit var glDrawable: GLAutoDrawable
}