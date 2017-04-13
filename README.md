# Tasky  
Aplikacija omogućuje dodavanje novog zadatka klikom na plusić u donjem desnom kutu, brisanje zadatka dugim klikom na zadatak 
koji se želi obrisati te izmjenu podataka za već uneseni zadatak. Zadaci se prikazuju u obliku liste. Atributi zadatka definirani su 
u klasi Task u kojoj se također omogućuje dohvaćanje i mijenjanje vrijednosti atributa. Klikom na plusić u donjem desnom kutu 
intentom se prelazi iz ListActivity-a u AddTaskActivity gdje se korisniku omogućuje unos naslova, opisa i prioriteta zadatka te klikom na tipku Add 
dohvaćaju se podatci i prosljeđuju u ListActivity u kojem se poziva funkcija iz DBHelper-a za spremanje podataka u bazu te se 
uneseni zadatak prikazuje korisniku. Dugim klikom na određeni zadatak dohvaća se id zadatka koji se prosljeđuje funkciji za brisanje 
zadatka iz baze nakon čega zadatak brišemo iz liste zadataka prikazane korisniku. Kratkim klikom na zadatak intentom se šalju podatci 
u UpdateTaskActivity u kojem je omogućena izmjena podataka za već uneseni zadatak. Korisniku se prikazuju uneseni podatci i omogućuje 
njihova promjena te klikom na gumb Save unesene promjene se spremaju u bazu i korisniku se prikazuje izmijenjena lista zadataka.  
  
Literatura i izvori:  
Predlošci s laboratorijskih vježbi  
http://stackoverflow.com/questions/30676208/how-to-create-ring-shape-drawable-in-android (Rounded button)  
http://stackoverflow.com/questions/4984313/spacing-between-listview-items-android (Item spacing)  
http://stackoverflow.com/questions/23517879/set-background-color-programmatically (Background color)  
http://stackoverflow.com/questions/23123833/edittext-automatically-go-to-a-new-line (MultiLine)  
http://stackoverflow.com/questions/1377336/how-to-make-a-listview-transparent-in-android (Transparent listview)  
https://unsplash.com/search/background?photo=U5mHl-uACe0 (Background image)  