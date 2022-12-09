/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Eloy
 */
public class TowerDefense extends SimpleApplication {

    float vel = 0.1f;

    Node suelo = new Node("Suelo");
    Node cielo = new Node("Cielo");
    Node figuras = new Node("Figuras");
    Node nodeEnemigo = new Node("NodeEnemigo");
    Spatial moverEnemigo;
    Enemigo enemigo[] = new Enemigo[10];

    private final static Trigger TRIGGER_SHOOT = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private final static String DESTROY_ACTION = "Destroy";

    public static void main(String[] args) {
        TowerDefense app = new TowerDefense();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        inputManager.addMapping(DESTROY_ACTION, TRIGGER_SHOOT);
        inputManager.addListener(analogListener, new String[]{DESTROY_ACTION});

        rootNode.attachChild(nodeEnemigo);
        HUD();
        flyCam.setMoveSpeed(30f);
        cielo();
        terreno();
        assets();
        generarEnemigos();
    }

    public void cielo() {

        Box cielo01 = new Box(30, 0, 30);
        Geometry geoCielo01 = new Geometry("Cielo01", cielo01);
        Material matCielo01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matCielo01.setTexture("ColorMap", assetManager.loadTexture("Textures/cielo.jpg"));
        geoCielo01.setMaterial(matCielo01);

        Box cielo02 = new Box(0, 30, 30);
        Geometry geoCielo02 = new Geometry("Cielo01", cielo02);
        Material matCielo02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matCielo02.setTexture("ColorMap", assetManager.loadTexture("Textures/cielo.jpg"));
        geoCielo02.setMaterial(matCielo02);

        Box cielo03 = new Box(0, 30, 30);
        Geometry geoCielo03 = new Geometry("Cielo03", cielo03);
        Material matCielo03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matCielo03.setTexture("ColorMap", assetManager.loadTexture("Textures/cielo.jpg"));
        geoCielo03.setMaterial(matCielo03);

        Box cielo04 = new Box(30, 30, 0);
        Geometry geoCielo04 = new Geometry("Cielo03", cielo04);
        Material matCielo04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matCielo04.setTexture("ColorMap", assetManager.loadTexture("Textures/cielo.jpg"));
        geoCielo04.setMaterial(matCielo04);

        Box cielo05 = new Box(30, 30, 0);
        Geometry geoCielo05 = new Geometry("Cielo03", cielo05);
        Material matCielo05 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matCielo05.setTexture("ColorMap", assetManager.loadTexture("Textures/cielo.jpg"));
        geoCielo05.setMaterial(matCielo05);

        geoCielo01.move(0, 30, 0);
        geoCielo02.move(30, 0, 0);
        geoCielo03.move(-30, 0, 0);
        geoCielo04.move(0, 0, 30);
        geoCielo05.move(0, 0, -30);

        rootNode.attachChild(cielo);
        cielo.attachChild(geoCielo01);
        cielo.attachChild(geoCielo02);
        cielo.attachChild(geoCielo03);
        cielo.attachChild(geoCielo04);
        cielo.attachChild(geoCielo05);
    }

