# Informatik Tools

> Eine Sammlung von Informatiksystemen, Programmen, Algorithmen und Texten für den Informatikunterricht.

In diesem Repository sind eine Reihe von Informatiksystemen, Programmen, Algorithmen und Texten gesammelt, die für die Nutzung im Informatikunterricht bestimmt sind. Zu den immer wiederkehrenden Themenfeldern in den verschiedenen Curricula bietet sich die Sammlung von erprobten didaktischen Systemen an, die zur Planung und Durchführung von Informatikunterricht genutzt werden können.

Beispiele sind Gerüste einer Sortiermaschine, in der Schülerinnen und Schüler eigene Sortieralgorithmen implementieren können, oder aber ein geschlossenes Informatiksystem, mit dessen Hilfe Suchalgorithmen handlungsorientiert erkundet werden können.

Alle Tools in diesem Repository sind von Informatiklehrkräften speziell für den Einsatz im Unterricht entwickelt worden. Eine kuratierte Liste von weiteren, großartigen Werkzeugen für den Informatikunterricht kann unter https://github.com/jneug/awesome-informatik-tools abgerufen werden.


## Projektversionen mit jml.py erzeugen

Für die Projekte gibt es in der Regel zwei Versionen: Eine Musterlösung und eine Schülerversion. Manchmal gibt es auch mehrere Versionen, die zum Beispiel zur Differenzierung in einem heterogenen Kurs eingesetzt werden können. Sie enthalten z.B. mal mehr Hilfestellungen oder komplexere Aufgaben. 

Da es mühsam ist, diese Versionen von Hand zu erstellen und aktuell zu halten, werden sie mit dem Python-Skript `jml.py` aus einer Basisversion erzeugt. Diese enthält den gesamten Quellcode eines Projektes. Teile können durhc speziell markierte Kommentare von bestimmten Versionen ausgeschlossen oder nur für diese eingefügt werden. `jml.py` generiert dann aus dem Projekt die geünschte Anzahl an Projektversionen.