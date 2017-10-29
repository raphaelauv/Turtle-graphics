# Turtle-graphics
Interpreter and Compiler of a drawing language

<img src="https://user-images.githubusercontent.com/10202690/32149170-c1092370-bd00-11e7-95d0-30f92d8db803.png" alt="alt text" width="500" height="whatever">
<img src="https://user-images.githubusercontent.com/10202690/32149180-c80be392-bd00-11e7-8d18-66bd0ba837a8.png" alt="alt text" width="500" height="whatever">
<img src="https://user-images.githubusercontent.com/10202690/32149183-c9ba909e-bd00-11e7-8769-1ac8a0c31559.png" alt="alt text" width="500" height="whatever">
<img src="https://user-images.githubusercontent.com/10202690/32149171-c12098c0-bd00-11e7-9787-62eebcee5d5e.png" alt="alt text" width="500" height="whatever">

Build with:
JFlex 1.6.1
Java 1.7

# COMPILATION


## 1

Ouvrir un Shell puis se placer
dans le dossier /src/moteur/

jflex programme.flex
Ajouté en première ligne du fichier "/src/moteur/lexer.java" :
package moteur;

## 2
Se placer dans le dossier ADS4 DESSIN ( a la racine du dossier src) :	
			
	find ./src -name *.java > sources_list.txt
	javac  -classpath "${CLASSPATH}" @sources_list.txt

-encoding utf8 ( en cas de problème 
d'encodage des caractères des fichiers .java )


## EXECUTION

	java Demarage