    public void terreno() {

        Box suelo01 = new Box(1, 0, 1);
        Geometry geoSuelo01 = new Geometry("Suelo", suelo01);
        Material matSuelo01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo01.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo01.setMaterial(matSuelo01);

        Box suelo02 = new Box(1, 0, 1);
        Geometry geoSuelo02 = new Geometry("Suelo", suelo02);
        Material matSuelo02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo02.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo02.setMaterial(matSuelo02);

        Box suelo03 = new Box(1, 0, 1);
        Geometry geoSuelo03 = new Geometry("Suelo", suelo03);
        Material matSuelo03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo03.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo03.setMaterial(matSuelo03);

        Box suelo04 = new Box(1, 0, 1);
        Geometry geoSuelo04 = new Geometry("Suelo", suelo04);
        Material matSuelo04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo04.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo04.setMaterial(matSuelo04);

        Box suelo05 = new Box(1, 0, 1);
        Geometry geoSuelo05 = new Geometry("Suelo", suelo05);
        Material matSuelo05 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo05.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo05.setMaterial(matSuelo05);

        Box suelo06 = new Box(1, 0, 1);
        Geometry geoSuelo06 = new Geometry("Suelo", suelo06);
        Material matSuelo06 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo06.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo06.setMaterial(matSuelo06);

        Box suelo07 = new Box(1, 0, 1);
        Geometry geoSuelo07 = new Geometry("Suelo", suelo07);
        Material matSuelo07 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo07.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo07.setMaterial(matSuelo07);

        Box suelo08 = new Box(1, 0, 1);
        Geometry geoSuelo08 = new Geometry("Suelo", suelo08);
        Material matSuelo08 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo08.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo08.setMaterial(matSuelo08);

        Box suelo09 = new Box(1, 0, 1);
        Geometry geoSuelo09 = new Geometry("Suelo", suelo09);
        Material matSuelo09 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo09.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo09.setMaterial(matSuelo09);

        Box suelo10 = new Box(1, 0, 1);
        Geometry geoSuelo10 = new Geometry("Suelo", suelo10);
        Material matSuelo10 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo10.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo10.setMaterial(matSuelo10);

        Box suelo11 = new Box(1, 0, 1);
        Geometry geoSuelo11 = new Geometry("Suelo", suelo11);
        Material matSuelo11 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo11.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo11.setMaterial(matSuelo11);

        Box suelo12 = new Box(1, 0, 1);
        Geometry geoSuelo12 = new Geometry("Suelo", suelo12);
        Material matSuelo12 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo12.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo12.setMaterial(matSuelo12);

        Box suelo13 = new Box(1, 0, 1);
        Geometry geoSuelo13 = new Geometry("Suelo", suelo13);
        Material matSuelo13 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo13.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo13.setMaterial(matSuelo13);

        Box suelo14 = new Box(1, 0, 1);
        Geometry geoSuelo14 = new Geometry("Suelo", suelo14);
        Material matSuelo14 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo14.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo14.setMaterial(matSuelo14);

        Box suelo15 = new Box(1, 0, 1);
        Geometry geoSuelo15 = new Geometry("Suelo", suelo15);
        Material matSuelo15 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo15.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo15.setMaterial(matSuelo15);

        Box suelo16 = new Box(1, 0, 1);
        Geometry geoSuelo16 = new Geometry("Suelo", suelo16);
        Material matSuelo16 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo16.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo16.setMaterial(matSuelo16);

        Box suelo17 = new Box(1, 0, 1);
        Geometry geoSuelo17 = new Geometry("Suelo", suelo17);
        Material matSuelo17 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo17.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo17.setMaterial(matSuelo17);

        Box suelo18 = new Box(1, 0, 1);
        Geometry geoSuelo18 = new Geometry("Suelo", suelo18);
        Material matSuelo18 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo18.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo18.setMaterial(matSuelo18);

        Box suelo19 = new Box(1, 0, 1);
        Geometry geoSuelo19 = new Geometry("Suelo", suelo19);
        Material matSuelo19 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo19.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo19.setMaterial(matSuelo19);

        Box suelo20 = new Box(1, 0, 1);
        Geometry geoSuelo20 = new Geometry("Suelo", suelo20);
        Material matSuelo20 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo20.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo20.setMaterial(matSuelo20);

        Box suelo21 = new Box(1, 0, 1);
        Geometry geoSuelo21 = new Geometry("Suelo", suelo21);
        Material matSuelo21 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo21.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo21.setMaterial(matSuelo21);

        Box suelo22 = new Box(1, 0, 1);
        Geometry geoSuelo22 = new Geometry("Suelo", suelo22);
        Material matSuelo22 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo22.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo22.setMaterial(matSuelo22);

        Box suelo23 = new Box(1, 0, 1);
        Geometry geoSuelo23 = new Geometry("Suelo", suelo23);
        Material matSuelo23 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo23.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo23.setMaterial(matSuelo23);

        Box suelo24 = new Box(1, 0, 1);
        Geometry geoSuelo24 = new Geometry("Suelo", suelo24);
        Material matSuelo24 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo24.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo24.setMaterial(matSuelo24);

        Box suelo25 = new Box(1, 0, 1);
        Geometry geoSuelo25 = new Geometry("Suelo", suelo25);
        Material matSuelo25 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuelo25.setTexture("ColorMap", assetManager.loadTexture("Textures/cesped.jpg"));
        geoSuelo25.setMaterial(matSuelo25);

        geoSuelo02.move(2, 0, 0);
        geoSuelo03.move(4, 0, 0);
        geoSuelo04.move(6, 0, 0);
        geoSuelo05.move(8, 0, 0);
        geoSuelo06.move(0, 0, 2);
        geoSuelo07.move(2, 0, 2);
        geoSuelo08.move(4, 0, 2);
        geoSuelo09.move(6, 0, 2);
        geoSuelo10.move(8, 0, 2);
        geoSuelo11.move(0, 0, -2);
        geoSuelo12.move(2, 0, -2);
        geoSuelo13.move(4, 0, -2);
        geoSuelo14.move(6, 0, -2);
        geoSuelo15.move(8, 0, -2);
        geoSuelo16.move(0, 0, -4);
        geoSuelo17.move(2, 0, -4);
        geoSuelo18.move(4, 0, -4);
        geoSuelo19.move(6, 0, -4);
        geoSuelo20.move(8, 0, -4);
        geoSuelo21.move(0, 0, 4);
        geoSuelo22.move(2, 0, 4);
        geoSuelo23.move(4, 0, 4);
        geoSuelo24.move(6, 0, 4);
        geoSuelo25.move(8, 0, 4);

        rootNode.attachChild(suelo);
        suelo.attachChild(geoSuelo01);
        suelo.attachChild(geoSuelo02);
        suelo.attachChild(geoSuelo03);
        suelo.attachChild(geoSuelo04);
        suelo.attachChild(geoSuelo05);
        suelo.attachChild(geoSuelo06);
        suelo.attachChild(geoSuelo07);
        suelo.attachChild(geoSuelo08);
        suelo.attachChild(geoSuelo09);
        suelo.attachChild(geoSuelo10);
        suelo.attachChild(geoSuelo11);
        suelo.attachChild(geoSuelo12);
        suelo.attachChild(geoSuelo13);
        suelo.attachChild(geoSuelo14);
        suelo.attachChild(geoSuelo15);
        suelo.attachChild(geoSuelo16);
        suelo.attachChild(geoSuelo17);
        suelo.attachChild(geoSuelo18);
        suelo.attachChild(geoSuelo19);
        suelo.attachChild(geoSuelo20);
        suelo.attachChild(geoSuelo21);
        suelo.attachChild(geoSuelo22);
        suelo.attachChild(geoSuelo23);
        suelo.attachChild(geoSuelo24);
        suelo.attachChild(geoSuelo25);
    }

