package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    float H,W,R,An,La;

    private Body BodyCa;
    private Fixture CaFix;

    private BitmapFont font;
    private SpriteBatch batch;

    private float borde;
    private float bordeRan;

    private Array<Body> bodiesW = new Array<Body>();
    private Array<Fixture>  fixturesW=new Array<Fixture>();
    private Array<Joint> joints=new Array<Joint>();

    @Override
    public void show() {

        An=10;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-5),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);
        borde=-((La/2)-.5f);
        t=0;

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);

        PolygonShape Cushape=new PolygonShape();
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

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");

        if(tn<=0&&(1/delta)>=40)
        {
            fps=df.format(1/delta);
            tn=.25f;
            PolygonShape Cushape=new PolygonShape();

            if(i==0) {
                bodiesW.add(world.createBody(createBody(0, 3, 'D')));
            }else
            {
                bodiesW.add(world.createBody(createBody(bodiesW.get(i-1).getPosition().x, bodiesW.get(i-1).getPosition().y, 'D')));
            }
            Cushape.setAsBox(.04f,.03f);
            fixturesW.add(bodiesW.get(i).createFixture(createFix(Cushape,1f,0,3f)));
            Cushape.dispose();
            i=i+1;
            if(i>=2)
            {
             joints.add(world.createJoint(createRevjoin(bodiesW.get(i-2),bodiesW.get(i-1),0.035f,0,-0.035f,0,true,false,0,0)));
            }
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

    private JointDef createRevjoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean cl, boolean M, float Mt, float s) {
        RevoluteJointDef Revjoin=new RevoluteJointDef();
        Revjoin.bodyA=BA;
        Revjoin.bodyB=BB;
        Revjoin.localAnchorA.set(Ax,Ay);
        Revjoin.localAnchorB.set(Bx,By);
        Revjoin.collideConnected=cl;
        Revjoin.enableMotor=M;
        Revjoin.maxMotorTorque=Mt;
        Revjoin.motorSpeed=s;
        return Revjoin;
    }


    private float Boy2Piy(float Cy) { return An*R-((An/2)-Cy)*R; }
    private float Box2Pix(float Cx) { return (Cx+(La/2))*R; }
    private float Piy2Boy(float Py) { return (An/2)-(Py/R); }
    private float Pix2Box(float  Px) { return (Px/R)-(La/2); }
}