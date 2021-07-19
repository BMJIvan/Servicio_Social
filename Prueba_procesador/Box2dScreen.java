package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private BitmapFont font;
    private SpriteBatch batch;

    float H,W,R,An,La;

    private Body BodyCa;
    private Fixture CaFix;

    private long ti;
    private long time;
    private float borde;
    private float bordeRan;

    private Vector2 chain;
    private Array<Body> bodiesW = new Array<Body>();
    private Array<Fixture>  fixturesW=new Array<Fixture>();

    @Override
    public void show() {

        An=10;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);
        borde=-((La/2)-.5f);
        t=0;

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);


        PolygonShape Cushape=new PolygonShape();

       // BodyGr=world.createBody(createBody(0,-5f,'S'));
        //Cushape.setAsBox(La, .25f);
       // GrFix=BodyGr.createFixture(createFix(Cushape,1,1,0));


        Vector2[] chain=new Vector2[5];
        chain[0]=new Vector2(-La/2+.01f,-5+.01f);
        chain[1]=new Vector2(-La/2+.01f,4);
        chain[2]=new Vector2(La/2,4);
        chain[3]=new Vector2(La/2,-5+.01f);
        chain[4]=new Vector2(-La/2+.01f,-5+.01f);

        BodyCa=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        CaFix=BodyCa.createFixture(createFix(cadena,0,0,1));

        Cushape.dispose();

        ShaderProgram.pedantic=false;
        graficaf=new ArrayList<Float>();
        graficaf.clear();

    }

    private String VertShader(float r,float g,float b,float a) {
        String R,G,B,A;
        R=Float.toString(r);
        G=Float.toString(g);
        B=Float.toString(b);
        A=Float.toString(a);
        return  "attribute vec4 a_position;    \n" +
                "attribute vec4 a_color;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;" +
                "void main()                  \n" +
                "{                            \n" +
                "   v_color = vec4("+R+", "+G+", "+B+", "+A+"); \n" +
                " gl_Position =  u_projTrans * a_position;\n" +
                "}                            \n" ;
    }

    private String FragShader() {
        return "varying vec4 v_color;\n" +
                "void main()                                  \n" +
                "{                                            \n" +
                "  gl_FragColor = v_color;\n" +
                "}";
    }

    private FixtureDef createFix(Shape cushape, float Den, float Fri, float Res) {
        FixtureDef def=new FixtureDef();
        def.shape=cushape;
        def.density=Den;
        def.friction=Fri;
        def.restitution=Res;
        return def;
    }

    private BodyDef createBody(float Px, float Py, Character Tipo) {
        BodyDef def=new BodyDef();
        def.position.set(Px,Py);
        switch(Tipo)
        {
            case 'D':
                def.type= BodyDef.BodyType.DynamicBody;
                break;
            case 'S':
                def.type= BodyDef.BodyType.StaticBody;
                break;
            case 'K':
                def.type= BodyDef.BodyType.KinematicBody;
                break;
            default:
                def.type= BodyDef.BodyType.StaticBody;
        }
        return def;
    }

    @Override
    public void dispose() {

        Gdx.input.vibrate(500);

        Array<Joint> joints=new Array<Joint>();
        world.getJoints(joints);
        for(int u = 0; u < joints.size; u++)
        {
            world.destroyJoint(joints.get(u));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int u = 0; u < bodies.size; u++)
        {
            world.destroyBody(bodies.get(u));
        }

        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
    }


    private String fps,tiempo,cuerpos;

    private float t=0,tn=0;
    private int i=0;

    private List<Float> graficaf;
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");
        //str=df.format(((BodyCu2.getPosition().x)*100)+45);

        if(tn<=0&&(1/delta)>=40)
        {
            fps=df.format(1/delta);
            tn=.15f;
            CircleShape Crshape=new CircleShape();
            bordeRan=borde+((float) Math.random()*.7f);
            bodiesW.add(world.createBody(createBody(bordeRan,((float)Math.random()*.25f+3.5f),'D')));
            Crshape.setRadius(.125f);
            fixturesW.add(bodiesW.get(i).createFixture(createFix(Crshape,(float)(1/(Math.pow(.5f,2)*Math.PI)),0,.99f)));
            Crshape.dispose();
            i=i+1;
        }

        tiempo=df.format(t);
        fps=df.format(1/delta);
        cuerpos=df.format(i);

        batch.begin();
        font.draw(batch,"FPS = "+fps,Box2Pix(-6),Boy2Piy(4.5f));
        font.draw(batch,"Tiempo = "+tiempo,Box2Pix(-2),Boy2Piy(4.5f));
        font.draw(batch,"Cuerpos = "+cuerpos,Box2Pix(2),Boy2Piy(4.5f));
        batch.end();

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
        t=t+delta;
        tn=tn-delta;

    }

    private float Boy2Piy(float Cy) {
        return An*R-((An/2)-Cy)*R;
    }

    private float Box2Pix(float Cx) {
        return (Cx+(La/2))*R;
    }

    private float Piy2Boy(float Py) {
        return (An/2)-(Py/R);
    }

    private float Pix2Box(float  Px) {
        return (Px/R)-(La/2);
    }


}