    private void assets() {

        Node torre = new Node("Torre");

        Box superior = new Box(0.9f, 0.9f, 0.5f);
        Geometry geoSuperior = new Geometry("superior", superior);
        Material matSuperior = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSuperior.setTexture("ColorMap", assetManager.loadTexture("Textures/paredPiedra.jpg"));
        geoSuperior.setMaterial(matSuperior);

        Box inferior = new Box(0.7f, 0.7f, 1);
        Geometry geoinferior = new Geometry("inferior", inferior);
        Material matInferior = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matInferior.setTexture("ColorMap", assetManager.loadTexture("Textures/paredPiedra.jpg"));
        geoinferior.setMaterial(matInferior);

        Box puerta = new Box(0.02f, 0.3f, 0.2f);
        Geometry geoPuerta = new Geometry("Puerta", puerta);
        Material matPuerta = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matPuerta.setTexture("ColorMap", assetManager.loadTexture("Textures/puerta.jpg"));
        geoPuerta.setMaterial(matPuerta);

        geoSuperior.rotate(FastMath.DEG_TO_RAD * 90, 0, 0);
        geoinferior.rotate(FastMath.DEG_TO_RAD * 90, 0, 0);
        geoinferior.move(0, 1, 0);
        geoSuperior.move(0, 1.8f, 0);
        geoPuerta.move(-0.7f, 0.3f, 0);

        torre.attachChild(geoSuperior);
        torre.attachChild(geoinferior);
        torre.attachChild(geoPuerta);
        torre.move(8, 0, 0);

        rootNode.attachChild(figuras);
        figuras.attachChild(torre);

        Node arbol01 = new Node("Arbol01");

        Box tronco01 = new Box(0.1f, 0.1f, 0.1f);
        Geometry geoTronco01 = new Geometry("tronco01", tronco01);
        Material matTronco01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matTronco01.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-madera.jpg"));
        geoTronco01.setMaterial(matTronco01);

        Box hojaInferior01 = new Box(0.3f, 0.13f, 0.3f);
        Geometry geoHojaInferior01 = new Geometry("tronco01", hojaInferior01);
        Material matHojaInferior01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaInferior01.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaInferior01.setMaterial(matHojaInferior01);

        Box hojaMedia01 = new Box(0.2f, 0.13f, 0.2f);
        Geometry geoHojaMedia01 = new Geometry("tronco01", hojaMedia01);
        Material matHojaMedia01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaMedia01.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaMedia01.setMaterial(matHojaMedia01);

        Box hojaAlta01 = new Box(0.13f, 0.13f, 0.13f);
        Geometry geoHojaAlta01 = new Geometry("tronco01", hojaAlta01);
        Material matHojaAlta01 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaAlta01.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaAlta01.setMaterial(matHojaAlta01);

        geoTronco01.move(0, 0.1f, 0);
        geoHojaInferior01.move(0, 0.3f, 0);
        geoHojaMedia01.move(0, 0.5f, 0);
        geoHojaAlta01.move(0, 0.7f, 0);

        figuras.attachChild(arbol01);
        arbol01.attachChild(geoTronco01);
        arbol01.attachChild(geoHojaInferior01);
        arbol01.attachChild(geoHojaMedia01);
        arbol01.attachChild(geoHojaAlta01);

        arbol01.move(7, 0, 2);

        Node arbol02 = new Node("Arbol02");

        Box tronco02 = new Box(0.1f, 0.1f, 0.1f);
        Geometry geoTronco02 = new Geometry("tronco01", tronco02);
        Material matTronco02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matTronco02.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-madera.jpg"));
        geoTronco02.setMaterial(matTronco02);

        Box hojaInferior02 = new Box(0.3f, 0.13f, 0.3f);
        Geometry geoHojaInferior02 = new Geometry("tronco01", hojaInferior01);
        Material matHojaInferior02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaInferior02.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaInferior02.setMaterial(matHojaInferior02);

        Box hojaMedia02 = new Box(0.2f, 0.13f, 0.2f);
        Geometry geoHojaMedia02 = new Geometry("tronco01", hojaMedia02);
        Material matHojaMedia02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaMedia02.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaMedia02.setMaterial(matHojaMedia02);

        Box hojaAlta02 = new Box(0.13f, 0.13f, 0.13f);
        Geometry geoHojaAlta02 = new Geometry("tronco01", hojaAlta02);
        Material matHojaAlta02 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaAlta02.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaAlta02.setMaterial(matHojaAlta02);

        geoTronco02.move(0, 0.1f, 0);
        geoHojaInferior02.move(0, 0.3f, 0);
        geoHojaMedia02.move(0, 0.5f, 0);
        geoHojaAlta02.move(0, 0.7f, 0);

        figuras.attachChild(arbol02);
        arbol02.attachChild(geoTronco02);
        arbol02.attachChild(geoHojaInferior02);
        arbol02.attachChild(geoHojaMedia02);
        arbol02.attachChild(geoHojaAlta02);

        arbol02.move(6, 0, -2);

        Node arbol03 = new Node("Arbol03");

        Box tronco03 = new Box(0.1f, 0.1f, 0.1f);
        Geometry geoTronco03 = new Geometry("tronco01", tronco03);
        Material matTronco03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matTronco03.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-madera.jpg"));
        geoTronco03.setMaterial(matTronco03);

        Box hojaInferior03 = new Box(0.3f, 0.13f, 0.3f);
        Geometry geoHojaInferior03 = new Geometry("tronco03", hojaInferior03);
        Material matHojaInferior03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaInferior03.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaInferior03.setMaterial(matHojaInferior03);

        Box hojaMedia03 = new Box(0.2f, 0.13f, 0.2f);
        Geometry geoHojaMedia03 = new Geometry("tronco03", hojaMedia03);
        Material matHojaMedia03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaMedia03.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaMedia03.setMaterial(matHojaMedia03);

        Box hojaAlta03 = new Box(0.13f, 0.13f, 0.13f);
        Geometry geoHojaAlta03 = new Geometry("tronco03", hojaAlta03);
        Material matHojaAlta03 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaAlta03.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaAlta03.setMaterial(matHojaAlta03);

        geoTronco03.move(0, 0.1f, 0);
        geoHojaInferior03.move(0, 0.3f, 0);
        geoHojaMedia03.move(0, 0.5f, 0);
        geoHojaAlta03.move(0, 0.7f, 0);

        figuras.attachChild(arbol03);
        arbol03.attachChild(geoTronco03);
        arbol03.attachChild(geoHojaInferior03);
        arbol03.attachChild(geoHojaMedia03);
        arbol03.attachChild(geoHojaAlta03);

        arbol03.move(3, 0, -2.5f);

        Node arbol04 = new Node("Arbol04");

        Box tronco04 = new Box(0.1f, 0.1f, 0.1f);
        Geometry geoTronco04 = new Geometry("tronco01", tronco04);
        Material matTronco04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matTronco04.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-madera.jpg"));
        geoTronco04.setMaterial(matTronco04);

        Box hojaInferior04 = new Box(0.3f, 0.13f, 0.3f);
        Geometry geoHojaInferior04 = new Geometry("tronco03", hojaInferior04);
        Material matHojaInferior04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaInferior04.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaInferior04.setMaterial(matHojaInferior04);

        Box hojaMedia04 = new Box(0.2f, 0.13f, 0.2f);
        Geometry geoHojaMedia04 = new Geometry("tronco03", hojaMedia04);
        Material matHojaMedia04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaMedia04.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaMedia04.setMaterial(matHojaMedia04);

        Box hojaAlta04 = new Box(0.13f, 0.13f, 0.13f);
        Geometry geoHojaAlta04 = new Geometry("tronco03", hojaAlta04);
        Material matHojaAlta04 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matHojaAlta04.setTexture("ColorMap", assetManager.loadTexture("Textures/textura-hojas.jpg"));
        geoHojaAlta04.setMaterial(matHojaAlta04);

        geoTronco04.move(0, 0.1f, 0);
        geoHojaInferior04.move(0, 0.3f, 0);
        geoHojaMedia04.move(0, 0.5f, 0);
        geoHojaAlta04.move(0, 0.7f, 0);

        figuras.attachChild(arbol04);
        arbol04.attachChild(geoTronco04);
        arbol04.attachChild(geoHojaInferior04);
        arbol04.attachChild(geoHojaMedia04);
        arbol04.attachChild(geoHojaAlta04);

        arbol04.move(1, 0, -2);
    }

