WBA2 Phase 2
===========

##Ausarbeitung eines Szenarios

Im Folgenden wird ein Szenario für das Modul Web-basierte Anwendungen 2: verteilte Systeme ausgearbeitet. Zunächst wurden mögliche Szenarien grob betrachtet und in Frage kommende ausgearbeitet. Nach der detaillierten Erarbeitung zweier Szenarien wurde Pro und Kontra abgewägt, sodass sich letztendlich für das Sport-Szenario entschieden wurde.

Das Szenario beschreibt den Bedarf nach Informationserhalt bezüglich Sportveranstaltungen. Hierbei wird weder zwischen Alter noch Geschlecht unterschieden. Entsprechend ergibt sich eine weitreichende Zielgruppe. In der bevorstehenden Phase soll nun jenes Szenario in Form einer Anwendung realisiert werden. 

Die zu entwickelnde Anwendung soll den Anwender über bevorstehende Sportveranstaltungen sowie allgemeine Sportarten informieren und benachrichtigen. 
Dazu werden sowohl synchrone, als auch asynchrone Datenübertragungsarten einbezogen.
Der allgemeine Informationsabruf für Informationen bezüglich Sportveranstaltungen wird synchron realisiert. Diese Auskunft werden beispielsweise Sportarten, Sportgruppen (Rückschlagsport, Kampfsport, Schwimmen,...), Termine, Orte, Vorraussetzungen, Preise etc. beinhalten.
Veranstalter haben die Möglichkeit neue Events zu erstellen und benötigte Räume/Materialien,. zu reservieren, wobei diese Räume/Materialien,... ebenfalls Ressourcen darstellen.

Im Gegenzug dazu werden Abonnements sowie Benachrichtigungen zu einer abonnierten Sportgruppe (Rückschlagsportarten, Kampfsportarten,..), Sportart (Tennis, Judo,..), Räumlichkeit (Tennisplatz, Sporthalle,..)  oder Materialien (Bälle, Schläger, Judomatten,...) asynchron verwirklicht. Wobei die an Sportveranstaltungen Interessierten lediglich Ressourcen wie Sportgruppe und Sportart abonnieren können, wohingehen Veranstalter auch Räumlichkeiten und Materialien für ihre Veranstaltung reservieren und abonnieren (zwecks Verfügbarkeitsprüfung) können.
Ebenfalls werden für bereits bestehende Veranstaltungen Terminänderungen wie Ort- oder Zeitänderungen asynchron mitgeteilt indem der Veranstalter/Leiter/Trainer diese publiziert.
