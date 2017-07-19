# Voronoi Sample
This is a toy application for visualing the Voronoi Diagram given a set of sites.
<br/>
![Vornoi sample screenshot](https://raw.githubusercontent.com/andreaiacono/andreaiacono.github.io/master/img/voronoi.png)

## Project
The application has a Swing GUI to show the diagram. On the right, there's a control panel to chosse the distance metric to use and the number of sites to show.
In the lower part of the panel, there's "Start Animation" button which starts moving randomly the positions of the sites and updating the diagrams accordingly.
 
## Requirements

* Java 8 (with the java executable available in path)

## Usage
The project is built with Maven, so to create the JAR you just need to launch:

    mvn clean install
    
and then launch it:

    java -jar target/Voronoi.jar
    

## Improvements
Implementing Fortune's Algorithm for lowering time complexity to O(n log n)