    private void HUD() {
        Box puntero = new Box(1, 1, 1);
        Geometry geoPuntero = new Geometry("puntero", puntero);
        Material matPuntero = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matPuntero.setColor("Color", ColorRGBA.White);
        geoPuntero.setMaterial(matPuntero);
        geoPuntero.setLocalTranslation(settings.getWidth() / 2, settings.getHeight() / 2, 0);
        geoPuntero.scale(4);
        guiNode.attachChild(geoPuntero);
    }

    public void generarEnemigos() {
        String nombre;
        float velocidad = 0.6f;

        for (int i = 0; i <=9; i++) {
            nombre = "Enemigo" + i;
            generarEnemigo(nombre, velocidad, i);
            nodeEnemigo.attachChild(enemigo[i].getGeometria());
            velocidad = velocidad + vel;
        }
    }

    public void generarEnemigo(String nombre, float velocidad, int numero) {
        System.out.println(nombre);
        enemigo[numero] = new Enemigo(nombre, velocidad);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.randomColor());
        enemigo[numero].getGeometria().setMaterial(mat);
    }

    public void movimiento(float tpf) {
        for (int i = 0; i <=9; i++) {
            if (enemigo[i].velocidad != 0) {
                moverEnemigo = rootNode.getChild(enemigo[i].getNombre());
                moverEnemigo.move(tpf * enemigo[i].getVelocidad(), 0, 0);
            }
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        movimiento(tpf);
        int cont = 0;
                            int j = 0;
                            while(j == 9){
                                if(enemigo[j].getVelocidad() == 0){
                                    cont++;
                                }
                                j++;
                            }
                            if(cont == 9){
                                generarEnemigos();
                                vel++;
                            }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float identy, float tpf) {
            if (name.equals(DESTROY_ACTION)) {

                CollisionResults result = new CollisionResults();
                Ray ray = new Ray(cam.getLocation(), cam.getDirection());
                rootNode.collideWith(ray, result);

                if (result.size() > 0) {
                    Geometry target = result.getClosestCollision().getGeometry();
                    for (int i = 0; i <=9; i++) {
                        if (target.getName() == enemigo[0].getNombre()
                                || target.getName() == enemigo[1].getNombre()
                                || target.getName() == enemigo[2].getNombre()
                                || target.getName() == enemigo[3].getNombre()
                                || target.getName() == enemigo[4].getNombre()
                                || target.getName() == enemigo[5].getNombre()
                                || target.getName() == enemigo[6].getNombre()
                                || target.getName() == enemigo[7].getNombre()
                                || target.getName() == enemigo[8].getNombre()
                                || target.getName() == enemigo[9].getNombre()) {
                            target.removeFromParent();
                            if (target.getName() == enemigo[i].getNombre()) {
                                enemigo[i].setVelocidad(0);
                            }
                            
                        }
                    }
                }
            }
        }
    };
}
