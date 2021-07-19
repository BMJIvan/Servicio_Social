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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
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

    private Body BodyCu1,BodyCu2,BodySu;
    private Fixture Cu1Fix,Cu2Fix,SuFix;

    private BitmapFont font;
    private SpriteBatch batch;

    private String V1,V2,V10,V20;
    private  float t,vel1,vel2,vel10,vel20;

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

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);

        Vector2[] chain=new Vector2[2];
        chain[0]=new Vector2(-La/2,-5);
        chain[1]=new Vector2(La/2,-5);

        BodySu=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        SuFix=BodySu.createFixture(createFix(cadena,0,0,0));

        PolygonShape Cushape=new PolygonShape();
        BodyCu1=world.createBody(createBody(-2,-4.5f,'D'));
        Cushape.setAsBox(.5f,.5f);
        Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,.9f));
        BodyCu2=world.createBody(createBody(2,-3.5f,'D'));
        Cushape.setAsBox(1.5f,1.5f);
        Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,1));
        Cushape.dispose();

        BodyCu1.setFixedRotation(true);
        BodyCu2.setFixedRotation(true);
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

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0f, 0.1f, 0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");

        if(t==0) {
            BodyCu1.applyLinearImpulse(0, 15* BodyCu1.getMass(), BodyCu1.getLocalCenter().x, BodyCu1.getLocalCenter().y, true);
            BodyCu2.applyLinearImpulse(0, 60, BodyCu2.getLocalCenter().x, BodyCu2.getLocalCenter().y, true);
            vel10=BodyCu1.getLinearVelocity().y;
            V10=df.format(vel10);
            vel20=BodyCu2.getLinearVelocity().y;
            V20=df.format(vel20);
        }

        vel1=BodyCu1.getLinearVelocity().y;
        vel2=BodyCu2.getLinearVelocity().y;

        V1=df.format(vel1);
        V2=df.format(vel2);

        batch.begin();
        font.draw(batch,"velocidad = "+V1,Box2Pix(-6),Boy2Piy(1.5f));
        font.draw(batch,"Velocidad = "+V2,Box2Pix(2.5f),Boy2Piy(1.5f));
        font.draw(batch,"velocidad inicial ="+V10,Box2Pix(-6),Boy2Piy(1));
        font.draw(batch,"velocidad inicial ="+V20,Box2Pix(2.5f),Boy2Piy(1));
        batch.end();

        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);

        t=t+.01f;
    }

    private float Boy2Piy(float Cy) { return An*R-((An/2)-Cy)*R; }
    private float Box2Pix(float Cx) { return (Cx+(La/2))*R; }
    private float Piy2Boy(float Py) { return (An/2)-(Py/R); }
    private float Pix2Box(float  Px) { return (Px/R)-(La/2); }
}