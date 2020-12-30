								**PROGETTO MOVIDA**
								**A.A 2019/2020**
							**CORSO DI ALGORITMI E STRUTTURE DATI**

Presentato da:
Kusamdevi Lal \n
Sharone Oulato



**Implementazione:**
	Inizialmente è stata fatta un’implementazione base delle strutture dati in cui è stato provato a prendere dimistichezza con le funzioni, inserisci, cancella, ricerca. Una volta fatto, si è passato all’implementazione di una classe astratta “StrutturaDati” che serve per implementare le funzioni comuni al “BTree" e alla "LinkedList".
    Analogo procedimento è stato per gli algoritmi di ordinamento. Dove sono state implementate gli algoritmi dati, BubbleSort e MergeSort, che poi vengono raggruppati nella classe Sorting.

##LinkedList:


##BTree:
Per quanto riguarda il Btree ho deciso di implementarlo semplicemente come un albero binario, l’ordine è 2 e ho un’unica chiave. Per definizione il binarytree è sempre un btree con ordine 2. 
Per l’inserimento dei film, all’interno del’albero, è stata implementata una funzione compareTo, che mette in ordine alfabetico le stringhe. In questo, in base al nome dei film.

Poi sono state implementate le funzioni search, delete, getMovies.
    - Per la funzione search, che prende come parametri di input un nodo e il film, a seconda che la “stringa” di input sia più grande o più piccola, cerca rispettivamente nel ramo dx o sx corrispettivamente.
    - Per la funzione delete, si cerca il nodo da cancellare e  si guardano i rispettivi casi, se il nodo non ha figli, si cancella semplicemente il nodo, se il nodo invece ha figli, bisogna cercare il nodo che deve essere sostituito al nodo target.
    - La funzione getMovies invece scorre tutto l’albero e memorizza I dati in un array e questo viene dato come risultato della funzione.

La classe “StrutturaDati” è semplicemente una classe astratta che raggruppa le funzioni in comune che possono essere usate da entrambe le strutture dati.

##MovidaCore:
E infine abbiamo MovidaCore, dove vengono implementate le funzioni richieste.
Grazie alla classe “StrutturaDati” si è riusciti ad ottenere le informazioni richieste.
	
Con searchMoviesByTitle (String title), viene fatta la ricerca del film per titolo, nel caso è presente, questo viene restituito, altrimenti no.

Con searchMoviesInYear(Integer year), vegnono restituiti i film girati nell'anno che è stato passato come parametro.

Con searchMoviesDirectedBy(String name), vengono restituiti i film che sono stati girati dal regista passato come parametro.

Con searchMoviesStarredBy(String name) vengono restituiti i film che sono stati recitati dall’attore che viene passato come parametro.

Con searchMostVotedMovies(Integer N) vengono restituiti gli N film più votati.

Con searchMostRecentedMovies(Integer N) vengono restituiti gli N film più recenti.

Con searchMostActiveActors(Integer N) vengono restituiti gli attori che hanno partecipato a più film.

Con LoadFromFile (File f), è stata implementata la funzione di lettura del file dato. 
Viene usata prima la funzione di BufferReader, grazie alla quale ho contato il numero di righe presenti sul file, contando ovviamente anche gli spazi.
Sapendo la "struttura" presente sul file, ho diviso il numero ottenuto per 6. Nella prima riga si ha il titolo, nella seconda riga, l'anno e così via. 
Poi c'è stata l'effettiva lettura delle righe presenti. 
In ogni ciclo viene letta la riga, viene salvata e da questa venivano rimossi i caratteri "non utili". Una volta fatto, le variabili venivano date per creare l'oggetto Movie.

Con saveToFile(File f), è stata implementata la funzione di scrittura del file. Vengono presi i film salvati e vengono poi scritti sul file.

Con clear(), i dati memorizzati sulla struttura dati vengono cancellati.

Con countMovies(), vengono restituiti il numero dei film presenti.

Con countPeople(), vengono restituiti il numero di presone che ha partecipato al film, quindi il numero del cast, più i registi.

Con deleteMovieByTitle(String Title), viene cancellato dalla struttura dati il film che viene passato come parametro.

Con getMovieByTitle(String name) vengono restituite le informazioni inerenti al film passato per parametro, nel caso questo sia presente nel DB.

Con getPersonByName(String name) vengono restituite le informazioni inerenti alla persona di cui il nome è passato per parametro.

