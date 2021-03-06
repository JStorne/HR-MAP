package com.hr.hrmap;

import android.util.Log;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinxi on 3-7-16.
 */
public class Plattegrond {

    UndirectedGraph<Locatie, DefaultEdge> g =  new SimpleGraph<Locatie, DefaultEdge>(DefaultEdge.class);
    List<Locatie> locaties = new ArrayList<>();
    List<Locatie> path;
    Locatie start;
    Locatie destination;

    public Plattegrond() {
        this.initLocaties();
    }

    private void initLocaties()
    {
        Locatie lift = new Locatie(1325, 180, "lift", 30, 1);
        lift.visible = true;
        this.locaties.add(lift); //lift
        this.g.addVertex(lift);

        Locatie m1 = new Locatie(1000, 400, "m1", 30, 1);
        this.locaties.add(m1); //m1
        this.g.addVertex(m1);
        this.g.addEdge(lift, m1);

        Locatie m2 = new Locatie(800, 400, "m2", 30, 1);
        this.locaties.add(m2); //m2
        this.g.addVertex(m2);
        this.g.addEdge(m1, m2);

        Locatie m3 = new Locatie(600, 400, "m3", 30, 1);
        this.locaties.add(m3); //m3
        this.g.addVertex(m3);
        this.g.addEdge(m2, m3);


        Locatie m4 = new Locatie(400, 400, "m4", 30, 1);
        this.locaties.add(m4); //m1
        this.g.addVertex(m4);
        this.g.addEdge(m3, m4);

        Locatie h1_110 = new Locatie(660, 180, "H.1.101", 30, 1);
        this.locaties.add(h1_110); //H.1.110
        this.g.addVertex(h1_110);
        this.g.addEdge(m3, h1_110);

        Locatie h1_112 = new Locatie(790, 180, "H.1.102", 30, 1);
        this.locaties.add(h1_112); //H.1.112
        this.g.addVertex(h1_112);
        this.g.addEdge(m2, h1_112);

        Locatie h1_405 = new Locatie(300, 400, "H.1.405", 30, 1);
        this.locaties.add(h1_405); //H.1.114
        this.g.addVertex(h1_405);
        this.g.addEdge(m4, h1_405);

        Locatie h1_104 = new Locatie(930, 180, "H.1.104", 30, 1); //H.1.114
        this.locaties.add(h1_104); //H.1.114
        this.g.addVertex(h1_104);
        this.g.addEdge(m1, h1_104);


        Locatie h1_403 = new Locatie(300, 525, "H.1.403", 30, 1);
        this.locaties.add(h1_403); //H.1.403
        this.g.addVertex(h1_403);
        this.g.addEdge(m4, h1_403);


        Locatie h1_319 = new Locatie(410, 570, "H.1.319", 30, 1);
        this.locaties.add(h1_319); //H.1.319
        this.g.addVertex(h1_319);
        this.g.addEdge(m4, h1_319);

        Locatie h1_318 = new Locatie(480, 570, "H.1.318", 30, 1);
        this.locaties.add(h1_318); //H.1.318
        this.g.addVertex(h1_318);
        this.g.addEdge(m3, h1_318);

        Locatie h1_315 = new Locatie(680, 570, "H.1.315", 30, 1);
        this.locaties.add(h1_315); //H.1.315
        this.g.addVertex(h1_315);
        this.g.addEdge(m2, h1_315);

        Locatie h1_312 = new Locatie(860, 570, "H.1.312", 30, 1);
        this.locaties.add(h1_312); //H.1.312
        this.g.addVertex(h1_312);
        this.g.addEdge(m1, h1_312);


        Locatie h1_306 = new Locatie(1050, 570, "H.1.306", 30, 1);
        this.locaties.add(h1_306); //H.1.306
        this.g.addVertex(h1_306);
        this.g.addEdge(m1, h1_306);

        Locatie h1_206 = new Locatie(1380, 525, "H.1.206", 30, 1);
        this.locaties.add(h1_206); //H.1.206
        this.g.addVertex(h1_206);
        this.g.addEdge(lift, h1_206);
        this.g.addEdge(m1, h1_206);

        Locatie h1_204 = new Locatie(1390, 380, "H.1.204", 30, 1);
        this.locaties.add(h1_204); //H.1.204r
        this.g.addVertex(h1_204);
        this.g.addEdge(lift, h1_204);

        /*
        Tweede verdieping
         */
        Locatie h2_lift = new Locatie(1300, 180, "lift", 30, 2);
        this.locaties.add(h2_lift); //H.1.204
        this.g.addVertex(h2_lift);
        this.g.addEdge(lift, h2_lift);

        Locatie n1 = new Locatie(1000, 400, "n1", 30, 2);
        this.locaties.add(n1); //m1
        this.g.addVertex(n1);
        this.g.addEdge(h2_lift, n1);

        Locatie n2 = new Locatie(800, 400, "n2", 30, 2);
        this.locaties.add(n2); //m2
        this.g.addVertex(n2);
        this.g.addEdge(n1, n2);

        Locatie n3 = new Locatie(600, 400, "n3", 30, 2);
        this.locaties.add(n3); //m3
        this.g.addVertex(n3);
        this.g.addEdge(n2, n3);


        Locatie n4 = new Locatie(400, 400, "n4", 30, 2);
        this.locaties.add(n4); //m1
        this.g.addVertex(n4);
        this.g.addEdge(n3, n4);

        Locatie h2_109 = new Locatie(620, 180, "H.2.109", 30, 2);
        this.locaties.add(h2_109); //H.2.109
        this.g.addVertex(h2_109);
        this.g.addEdge(n3, h2_109);

        Locatie h2_110 = new Locatie(690, 180, "H.2.110", 30, 2);
        this.locaties.add(h2_110); //H.2.110
        this.g.addVertex(h2_110);
        this.g.addEdge(n3, h2_110);

        Locatie h2_111 = new Locatie(740, 180, "H.2.111", 30, 2);
        this.locaties.add(h2_111); //H.2.111
        this.g.addVertex(h2_111);
        this.g.addEdge(n2, h2_111);

        Locatie h2_112 = new Locatie(820, 180, "H.2.112", 30, 2);
        this.locaties.add(h2_112); //H.2.112
        this.g.addVertex(h2_112);
        this.g.addEdge(n2, h2_112);

        Locatie h2_114 = new Locatie(960, 180, "H.2.114", 30, 2);
        this.locaties.add(h2_114); //H.2.114
        this.g.addVertex(h2_114);
        this.g.addEdge(n1, h2_114);

        Locatie h2_405 = new Locatie(300, 400, "H.2.405", 30, 2);
        this.locaties.add(h2_405); //H.2.403
        this.g.addVertex(h2_405);
        this.g.addEdge(n4, h2_405);

        Locatie h2_403 = new Locatie(300, 525, "H.2.403", 30, 2);
        this.locaties.add(h2_403); //H.2.403
        this.g.addVertex(h2_403);
        this.g.addEdge(n4, h2_403);

        Locatie h2_319 = new Locatie(400, 550, "H.2.319", 30, 2);
        this.locaties.add(h2_319); //H.2.319
        this.g.addVertex(h2_319);
        this.g.addEdge(n4, h2_319);

        Locatie h2_318 = new Locatie(680, 550, "H.2.315", 30, 2);
        this.locaties.add(h2_318); //H..318
        this.g.addVertex(h2_318);
        this.g.addEdge(n2, h2_318);
        this.destination = h2_318;

        Locatie h2_312 = new Locatie(890, 550, "H.2.312", 30, 2);
        this.locaties.add(h2_312); //H.2.312
        this.g.addVertex(h2_312);
        this.g.addEdge(n1, h2_312);


        Locatie h2_308 = new Locatie(1150, 550, "H.2.308", 30, 2);
        this.locaties.add(h2_308); //H.2.308
        this.g.addVertex(h2_308);
        this.g.addEdge(n1, h2_308);

        Locatie h2_309 = new Locatie(1100, 550, "H.2.309", 30, 2);
        this.locaties.add(h2_309); //H.2.309
        this.g.addVertex(h2_309);
        this.g.addEdge(n1, h2_309);

        Locatie h2_206 = new Locatie(1369, 525, "H.2.206", 30, 2);
        this.locaties.add(h2_206); //H.1.206
        this.g.addVertex(h2_206);
        this.g.addEdge(h2_lift, h2_206);

        Locatie h2_204 = new Locatie(1369, 370, "H.2.204", 30, 2);
        this.locaties.add(h2_204); //H.1.204
        this.g.addVertex(h2_204);
        this.g.addEdge(h2_lift, h2_204);

    }

