### Beschrijving
Kort gezegd is mijn app een point en click puzzelspelletje. Dit houdt in dat de gebruiker een bepaald doel moet bereiken door op een zekere
volgorde items aan te klikken en puzzels op te lossen. Bij mijn spel is dit doel het laten ontsnappen van de hoofdpersoon, de kuikensprite, 
uit de legbatterij. Hiervoor moet de gebruiker praten met een andere kip en een slang, en op bepaalde items klikken voor hints. Bij het klikken op bepaalde
voorwerpen (de kip op een bepaald moment en het stuk papier) opent zich een nieuw scherm met een hint of een puzzel. 
Ten slotte is er een muteknop waarmee de gebruiker de geluiden van het spel kan uitzetten. 

### Technisch Ontwerp
Mijn code heeft twee belangrijke Activity's waar andere activity's naartoe leiden of van waaruit ze worden geopend. Dit zijn MainActivity en 
EscapeActivity. Wanneer het spel opent, wordt de gebruiker eerst naar startActivity geleid. Vanuit hier kan er alleen op "start =" worden geklikt
waardoor men in MainActivity terecht komt. Vanuit hier kan men naar ScrollActivity en LockActivity, waarna men meteen weer terugkomt in main.
Uiteindelijk als alle puzzels opgelost zijn, kan de gebruiker naar EscapeActivity via een onClickListener op de toiletsprite. In EscapeActivity
kan men terug naar MainActivity door op "back" te klikken, aangezien er in MainActivity een code zit die men in EscapeActivity nodig heeft. 
Als de puzzel in EscapeActivity is opgelost, kan men via een onClickListener op de deurknop naar WinActivity. Hier zit alleen een button waarmee
men terug kan naar MainActivity om het spel opnieuw te spelen.
Verder zijn er drie handlersklassen die functies bevatten waar alle activtities gebruik van moeten kunnen maken. Ten eerste is er SpriteHandler,
die alle functies bevat ie de bewegingen van de sprites uitvoeren. Daarnaast is er TextHandler, die de functies bevat die de teksten laten oppoppen
en zorgt dat ze weer weg gaan op het juiste moment. Ten sotte is er AudioHandler, een vrij simpele klasse die checkt of de gebruiker de mutebutton
heeft gebruikt en alss dit niet het geval is de geluiden afspeelt. 
Voor een meer uitgebreid overzicht van al mijn klassen en activities, zie onderstaand diagram. 

![alt text] (https://github.com/tinkazorge/PointClickGame/blob/master/doc/DesignFinal%20(4).png "Klassediagram")

### Uitdagingen
Eén van de dingen die me het meeste tijd heeft gekost is het proberen te implementeren van functionaliteiten die op een veel gemakkelijker manier konden worden gedaan. 

#### Besturing
Zo ben ik en tijd bezig geweest met het laten bewegen van de hoofdsprite door de x en y locatie proberen te verkrijgen. Uiteindelijk bleek het gemakkelijker te zijn om gewoon de margins van de sprite op te vragen en deze te updaten wanneer de gebruiker op de pijltjestoetsen klikt. Ik wilde eigenlijk dat de sprite naar elke plek zou lopen waar de gebruiker
op tikte in het scherm, maar heb uiteindelijk pijltjestoetsen gekozen omdat deze voor de gebruiker minder frustrerend zijn om mee te werken. Wat als de gebruiker naar een plek wil lopen waarvan ik niet wil dat hij hierheen loopt? de pijltjestoetsen schelen een hoop onnodige kliks op allerlei plekken. 

#### Klassen
In mijn initiële idee over de technische vormgeving wilde ik klassen implementeren voor alle sprites die een hoop functionaliteiten hebben. Zo zou er een klasse komen voor het kuiken, voor de kip en de slang. Dit vond ik echter toch niet nodig. Ik heb toen een klasse gemaakt genaamd ClickObjects, waarin de functionaliteiten van alle geklikte objecten in zou komen te staan. Ook dit vond ik uiteindelijk niet de beste manier, omdat ik in de onClickListener veel gebruik maak van ImageViews en TextViews en dusn alle sprites dubbel zou moeten definieren in hun nieuwe klasse, of heel veel variabelen zou moeten doorgeven, wat me onhandig en ingewikkeld leek.. 
De andere klasse die ik wilde maken is TextPopUp, en iets vergeijkbaars heb ik uiteindelijk wel gedaan. 
Wat me vooral opviel in de onClickListeners was dat een hoop code overeenkwam. Functies die bijvoorbeeld de sprites zichtbaar en onzichtbaar maakten waren telkens weer nodig, voor bijna alle sprites, maar ook in meerdere activities. Toen heb ik een klasse gemaakt genaamd Settings, die al deze functies die in meerdere activities werden gebruikt beheerde. Dit werd echter een vrij lange klasse met een hoop functies met verschillende functionaliteiten. Deze klasse heb ik dus uiteindelijk opgesplitst in spriteHandler, textHandler en audioHandler. Op deze manier wordt door de klassenaam meteen duidelijk wat voor globale functie de mehod vervult. 

#### Bugs
Ik ben natuurlijk tegen een aantal bugs aangelopen, maar niets onoverkomelijks. Dit komt mede omdat ik creatief gezien veel ruimte heb binnen het spel om bepaalde dingen te schrappen wanneer ze niet werken en te vervangen voor andere functionaliteiten. In het begin had ik per ongeluk een nieuwe Git repository aangemaakt toen ik mijn spel opsloeg, en het heeft me wat tijd en moeite gekost om deze weer te combineren met mijn andere repository waar de documenten al instonden, waarbij ik wat nieuwe dingen heb geleerd over mergen met git bash. Een dingetje waar ik een tijd mee heb gestruggeld is ActivityForResult, maar uiteindelijk ben ik tevreden met het resultaat, alhoewel ik niet helemaal tevreden ben met het feit dat ik een deel van de code in de ActivityOnResult functie heb moeten zetten. Een andere bug die ik kreeg op het einde was een memoryError; na uren zoeken bleek dat ik mijn spel te groot was voor de emulator maar het op telefoons wel gewoon deed. 

#### Animatie
Uiteindelijk heb ik besloten geen animatie te doen van de slang die de eieren en de kip opeet en uit de wc omhoog komt en weer naar beneden gaat. In plaats daarvan heb ik geluiden toegevoegd die duidelijk maken wat er precies gebeurd is (hapgeluiden voor de slang, klikgeluiden wanneer er sloten worden geopend). Hier heb ik een mutebutton voor toegevoegd, zodat de speler niet het spel uithoeft om de geluidjes uit te zetten.

#### API
Van te voren was ik van plan een API te gaan gebruiken zoals Unity of AndEngine, maar dit vond ik uiteindelijk niet nodig. Voor animaties was het uiteindelijk misschien toch handig geweest, maar zonder API en met alleen Android Studio ben ik een heel eind gekomen. 

### Beslissingen
Er zijn een aantal dingen die ik anders heb gedaan dan ik in het begin had gedacht. Zo was mijn MVP één level, maar het leek me beter om twee levels te maken aangezien het niet handig was om te veel clickable objects in een scherm te plaatsen, anders zouden ze overlappen en werd het spel moeilijker te spelen. Daarnaast is mijn klasse-indeling totaal veranderd. Op de manier waarop ik het nu doe doe kunnen alle activities op een overzichtelijke manier gebruik maken van functies uit de Handlerklassen. Ten slotte heb ik het idee van animaties geschrapt, omdat het te veel werk zou zijn in een korte tijd om een mooie animatie te creeëren; hier was een API misschien handig voor geweest. 




