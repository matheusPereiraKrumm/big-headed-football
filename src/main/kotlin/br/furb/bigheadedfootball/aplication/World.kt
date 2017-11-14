package br.furb.bigheadedfootball.aplication

import br.furb.bigheadedfootball.aplication.componets.Camera
import br.furb.bigheadedfootball.aplication.componets.Color
import br.furb.bigheadedfootball.aplication.componets.Point
import br.furb.bigheadedfootball.common.*
import com.sun.opengl.util.GLUT
import javax.media.opengl.DebugGL
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLEventListener
import javax.media.opengl.glu.GLU


class World : GLEventListener {

    private var backgroundColor : Color = Color.GREY
    private var camera: Camera = Camera(Point(25.0, 50.0 , 100.0),// ORIGEM
                                        Point(25.0, 0.0 , 15.0))// DESTINO

    private var aberturaCamera = 50.0


    override fun init(glAutoDrawable: GLAutoDrawable?) {
        GLProvider.glDrawable = glAutoDrawable!!
        GLProvider.gl = glAutoDrawable.gl
        GLProvider.glu = GLU()
        GLProvider.glut = GLUT()
        GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
        GLProvider.gl.glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)


        turnOnLight()

    }

    private fun turnOnLight(){
        gl{
            val posLight = floatArrayOf(5.0f, 5.0f, 10.0f, 0.0f)
            glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0)
            glEnable(GL.GL_LIGHT0)
        }
    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        GLProvider.gl.glMatrixMode(GL.GL_PROJECTION)
        GLProvider.gl.glLoadIdentity()
        GLProvider.gl.glViewport(0, 0, width, height)

        glu{
            gluPerspective(aberturaCamera, (width/height).toDouble(), 0.1, 1000.0)
        }
    }

    override fun display(p0: GLAutoDrawable?) {
        GLProvider.gl.glClear(GL.GL_COLOR_BUFFER_BIT)
        GLProvider.gl.glMatrixMode(GL.GL_MODELVIEW)
        GLProvider.gl.glLoadIdentity()

        draw()

        GLProvider.gl.glFlush()
    }

    override fun displayChanged(p0: GLAutoDrawable?, p1: Boolean, p2: Boolean) {

    }

    private fun draw() {
        glu {
            gluLookAt(camera.pointDirection.x, camera.pointDirection.y, camera.pointDirection.z,
                    camera.position.x, camera.position.y, camera.position.z,
                    camera.topCam.x, camera.topCam.y, camera.topCam.z)
                drawAxis()
                glColor(Color.RED)

            light{
                val cor : FloatArray = floatArrayOf(0.01f, 0.1f, 0.01f, 1f)
                gl{ glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, cor , 0)}
                glut{

                    matrix {
                        scale(50f,.1F,70f)
                        translate(0.5f, -1.5f, 0.5f)
                        glutSolidCube(1f)
                    }

                    matrix {
                        scale(20f,9F,3f)
                        translate(1.25f, 0.5f, -0.5f)
                        glutSolidCube(1f)
                    }

                    val coramarelo : FloatArray = floatArrayOf(1f, 0.1f, 0.01f, 1f)
                    gl{ glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, coramarelo , 0)}

                    matrix {
                        scale(2f,6F,2f)
                        translate(3f, .5f, 10f)
                        glutSolidCube(1f)
                    }
                    matrix {
                        scale(2f,6F,2f)
                        translate(22f, .5f, 10f)
                        glutSolidCube(1f)
                    }

                    matrix {
                        scale(2f,6F,2f)
                        translate(9f, .5f, 5f)
                        glutSolidCube(1f)
                    }
                    matrix {
                        scale(2f,6F,2f)
                        translate(16f, .5f, 5f)
                        glutSolidCube(1f)
                    }

                    val cor : FloatArray = floatArrayOf(1f, 1f, 0.01f, 1f)
                    gl{ glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, cor , 0)}


                    matrix {
                        scale(2f,6F,2f)
                        translate(12f, .5f, 30f)
                        glutSolidCube(1f)
                        matrix {
                            scale(0.4f,0.2F,0.4f)
                            translate(0f, -4f, -3f)
                            glutSolidCube(1f)
                        }
                    }

                    /*glQuads {
                        glPoint(Point(0.0, 0.0, 0.0))
                        glPoint(Point(100.0, 0.0, 0.0))
                        glPoint(Point(100.0, 0.0, -100.0))
                        glPoint(Point(0.0, 0.0, -100.0))
                    }*/
                }
            }
        }
    }

    fun translate(x: Float, y: Float, z: Float){
        gl{ glTranslatef(x, y, z) }
    }

    fun scale(x: Float, y: Float, z: Float){
        gl{ glScalef(x, y, z) }
    }

    private fun drawAxis() {
        val center = Point(0.0, 0.0,0.0)
        gl{
            // eixo X - Red
            glColor(Color.RED)
            drawGl(GL.GL_LINES){
                glPoint(center)
                glPoint(Point(10.0, 0.0, 0.0))
            }
            // eixo Y - Green
            glColor(Color.GREEN)
            drawGl(GL.GL_LINES){
                glPoint(center)
                glPoint(Point(0.0, 10.0, 0.0))
            }
            // eixo Z - Blue
            glColor(Color.BLUE)
            drawGl(GL.GL_LINES){
                glPoint(center)
                glPoint(Point(0.0, 0.0, 10.0))
            }
        }
    }
}