    public void calculatePath()
    {
        DijkstraShortestPath path = new DijkstraShortestPath<>(g, this.start, this.destination);
        this.path = Graphs.getPathVertexList(path.getPath());
    }

    public void reset()
    {
        this.start = null;
        this.destination = null;
        this.path = null;
        this.clearVisibility();
    }

    /**
     * Get the shortest route
     *
     * @return
     */
    public List<Locatie> getPath()
    {
        return this.path;
    }

    /**
     * Return a list of rooms of a given floor. Only support 1 and 2 at the moment.
     *
     * @param verdieping
     * @return
     */
    public List<Locatie> getLocatiesVanVerdieping(int verdieping)
    {
        List<Locatie> verdiepingLocaties = new ArrayList<>();
        for (Locatie locatie: locaties
             ) {
            if(locatie.verdieping == verdieping){
                verdiepingLocaties.add(locatie);
            }
        }

        return verdiepingLocaties;
    }

    public void setLocatieVisitble(int id)
    {
        for (Locatie locatie: locaties
                ) {
            if(locatie.id == id){
               locatie.visible = true;
                return;
            }
        }
    }

    public Locatie getLocatieByID(int id)
    {
        for (Locatie locatie: locaties
                ) {
            if(locatie.id == id){
                return locatie;
            }
        }

        return null;
    }


    public void setLocatieStart(int id)
    {
        for (Locatie locatie: locaties
                ) {
            if(locatie.id == id){
                locatie.visible = true;
                this.start = locatie;
                return;
            }
        }
    }


    public void clearVisibility()
    {
        for (Locatie locatie: locaties
                ) {
            if(locatie.naam.contains("lift") == false){
                locatie.visible = false;
            }
        }
    }

    public void setDestination(int id)
    {
        for (Locatie locatie: locaties
                ) {
            if(locatie.id == id){
                this.destination = locatie;
                break;
            }
        }

        if(this.destination != null){
            this.calculatePath();
        }
    }
}