Con getAllMovies(), vengono  restituiti tutti i film presenti nel DB.

Con getAllPeople(), vengono restituite tutte le persone che hanno lavorato nel film, ovvero il cast e il regista.

Poi ci sono le funzioni che richiamano i metodi per il grafo.


##Graph
Per definizione del grafo, sappiamo che G = (V, E), dove V sono i vertici ed E sono gli archi. E’ stato messo come impostazione che i nodi del grafo siano le persone, ovvero gli attori che hanno partecipato ai vari film e gli archi siano una di lista di collaborazioni tra gli attori. 
Possiamo avere che un attore può avere collaborato con più di un attore, per questo è stato scelto di usare una lista. 
Sono state implementate alcune funzioni di base che potessero permettere di poter lavorare con la struttura dati, come Add, che aggiunge i vari vertici al grafo.
La funzione InsertMovieGraph, grazie alla quale posso creare gli archi sul grafo. 

GetCollaborators, è una funzione che attraversa il grafo, cerca l’elemento che corrisponde al parametro passato e ne restituisce i collaboratori. Tenendo conto che il collaboratore A, è l’attore passato da input, abbiamo che i suoi collaboratori, sono i collaboratori B. (Per definizione collaborazione è formata da un attoreA e un attoreB).

GetTeam(Person actor), in questa funzione vengono restituiti tutti i collaboratori diretti e indiretti del parametro passato in input. 
Variabili importanti nel codice:
-una lista collaborators
-una lista tmp_array
-una lista team
-una lista different
Otteniamo prima di tutto i collaboratori diretti dell’attore passato come input che vengono salvati nella lista di collaborators. Per ogni elemento di collaborators, ne calcoliamo i suoi collaboratori diretti che vengono salvati in tmp_array. Per ogni elemento in tmp_array si controlla se questo appartiene a team, nel caso non appartenga, viene aggiunto alla lista team e alla lista different. 
Una volta fatto ciò, tutti gli elementi di collaborators vengono rimossi, si verifica che la lista sia vuota. 
Si verifica che la lista different sia vuota, nel caso non lo sia, alla lista collaborators vengono aggiunti gli elementi di different e dalla lista different vengono rimossi tutti gli elementi di different.
Nel caso la lista di different sia vuota, si conclude e si restituisce la lista team.

Con maximizeCollaborationsInTheTeamOf (Person actor), per implementare questa funzione, nella quale bisognava calcolare un MST (un minimum spanning tree), ma in questo caso, bisognava tenere conto dello score più alto, è stato deciso di implementarlo tramite l’algoritmo di Prim.
Variabili importanti utilizzati nel codice:
Lista Visited;
Lista adjacent_nodes;
Lista unreached_nodes;
Nodo/Person indexpoint.

Prima di tutto otteniamo il grafo su cui andare a lavorare, quindi otteniamo da actor, il team.
Dopodiché viene scelto un punto iniziale e viene salvato su indexpoint. Viene deciso che il punto iniziale è actor, parametro passato in input.
Aggiungiamo alla lista visited, indexpoint. 
Una volta inizializzato le varie variabili, si ha un ciclo while nel quale:
-vengono calcolati i nodi adiacenti al nodo di partenza (in questo caso, otteniamo i collaboratori diretti). 
-controlliamo che i nodi adiacenti non siano già visitati, se non sono visitati, allora bisogna scegliere quale nodo aggiungere al MST.
-salviamo quindi, tutte le collaborazioni, in ordine decrescente basandoci sullo score di ogni collaborazioni,  dell’indexpoint che dei nodi visitati.
-Quindi si ottiene una lista chiamata unreached_nodes.
-Per scegliere il nodo successivo da inserire nel MST, viene deciso di default l’elemento 0-esimo della lista (dato che la lista è già in ordine e stiamo costruendo un MST), però può succedere che l’elemento 0-esimo appartenga già alla lista visited, in questo caso, cerchiamo un altro elemento che non appartenga alla lista, così da sceglierlo come nodo successivo per costruire l’MST.
-una volta che viene scelto l’elemento da aggiungere, viene aggiornato l’indexpoint, viene salvata la collaborazione scelta e viene aggiunto il nodo scelto, nella lista visited.
-L’algoritmo si conclude quando tutti gli elementi di visited sono uguali agli elementi del team. 
