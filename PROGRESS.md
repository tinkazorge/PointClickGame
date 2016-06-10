###Dag 1 
Op de eerste dag gaat het alleen om het idee voor mijn app. Hoewel ik het verhaal al een beetje heb bedacht, moet ik nog schetsen maken en
verzinnen welke puzzels ik precies wil gebruiken. Ik heb besloten om pijltjestoetsen te gebruiken om de sprite te bewegen, dit zijn gewoon imageviews
die ik op het scherm zet. Ik heb in Photoshop een kuiken-sprite getekend, maar ik was er niet tevreden over, dus heb ik een veel meer gepixelde
getekend die ik mooier vond. ik heb ook meten de achtergrond erin gezet; hoewel ik weet dat het niet het allerbalngrijkste is, wil ik de visuals
al een beetje hebben zodat ik het echt kan testen zoals ik wil dat het wordt (bijvoorbeeld als ik een lijn moet gaan kiezen waarop de
sprite kan lopen, wil ik wel dat deze overeenkomt met de achtergrond en meteen ook maar met de startlocatie van de sprite). Ten slotte was ik van
plan om Unity te gebruiken als API, maar aan het einde van de dag wees Julian me op een gemakkelijker API, namelijk AndEngine. 

###Dag 2
Op deze designdag heb ik vooral nagedacht over alle klassen die ik wil implementeren. Ik heb wat kleinere klassen bedacht om alle functies in te 
zetten die terug te vinden zijn in het klassediagram van mijn designdocument. Ook deze heb ik vandaag gemaakt in creately, een online programma 
voor het maken van UML's. Tussendoor ben ik begonnen met het downloaden van AndEngine en deze in Android Studio te zetten. Hierbij liep ik tegen
een paar problemen aan, en toen ik Martijn om raad vroeg, gaf hij aan dat een API misschien helemaal niet nodig zou zijn. Ik heb dan ook besloten
AndEngine niet meer te gaan gebruiken, maar gewoon zelf te beginnen met implementeren. 

###Dag 3 
Het werken met onTouchClick, waarmee ik probeer te detecteren wanneer een user het scherm aanraakt zodat ik naar die locatie kan lopen, 
blijkt erg ingewikkeld. Ik krijg de coordinaten van de click steeds niet te pakken. Ik zit eraan te denken om terug te schakelen naar pijltjestoetsen 
om te kijken of dit wat simpeler te implementeren is. Ik heb verder het Design document afgerond. Ten slotte heb ik een simpel startscherm geïmplementeerd. 

###Dag 4
Ik ben begonnen met implementeren van wat er gebeurt als de gebruiker op bepaalde voorwerpen klikt. Eerst is er de doortrek; waneer de gebruikerer drie keer op klikt, verandert de tekst, en bij de vierde keer moet de slang tevoorschijn komen. Hoewel de slang nu nog abrupt verschijnt in plaats van het animatie idee dat ik in mijn hoofd had, komt er tenminste wel al iets. 

Ik benook begonnen met het implementeren en uitzoeken van de klassen, maar het is allemaal wat naar de achtergrond gezakt. Ik was een tijdje bezig met het uitvinden van de coördinaten van de sprite en hoe ik deze kon aanpassen, tot ik erachter kan dat ik de sprite vrij simpel via de layout kon verschuiven. Ook ObjectCollision blijkt niet echt nodig; ik kan de sprite gewoon naar de voorgrond verschuiven. Ik heb op dit moment erg lelijke code staan. De game doet al veel maar ik heb alles in mainactivity staan en veel dingen gecopy'd en paste'd die ik ook in een methode kan zetten. Dit is natuurlijk niet de bedoeling, maar ik wilde snel iets maken om te kunnen laten zien bij de demo en ik moet echt weer even goed kijken naar klassen en methoden om dat weer op te halen. Ik ga het weekend en misschien komende week nog besteden aan het mooi in klassen indelen van de code die ik al heb geschreven. Verder heb ik nog niet alle puzzels van het eerste level af. De slang verschijnt nu na 4 keer trekken aan de doortrek. Ook staat er een kip die aangeeft te gestresst te zijn om eieren te leggen. Als de gebruiker op zichzelf klikt, trekt de hoofdpersoon een veertje uit zijn buik. De bedoeling is dat dit veertje gebruikt kan worden als massagetool waarna de kip al kalmeren en eieren zal leggen, die aan de slang gegeven kunnen worden. 

