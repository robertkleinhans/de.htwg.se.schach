# de.htwg.se.schach

# 1. Allgemeines
Dieses Projekt stellt ein Schachspiel dar, welches die Konsole zur Textausgabe und *java.swing* zur GUI nutzt.
Der Start des Spieles erfolgt über den Aufruf der *main*-Methode der *Chess.java* Klasse.

# 2. Struktur In/Out
Das Konzept dieses Projekts ist im Prinzip schlauchartig gestaltet, verzweigt sich lediglich beim User-input und der Ausgabe.

## 2.1 User-Input
Um den User-Input auf zwei Ebenen (GUI und TUI) gleichzeitig zu erhalten verzweigt sich das Programm hier mittels Threads. Ein neuer Thread wird erzeugt, um den TUI-Input aufzunehmen, während sich die GUI eigenständig um den GUI-Input kümmert. Anschließend werden alle Inputs über die *ChessView* zum Controller geschleust.    

## 2.2 Ausgabe
Die Ausgabe wird erst durch die *ChessView* verifiziert, bevor die GUI benachrichtigt wird.

# 3. Struktur Control
Die gesamte Spiellogik wird durch den *MovementHandler* gehalten und die einzige Klasse, die mit dieser Ebene kommuniziert, ist die *ChessView*. Auch die Spielfiguren auf der *Model-Ebene* haben keinerlei Informationen über ihre Position sondern lediglich, was für eine Figur sie sind.     
Somit beinhaltet der *MovementHandler* alle Informationen über die Figuren und deren Positionen.

# 4. GUI    
In der GUI ist der erste Spieler immer *weiß* (nach dem Sprichwort "Weiß beginnt, Schwarz gewinnt"). Im Anschluss sind die Spieler abwechseln an der Reihe, um ihre Züge zu machen. Bei falschen Eingaben auf der TextUI oder ungültigen Bewegungen in der GUI wird der aktuelle Spieler benachrichtigt (nur auf der TUI).     
Ein Spieler ist so lange an der Reihe, bis er einen gültigen Zug macht. Erst dann wird gewechselt.
