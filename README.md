# Informatik Tools

> Eine Sammlung von Informatiksystemen, Programmen, Algorithmen und Texten für den Informatikunterricht.

In diesem Repository sind meine Projekte für den Informatikunterricht gesammelt. Ich bemühe mich, die Projekte umfassend zu dokumentieren und fehlerfrei zu halten, allerdings gelingt das in der Hitze des schulischen Alltags manchmal nur bedingt. Daher bin ich über jegliche Fehlermeldungen und Verbesserungsvorschläge dankbar. 

Die Projekte sind grob nach Themengebieten sortiert, auch, wenn sich einige Projekte nicht ganz eindeutig einsortieren lassen. Innerhalb der Themen sind die Projekte noch einmal nach Programmiersprache sortiert. 

Das Repository ist öffentlich und soll als Fundgrube für andere Lehrkräfte dienen. Daher können alle Quelltexte beliebig für den eigenen Unterricht kopiert, verändert und genutzt werden. Sofern spezielle Rechte oder Quellenangaben zu einem Projekt gehören, sind diese in der jeweiligen Readme angegeben. 

Beachte aber vor dem Einsatz die Hinweise zu Projektversionen unten.

## Eigene Projekte beisteuern

Ich bin immer auf der Suche nach guten Projektideen, deshalb würde ich mich sehr über Beiträge freuen. Wenn Du ein Projekt hast, dass Du beisteuern würdest, schick gerne einen Pull-Request oder das Projekt per Mail direkt an mich. 

Das Projekt muss keine Qualitätsansprüche erfüllen (ein Blick auf meine Projekte sollte dies deutlich machen). Eine kurze Beschreibung zum Projekt wäre aber wünschenswert. 

## Projektversionen erzeugen

Die Projekte in diesem Repository sind nicht direkt für den Einsatz im Unterricht gedacht, sondern dienen als Basisprojekte, aus denen Schülerversionen und Musterlösungen automatisiert erzeugt werden können. Manchmal gibt es auch mehrere Versionen, die zum Beispiel zur Differenzierung in einem heterogenen Kurs eingesetzt werden können. Sie enthalten z.B. mal mehr Hilfestellungen oder komplexere Aufgaben. 

Da es mühsam ist, diese Versionen von Hand zu erstellen und aktuell zu halten, wird das Tool [jml](https://github.com/jneug/jml) eingesetzt. Die Basisversionen enthalten den gesamten Quellcode der Projekte. Teile können durch speziell markierte Kommentare von bestimmten Versionen ausgeschlossen oder nur für diese eingefügt werden. `jml` generiert dann aus dem Projekt die gewünschte Anzahl an Projektversionen.

Die Projektversionen werden regelmäßig erzeugt und separat in das Repository [jneug/schule-versionen](https://github.com/jneug/schule-versionen) eingestellt. 