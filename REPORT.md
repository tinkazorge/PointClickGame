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
Voor een meer uitgebreid overicht van al mijn klassen en activities, zie onderstaand diagram. 

![alt text] (https://github.com/tinkazorge/PointClickGame/blob/master/doc/DesignFinal%20(4).png "Klassediagram")