Ten slotte heb ik het besturingssysteem aangepast. Ik gebruik nu definitief pijltjestoetsen om de sprite te bewegen. Ik heb verschillende sprites over elkaar geplakt om een enigszins bewegende animatie te krijgn. 

###Dag 5
Nadat ik veel (helaas op niet bepaald de goeie manier) heb geïmplementeerd gisteren, moet ik eigenlijk al mijn klassen gaan herzien. Ik heb DetectCollision al verwijderd, omdat dit niet nodig bleek. Ik weet nu ook niet meer zo goed wat ik met mijn klasse Chicken aanmoet, omdat het belangrijkste van deze klasse was om de coordinaten te rkijgen en te setten om de chicken te bewegen. Nu blijkt echter dat ik dat veel simpeler kan doen door de linkermarge van de sprite aan te passen. Dit staat nu in main, dus dat zou ik wel in deze klasse kunnen zetten. 

Ik heb uiteindelijk besloten de chicken class weg te den en het verschuiven van de sprite in ClickObjects te gooien. Hetzelfde geldt voor de snake class. Ze hebben uiteindelijk allebei weinig properties en ze zijn beiden klikbare objecten, waarbij iets moet gebeuren. Mijn klassen zijn nu dus ClickObjects en TextPopUp; aan beiden moeten objecten kunnen worden meegegeven. Nu ik ben begonnen met de TextPopUps klasse ben ik hier ook een beetje aan het twijfelen. Ik denk dat een aparte klasse wel handig is omdat ik erg veel tekst gebruik, maar het voelt een beetje dubbelop omdat ik hier eigenlijk hetzelfde doe als in de clickobjects klasse: een object dat is aangeklikt wordt doorgegeven en er moet iets mee gebeuren.

###Dag 6
Ik heb een nieuw scherm gemaakt met een puzzel erin die het slot van het kippenhok moet openmaken; als je het juiste antwoord invult ga je terug naar het main screen. Ik heb daarnaast een paar bugs opgelost. 

###Dag 7
Ik ben bezig geweest met het afmaken va de puzzels en het ordenen van de methods. TextPopUp en makeVisible en Invisible hebbenmijn code al iets oevrzichterlijker gemaakt. Ik heb geprobeerd van mijn puzzel een ActivityForResult te maken, maar dit bleek niet te kunnen. Nu geef ik een boolean door. Ik blijf echter steeds errors krijgen als de boolean moet worden doorgegeven. 

###Dag 8 
Ik heb mijn code laten zien en code hour, en martijn raadde me aan een subklasse te maken. Ik heb wat veel code in mijn mainactivity staan die verdeeld moet worden over wat meer klassen. Dit zal gebeuren als ik mijn clickItems klasse ga inrichten. Verder heb ik wat problemen met de gitRepository; ik moet mijn code in mijn Programmeerproject repository krijgen, want ik heb per ongeluk hier een nieuwe repository voor gemaakt waarvan ik het pad niet kan vinden en dus ook niet kan committen. 

###Dag 9
Ik heb eindelijk de boolean bug opgelost waar ik twee dagen op heb zitten prutsen: ik heb ActivityOnResult gebruikt en de hieropvolgende acties in de methode gezet. Ik moet nog kijken of dit op een mooiere manier kan, maar dit was de enige manier waarop de code lijkt te werken. Als men de puzzel oplost, komt de kip nu uit zijn hokje. 
'
###Dag 10
Voor de presentatie heb ik het level afgrond, maar het zit nog vol bugs. Belangrijk is de animaties die ik nog niet heb toegevoegd, deze gaan me veel tijd kosten, maar op dit moment verdwijnen de sprites gewoon op het einde (zoals de kippen en de slang). Ik heb ook gewerkt aan het vinden van de layoutparameters om te zorgen dat de hoofdsprite niet van het scherm af kan lopen. 
