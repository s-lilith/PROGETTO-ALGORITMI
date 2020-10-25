								**PROGETTO MOVIDA**
								**A.A 2019/2020**
							**CORSO DI ALGORITMI E STRUTTURE DATI**

#Presentato da:
	Sharone Oulato
	Kusamdevi Lal



**Implementazione:**
	Inizialmente è stata fatta un’implementazione base delle strutture dati in cui è stato provato a prendere dimistichezza con le funzioni, inserisci, cancella, ricerca. Una volta fatto, si è passato all’implementazione di una classe astratta “StrutturaDati” che serve per implementare le funzioni comuni al “LinkedList” e al Btree.

##LinkedList:


##BTree:
Per quanto riguarda il Btree ho deciso di implementarlo semplicemente come un albero binario, l’ordine è 2 e ho un’unica chiave. Per definizione il binarytree è sempre un btree con ordine 2. 
Per l’inserimento dei film, all’interno del’albero, è stata implementata una funzione compareTo che dà un’ordine crescente(da riguardare) rispetto alle stringhe. 
(spiegare come è stata fatta questa cosa).

Poi sono state implementate le funzioni search, delete, getMovies.
    • Per la funzione search, che prende come parametri di input un nodo e il film, a seconda che la “stringa” di input sia più grande o più piccola, cerca rispettivamente nel ramo dx o sx corrispettivamente.

    • Per la funzione delete, si cerca il nodo da cancellare, guardare I rispettivi casi, se il nodo non ha figli, si cancella semplicemente il nodo, se il nodo invece ha figli, bisogna cercare il nodo che deve essere sostituito al nodo target.

    • La funzione getMovies invece scorre tutto l’albero e memorizza I dati in un array e questo viene dato come risultato della funzione.

La classe “StrutturaDati” è semplicemente una classe astratta che raggruppa le funzioni in comune che possono essere usate da entrambe le strutture dati.

##MovidaCore:
E infine abbiamo MovidaCore, dove vengono implementate le funzioni richieste.
Grazie alla classe “StrutturaDati” si è riusciti ad ottenere le informazioni richieste.
	

Con LoadFromFile (f), è stata fatta la lettura delle varie stringhe sul file. 




