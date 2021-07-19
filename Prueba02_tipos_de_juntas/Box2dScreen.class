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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private RevoluteJoint JoinRev1,JoinRev2;
    private Body BodyTr1;
    private Fixture Tr1Fix;
    private Body BodyCr1,BodyCr2;
    private Fixture Cr1Fix,Cr2Fix;

    private PrismaticJoint JoinPr;
    private Body BodyCu1,BodyCu2;
    private Fixture Cu1Fix,Cu2Fix;

    private WeldJoint JoinWel1,JoinWel2;
    private Body BodyCr3,BodyCr4;
    private Fixture Cr3Fix,Cr4Fix;
    private Body BodyCu3;
    private Fixture Cu3Fix,Cu4Fix;

    private DistanceJoint JoinDis1,JoinDis2;
    private Body BodyCr5,BodyCr6;
    private Fixture Cr5Fix,Cr6Fix;
    private Body BodyTr2;
    private Fixture Tr2Fix;

    private PulleyJoint JoinPul;
    private Body BodyCu5,BodyCu6;
    private Fixture Cu5Fix,Cu6Fix;

    private MouseJoint JoinMou;
    private Body BodyCc,BodyGr;
    private Fixture CcFix;

    private Body BodyCu4;

    private BitmapFont font;
    private SpriteBatch batch;

    float H,W,R,An,La;

    @Override
    public void show() {

        An=48;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        cont=12;
        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/480);

        CircleShape Crshape=new CircleShape();
        PolygonShape Cushape=new PolygonShape();

        BodyCr1=world.createBody(createBody(-20,15,'D'));
        Crshape.setRadius(2);
        Cr1Fix=BodyCr1.createFixture(createFix(Crshape,1,0,1));

        BodyCr2=world.createBody(createBody(-20,15,'D'));
        Crshape.setRadius(1);
        Cr2Fix=BodyCr2.createFixture(createFix(Crshape,1,0,1));

        Vector2[] vertices=new Vector2[3];
        vertices[0]=new Vector2(-1,-1);
        vertices[1]=new Vector2(0,1);
        vertices[2]=new Vector2(1,-1);
        BodyTr1=world.createBody(createBody(-20,15,'S'));
        Cushape.set(vertices);
        Tr1Fix=BodyTr1.createFixture(createFix(Cushape,.1f,.1f,.1f));

        BodyCu1=world.createBody(createBody(-20,0,'S'));
        Cushape.setAsBox(.5f,.5f);
        Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,1));

        BodyCu2=world.createBody(createBody(-20,0,'D'));
        Cushape.setAsBox(.5f,.5f);
        Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,1));

        BodyCu3=world.createBody(createBody(-20,-15,'D'));
        Cushape.setAsBox(1.5f,1.5f);
        Cu3Fix=BodyCu3.createFixture(createFix(Cushape,1,0,1));

        BodyCr3=world.createBody(createBody(-20,-15,'D'));
        Crshape.setRadius(1);
        Cr3Fix=BodyCr3.createFixture(createFix(Crshape,1,1,0));

        BodyCr4=world.createBody(createBody(-20,-15,'D'));
        Crshape.setRadius(1);
        Cr4Fix=BodyCr4.createFixture(createFix(Crshape,1,1,0));

        BodyCu4=world.createBody(createBody(0,-20,'S'));
        Cushape.setAsBox(25,.5f);
        Cu4Fix=BodyCu4.createFixture(createFix(Cushape,1,.1f,.1f));

        BodyTr2=world.createBody(createBody(0,15,'S'));
        Cushape.set(vertices);
        Tr2Fix=BodyTr2.createFixture(createFix(Cushape,1,0,1));

        BodyCr5=world.createBody(createBody(10,15,'D'));
        Crshape.setRadius(2);
        Cr5Fix=BodyCr5.createFixture(createFix(Crshape,1,1,0));

        BodyCr6=world.createBody(createBody(20,15,'D'));
        Crshape.setRadius(1);
        Cr6Fix=BodyCr6.createFixture(createFix(Crshape,1,1,0));

        BodyCu5=world.createBody(createBody(10,-5,'D'));
        Cushape.setAsBox(.5f,.5f);
        Cu5Fix=BodyCu5.createFixture(createFix(Cushape,1,0,.1f));

        BodyCu6=world.createBody(createBody(15,-5,'D'));
        Cushape.setAsBox(.5f,.5f);
        Cu6Fix=BodyCu6.createFixture(createFix(Cushape,1,0,.1f));

        BodyCc=world.createBody(createBody(0,0,'D'));
        Cushape.setAsBox(1,1);
        CcFix=BodyCc.createFixture(createFix(Cushape,1,1,0));

        BodyGr=world.createBody(createBody(0,0,'S'));

        Cushape.dispose();
        Crshape.dispose();

        JoinMou=(MouseJoint) world.createJoint(createMoujoin(BodyGr,BodyCc,true,1000,0,100));
        JoinPul=(PulleyJoint) world.createJoint(createPuljoin(BodyCu5,BodyCu6,0,.5f,0,.5f,10,5,15,5,true,5,5,1));
        JoinDis1=(DistanceJoint) world.createJoint(createDisjoin(BodyTr2,BodyCr5,0,0,0,0,true,10,0,0));
        JoinDis2=(DistanceJoint) world.createJoint(createDisjoin(BodyCr5,BodyCr6,0,0,0,0,true,10,0,0));
        JoinWel1=(WeldJoint) world.createJoint(createWelJoin(BodyCu3,BodyCr3,-1.5f,-2,.3f,1.3f,true,0,0));
        JoinWel2=(WeldJoint) world.createJoint(createWelJoin(BodyCu3,BodyCr4,1.5f,-2,-.3f,1.3f,true,0,0));
        JoinPr=(PrismaticJoint) world.createJoint(createPrJoin(BodyCu1,BodyCu2,.5f,0,-.5f,0,true,true,false,0,0,10,1,-1));
        JoinRev1=(RevoluteJoint) world.createJoint(createRevjoin(BodyTr1,BodyCr1,0,0,-5,0,true,false,0,0));
        JoinRev2=(RevoluteJoint) world.createJoint(createRevjoin(BodyCr1,BodyCr2,0,0,-5,0,true,false,0,0));
    }

    private JointDef createMoujoin(Body BA,Body BB,boolean cl,float m,float Dm,float Fr) {
        MouseJointDef Moujoin=new MouseJointDef();
        Moujoin.bodyA=BA;
        Moujoin.bodyB=BB;
        Moujoin.collideConnected=cl;
        Moujoin.maxForce=m*BB.getMass();
        Moujoin.dampingRatio=Dm;
        Moujoin.frequencyHz=Fr;
        return Moujoin;
    }

    private JointDef createPuljoin(Body BA,Body BB,float Ax,float Ay,float Bx,float By,float Gax,float Gay,float Gbx,float Gby,boolean cl,float La,float Lb,float r) {
        PulleyJointDef Puljoin=new PulleyJointDef();
        Puljoin.bodyA=BA;
        Puljoin.bodyB=BB;
        Puljoin.localAnchorA.set(Ax,Ay);
        Puljoin.localAnchorB.set(Bx,By);
        Puljoin.groundAnchorA.set(Gax,Gay);
        Puljoin.groundAnchorB.set(Gbx,Gby);
        Puljoin.collideConnected=true;
        Puljoin.lengthA=La;
        Puljoin.lengthB=Lb;
        Puljoin.ratio=r;
        return Puljoin;
    }

    private JointDef createDisjoin(Body BA,Body BB,float Ax,float Ay,float Bx,float By,boolean cl,float Le,float Dm,float Fr) {
        DistanceJointDef Disjoin=new DistanceJointDef();
        Disjoin.bodyA=BA;
        Disjoin.bodyB=BB;
        Disjoin.localAnchorA.set(Ax,Ay);
        Disjoin.localAnchorB.set(Bx,By);
        Disjoin.collideConnected=cl;
        Disjoin.length=Le;
        Disjoin.dampingRatio=Dm;
        Disjoin.frequencyHz=Fr;
        return Disjoin;
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

    private JointDef createPrJoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean cl, boolean lm, boolean M,float s, float L, float U, int lx, int ly) {
        PrismaticJointDef Prjoin=new PrismaticJointDef();
        Prjoin.bodyA=BA;
        Prjoin.bodyB=BB;
        Prjoin.localAnchorA.set(Ax,Ay);
        Prjoin.localAnchorB.set(Bx,By);
        Prjoin.collideConnected=cl;
        Prjoin.enableLimit=lm;
        Prjoin.enableMotor=M;
        Prjoin.motorSpeed=s;
        Prjoin.lowerTranslation=L;
        Prjoin.upperTranslation=U;
        Prjoin.localAxisA.set(lx,ly);
        return Prjoin;
    }

    private JointDef createWelJoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean coll, float Dm, int Fr) {
        WeldJointDef Weljoin=new WeldJointDef();
        Weljoin.bodyA=BA;
        Weljoin.bodyB=BB;
        Weljoin.collideConnected=coll;
        Weljoin.localAnchorA.set(Ax,Ay);
        Weljoin.localAnchorB.set(Bx,By);
        Weljoin.dampingRatio=Dm;
        Weljoin.frequencyHz=Fr;
        return Weljoin;
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
        BodyCr1.destroyFixture(Cr1Fix);
        BodyCr2.destroyFixture(Cr2Fix);
        BodyTr1.destroyFixture(Tr1Fix);
        BodyCu1.destroyFixture(Cu1Fix);
        BodyCu2.destroyFixture(Cu2Fix);
        BodyCr3.destroyFixture(Cr3Fix);
        BodyCr4.destroyFixture(Cr4Fix);
        BodyCu3.destroyFixture(Cu3Fix);
        BodyCu4.destroyFixture(Cu4Fix);
        BodyTr2.destroyFixture(Tr2Fix);
        BodyCr5.destroyFixture(Cr5Fix);
        BodyCr6.destroyFixture(Cr6Fix);

        Array<Joint> joints=new Array<Joint>();
        world.getJoints(joints);
        for(int i = 0; i < joints.size; i++)
        {
            world.destroyJoint(joints.get(i));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int i = 0; i < bodies.size; i++)
        {
            world.destroyBody(bodies.get(i));
        }
        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
    }

    private  float corx,cory;
    private float Fx,Fy;
    private String str1,str2,str3;
    private int cont;

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        corx=Gdx.input.getX();
        cory=Gdx.input.getY();

        if(corx<=0||corx>=W||cory<=0||cory>=H)
        {
            JoinMou.setTarget(new Vector2(0,0));
        }else{
            JoinMou.setTarget(new Vector2(Pix2Box(corx),Piy2Boy(cory)));
        }

        if(BodyCr3.getPosition().y<((-An/2)-1.5f)&&BodyCr4.getPosition().y<((-An/2)-1.5f)&&BodyCu3.getPosition().y<((-An/2)-1.5f))
        {
            BodyCr3.setTransform(BodyCr3.getPosition().x-BodyCu3.getPosition().x,BodyCr3.getPosition().y+An+5,BodyCr3.getAngle());
            BodyCr4.setTransform(BodyCr4.getPosition().x-BodyCu3.getPosition().x,BodyCr4.getPosition().y+An+5,BodyCr4.getAngle());
            BodyCu3.setTransform(0,BodyCu3.getPosition().y+An+5,BodyCu3.getAngle());
            BodyCr3.setLinearVelocity(0,0);
            BodyCr4.setLinearVelocity(0,0);
            BodyCu3.setLinearVelocity(0,0);
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        if(cont==0) {
            Fx = JoinMou.getReactionForce(1 / delta).x;
            Fy = JoinMou.getReactionForce(1 / delta).y;
            str1=df.format(Math.sqrt(Math.pow(Fx,2)+Math.pow(Fy,2)));
            str2=df.format(Fx);
            str3=df.format(Fy);
            cont=12;
        }cont=cont-1;

        batch.begin();
        font.draw(batch,"magnitud: "+str1,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y + 1.5f));
        font.draw(batch,"X: "+str2,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y));
        font.draw(batch,"Y: "+str3,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y - 1.5f))   ;
        batch.end();

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
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
