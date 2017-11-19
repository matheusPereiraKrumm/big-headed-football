package br.furb.bigheadedfootball.world

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.Camera
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.PointCommom
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import br.furb.bigheadedfootball.world.objects.parts.*
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLEventListener


class World : GLEventListener {

    private val backgroundColor: Color = Color.GREY
    private val camera: Camera
    private val graphicalObjects: ArrayList<GraphicalObject> = ArrayList()
    var mainPlayer: MainPlayer = MainPlayer()

    init {
        val camp = Camp()
        camera = Camera(mainPlayer)

        graphicalObjects.add(camp)
        graphicalObjects.add(Goal())
        populateCharacter()
        mainPlayer.childGraphicalObjects.add(Ball())
        graphicalObjects.add(mainPlayer)
    }

    fun populateCharacter() {
        val positions = listOf(
                Point(10.0, 0.0, 30.0),
                Point(20.0, 0.0, 20.0),
                Point(30.0, 0.0, 20.0),
                Point(40.0, 0.0, 30.0)
        )

        positions.forEach {
            val character = Player()
            character.transformation.translation(it.x, it.y, it.z)
            graphicalObjects.add(character)
        }
    }

    override fun init(glAutoDrawable: GLAutoDrawable?) {
        initializeProvider(glAutoDrawable)
        gl {
            glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)
            val posLight = floatArrayOf(5.0f, 50.0f, 90.0f, 0.0f)
            glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0)
            glEnable(GL.GL_LIGHT0)

            glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE)

            glEnable(GL.GL_CULL_FACE)
            glEnable(GL.GL_DEPTH_TEST)
        }
    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        gl {
            glMatrixMode(GL.GL_PROJECTION)
            glLoadIdentity()
            glViewport(0, 0, width, height)
        }

        glu {
            gluPerspective(50.0, (width / height).toDouble(), 0.1, 1000.0)
        }
    }

    override fun display(p0: GLAutoDrawable?) {
        gl {
            glClear(GL.GL_COLOR_BUFFER_BIT xor GL.GL_DEPTH_BUFFER_BIT)
            glMatrixMode(GL.GL_MODELVIEW)
            glLoadIdentity()
            camera.draw()
            draw()

            glFlush()
        }
    }

    override fun displayChanged(p0: GLAutoDrawable?, p1: Boolean, p2: Boolean) {

    }

    private fun draw() {
        gl { glEnable(GL.GL_COLOR_MATERIAL) }
        drawAxis()

        graphicalObjects.forEach {
            it.draw()
        }
    }

    private fun drawAxis() {
        val center = PointCommom.neutralPoint()
        gl {
            // eixo X - Red
            glColor(Color.RED)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(10.0, 0.0, 0.0))
            }
            // eixo Y - Green
            glColor(Color.GREEN)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(0.0, 10.0, 0.0))
            }
            // eixo Z - Blue
            glColor(Color.BLUE)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(0.0, 0.0, 10.0))
            }
        }
    }

}